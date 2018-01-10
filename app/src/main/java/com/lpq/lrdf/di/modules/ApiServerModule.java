package com.lpq.lrdf.di.modules;

import com.lpq.lrdf.config.IRequestUrlConfig;
import com.lpq.lrdf.net.http.CustomerOkHttpClient;
import com.lpq.lrdf.net.inf.ApiServer;
import com.lpq.lrdf.net.utils.RequestParameterUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <br/>
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
@Module
public class ApiServerModule {

    @Singleton
    @Provides
    public ApiServer provideApiServer() {
        OkHttpClient client = CustomerOkHttpClient.getClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IRequestUrlConfig.APP_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiServer.class);
    }

    @Singleton
    @Provides
    public RequestParameterUtils provideRequestParameterUtils() {
        return RequestParameterUtils.getInstance();
    }
}
