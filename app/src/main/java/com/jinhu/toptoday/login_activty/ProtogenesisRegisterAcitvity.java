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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jinhu.toptoday.R;
import com.jinhu.toptoday.app.MyApp;
import com.jinhu.toptoday.util.Url;
import com.jinhu.toptoday.util.VolleyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ProtogenesisRegisterAcitvity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image_pr_fahui;
    private EditText ed_pr_id;
    private EditText ed_pr_pwd;
    private EditText ed_pr_pwd_too;
    private EditText ed_pr_email;
    private Button bt_pr_pr;
    private LinearLayout activity_protogenesis_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protogenesis_register);
        //
        initView();
    }

    private void initView() {
        image_pr_fahui = (ImageView) findViewById(R.id.image_pr_fahui);
        ed_pr_id = (EditText) findViewById(R.id.ed_pr_id);
        ed_pr_pwd = (EditText) findViewById(R.id.ed_pr_pwd);
        ed_pr_pwd_too = (EditText) findViewById(R.id.ed_pr_pwd_too);
        ed_pr_email = (EditText) findViewById(R.id.ed_pr_email);
        bt_pr_pr = (Button) findViewById(R.id.bt_pr_pr);

        bt_pr_pr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_pr_pr:
                submit();
                break;
            case R.id.image_pr_fahui:
                finish();
                break;
        }
    }

    //注册
    private void submit() {
        // validate
        String id = ed_pr_id.getText().toString().trim();
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this, "请输入帐号", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = ed_pr_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String too = ed_pr_pwd_too.getText().toString().trim();
        if (TextUtils.isEmpty(too)) {
            Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = ed_pr_email.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "请输入邮箱地址", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        //username=qqqqqqqq&password=qq&password_confirm=qq&client=android&email=45402354@qq.com
        //vollet框架
        HashMap<String, String> params = new HashMap<>();
        params.put("username", id);
        params.put("password", pwd);
        params.put("password_confirm", too);
        params.put("client", Url.SYSTEM_TYPE);
        params.put("email", email);
        VolleyUtils.post(this, Url.LINK_MOBILE_REG, params, new VolleyUtils.MyCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.getInt("code");
                    if (code == 200) {
                        Toast.makeText(ProtogenesisRegisterAcitvity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                        /**
                         * 填写登录状态
                         */
                        SharedPreferences sharedPrefernces = MyApp.getSharedPrefernces(ProtogenesisRegisterAcitvity.this);
                        SharedPreferences.Editor edit = sharedPrefernces.edit();
                        edit.putBoolean("logined", true);
                        edit.commit();
                        //跳转到用户设置
                        Intent intent = new Intent(ProtogenesisRegisterAcitvity.this, UserInfoSetActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (code == 400) {
                        JSONObject datas = jsonObject.getJSONObject("datas");
                        String error = datas.getString("error");
                        Toast.makeText(ProtogenesisRegisterAcitvity.this, error, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ProtogenesisRegisterAcitvity.this, "服务器异常，请稍后再试", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorMsg) {
                Toast.makeText(ProtogenesisRegisterAcitvity.this, "注册失败！", Toast.LENGTH_LONG).show();
            }
        });
 /*       HttpUtils.getInstance().register(Url.LINK_MOBILE_REG,
                new String[]{"username", "password", "password_confirm", "client", "email"},
                new String[]{id, pwd, too, Url.SYSTEM_TYPE, email}, new HttpUtils.MyHttpCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("1212121212", "onSuccess: -----" + result);

                        Toast.makeText(ProtogenesisRegisterAcitvity.this, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String errorMsg) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });*/
    }
}
