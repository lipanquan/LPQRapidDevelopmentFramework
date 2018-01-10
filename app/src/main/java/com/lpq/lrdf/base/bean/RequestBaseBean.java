package com.lpq.lrdf.base.bean;

/**
 * 功能：基类请求bean <br />
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public class RequestBaseBean<B> {

    /**
     * 固定部分
     */
    private String XX;
    private String XXX;

    /**
     * 可变部分
     */
    private B body;
}