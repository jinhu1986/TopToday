package com.jinhu.toptoday.util;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/20  18:57
 */

public class OkHttpUtils {

    private volatile static OkHttpUtils instance;

    /**
     * 单利模式
     *
     * @return
     */
    public static OkHttpUtils getInstance() {
        if (instance == null) {
            synchronized (OkHttpClient.class) {
                if (instance == null) {
                    instance = new OkHttpUtils();
                }
            }
        }
        return instance;
    }

    /**
     * get请求方式
     *
     * @return
     */

    public void getData(String url, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * post请求方式
     *
     * @return
     */
    public void postData(String url, String key, String value, okhttp3.Callback callback) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add(key, value)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
