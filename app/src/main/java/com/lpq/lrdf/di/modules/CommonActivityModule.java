package com.lpq.lrdf.di.modules;

import com.lpq.lrdf.main.inf.view.IFragment1View;
import com.lpq.lrdf.main.inf.view.IFragment2View;
import com.lpq.lrdf.main.inf.view.IFragment3View;
import com.lpq.lrdf.main.inf.view.IFragment4View;
import com.lpq.lrdf.main.inf.view.IMainView;

import dagger.Module;
import dagger.Provides;

/**
 * 功能：CommonActivityModule <br/>
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
@Module
public class CommonActivityModule {
    /*************登录**********/
//    private ILoginView loginView;
//
//    public CommonActivityModule(ILoginView loginView) {
//        this.loginView = loginView;
//    }
//
//    @Provides
//    public ILoginView provideLoginView() {
//        return this.loginView;
//    }


    /*************主页**********/
    private IMainView mainView;

    public CommonActivityModule(IMainView view) {
        this.mainView = view;
    }

    @Provides
    public IMainView provideView() {
        return this.mainView;
    }


    /*************导航1**********/
    private IFragment1View fragment1View;

    public CommonActivityModule(IFragment1View view) {
        this.fragment1View = view;
    }

    @Provides
    public IFragment1View provideFragment1View() {
        return this.fragment1View;
    }

    /*************导航2**********/
    private IFragment2View fragment2View;

    public CommonActivityModule(IFragment2View view) {
        this.fragment2View = view;
    }

    @Provides
    public IFragment2View provideFragment2View() {
        return this.fragment2View;
    }

    /*************导航3**********/
    private IFragment3View fragment3View;

    public CommonActivityModule(IFragment3View view) {
        this.fragment3View = view;
    }

    @Provides
    public IFragment3View provideFragment3View() {
        return this.fragment3View;
    }

    /*************导航4**********/
    private IFragment4View fragment4View;

    public CommonActivityModule(IFragment4View view) {
        this.fragment4View = view;
    }

    @Provides
    public IFragment4View provideFragment4View() {
        return this.fragment4View;
    }

}