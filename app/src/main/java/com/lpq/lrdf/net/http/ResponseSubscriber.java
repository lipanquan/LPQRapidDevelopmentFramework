package com.lpq.lrdf.net.http;

import android.support.annotation.StringRes;

import com.lpq.lrdf.app.LPQApplication;
import com.lpq.lrdf.base.inf.IBaseView;
import com.lpq.lrdf.net.bean.ResponseDateBase;

import java.io.IOException;

import com.lpq.base.lib.utils.Logger;
import com.lpq.lrdf.utils.ErrorCodeUtils;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 再次封装的Subscriber对象，做请求处理后的处理判断，及控制对话框逻辑
 *
 * @author: lipanquan <br/>
 * email: lipanquan@a-eye.cn
 * @time: 2017/7/25 09:43
 * 注意：ResponseDateBase不能为抽象类，否则JSon无法转换
 */
public abstract class ResponseSubscriber<T extends ResponseDateBase> extends Subscriber<T> {

    /**
     * MVP模式中P与View交互的父接口的子类对象
     */
    private IBaseView view;

    /**
     * 是否显示对话框
     */
    private boolean isShowLoading;

    /**
     * 对话框显示的文字
     */
    private String loadingText;

    /**
     * 创建Subscriber封装类ResponseSubscriber对象的构造方法
     *
     * @param view MVP模式中P与View交互的父接口的子类对象
     */
    public ResponseSubscriber(IBaseView view) {
        this.view = view;
    }

    /**
     * 创建Subscriber封装类ResponseSubscriber对象的构造方法
     *
     * @param view          MVP模式中P与View交互的父接口的子类对象
     * @param isShowLoading 对话框显示的文字
     */
    public ResponseSubscriber(IBaseView view, boolean isShowLoading) {
        this(view);
        this.isShowLoading = isShowLoading;
    }

    /**
     * 创建Subscriber封装类ResponseSubscriber对象的构造方法
     *
     * @param view        MVP模式中P与View交互的父接口的子类对象
     * @param loadingText 对话框显示的文字
     */
    public ResponseSubscriber(IBaseView view, String loadingText) {
        this(view, true);
        this.loadingText = loadingText;
    }

    /**
     * 创建Subscriber封装类ResponseSubscriber对象的构造方法
     *
     * @param view        MVP模式中P与View交互的父接口的子类对象
     * @param loadingText 对话框显示的文字
     */
    public ResponseSubscriber(IBaseView view, @StringRes int loadingText) {
        this(view, true);
        this.loadingText = view.getResString(loadingText);
    }

    @Override
    public void onCompleted() {
        if (isShowLoading)
            view.dismissLoading();
//        view.removeSubscription(this);
        onFinally();
    }

    @Override
    public void onError(Throwable e) {
        if (isShowLoading)
            view.dismissLoading();
        String error = "";
        if (e instanceof ResponseErrorException) {
            error = e.getMessage();
        } else if (e instanceof IOException) {
            error = "请检查您的网络后重试";
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            error = httpException.getMessage();
        } else {
//            error = "未知错误";
            error = e.getMessage();
            Logger.e("RequestFailedAction", e.getMessage());
        }
        onError(error);
//        view.removeSubscription(this);
        onFinally();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!LPQApplication.networkAvailable) {
            onError("网络不可用，请检查网络后重试!");
//            throw  new ResponseErrorException("网络不可用，请检查网络后重试!");
        }
        if (isShowLoading) {
            if (!view.isNull(loadingText)) {
                view.showLoading(loadingText);
            } else {
                view.showLoading();
            }
        }
    }

    @Override
    public void onNext(T t) {
        if (t.isSuccessed()) {
            onSuccess(t);
        } else {
            onError(ErrorCodeUtils.getErrorMessage(LPQApplication.getAppContext(), t.getErrorC()));
//            onError(t.getErrorCode());
        }
    }

    /**
     * 返回结果成功，回调传回结果
     *
     * @param response 正确处理后的结果
     */
    public abstract void onSuccess(T response);

    /**
     * 当返回结果中标示不成功时回调
     *
     * @param errorMessage 错误信息
     */
    public void onError(String errorMessage) {
        if (view != null)
            view.showError(errorMessage);
    }

    /**
     * 无论成功失败都回调这个方法
     */
    public void onFinally() {

    }
}