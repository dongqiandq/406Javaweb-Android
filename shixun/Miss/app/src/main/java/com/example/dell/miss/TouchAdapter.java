package com.example.dell.miss;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TouchAdapter extends BaseAdapter {

    private List<Article> list;
    private int itemId;
    private Context context;

    public TouchAdapter(List<Article> list,Context context,int itemId){
        this.list = list;
        this.itemId = itemId;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (null != list){
            return  list.size();
        }else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (null != list){
            return list.get(position);
        }else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(itemId,parent,false);
//            convertView = LayoutInflater.from(context).inflate(R.layout.touch_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imgGushi = convertView.findViewById(R.id.img_gushi);
            viewHolder.tvTitle = convertView.findViewById(R.id.tv_title);
            viewHolder.tvYueDuNum = convertView.findViewById(R.id.tv_yuedunum);
            viewHolder.tvPingLunNum = convertView.findViewById(R.id.tv_pinglunnum);
            viewHolder.tvNull = convertView.findViewById(R.id.tv_null);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        LinearLayout item = convertView.findViewById(R.id.gandong_item);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,StoryDetailsActivity.class);
                intent.putExtra("articleId",list.get(position).getId());
                intent.putExtra("img",list.get(position).getPicture());
                intent.putExtra("title",list.get(position).getTitle());
                intent.putExtra("content",list.get(position).getContent());
//                intent.putExtra("time",list.get(position).getTime().toString());
                intent.putExtra("commentNum",list.get(position).getCommentNum());
                intent.putExtra("candle",list.get(position).getCandle());
                context.startActivity(intent);
            }
        });

        Util.getDBImage(context,Constant.URL+"images/"+list.get(position).getPicture(),viewHolder.imgGushi);
        viewHolder.tvTitle.setText(list.get(position).getTitle());
        viewHolder.tvYueDuNum.setText(list.get(position).getVisitors()+"");
        viewHolder.tvPingLunNum.setText(list.get(position).getCommentNum()+"");
        viewHolder.tvNull.setText("");
        return convertView;
    }

    private class ViewHolder{
        public ImageView imgGushi;
        public TextView tvTitle;
        public TextView tvPingLunNum;
        public TextView tvYueDuNum;
        public TextView tvNull;
    }
}
