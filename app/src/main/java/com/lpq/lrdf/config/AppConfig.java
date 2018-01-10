package com.lpq.lrdf.config;

import com.lpq.base.lib.config.LibAppConfig;

/**
 * 功能：App的配置信息<br/>
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public final class AppConfig extends LibAppConfig {
    private AppConfig() {
    }

    public static boolean IS_DEBUG = false;

    /**
     * 网络请求超时的时间
     */
    public static final int REQUEST_SERVER_DEFAULT_TIMEOUT = 30 * 1000;

}