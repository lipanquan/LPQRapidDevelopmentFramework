package com.lpq.lrdf.di.components;

import com.lpq.lrdf.di.modules.CommonActivityModule;
import com.lpq.lrdf.di.scope.ActivityScope;
import com.lpq.lrdf.main.activity.MainActivity;
import com.lpq.lrdf.main.fragment.Fragment1;
import com.lpq.lrdf.main.fragment.Fragment2;
import com.lpq.lrdf.main.fragment.Fragment3;
import com.lpq.lrdf.main.fragment.Fragment4;

import dagger.Component;

/**
 * 功能： CommonBaseMVPActivity——Component<br />
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
@ActivityScope
@Component(dependencies = ApiServerComponent.class, modules = CommonActivityModule.class)
public interface ICommActivityComponent {

    /**
     * 注入MainActivity
     *
     * @param activity MainActivity
     */
    void inject(MainActivity activity);

    void inject(Fragment1 fragment);

    void inject(Fragment2 fragment);

    void inject(Fragment3 fragment);

    void inject(Fragment4 fragment);
}