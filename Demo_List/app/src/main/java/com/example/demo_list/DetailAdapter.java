package com.example.demo_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by 秦 on 2016/11/11.
 */

public class DetailAdapter extends BaseAdapter{
    private LinkedList<D> mData;
    private Context mContext;

    public DetailAdapter(LinkedList<D> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.detail_item,parent,false);

        TextView txt_aName = (TextView)convertView.findViewById(R.id.t_state);
        TextView txt_aSpeak = (TextView)convertView.findViewById(R.id.t_time);

        txt_aName.setText(mData.get(position).getQingKuang());
        txt_aSpeak.setText("第"+mData.get(position).getWeek()+"周"+"第"+mData.get(position).getTime()+"次课");

        return convertView;
    }
}
