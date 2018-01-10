package com.lpq.lrdf.net.inf;

import com.lpq.lrdf.config.IRequestUrlConfig;
import com.lpq.lrdf.net.bean.ResponseDateBase;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 功能：请求Server接口配置 <br/>
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public interface ApiServer {

    /**
     * 获取最新apk信息
     *
     * @return 最新apk信息
     */
    @POST(IRequestUrlConfig.APP_BASE_URL)
    Observable<ResponseDateBase<String>> getAppConfig(@Body RequestBody params);

    @FormUrlEncoded
    Observable<ResponseDateBase<String>> getVerificationCode(@FieldMap Map<String, String> params);

    /**
     * 修改头像
     *
     * @return
     */
//    @Multipart
//    @POST("/App/UserHandler.ashx")
//    Observable<TouXiangInfo> updateImage(@PartMap Map<String, RequestBody> params);

//    public Observable<TouXiangInfo> updateImage(String path) {
//        File imgFile = new File(path);
//        Map<String, String> params = new HashMap<>();
//        params.put("op", "editHeadPic");
//        params.putAll(MD5Utils.generatePublicParams());
//        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), imgFile);
//        RequestBody p = RequestBody.create(MediaType.parse("text/plain"), "editHeadPic");
//        Map<String, RequestBody> params1 = new HashMap<>();
//        params1.put("image\"; filename=\"" + imgFile.getName() + "", fileBody);
//        params1.put("op", p);
//        params1.putAll(MD5Utils.generateBodyPublicParams(params));
//        return httpService.updateImage(params1);
//    }
}