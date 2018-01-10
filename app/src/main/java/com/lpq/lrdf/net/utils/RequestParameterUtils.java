package com.lpq.lrdf.net.utils;

import com.lpq.base.lib.utils.GsonUtils;
import com.lpq.lrdf.base.bean.RequestBaseBean;
import com.lpq.lrdf.config.IParameterConfig;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 功能：请求参数生成辅助工具类 <br />
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public final class RequestParameterUtils implements IParameterConfig {

    private static RequestParameterUtils instance = new RequestParameterUtils();

    private RequestParameterUtils() {
    }

    /**
     * 获取参数生成辅助工具类
     *
     * @return 参数生成辅助工具类
     */
    public static RequestParameterUtils getInstance() {
        return instance;
    }

    /**
     * 获取XXX参数 Observable<ResponseDateBase<String>> getAppConfig(@Body RequestBody params);
     *
     * @return RequestBody
     */
    public RequestBody getAppConfigParameters() {
        RequestBaseBean requestBaseBean = new RequestBaseBean();
//        requestBaseBean.setBody(new XXX());
        return RequestBody.create(MediaType.parse("application/json"), GsonUtils.getInstance().o2J(requestBaseBean));
    }

    /**
     * 获取获取验证码参数 对应Observable<ResponseDateBase<String>> getAppConfig(@FieldMap Map<String, String> params);
     *
     * @return 获取验证码参数
     */
    public Map<String, String> getVerificationCodeParameters() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put(BODY, "XXX");
//        params.put(key, value);
//        params.put(key, value);
        return params;
    }
}