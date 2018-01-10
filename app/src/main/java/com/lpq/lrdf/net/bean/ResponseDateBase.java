package com.lpq.lrdf.net.bean;

/**
 * 功能返回的结果格式 <br />
 * 作者：lipanquan on 2018/1/9 <br />
 * 邮箱：862321807@qq.cn <br />
 */
public class ResponseDateBase<T> {

    /**
     * 处理结果标志0为错误（错误信息错误码表），1为创建处理成功
     */
    protected int flag = 1;

    /**
     * 错误码-详见错误码表对应的错误信息
     */
    protected int errorC;

    /**
     * 指定的任意的真实有效结果
     */
    private T date;

    public T getDate() {
        return date;
    }

    /**
     * 获取处理结果标志0为错误（错误信息错误码表），1为创建处理成功
     *
     * @return 处理结果标志0为错误（错误信息错误码表），1为创建处理成功
     */
    public int getFlag() {
        return this.flag;
    }

    /**
     * 获取错误码-详见错误码表对应的错误信息
     *
     * @return 错误码-详见错误码表对应的错误信息
     */
    public int getErrorC() {
        return errorC;
    }

    /**
     * 判断服务器是否处理成功
     *
     * @return true服务器处理成功
     */
    public boolean isSuccessed() {
        return this.flag == 1;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setErrorC(int errorC) {
        this.errorC = errorC;
    }

    public void setDate(T date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RequestDateBase{" +
                "errorC=" + errorC +
                ", flag=" + flag +
                '}';
    }
}