package com.jinhu.toptoday.login_activty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jinhu.toptoday.R;
import com.jinhu.toptoday.activity.NewMainActivity;
import com.jinhu.toptoday.app.MyApp;
import com.jinhu.toptoday.util.CircleImageView;
import com.jinhu.toptoday.util.Login;
import com.jinhu.toptoday.util.Night_styleutils;
import com.jinhu.toptoday.util.ProtraitUtils;

import java.io.File;


public class UserInfoSetActivity extends AppCompatActivity {

    private ImageView iv_back_include_head_login;
    private TextView tv_back_include_head_login;
    private TextView tv_right_include_head_login;
    private CircleImageView iv_protrait_user_info_set_;
    private EditText et_name_user_info_set;
    private int theme = 0;
    private Intent phoneData;
    private File mBackPhone;
    private SharedPreferences.Editor mEdit;
    private String mBackPhoneAbsolutePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_set);
        initView();
        setImage();
        initData();
    }

    private void initData() {
        tv_right_include_head_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        mEdit = MyApp.getSharedPrefernces(UserInfoSetActivity.this).edit();
    }

    private void initView() {
        iv_back_include_head_login = (ImageView) findViewById(R.id.iv_back_include_head_login);
        iv_back_include_head_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_back_include_head_login = (TextView) findViewById(R.id.tv_back_include_head_login);
        tv_back_include_head_login.setText("设置个人信息");
        tv_right_include_head_login = (TextView) findViewById(R.id.tv_right_include_head_login);
        tv_right_include_head_login.setVisibility(View.VISIBLE);
        tv_right_include_head_login.setText("完成");
        iv_protrait_user_info_set_ = (CircleImageView) findViewById(R.id.iv_protrait_user_info_set_);
        et_name_user_info_set = (EditText) findViewById(R.id.et_name_user_info_set);
        //点击完成，跳转到详情页
        tv_right_include_head_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    //设置头像
    private void setImage() {
        iv_protrait_user_info_set_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProtraitUtils.initPhone(UserInfoSetActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //返回得到，头像的那张照片 File
        mBackPhone = ProtraitUtils.getBackPhone(UserInfoSetActivity.this, requestCode, resultCode, data, iv_protrait_user_info_set_);
        mBackPhoneAbsolutePath = mBackPhone.getAbsolutePath();
    }

    private void submit() {
        // validate
        String edit = et_name_user_info_set.getText().toString().trim();
        if (TextUtils.isEmpty(edit)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        // 保存登录个人信息
        mEdit.putString(Login.IMAGE, mBackPhoneAbsolutePath);
        mEdit.putString(Login.NAME, edit);
        mEdit.commit();

        //
        Intent intent = new Intent(UserInfoSetActivity.this, NewMainActivity.class);
        startActivity(intent);
        finish();
    }

}
