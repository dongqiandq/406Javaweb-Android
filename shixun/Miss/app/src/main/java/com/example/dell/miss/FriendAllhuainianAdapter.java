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
import java.util.List;

public class FriendAllhuainianAdapter extends BaseAdapter {
    private List<FriendAllhuainianMessage> list = new ArrayList<>();
    private int itemId;
    private Context context;
    private int zanCount=0;

    private int ITEM=0;

    //添加数据
    public void addItem(FriendAllhuainianMessage message){
        list.add(message);
        notifyDataSetChanged();
    }

    public FriendAllhuainianAdapter(Context context, List<FriendAllhuainianMessage> list, int itemId){
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
        ViewHolder viewHolder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(itemId,null);
            viewHolder = new ViewHolder();
            viewHolder.upName = convertView.findViewById(R.id.tv_recallItemName);
            viewHolder.upTime = convertView.findViewById(R.id.tv_recallItem_time);
            viewHolder.upDescription = convertView.findViewById(R.id.tv_recallItem_des);

            viewHolder.zanNum = convertView.findViewById(R.id.tv_zanNum);
            viewHolder.zan = convertView.findViewById(R.id.iv_recallItem_zan);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.upName.setText(list.get(position).upName);
        viewHolder.upTime.setText(list.get(position).upTime);
        viewHolder.upDescription.setText(list.get(position).upDescription);
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ITEM == 0){
                    zanCount = zanCount + 1;
                    finalViewHolder.zanNum.setText(zanCount+"");
                    finalViewHolder.zan.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.liangzan));
                    ITEM=1;
                }else {
                    zanCount = zanCount - 1;
                    finalViewHolder.zanNum.setText(zanCount+"");
                    finalViewHolder.zan.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.zan));
                    ITEM=0;
                }
            }
        });
        return convertView;
    }

    private class ViewHolder{
        public TextView upName;
        public TextView upTime;
        public TextView upDescription;
        public TextView zanNum;
        public ImageView zan;
    }
}
