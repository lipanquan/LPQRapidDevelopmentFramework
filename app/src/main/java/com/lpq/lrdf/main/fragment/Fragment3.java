package com.lpq.lrdf.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lpq.lrdf.R;
import com.lpq.lrdf.base.NavigationBaseFragment;
import com.lpq.lrdf.di.components.DaggerICommActivityComponent;
import com.lpq.lrdf.di.modules.CommonActivityModule;
import com.lpq.lrdf.main.inf.view.IFragment3View;
import com.lpq.lrdf.main.presenter.Fragment3Presenter;

import butterknife.ButterKnife;

/**
 * 功能：导航3<br />
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public class Fragment3 extends NavigationBaseFragment<IFragment3View, Fragment3Presenter> implements IFragment3View {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_3, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DaggerICommActivityComponent.builder().apiServerComponent(getApiServerComponent())
                .commonActivityModule(new CommonActivityModule(this))
                .build().inject(this);
        unbinder = ButterKnife.bind(this, getView());
    }
}