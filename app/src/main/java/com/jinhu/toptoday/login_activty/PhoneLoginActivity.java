package com.jinhu.toptoday.login_activty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jinhu.toptoday.R;
import com.jinhu.toptoday.activity.NewMainActivity;
import com.jinhu.toptoday.app.MyApp;
import com.jinhu.toptoday.util.Night_styleutils;
import com.jinhu.toptoday.util.Url;
import com.jinhu.toptoday.util.VolleyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class PhoneLoginActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView iv_back_include_head_login;
    private TextView tv_back_include_head_login;
    private TextView tv_right_include_head_login;
    private EditText et_number_phone_login;
    private EditText et_pwd_phone_login;
    private TextView tv_register_phone_login;
    private TextView tv_pwd_phone_login;
    private Button btn_login_phone_login;
    private int theme = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login_acitivity);
        initView();


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
        tv_back_include_head_login.setText("登录今日头条");
        tv_right_include_head_login = (TextView) findViewById(R.id.tv_right_include_head_login);
        tv_right_include_head_login.setOnClickListener(this);
        et_number_phone_login = (EditText) findViewById(R.id.et_number_phone_login);
        et_number_phone_login.setOnClickListener(this);
        et_pwd_phone_login = (EditText) findViewById(R.id.et_pwd_phone_login);
        et_pwd_phone_login.setOnClickListener(this);
        tv_register_phone_login = (TextView) findViewById(R.id.tv_register_phone_login);
        tv_register_phone_login.setOnClickListener(this);
        tv_pwd_phone_login = (TextView) findViewById(R.id.tv_pwd_phone_login);
        tv_pwd_phone_login.setOnClickListener(this);
        btn_login_phone_login = (Button) findViewById(R.id.btn_login_phone_login);
        btn_login_phone_login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_phone_login:
                submit();
                break;
            case R.id.tv_register_phone_login:
                Intent intent = new Intent(PhoneLoginActivity.this, ProtogenesisRegisterAcitvity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_pwd_phone_login:
                Intent intent1 = new Intent(PhoneLoginActivity.this, PwdBackActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }

    private void submit() {
        // validate
        String phone = et_number_phone_login.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = et_pwd_phone_login.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        //act=login&username=qqqqqqqq&password=qq&client=android
        // TODO validate success, do something
        //vollet框架
        HashMap<String, String> params = new HashMap<>();
        params.put("username", phone);
        params.put("password", pwd);
        params.put("client", Url.SYSTEM_TYPE);
        VolleyUtils.post(this, Url.LINK_MOBILE_LOGIN, params, new VolleyUtils.MyCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.getInt("code");
                    if (code == 200) {
                        /**
                         * 填写登录状态
                         */
                        SharedPreferences sharedPrefernces = MyApp.getSharedPrefernces(PhoneLoginActivity.this);
                        SharedPreferences.Editor edit = sharedPrefernces.edit();
                        edit.putBoolean("logined", true);
                        //跳转到主界面
                        Toast.makeText(PhoneLoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PhoneLoginActivity.this, NewMainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (code == 400) {
                        JSONObject datas = jsonObject.getJSONObject("datas");
                        String error = datas.getString("error");
                        Toast.makeText(PhoneLoginActivity.this, error, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(PhoneLoginActivity.this, "服务器异常，请稍后再试", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorMsg) {
                Toast.makeText(PhoneLoginActivity.this, "无法登陆！", Toast.LENGTH_LONG).show();
            }
        });

    }
}
