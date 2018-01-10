package com.lpq.lrdf.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.lpq.lrdf.app.LPQApplication;
import com.lpq.lrdf.base.inf.IBaseView;
import com.lpq.lrdf.base.presenter.BasePresenter;
import com.lpq.lrdf.di.components.ApiServerComponent;

import javax.inject.Inject;

/**
 * 功能：所有MVP模式的基类activity封装 <br/>
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public abstract class CommonBaseMVPActivity<V extends IBaseView, P extends BasePresenter<V>>
        extends CommonBaseActivity {

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (this.mPresenter.onKeyUp(keyCode, event))
            return true;
        return super.onKeyUp(keyCode, event);
    }
}
