package com.jinhu.toptoday.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.jinhu.toptoday.R;
import com.jinhu.toptoday.adapter.VpAdapter;
import com.jinhu.toptoday.base.BaseFragment;
import com.jinhu.toptoday.util.MagicUtils;
import com.jinhu.toptoday.util.Url;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/11  19:59
 */

public class HomeFragment extends BaseFragment {

    private MagicIndicator mIndicator;
    private ViewPager mViewPager;
    private List<String> mList;


    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.home_fragment, null);
        mIndicator = (MagicIndicator) view.findViewById(R.id.magic_indicator);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_home);
        return view;
    }

    @Override
    protected void initData() {
        initViewPager();
        initIndicator();
    }

    private void initIndicator() {
        mList = new ArrayList<>();
        mList.addAll(Arrays.asList(Url.TITLES));
        MagicUtils.initMagicIndicator(mContext, mIndicator, mViewPager, mList);
    }

    private void initViewPager() {
        VpAdapter adapter = new VpAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
    }
}
