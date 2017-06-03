package com.wang.neteasemusic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wang.neteasemusic.R;

/**
 * Created by HP on 2017/6/3.
 */

public class MenuAdapter extends BaseAdapter {
    private int[] drawer_icon = new int[]{R.drawable.topmenu_icn_night,
            R.drawable.topmenu_icn_skin, R.drawable.topmenu_icn_time,
            R.drawable.topmenu_icn_vip, R.drawable.topmenu_icn_exit};
    private String[] drawer_name = new String[]{"夜间模式",
            "主题换肤", "定时关闭音乐",
            "下载歌曲品质", "退出"};
    private LayoutInflater mInflater;
    private Context mcontext;

    public MenuAdapter(Context context) {
        mcontext = context;
        this.mInflater = LayoutInflater.from(mcontext);
    }

    @Override
    public int getCount() {
        return drawer_name.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        View view;
        if (convertView == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.design_drawer_item, null);
            holder.iconIm = (ImageView) view.findViewById(R.id.drawer_imageView);
            holder.iconText = (TextView) view.findViewById(R.id.drawer_textView);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.iconText.setText(drawer_name[position]);
        holder.iconIm.setBackgroundResource(drawer_icon[position]);

        return view;
    }

    static class ViewHolder {
        public ImageView iconIm;
        public TextView iconText;


    }
}
