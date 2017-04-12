package com.jinhu.toptoday.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.jinhu.toptoday.R;
import com.jinhu.toptoday.adapter.LvAdapter;
import com.jinhu.toptoday.base.BaseFragment;
import com.jinhu.toptoday.bean.JsonBean;
import com.jinhu.toptoday.db.ChildInfo;
import com.jinhu.toptoday.db.DbUtils;
import com.jinhu.toptoday.util.GsonUtils;
import com.jinhu.toptoday.util.HttpUtils;
import com.jinhu.toptoday.util.Url;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/12  18:41
 */

public class ItemFragment extends BaseFragment {

    private ListView mListView;
    private String mValue;
    private final String KEY = "uri";

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.item_frag_layout, null);
        mListView = (ListView) view.findViewById(R.id.lv_item_frag);
        mValue = mBundle.getString("url");
        return view;
    }

    @Override
    protected void initData() {
        //获取网络数据
        HttpUtils.getInstance().httpXUtilsPOST(Url.ADD_BASE, KEY, mValue, new HttpUtils.MyHttpCallback() {
            @Override
            public void onSuccess(String result) {
                JsonBean jsonBean = GsonUtils.gsonToBean(result, JsonBean.class);
                List<JsonBean.ResultBean.DataBean> data = jsonBean.getResult().getData();
                List<ChildInfo> list = new ArrayList<ChildInfo>();
                try {
                    DbUtils.getDb().dropDb();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < data.size(); i++) {
                    //存入数据库
                    ChildInfo childInfo = new ChildInfo();
                    childInfo.setcImage(data.get(i).getThumbnail_pic_s());
                    childInfo.setcTitle(data.get(i).getTitle());
                    childInfo.setcName(data.get(i).getAuthor_name());
                    childInfo.setcCategory(data.get(i).getCategory());
                    list.add(childInfo);
                    try {
                        DbUtils.getDb().save(childInfo);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
                //查询
                try {
                    List<ChildInfo> infos = DbUtils.getDb().selector(ChildInfo.class).findAll();
                    LvAdapter adapter = new LvAdapter(mContext, infos);
                    mListView.setAdapter(adapter);
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorMsg) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public static ItemFragment getInstance(String address) {
        Bundle bundle = new Bundle();
        bundle.putString("url", address);
        ItemFragment itemFragment = new ItemFragment();
        itemFragment.setArguments(bundle);
        return itemFragment;
    }
}
