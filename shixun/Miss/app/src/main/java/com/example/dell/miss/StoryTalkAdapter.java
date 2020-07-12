package com.example.dell.miss;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoryTalkAdapter extends BaseAdapter {
    private List<Comment> list;
    private int itemId;
    private Context context;
    private int zanCount=0;

    private int ITEM=0;

    //添加数据
    public void addItem(Comment message){
        list.add(message);
        notifyDataSetChanged();
    }

    public StoryTalkAdapter(Context context, List<Comment> list, int itemId){
        this.context = context;
        this.list = list;
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
        StoryTalkAdapter.ViewHolder viewHolder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(itemId,null);
            viewHolder = new StoryTalkAdapter.ViewHolder();
            viewHolder.ivHead=convertView.findViewById(R.id.iv_head);
            viewHolder.upName = convertView.findViewById(R.id.tv_recallItemName);
            viewHolder.upDescription = convertView.findViewById(R.id.tv_recallItem_des);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (StoryTalkAdapter.ViewHolder) convertView.getTag();
        }
        Util.getDBImage(context,Constant.URL+"images/"+list.get(position).getUser().getHeader(),viewHolder.ivHead);
        viewHolder.upName.setText(list.get(position).getUser().getName());
        viewHolder.upDescription.setText(list.get(position).getContent());
        return convertView;
    }

    private class ViewHolder{
        public ImageView ivHead;
        public TextView upName;
        public TextView upDescription;
    }
}
