package com.example.dell.miss;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FriendRecallAdapter extends BaseAdapter {
    private List<FriendRecall> list = new ArrayList<>();
    private int itemId;
    private Context context;

    public FriendRecallAdapter (Context context,List<FriendRecall> listr,int itemId){
        this.context = context;
        this.list = listr;
        this.itemId = itemId;
    }

    @Override
    public int getCount() {
        if (null != list){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (null != list){
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(itemId,null);
            viewHolder = new ViewHolder();
            viewHolder.title = convertView.findViewById(R.id.tv_recallTitle);
            viewHolder.time = convertView.findViewById(R.id.tv_recallTime);
            viewHolder.description = convertView.findViewById(R.id.tv_recallDescription);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(list.get(position).title);
        viewHolder.time.setText(list.get(position).time);
        viewHolder.description.setText(list.get(position).description);
        LinearLayout item = convertView.findViewById(R.id.ll_recallItem);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FriendRecallItemDetailActivity.class);
                intent.putExtra("title",list.get(position).title);
                intent.putExtra("time",list.get(position).time);
                intent.putExtra("rde",list.get(position).description);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private class ViewHolder{
        public TextView title;
        public TextView time;
        public TextView description;
    }
}

