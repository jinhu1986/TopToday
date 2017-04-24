package com.jinhu.toptoday.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jinhu.toptoday.R;
import com.jinhu.toptoday.app.MyApp;
import com.jinhu.toptoday.util.Login;

import static com.jinhu.toptoday.R.id.iv_back_include_head_login;
import static com.jinhu.toptoday.R.id.tv_back_include_head_login;

public class MyAcitivty extends AppCompatActivity {

    private ImageView mIvBackIncludeHeadLogin;
    private TextView mTvBackIncludeHeadLogin;
    private TextView mTvRightIncludeHeadLogin;
    private ImageView mImageView2;
    private ImageView mHomePagerImage;
    private TextView mHomePagerText;
    private CheckBox mHomePagerCheck;
    private TextView mHomePagerTextGone;
    private LinearLayout mActivityHomePager;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //
        initView();
        //
        initData();
        //
        initLogin();
        //
        setListen();
    }

    private void initData() {
        //
        mSharedPreferences = MyApp.getSharedPrefernces(MyAcitivty.this);
    }

    /**
     * 判断登录的逻辑
     */
    private void initLogin() {
        //登录判断
        String imageUrl = mSharedPreferences.getString(Login.IMAGE, "");
        String name = mSharedPreferences.getString(Login.NAME, "");
        switch (mSharedPreferences.getInt(Login.STATE, -1)) {
            case Login.TEACHER:
                Glide.with(MyAcitivty.this).load(imageUrl).into(mHomePagerImage);
                mHomePagerText.setText(name);
                break;
            case Login.QQ:
                Glide.with(MyAcitivty.this).load(imageUrl).into(mHomePagerImage);
                mHomePagerText.setText(name);
                break;
        }
    }

    private void setListen() {
        //左上角返回键
        mIvBackIncludeHeadLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //头像图片，点击跳转到账号管理
        mHomePagerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MyAcitivty.this, AccountAcitivity.class);
                startActivity(mIntent);
                finish();
            }
        });
        //设置，点击跳转到账号管理
    }

    private void initView() {
        mIvBackIncludeHeadLogin = (ImageView) findViewById(iv_back_include_head_login);
        mTvBackIncludeHeadLogin = (TextView) findViewById(tv_back_include_head_login);
        mTvBackIncludeHeadLogin.setText("个人主页");
        mHomePagerImage = (ImageView) findViewById(R.id.home_pager_image);
        mHomePagerText = (TextView) findViewById(R.id.home_pager_text);
        mHomePagerCheck = (CheckBox) findViewById(R.id.home_pager_check);
        mHomePagerTextGone = (TextView) findViewById(R.id.home_pager_text_gone);
    }
}
