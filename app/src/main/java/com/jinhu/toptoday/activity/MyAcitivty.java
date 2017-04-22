package com.jinhu.toptoday.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jinhu.toptoday.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //
        initView();
        //
        initData();
        //
        setListen();
    }

    private void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String image = intent.getStringExtra("image");
        Glide.with(MyAcitivty.this).load(image).into(mHomePagerImage);
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
