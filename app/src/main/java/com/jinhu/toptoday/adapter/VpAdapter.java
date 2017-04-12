package com.jinhu.toptoday.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jinhu.toptoday.fragment.ItemFragment;
import com.jinhu.toptoday.util.Url;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/12  20:18
 */

public class VpAdapter extends FragmentPagerAdapter {
    //List<Fragment> list;
    private String[] titles = Url.TITLES;

    public VpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ItemFragment.getInstance(Url.ADDS[position]);
    }

    @Override
    public int getCount() {
        return Url.ADDS.length;
    }

}
