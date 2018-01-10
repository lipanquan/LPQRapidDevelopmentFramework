package com.lpq.lrdf.net.http;

import android.os.Environment;
import android.text.TextUtils;

import com.lpq.base.lib.utils.Logger;
import com.lpq.lrdf.config.AppConfig;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 功能：网络请求 <br />
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public class CustomerOkHttpClient {

    public static OkHttpClient client;

    private CustomerOkHttpClient() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static void create() {
        int maxCacheSize = 10 * 1024 * 1024;
        File cacheFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "LpqCache" + File.separator);
        createOrExistsDir(cacheFile);
        Cache cache = new Cache(cacheFile, maxCacheSize);
        Interceptor cacheInterceptor = chain -> {
            Request request = chain.request();
            Response response = chain.proceed(request);

            String cacheControl = request.cacheControl().toString();
            if (TextUtils.isEmpty(cacheControl)) {
                cacheControl = "public, max-age=60 ,max-stale=2419200";
            }
            return response.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        };

        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(cacheInterceptor)
                .addInterceptor(new LoggingInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(AppConfig.REQUEST_SERVER_DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(AppConfig.REQUEST_SERVER_DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(AppConfig.REQUEST_SERVER_DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }

    public static OkHttpClient getClient() {
        if (client == null) {
            create();
        }
        return client;
    }

    static class LoggingInterceptor implements Interceptor {
        private static final String TAG = "CustomerOkHttpClient";

        private static final Charset UTF8 = Charset.forName("UTF-8");

        private String protocol(Protocol protocol) {
            return protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1";
        }

        private boolean bodyEncoded(Headers headers) {
            String contentEncoding = headers.get("Content-Encoding");
            return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            boolean logBody = true;
            boolean logHeaders = true;

            RequestBody requestBody = request.body();
            boolean hasRequestBody = requestBody != null;

            Connection connection = chain.connection();
            Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
            String requestStartMessage =
                    "--> " + request.method() + ' ' + request.url() + ' ' + protocol(protocol);
            if (!logHeaders && hasRequestBody) {
                requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
            }
            Logger.i(TAG, requestStartMessage);

            if (logHeaders) {
                if (hasRequestBody) {
                    // Request body headers are only present when installed as a network interceptor. Force
                    // them to be included (when available) so there values are known.
                    if (requestBody.contentType() != null) {
                        Logger.i(TAG, "Content-Type: " + requestBody.contentType());
                    }
                    if (requestBody.contentLength() != -1) {
                        Logger.i(TAG, "Content-Length: " + requestBody.contentLength());
                    }
                }

                Headers headers = request.headers();
                for (int i = 0, count = headers.size(); i < count; i++) {
                    String name = headers.name(i);
                    // Skip headers from the request body as they are explicitly logged above.
                    if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                        Logger.i(TAG, name + ": " + headers.value(i));
                    }
                }

                if (!logBody || !hasRequestBody) {
                    Logger.i(TAG, "--> END " + request.method());
                } else if (bodyEncoded(request.headers())) {
                    Logger.i(TAG, "--> END " + request.method() + " (encoded body omitted)");
                } else {
                    Buffer buffer = new Buffer();
                    requestBody.writeTo(buffer);

                    Charset charset = UTF8;
                    MediaType contentType = requestBody.contentType();
                    if (contentType != null) {
                        charset = contentType.charset(UTF8);
                    }

                    Logger.i(TAG, buffer.readString(charset));

                    Logger.i(TAG, "--> END " + request.method()
                            + " (" + requestBody.contentLength() + "-byte body)");
                }
            }

            long startNs = System.nanoTime();
            Response response = chain.proceed(request);
            long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

            ResponseBody responseBody = response.body();
            long contentLength = responseBody.contentLength();
            String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
            Logger.i(TAG, "<-- " + response.code() + ' ' + response.message() + ' '
                    + response.request().url() + " (" + tookMs + "ms" + (!logHeaders ? ", "
                    + bodySize + " body" : "") + ')');

            if (logHeaders) {
                Headers headers = response.headers();
                for (int i = 0, count = headers.size(); i < count; i++) {
                    Logger.i(TAG, headers.name(i) + ": " + headers.value(i));
                }

//                if (!logBody || !HttpEngine.hasBody(response)) {
//                    Logger.i(TAG, "<-- END HTTP");
//                } else
                if (bodyEncoded(response.headers())) {
                    Logger.i(TAG, "<-- END HTTP (encoded body omitted)");
                } else {
                    BufferedSource source = responseBody.source();
                    source.request(Long.MAX_VALUE); // Buffer the entire body.
                    Buffer buffer = source.buffer();
                    Charset charset = UTF8;
                    MediaType contentType = responseBody.contentType();
                    if (contentType != null) {
                        charset = contentType.charset(UTF8);
                    }
                    if (contentLength != 0) {
                        Logger.i(TAG, "");
                        Logger.i(TAG, buffer.clone().readString(charset));
                    }

                    Logger.i(TAG, "<-- END HTTP (" + buffer.size() + "-byte body)");
                }
            }

//        Request request = chain.request();
////        Logger.i(TAG, "request=" + request);
//        long t1 = System.nanoTime();
////        Logger.i(TAG, String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
//        Response response = chain.proceed(request);
//        long t2 = System.nanoTime();
////        Logger.i(TAG, String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
//        final String responseString = new String(response.body().bytes());
//        Logger.i(TAG, "Response: " + responseString);
//        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), responseString)).build();
            return response;
        }
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    private static boolean createOrExistsDir(File file) {
        // 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

}
