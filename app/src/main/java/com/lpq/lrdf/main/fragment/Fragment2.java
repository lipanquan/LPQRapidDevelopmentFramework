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
import com.lpq.lrdf.main.inf.view.IFragment1View;
import com.lpq.lrdf.main.inf.view.IFragment2View;
import com.lpq.lrdf.main.presenter.Fragment1Presenter;
import com.lpq.lrdf.main.presenter.Fragment2Presenter;

import butterknife.ButterKnife;

/**
 * 功能：导航2 <br />
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public class Fragment2 extends NavigationBaseFragment<IFragment2View, Fragment2Presenter> implements IFragment2View {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2, container, false);
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