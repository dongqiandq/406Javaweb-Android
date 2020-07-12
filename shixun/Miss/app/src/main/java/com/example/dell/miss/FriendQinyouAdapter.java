package com.example.dell.miss;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FriendQinyouAdapter extends BaseAdapter {
    private List<FriendQinyouMessage> list = new ArrayList<>();
    private int itemId;
    private Context context;
    private int countFlower=0;
    private int ITEMCANDLE=0;
    private int countCandle=0;
    private int ITEMFLOWER=0;

    private int candle;
    private Intent intent1 = new Intent();

    public FriendQinyouAdapter (Context context,List<FriendQinyouMessage> list,int itemId){
        this.context = context;
        this.list = list;
        this.itemId = itemId;
    }

    @Override
    public int getCount() {
        if (null != list){
            return  list.size();
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
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(itemId,null);
            viewHolder = new ViewHolder();
            viewHolder.imgitemTouxiang = convertView.findViewById(R.id.img_itemTouxiang);
            viewHolder.tvqinyouName = convertView.findViewById(R.id.tv_qinyouName);
            viewHolder.tvqinyouTime = convertView.findViewById(R.id.tv_qinyouTime);
            viewHolder.tvqinyouPossition = convertView.findViewById(R.id.tv_qinyouPossition);
            viewHolder.tvqinyouDescription = convertView.findViewById(R.id.tv_qinyouDescription);
            viewHolder.llqinyouLook = convertView.findViewById(R.id.ll_qinyouLook);
            viewHolder.tvlookNum = convertView.findViewById(R.id.tv_lookNum);
            viewHolder.llqinyouFlower = convertView.findViewById(R.id.ll_qinyouFlower);
            viewHolder.ivFlower = convertView.findViewById(R.id.iv_flower);
            viewHolder.tvflowerNum = convertView.findViewById(R.id.tv_flowerNum);
            viewHolder.llqinyouCandle = convertView.findViewById(R.id.ll_qinyouCandle);
            viewHolder.ivCandle = convertView.findViewById(R.id.iv_candle);
            viewHolder.tvcandleNum = convertView.findViewById(R.id.tv_candleNum);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LinearLayout item = convertView.findViewById(R.id.item);
        final ViewHolder finalViewHolder2 = viewHolder;
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FriendQinyouListDetail.class);
                intent.putExtra("name",list.get(position).name);
                intent.putExtra("shengping",list.get(position).shangping);
                intent.putExtra("time",list.get(position).time);
                intent.putExtra("description",list.get(position).description);
                intent.putExtra("candleNum", finalViewHolder2.tvcandleNum.getText().toString());
                intent.putExtra("flowerNum",finalViewHolder2.tvflowerNum.getText().toString());
                intent.putExtra("fimg",list.get(position).img);
                context.startActivity(intent);

            }
        });
        viewHolder.imgitemTouxiang.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),list.get(position).img));
        viewHolder.tvqinyouName.setText(list.get(position).name);
        viewHolder.tvqinyouTime.setText(list.get(position).time);
        viewHolder.tvqinyouPossition.setText(list.get(position).possition);
        viewHolder.tvqinyouDescription.setText(list.get(position).description);
        viewHolder.tvlookNum.setText(list.get(position).lookNum+"");
        final ViewHolder finalViewHolder1 = viewHolder;
        viewHolder.ivFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countFlower==0){
                    finalViewHolder1.ivFlower.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.flower1));
                    countFlower = 1;
                }else {
                    finalViewHolder1.ivFlower.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.flower0));
//                    finalViewHolder1.tvflowerNum.setText((Integer.parseInt(flowerNum.getText().toString()) - 1)+"");
                    countFlower = 0;
                }
                finalViewHolder1.tvflowerNum.setText(countFlower+"");
//                finalViewHolder1.ivFlower.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.flower1));
//                countFlower = countFlower+1;
//                finalViewHolder1.tvflowerNum.setText(countFlower+"");
//                ITEMFLOWER=1;
            }
        });
        intent1.putExtra("ITEMFLOWER",ITEMFLOWER);
        final ViewHolder finalViewHolder = viewHolder;
        candle=0;
        viewHolder.ivCandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ITEMCANDLE==0){
                    finalViewHolder.ivCandle.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.candle1));
                    countCandle = countCandle+1;
                    finalViewHolder.tvcandleNum.setText(countCandle+"");
                    ITEMCANDLE = 1;
                    candle=1;
                }else {
                    finalViewHolder.ivCandle.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.candle));
                    countCandle = countCandle-1;
                    finalViewHolder.tvcandleNum.setText(countCandle+"");
                    ITEMCANDLE = 0;
                    candle=0;
                }
            }
        });
        Log.e("candle",candle+"");
        intent1.putExtra("candle",candle);
        return convertView;
    }

    private class ViewHolder{
        public ImageView imgitemTouxiang;
        public TextView tvqinyouName;
        public TextView tvqinyouTime;
        public TextView tvqinyouPossition;
        public TextView tvqinyouDescription;
        public LinearLayout llqinyouLook;
        public TextView tvlookNum;
        public LinearLayout llqinyouFlower;
        public TextView tvflowerNum;
        public LinearLayout llqinyouCandle;
        public TextView tvcandleNum;
        public ImageView ivFlower;
        public ImageView ivCandle;
    }
}
