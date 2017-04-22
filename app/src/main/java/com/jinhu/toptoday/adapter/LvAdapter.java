package com.jinhu.toptoday.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinhu.toptoday.R;
import com.jinhu.toptoday.bean.JsonBean;
import com.jinhu.toptoday.util.ImageUtils;

import java.util.List;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/12  20:11
 */

public class LvAdapter extends BaseAdapter {
    Context context;
    List<JsonBean.ResultBean.DataBean> list;

    public LvAdapter(Context context, List<JsonBean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_list_view, null);
            holder.iamge = (ImageView) convertView.findViewById(R.id.iv_item);
            holder.text_01 = (TextView) convertView.findViewById(R.id.tv_item_01);
            holder.text_02 = (TextView) convertView.findViewById(R.id.tv_item_02);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageUtils.initImage(holder.iamge, list.get(position).getThumbnail_pic_s(), false);
        holder.text_01.setText(list.get(position).getTitle());
        holder.text_02.setText(list.get(position).getAuthor_name());
        return convertView;
    }

    class ViewHolder {
        ImageView iamge;
        TextView text_01;
        TextView text_02;
    }
}
