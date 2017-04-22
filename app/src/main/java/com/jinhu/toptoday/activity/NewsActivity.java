package com.jinhu.toptoday.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jinhu.toptoday.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.Log;

import static com.jinhu.toptoday.R.id.webView;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener {
    private WebView mWebView;
    private String mNews;
    private ImageView iv_back_include_head_login;
    private TextView tv_back_include_head_login;
    private TextView tv_right_include_head_login;
    private ImageView next_share_image;
    private int theme = 0;
    private String mAuthor_name;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        //
        initView();
        //
        initData();
        //
        initWebView(mNews);
    }

    private void initData() {
        Intent intent = getIntent();
        mNews = intent.getStringExtra("news");
        mAuthor_name = intent.getStringExtra("author_name");
        mTitle = intent.getStringExtra("title");
    }

    private void initWebView(String news) {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl(news);
    }

    private void initView() {
        mWebView = (WebView) findViewById(webView);
        iv_back_include_head_login = (ImageView) findViewById(R.id.iv_back_include_head_login);
        iv_back_include_head_login.setOnClickListener(this);
        tv_back_include_head_login = (TextView) findViewById(R.id.tv_back_include_head_login);
        tv_back_include_head_login.setText("");
        tv_back_include_head_login.setOnClickListener(this);
        tv_right_include_head_login = (TextView) findViewById(R.id.tv_right_include_head_login);
        tv_right_include_head_login.setOnClickListener(this);
        next_share_image = (ImageView) findViewById(R.id.next_share_image);
        next_share_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_share_image:
                UMWeb umWeb = new UMWeb(mNews);
                umWeb.setTitle(mAuthor_name);
                umWeb.setDescription(mTitle);
                // UMImage image = new UMImage(this, mUrl);//资源文件
                new ShareAction(this)
                        .withText("hello")
                        /*.withMedia(umWeb)*/
                        .withMedia(umWeb)
                        .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN,
                                SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                                SHARE_MEDIA.QZONE
                        )
                        .setCallback(umShareListener).open();
                break;
            case R.id.iv_back_include_head_login:
                finish();
                break;
        }
    }

    /**
     * ??这方法是干啥的？？
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //QQ分享
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            //Log.d("plat", "platform" + platform);
            Toast.makeText(NewsActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(NewsActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(NewsActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
}
