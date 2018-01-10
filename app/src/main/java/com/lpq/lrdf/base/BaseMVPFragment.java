package com.lpq.lrdf.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lpq.base.lib.activity.LibBaseFragment;
import com.lpq.lrdf.R;
import com.lpq.lrdf.app.LPQApplication;
import com.lpq.lrdf.base.inf.IBaseView;
import com.lpq.lrdf.base.presenter.BasePresenter;
import com.lpq.lrdf.di.components.ApiServerComponent;

import javax.inject.Inject;

import butterknife.Unbinder;

/**
 * 功能：主页内容基类Fragment <br />
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public abstract class BaseMVPFragment<V extends IBaseView, P extends BasePresenter<V>>
        extends LibBaseFragment implements IBaseView {

    /**
     * Unbinder对象
     */
    protected Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 获取LlwApplication对象
     */
    public LPQApplication getLApplication() {
        return ((CommonBaseActivity) getActivity()).getLApplication();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (((CommonBaseActivity) getActivity()).isNotNull(unbinder))
            unbinder.unbind();
    }

    @Inject
    protected P mPresenter;

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (!isNull(mPresenter))
            mPresenter.onClick(v);
    }

    /**
     * 获取ApiServerComponent
     *
     * @return ApiServerComponent
     */
    protected ApiServerComponent getApiServerComponent() {
        return ((LPQApplication) getApplication()).getApiServerComponent();
    }

    private View dialogView;

    private Dialog dialog;

    /**
     * 显示对话框
     */
    public void showLoading() {
        if (isNull(dialog)) {
            dialogView = LayoutInflater.from(this.getContext()).inflate(R.layout.dialog_common, null);
            dialog = new AlertDialog.Builder(this.getContext())
                    .setView(dialogView)
                    .setCancelable(false).create();
        }
        dialog.show();
    }

    /**
     * 显示带文字提示的对话框
     *
     * @param loadingText 提示内容
     */
    public void showLoading(String loadingText) {
        if (isNull(dialog)) {
            dialogView = LayoutInflater.from(this.getContext()).inflate(R.layout.dialog_common, null);
            dialog = new AlertDialog.Builder(this.getContext())
                    .setView(dialogView)
                    .setCancelable(false).create();
        }
        ((TextView) dialogView.findViewById(R.id.tv_dialog_hint)).setText(loadingText);
        dialog.show();
    }

    /**
     * 关闭对话框
     */
    public void dismissLoading() {
        if (isNotNull(dialog))
            dialog.dismiss();
    }

    @Override
    public void showError(String error) {

    }
}