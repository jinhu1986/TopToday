package com.jinhu.toptoday.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jinhu.toptoday.util.Url;

import java.util.List;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/12  20:18
 */

public class VpAdapter extends FragmentPagerAdapter {
    //List<Fragment> list;
    private String[] titles = Url.TITLES;
    private List<Fragment> list;

    public VpAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
