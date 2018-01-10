package com.lpq.lrdf.main.presenter;

import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.lpq.lrdf.R;
import com.lpq.lrdf.base.NavigationBaseFragment;
import com.lpq.lrdf.base.presenter.BasePresenter;
import com.lpq.lrdf.main.fragment.Fragment1;
import com.lpq.lrdf.main.fragment.Fragment2;
import com.lpq.lrdf.main.fragment.Fragment3;
import com.lpq.lrdf.main.fragment.Fragment4;
import com.lpq.lrdf.main.inf.view.IMainView;
import com.lpq.lrdf.net.bean.ResponseDateBase;
import com.lpq.lrdf.net.http.ResponseSubscriber;
import com.lpq.lrdf.net.http.RxThreadUtil;
import com.lpq.lrdf.net.inf.ApiServer;
import com.lpq.lrdf.net.utils.RequestParameterUtils;

import javax.inject.Inject;

/**
 * 功能：主界面的业务逻辑处理类 <br />
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
//@Module 这里不能用Module，因为父类构造有3个
public class MainPresenter extends BasePresenter<IMainView> implements RadioGroup.OnCheckedChangeListener {
 // 3个构造只能选择其中一个，必须使用@Inject

    /**
     * 不需要网络请求，不需要RequestParameterUtils工具类使用该构造
     * @param iBaseView
     */
//    @Inject
//    public MainPresenter(IMainView iBaseView) {
//        super(iBaseView);
//    }

    /**
     * 需要网络请求，不需要RequestParameterUtils工具类使用该构造
     * @param apiServer
     * @param iBaseView
     */
//    @Inject
//    public MainPresenter(ApiServer apiServer, IMainView iBaseView) {
//        super(apiServer, iBaseView);
//    }

    /**
     * 需要网络请求，需要RequestParameterUtils工具类使用该构造
     * @param iBaseView
     */
    @Inject
    public MainPresenter(ApiServer apiServer, IMainView iBaseView, RequestParameterUtils requestParameterUtils) {
        super(apiServer, iBaseView, requestParameterUtils);
        initData();
        // 通过接口实现调用activity各种方法
//        getView().startExitActivity();
//        getView().XXX();
    }

    /**
     * 请求体RequestBody
     */
    private void testInternet1() {
        getApiServer().getAppConfig(getRequestParameterUtils().getAppConfigParameters())
                .compose(RxThreadUtil.networkSchedulers())
                .subscribe(new ResponseSubscriber<ResponseDateBase<String>>(getView()) {
                    @Override
                    public void onSuccess(ResponseDateBase<String> response) {
//                        response.getDate(); // 获取真实的数据
                    }
                });
    }

    /**
     * 表单提交方式
     */
    private void testInternet2() {
        getApiServer().getVerificationCode(getRequestParameterUtils().getVerificationCodeParameters())
                .compose(RxThreadUtil.networkSchedulers())
                .subscribe(new ResponseSubscriber<ResponseDateBase<String>>(getView()) {
                    @Override
                    public void onSuccess(ResponseDateBase<String> response) {

                    }
                });
    }

    private NavigationBaseFragment fragment1;
    private NavigationBaseFragment fragment2;
    private NavigationBaseFragment fragment3;
    private NavigationBaseFragment fragment4;
    private NavigationBaseFragment currentShowFragment;

    private void initData() {
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        // 获得fragmentManager
        FragmentTransaction transaction = getView().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.ll_content, fragment1);
        currentShowFragment = fragment1;
        transaction.add(R.id.ll_content, fragment2);
        transaction.hide(fragment2);
        transaction.add(R.id.ll_content, fragment3);
        transaction.hide(fragment3);
        transaction.add(R.id.ll_content, fragment4);
        transaction.hide(fragment4);
        // 提交修改
        transaction.commit();
    }

    /**
     * 切换导航界面
     *
     * @param position 切换到的界面的索引 0-3
     */
    private void switchNavigation(int position) {
        NavigationBaseFragment switchFragment = null;
        switch (position) {
            case 0: {
                switchFragment = fragment1;
                break;
            }
            case 1: {
                switchFragment = fragment2;
                break;
            }
            case 2: {
                switchFragment = fragment3;
                break;
            }
            case 3: {
                switchFragment = fragment4;
                break;
            }
        }
        FragmentTransaction transaction = this.getView().getSupportFragmentManager().beginTransaction();
        transaction.hide(currentShowFragment);
        currentShowFragment = switchFragment;
        transaction.show(switchFragment);
        // 提交事务
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_navigation_1: {
                switchNavigation(0);
                getView().changeTitleText("导航页1");
                break;
            }
            case R.id.rb_navigation_2: {
                switchNavigation(1);
                getView().changeTitleText("导航页2");
                break;
            }
            case R.id.rb_navigation_3: {
                switchNavigation(2);
                getView().changeTitleText("导航页3");
                break;
            }
            case R.id.rb_navigation_4: {
                switchNavigation(3);
                getView().changeTitleText("导航页4");
                break;
            }
        }
    }
}