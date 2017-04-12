package com.jinhu.toptoday.fragment;

import android.view.View;

import com.jinhu.toptoday.R;
import com.jinhu.toptoday.base.BaseFragment;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/11  19:59
 */

public class HomeFragment extends BaseFragment {

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.home_fragment, null);
        return view;
    }

    @Override
    protected void initData() {

    }
}
