package com.lpq.lrdf.utils;

import android.app.Application;

import java.util.HashMap;

/**
 * 错误码工具类
 * Created by lipanquan on 2017/1/16.
 */
public final class ErrorCodeUtils {

    private ErrorCodeUtils() {
    }

    /**
     * 错误码对照表
     */
    private static HashMap<Integer, String> errorMap = new HashMap<Integer, String>();

    /**
     * 初始化错误码表
     *
     * @param application Application对象
     */
    private static void initMap(Application application) {
//        errorMap.put(-212, getResString(application, R.string.error_parameter_parse_fail));
//        errorMap.put(-521, getResString(application, R.string.error_service_handler_error));
//        errorMap.put(-101, getResString(application, R.string.error_service_handler_error));
//        errorMap.put(101, getResString(application, R.string.error_phone_number_not_null));
//        errorMap.put(102, getResString(application, R.string.error_phone_number_format_error));
//        errorMap.put(201, getResString(application, R.string.error_get_verification_code_type_error));
    }

    /**
     * 获取资源id对象的字符串
     *
     * @param application Application对象
     * @param id          资源id
     * @return 字符串
     */
    private static String getResString(Application application, int id) {
        return application.getResources().getString(id);
    }

    /**
     * 根据错误码获取错误码信息
     *
     * @param application Application对象
     * @param errorCode   错误码
     * @return 错误码信息
     */
    public static String getErrorMessage(Application application, int errorCode) {
        if (errorMap.isEmpty())
            initMap(application);
        if (errorMap.containsKey(errorCode))
            return errorMap.get(errorCode);
        else
            return "没有对应错误码信息";

    }
}
