package com.lpq.lrdf.base.inf;

import com.lpq.base.lib.inf.ILibBaseView;

/**
 * 功能：所有界面交互接口的父接口 <br/>
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public interface IBaseView extends ILibBaseView {

    /**
     * 显示对话框
     */
    void showLoading();

    /**
     * 显示带文字提示的对话框
     *
     * @param loadingText 提示内容
     */
    void showLoading(String loadingText);

    /**
     * 关闭对话框
     */
    void dismissLoading();

    /**
     * 错误提示
     *
     * @param error 提示的内容
     */
    void showError(String error);

}
