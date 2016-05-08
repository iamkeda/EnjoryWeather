package com.enjoryweather.app.util;

/**
 * Created by KD on 2016/5/8.
 * 回调接口用来回调服务返回的结果
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
