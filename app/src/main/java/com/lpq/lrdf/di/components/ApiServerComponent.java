package com.lpq.lrdf.di.components;

import com.lpq.lrdf.di.modules.ApiServerModule;
import com.lpq.lrdf.net.inf.ApiServer;
import com.lpq.lrdf.net.utils.RequestParameterUtils;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 功能：第三方类库的使用，单例模式 <br/>
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
@Singleton
@Component(modules = {ApiServerModule.class})
public interface ApiServerComponent {

    /**
     * 暴露OkHttpClient对象接口
     *
     * @return OkHttpClient
     */
    ApiServer getApiServer();

    /**
     * 获取请求参数生成辅助工具类
     *
     * @return RequestParameterUtils
     */
    RequestParameterUtils getRequestParameterUtils();

//    public abstract void inject(CommonBaseActivity activity);

}
