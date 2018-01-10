package com.lpq.lrdf.main.presenter;

import com.lpq.lrdf.base.presenter.BasePresenter;
import com.lpq.lrdf.main.inf.view.IFragment1View;

import javax.inject.Inject;

/**
 * 功能： <br />
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：lipanquan@a-eye.cn <br />
 */
public class Fragment1Presenter extends BasePresenter<IFragment1View> {

    @Inject
    public Fragment1Presenter(IFragment1View iBaseView) {
        super(iBaseView);
    }
}
