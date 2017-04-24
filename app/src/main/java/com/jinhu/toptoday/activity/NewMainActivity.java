package com.jinhu.toptoday.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jinhu.toptoday.R;
import com.jinhu.toptoday.adapter.VpAdapter;
import com.jinhu.toptoday.app.MyApp;
import com.jinhu.toptoday.bean.ChannelItem;
import com.jinhu.toptoday.bean.ChannelManage;
import com.jinhu.toptoday.fragment.ItemFragment;
import com.jinhu.toptoday.login_activty.LoginActivity;
import com.jinhu.toptoday.util.CircleImageView;
import com.jinhu.toptoday.util.Login;
import com.jinhu.toptoday.util.MagicUtils;
import com.jinhu.toptoday.util.Night_styleutils;
import com.jinhu.toptoday.util.UiUtils;
import com.jinhu.toptoday.util.Url;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.jinhu.toptoday.R.id.magic_indicator;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/18  20:06
 */

public class NewMainActivity extends FragmentActivity {
    private CheckBox menu_more;
    private MagicIndicator mIndicator;
    private ViewPager mViewPager;
    private List<String> mList;
    private SlidingMenu mMenu;
    private CircleImageView mImageView;
    private ImageView mIv_top_home;
    private int theme = 0;
    private ImageView mIv_qq_sliding;
    private RadioButton mBtn_night_style;
    private SHARE_MEDIA mShare_media = SHARE_MEDIA.QQ;
    private ArrayList<ChannelItem> mUserChannel;
    private List<Fragment> mFragList;
    private VpAdapter mAdapter;
    private CircleImageView mIv_loged_sliding;
    private LinearLayout mLayout;
    private String mName;
    private String mImageUrl;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSharedPreferences;
    private TextView mName_loged_sliding;
    private LinearLayout mLinearLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);
        //
        initView();
        //
        ceHua();
        //
        /**
         * 设置监听
         */
        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //
        initData();
        //
        initLogin();
        //
        initViewPager(mFragList);
        //
        initIndicator();
        //

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
                //侧滑页显示登录
                mLayout.setVisibility(View.GONE);
                mLinearLayout.setVisibility(View.VISIBLE);
                Glide.with(NewMainActivity.this).load(imageUrl).into(mIv_loged_sliding);
                Glide.with(NewMainActivity.this).load(imageUrl).into(mImageView);
                mName_loged_sliding.setText(name);
                break;
            case Login.QQ:
                //侧滑页显示登录
                mLayout.setVisibility(View.GONE);
                mLinearLayout.setVisibility(View.VISIBLE);
                Glide.with(NewMainActivity.this).load(imageUrl).into(mIv_loged_sliding);
                Glide.with(NewMainActivity.this).load(imageUrl).into(mImageView);
                mName_loged_sliding.setText(name);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 数据准备
     */
    private void initData() {
        //
        mSharedPreferences = MyApp.getSharedPrefernces(NewMainActivity.this);
        //
        mFragList = new ArrayList<>();
        mUserChannel = (ArrayList<ChannelItem>) ChannelManage.getManage(MyApp.getApp().getSQLHelper()).getUserChannel();
        Map<String, String> map = Url.getADD();
        for (int i = 0; i < mUserChannel.size(); i++) {
            for (Map.Entry<String, String> m : map.entrySet()) {
                //System.out.println(m.getKey()+"\t"+m.getValue());
                if (m.getKey().equals(mUserChannel.get(i).getName())) {
                    ItemFragment itemFragment = ItemFragment.getInstance(m.getValue());
                    mFragList.add(itemFragment);
                }
            }
        }
    }

    private static Bitmap getBitmapFromUri(Activity activity, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), uri);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initView() {
        mIndicator = (MagicIndicator) findViewById(magic_indicator);
        mViewPager = (ViewPager) findViewById(R.id.vp_home);
        mImageView = (CircleImageView) findViewById(R.id.iv_slid_home);
        //频道管理
        mIv_top_home = (ImageView) findViewById(R.id.iv_top_home);
    }

    private void initViewPager(List<Fragment> mFragList) {
        mAdapter = new VpAdapter(getSupportFragmentManager(), mFragList);
        mViewPager.setAdapter(mAdapter);
    }

    private void initIndicator() {
        mList = new ArrayList<>();
        for (int i = 0; i < mUserChannel.size(); i++) {
            mList.add(mUserChannel.get(i).getName());
        }
        MagicUtils.initMagicIndicator(this, mIndicator, mViewPager, mList);
        if (theme == 1) {
            mIndicator.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        if (theme == 2) {
            mIndicator.setBackgroundColor(Color.parseColor("#000000"));
        }
    }


    private void setListener() {
        /**
         * 设置监听
         * 夜间模式
         */
        mBtn_night_style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(NewMainActivity.this, "夜间模式", Toast.LENGTH_SHORT).show();
                UiUtils.switchAppTheme(NewMainActivity.this);
                reload();
            }
        });
        /**
         * 设置监听
         * 注册登录 手机号
         */
        menu_more.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent = new Intent(NewMainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 设置监听
         * viewpager
         */
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mMenu.setTouchModeAbove(
                            SlidingMenu.TOUCHMODE_FULLSCREEN);
                } else {
                    // 当在其他位置的时候，设置不可以拖拽出来(SlidingMenu.TOUCHMODE_NONE)，或只有在边缘位置才可以拖拽出来TOUCHMODE_MARGIN
                    mMenu.setTouchModeAbove(
                            SlidingMenu.TOUCHMODE_NONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /**
         * 设置监听
         * viewpager 点击侧滑
         */
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenu.toggle();
            }
        });
        /**
         * 设置监听
         * imageview 点击跳转到频道管理 PinDaoguanli
         */
        mIv_top_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewMainActivity.this, ChannelActivity.class));
                finish();
            }
        });
        /**
         * 设置监听
         * qq 第三方登录
         */
        mIv_qq_sliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI.get(NewMainActivity.this).getPlatformInfo(NewMainActivity.this, mShare_media, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        mLayout.setVisibility(View.GONE);
                        mIv_loged_sliding.setVisibility(View.VISIBLE);
                        mName = map.get("screen_name");
                        mImageUrl = map.get("profile_image_url");
                        //保存登录状态
                        SharedPreferences.Editor edit = mSharedPreferences.edit();
                        edit.putString(Login.NAME, mName);
                        edit.putString(Login.IMAGE, mImageUrl);
                        edit.putInt(Login.STATE, Login.QQ);
                        edit.commit();
                        //设置头像
                        mLayout.setVisibility(View.GONE);
                        mLinearLayout.setVisibility(View.VISIBLE);
                        Glide.with(NewMainActivity.this).load(mImageUrl).into(mIv_loged_sliding);
                        Glide.with(NewMainActivity.this).load(mImageUrl).into(mImageView);
                        mName_loged_sliding.setText(mName);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
            }
        });
        /**
         * 设置监听
         * 登陆后 点击 头像 跳到个人界面
         */
        mIv_loged_sliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewMainActivity.this, MyAcitivty.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //侧滑
    public void ceHua() {
        mMenu = new SlidingMenu(this);
        //NewMainActivity 此UI类，
        mMenu.attachToActivity(NewMainActivity.this, SlidingMenu.SLIDING_CONTENT);
        mMenu.setMenu(R.layout.slidingmenu);//侧滑页实际布局
        initViewSlidingMenu(mMenu);//查找布局内控件的方法
        // 设置可以左右滑动的菜单
        mMenu.setMode(SlidingMenu.LEFT);
        // 设置滑动菜单视图的宽度
        int widthPixels = this.getResources().getDisplayMetrics().widthPixels;
        mMenu.setBehindWidth(widthPixels / 7 * 6);
        // 设置渐入渐出效果的值
        mMenu.setFadeDegree(0.35f);
        // 设置触摸屏幕的模式,这里设置为全屏
        mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置下方视图的在滚动时的缩放比例
        mMenu.setBehindScrollScale(0.0f);
        //判断侧滑页是否是打开的
        if (!mMenu.isSecondaryMenuShowing()) {
            mMenu.showContent();
        } else {
            mMenu.showSecondaryMenu();
        }
    }

    //侧滑页的控件
    private void initViewSlidingMenu(SlidingMenu menu) {
        mIv_loged_sliding = (CircleImageView) menu.findViewById(R.id.iv_loged_sliding);
        menu_more = (CheckBox) menu.findViewById(R.id.menu_more);
        mIv_qq_sliding = (ImageView) menu.findViewById(R.id.iv_qq_sliding);
        mBtn_night_style = (RadioButton) menu.findViewById(R.id.btn_night_style);
        mLayout = (LinearLayout) menu.findViewById(R.id.layout_logs_sliding);
        mName_loged_sliding = (TextView) menu.findViewById(R.id.tv_name_loged_sliding);
        mLinearLayout = (LinearLayout) menu.findViewById(R.id.linear_sliding);
    }

    /**
     * 夜间模式
     */
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);//进入动画
        finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        startActivity(intent);
    }

    /**
     * qq第三方登录
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
