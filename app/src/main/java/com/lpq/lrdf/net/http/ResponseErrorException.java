package com.lpq.lrdf.net.http;

/**
 * 功能：自定义异常 <br />
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public class ResponseErrorException extends RuntimeException {
    public ResponseErrorException(String msg) {
        super(msg);
    }
}
