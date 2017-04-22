package com.jinhu.toptoday.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinhu.toptoday.R;
import com.jinhu.toptoday.app.MyApp;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;


public class AccountAcitivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back_include_head_login;
    private TextView tv_back_include_head_login;
    private TextView tv_right_include_head_login;
    private Button acc_button;
    private LinearLayout activity_account;
    private SharedPreferences.Editor mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
        initData();
    }

    private void initData() {
        SharedPreferences sharedPrefernces = MyApp.getSharedPrefernces(AccountAcitivity.this);
        mEdit = sharedPrefernces.edit();
    }

    private void initView() {
        iv_back_include_head_login = (ImageView) findViewById(R.id.iv_back_include_head_login);
        iv_back_include_head_login.setOnClickListener(this);
        tv_back_include_head_login = (TextView) findViewById(R.id.tv_back_include_head_login);
        tv_back_include_head_login.setText("账号管理");
        acc_button = (Button) findViewById(R.id.acc_button);

        acc_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.acc_button://退出登录
                UMShareAPI.get(AccountAcitivity.this).deleteOauth(this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

                        Toast.makeText(AccountAcitivity.this, "账号已退出", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
                //更改登录状态
                mEdit.clear();
                mEdit.commit();
                Intent intent = new Intent(AccountAcitivity.this, NewMainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_back_include_head_login:
                finish();
                break;
        }
    }
}
