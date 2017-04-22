package com.jinhu.toptoday.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.jinhu.toptoday.db.SQLHelper;
import com.umeng.socialize.PlatformConfig;

import org.xutils.x;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import cn.smssdk.SMSSDK;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/12  15:04
 */

public class MyApp extends Application {
    private static MyApp mAppApplication;
    private SQLHelper sqlHelper;

    /**
     * 单例模式
     * 获取Application
     *
     * @return
     */
    public static MyApp getApp() {
        if (mAppApplication == null) {
            synchronized (MyApp.class) {
                if (mAppApplication == null) {
                    mAppApplication = new MyApp();
                }
            }
        }
        return mAppApplication;
    }

    /**
     * 获得全局的shareperence
     *
     * @return
     */
    public static SharedPreferences getSharedPrefernces(Context context) {
        context = mAppApplication.getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", MODE_PRIVATE);
        return sharedPreferences;
    }


    @Override
    public void onCreate() {
        mAppApplication = this;
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false); // 开启debug会影响性能
        // 全局默认信任所有https域名 或 仅添加信任的https域名
        // 使用RequestParams#setHostnameVerifier(...)方法可设置单次请求的域名校验
        x.Ext.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        //短信验证注册
        SMSSDK.initSDK(getApplicationContext(), "1d01694d8fb5a", "e2aabb6bfb6e893351b5450b1cf68fab");
        //qq第三方登录
        PlatformConfig.setQQZone("1106105282", "vOGpoKysIAgjPcZj");
    }


    /**
     * 获取数据库Helper
     */
    public SQLHelper getSQLHelper() {
        if (sqlHelper == null)
            sqlHelper = new SQLHelper(mAppApplication);
        return sqlHelper;
    }

    /**
     * 摧毁应用进程时候调用
     */
    public void onTerminate() {
        if (sqlHelper != null)
            sqlHelper.close();
        super.onTerminate();
    }

    public void clearAppCache() {
    }
}

