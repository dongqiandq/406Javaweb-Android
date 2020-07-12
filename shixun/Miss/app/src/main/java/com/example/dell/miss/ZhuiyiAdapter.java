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

public class ZhuiyiAdapter extends BaseAdapter{
    private List<ZhuiyiMessage> list = new ArrayList<>();
    private int itemId;
    private Context context;

    private int countFlower=0;
    private int ITEMCANDLE=0;
    private int countCandle=0;
    private int ITEMFLOWER=0;

    private int candle;
    private Intent intent1 = new Intent();

    public ZhuiyiAdapter(List<ZhuiyiMessage> list,Context context,int itemId){
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
            return  list.get(position);
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
//            convertView = LayoutInflater.from(context).inflate(R.layout.zhuiyi_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imgRen = convertView.findViewById(R.id.img_ren);
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            viewHolder.tvYear = convertView.findViewById(R.id.tv_year);
            viewHolder.tvAddress = convertView.findViewById(R.id.tv_address);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LinearLayout item = convertView.findViewById(R.id.zy_item);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //向详情页传数据
                Intent intent = new Intent(context,CelebrityDetailActivity.class);
                intent.putExtra("name",list.get(position).getZyname());
                intent.putExtra("time",list.get(position).getZyyear());
                intent.putExtra("description",list.get(position).getContext());
                intent.putExtra("img",list.get(position).getImgId());
                intent.putExtra("jianjie",list.get(position).getJianjie());
                context.startActivity(intent);

            }
        });
        viewHolder.imgRen.setImageResource(list.get(position).getImgId());
        viewHolder.tvName.setText(list.get(position).getZyname());
        viewHolder.tvYear.setText(list.get(position).getZyyear());
        viewHolder.tvAddress.setText(list.get(position).getZyaddress());
        return convertView;
    }

    private class ViewHolder{
        public ImageView imgRen;
        public TextView tvName;
        public TextView tvYear;
        public TextView tvAddress;
    }

}
