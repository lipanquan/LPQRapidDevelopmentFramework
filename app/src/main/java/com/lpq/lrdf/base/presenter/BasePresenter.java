package com.lpq.lrdf.base.presenter;

import com.lpq.lrdf.base.inf.IBaseView;
import com.lpq.lrdf.config.IIntentKey;
import com.lpq.lrdf.config.ISPKey;
import com.lpq.lrdf.net.inf.ApiServer;
import com.lpq.lrdf.net.utils.RequestParameterUtils;

import com.lpq.base.lib.presenter.LibBasePresenter;

/**
 * 功能: 业务逻辑处理基类 <br/>
 * 增加RequestParameterUtils工具类 by:lipanquan 2018/1/9 <br />
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public abstract class BasePresenter<V extends IBaseView> extends LibBasePresenter<V> implements ISPKey, IIntentKey {

    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 请求参数生成辅助工具类
     */
    private RequestParameterUtils requestParameterUtils;

    /**
     * 请求Server接口
     */
    private ApiServer apiServer;

    /**
     * 构造方法
     *
     * @param apiServer 请求Server接口
     * @param iBaseView 业务处理接口
     */
    public BasePresenter(ApiServer apiServer, V iBaseView) {
        super(iBaseView);
        this.apiServer = apiServer;
    }

    /**
     * 构造方法
     *
     * @param apiServer             请求Server接口
     * @param iBaseView             业务处理接口
     * @param requestParameterUtils 请求参数生成辅助工具类
     */
    public BasePresenter(ApiServer apiServer, V iBaseView, RequestParameterUtils requestParameterUtils) {
        super(iBaseView);
        this.apiServer = apiServer;
        this.requestParameterUtils = requestParameterUtils;
    }

    /**
     * 构造方法
     *
     * @param iBaseView 业务处理接口
     */
    public BasePresenter(V iBaseView) {
        super(iBaseView);
    }

    /**
     * 获取view的方法
     *
     * @return 当前关联的view
     */
    public V getView() {
        return baseView.get();
    }

    /**
     * 获取请求Server接口
     *
     * @return 请求Server接口
     */
    public ApiServer getApiServer() {
        return apiServer;
    }

    /**
     * 获取请求参数生成辅助工具类
     *
     * @return 请求参数生成辅助工具类
     */
    public RequestParameterUtils getRequestParameterUtils() {
        return requestParameterUtils;
    }
}