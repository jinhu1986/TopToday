package com.jinhu.toptoday.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;

import com.jinhu.toptoday.R;
import com.jinhu.toptoday.activity.NewsActivity;
import com.jinhu.toptoday.adapter.LvAdapter;
import com.jinhu.toptoday.base.BaseFragment;
import com.jinhu.toptoday.bean.JsonBean;
import com.jinhu.toptoday.db.ChildInfo;
import com.jinhu.toptoday.util.GsonUtils;
import com.jinhu.toptoday.util.HttpUtils;
import com.jinhu.toptoday.util.Url;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import xlistview.bawei.com.xlistviewlibrary.XListView;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/12  18:41
 */

public class ItemFragment extends BaseFragment {

    private XListView mListView;
    private String mValue;
    private final String KEY = "uri";
    private List<ChildInfo> mInfos;
    private List<JsonBean.ResultBean.DataBean> mData;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.item_frag_layout, null);
        mListView = (XListView) view.findViewById(R.id.lv_item_frag);
        mValue = mBundle.getString("url");
        return view;
    }

    @Override
    protected void setListener() {
        /**
         * 监听
         * listview 条目点击
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, NewsActivity.class);
                intent.putExtra("news", mData.get(position - 1).getUrl());
                intent.putExtra("author_name", mData.get(position - 1).getAuthor_name());
                intent.putExtra("title", mData.get(position - 1).getTitle());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        //okHttp
       /* OkHttpUtils.getInstance().getData("http://result.eolinker.com/k2BaduF2a6caa275f395919a66ab1dfe4b584cc60685573?uri=ty",
                new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Log.d(TAG, "onResponse: ----------------------" + string);
                    }
                });*/
       /* try {
            OkHttpUtils.getInstance().postData("http://result.eolinker.com/k2BaduF2a6caa275f395919a66ab1dfe4b584cc60685573",
                    "uri", "ty", new okhttp3.Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d(TAG, "onResponse: ++++++++++++++++++++" + e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) {
                            try {
                                String string = response.body().string();
                                Log.d(TAG, "onResponse: ----------------------" + string);
                                JsonBean jsonBean = GsonUtils.gsonToBean(string, JsonBean.class);
                                mData = jsonBean.getResult().getData();
                                initListView(mData);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //获取网络数据
        HttpUtils.getInstance().httpXUtilsPOST(Url.ADD_BASE, KEY, mValue, new HttpUtils.MyHttpCallback() {
            @Override
            public void onSuccess(String result) {
                JsonBean jsonBean = GsonUtils.gsonToBean(result, JsonBean.class);
                mData = jsonBean.getResult().getData();
                initListView(mData);
             /*   try {
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
                    childInfo.setcUrl(data.get(i).getUrl());
                    list.add(childInfo);
                    try {
                        DbUtils.getDb().save(childInfo);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
                //查询
                try {
                    mInfos = DbUtils.getDb().selector(ChildInfo.class).findAll();
                    LvAdapter adapter = new LvAdapter(mContext, mInfos);
                    mListView.setAdapter(adapter);
                } catch (DbException e) {
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onError(String errorMsg) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initListView(List<JsonBean.ResultBean.DataBean> data) {
        LvAdapter adapter = new LvAdapter(mContext, mData);
        mListView.setAdapter(adapter);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                //显示刷新时间
                String time = showTime();
                mListView.setRefreshTime(time);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mListView.stopRefresh();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                mListView.stopLoadMore();
            }
        });
    }

    Handler mHandler = new Handler();

    private String showTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date());
        return time;
    }

    public static ItemFragment getInstance(String address) {
        Bundle bundle = new Bundle();
        bundle.putString("url", address);
        ItemFragment itemFragment = new ItemFragment();
        itemFragment.setArguments(bundle);
        return itemFragment;
    }
}
