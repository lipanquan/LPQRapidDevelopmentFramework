package com.lpq.lrdf.main.inf.view;

import android.support.v4.app.FragmentManager;

import com.lpq.lrdf.base.inf.IBaseView;

/**
 * 功能：主界面与业务逻辑处理类交互接口 <br />
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public interface IMainView extends IBaseView {

    FragmentManager getSupportFragmentManager();

    void changeTitleText(String title);
}
