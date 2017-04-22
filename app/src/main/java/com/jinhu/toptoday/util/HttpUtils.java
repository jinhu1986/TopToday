package com.jinhu.toptoday.util;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/12  19:10
 */

public class HttpUtils {
    private volatile static HttpUtils instance;

    /**
     * 单例模式
     *
     * @return
     */
    public static HttpUtils getInstance() {
        if (instance == null) {
            synchronized (HttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }

    /**
     * xUtils post 请求方式
     *
     * @param url    post方式的网络路径
     * @param key    params 的 key 值
     * @param values params 的 values 值
     */
    public void httpXUtilsPOST(String url, String key, String values, final MyHttpCallback callback) {

        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter(key, values);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onError(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                callback.onFinished();
            }
        });

    }

    /**
     * xUtils post 请求方式
     *
     * @param url    post方式的网络路径
     * @param keys   params 的 key 值
     * @param values params 的 values 值
     */
    public void register(String url, String[] keys, String[] values, final MyHttpCallback callback) {
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter(keys[0], values[0]);
        params.addQueryStringParameter(keys[1], values[1]);
        params.addQueryStringParameter(keys[2], values[2]);
        params.addQueryStringParameter(keys[3], values[3]);
        params.addQueryStringParameter(keys[4], values[4]);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onError(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                callback.onFinished();
            }
        });

    }

    /**
     * xUtils post 请求方式
     *
     * @param url    post方式的网络路径
     * @param keys   params 的 key 值
     * @param values params 的 values 值
     */
    public void login(String url, String[] keys, String[] values, final MyHttpCallback callback) {

        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter(keys[0], values[0]);
        params.addQueryStringParameter(keys[1], values[1]);
        params.addQueryStringParameter(keys[2], values[2]);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onError(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                callback.onFinished();
            }
        });

    }

    /**
     * 接口回调
     */
    public interface MyHttpCallback {
        void onSuccess(String result);

        void onError(String errorMsg);

        void onFinished();
    }

}
