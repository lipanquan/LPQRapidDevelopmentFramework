package com.lpq.lrdf.main.activity;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.lpq.lrdf.R;
import com.lpq.lrdf.base.CommonBaseMVPActivity;
import com.lpq.lrdf.di.components.DaggerICommActivityComponent;
import com.lpq.lrdf.di.modules.CommonActivityModule;
import com.lpq.lrdf.main.inf.view.IMainView;
import com.lpq.lrdf.main.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends CommonBaseMVPActivity<IMainView, MainPresenter> implements IMainView {

    @Override
    protected void setTitle() {
        setTitleText("主页");
        setTitleLeftVisibility(false);
        startTitleLoading();
    }

    @BindView(R.id.rg_navigation)
    RadioGroup rg_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        initTitle(this);
        DaggerICommActivityComponent.builder().apiServerComponent(getApiServerComponent())
                .commonActivityModule(new CommonActivityModule(this)).build().inject(this);
        setListener();
    }

    private void setListener() {
        this.rg_navigation.setOnCheckedChangeListener(this.mPresenter);
    }

    @Override
    public void changeTitleText(String title) {
        setTitleText(title);
    }
}