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
     * 单利模式
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
     * @param url    get方式的网络路径
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
     * 接口回调
     */
    public interface MyHttpCallback {
        void onSuccess(String result);

        void onError(String errorMsg);

        void onFinished();
    }

}
