package com.jinhu.toptoday.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/11  19:33
 */

public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
    }

    //初始化控件
    abstract void initView();

    //初始化数据
    abstract void initData();
    //
}
