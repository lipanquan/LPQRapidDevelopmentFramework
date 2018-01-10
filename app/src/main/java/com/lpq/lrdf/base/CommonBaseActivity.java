package com.lpq.lrdf.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lpq.base.lib.activity.LibBaseActivity;
import com.lpq.lrdf.R;
import com.lpq.lrdf.app.LPQApplication;
import com.lpq.lrdf.base.inf.IBaseView;
import com.lpq.lrdf.config.IIntentKey;
import com.lpq.lrdf.config.ISPKey;

import butterknife.Unbinder;

/**
 * 功能：通用标题基类Activity <br />
 * 增加自动更新底部时间机智 2018.1.9 by lipanquan <br />
 * 增加设置无操作多少毫秒后自动回到首页功能 2018.1.9 by lipanquan <br />
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public abstract class CommonBaseActivity extends LibBaseActivity
        implements IBaseView, ISPKey, IIntentKey {

    private LPQApplication lApplication;

    /**
     * Unbinder对象
     */
    protected Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lApplication = (LPQApplication) getApplication();
    }

    public LPQApplication getLApplication() {
        return lApplication;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isNotNull(unbinder))
            unbinder.unbind();
    }

    private View dialogView;

    private Dialog dialog;

    /**
     * 显示对话框
     */
    public void showLoading() {
        if (isNull(dialog)) {
            dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_common, null);
            dialog = new AlertDialog.Builder(this)
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
            dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_common, null);
            dialog = new AlertDialog.Builder(this)
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

    /**
     * 错误提示
     *
     * @param error 提示的内容
     */
    public void showError(String error) {
        showToastLong(error);
    }
}