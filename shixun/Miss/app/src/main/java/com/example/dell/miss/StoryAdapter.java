package com.example.dell.miss;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;
import static com.example.dell.miss.ConcreteLoginActivity.LoginUser;

public class StoryAdapter extends BaseAdapter {
    private List<Article> list;
    private Context context;
    private int itemId;

    public StoryAdapter(List<Article> list, Context context, int itemId){
        this.list = list;
        this.context = context;
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
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(itemId,null);
            viewHolder = new ViewHolder();
            viewHolder.img1 = convertView.findViewById(R.id.story_img);
            viewHolder.l1Talk=convertView.findViewById(R.id.l1_talk);
            viewHolder.l1lazhu=convertView.findViewById(R.id.l1_lazhu);
            viewHolder.img3 = convertView.findViewById(R.id.img3);
            viewHolder.tvText1 = convertView.findViewById(R.id.story_tv1);
            viewHolder.tvText2 = convertView.findViewById(R.id.story_tv2);
            viewHolder.tvText3 = convertView.findViewById(R.id.tv1);
            viewHolder.tvText4 = convertView.findViewById(R.id.tv2);
            viewHolder.tvText5 = convertView.findViewById(R.id.tv3);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LinearLayout item = convertView.findViewById(R.id.story_item);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanAsync scanAsync=new ScanAsync();
                scanAsync.execute(Constant.URL+"article/scan",list.get(position).getId());
                Intent intent = new Intent(context,StoryDetailsActivity.class);
                intent.putExtra("articleId",list.get(position).getId());
                intent.putExtra("img",list.get(position).getPicture());
                intent.putExtra("title",list.get(position).getTitle());
                intent.putExtra("content",list.get(position).getContent());
                intent.putExtra("time",list.get(position).getTime().toString());
                intent.putExtra("commentNum",list.get(position).getCommentNum());
                intent.putExtra("candle",list.get(position).getCandle());
                context.startActivity(intent);
            }
        });
//        viewHolder.img1.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.s1));
        Util.getDBImage(context,Constant.URL+"images/"+list.get(position).getPicture(),viewHolder.img1);
        viewHolder.tvText1.setText(list.get(position).getTitle());
        viewHolder.tvText2.setText(list.get(position).getContent());
        viewHolder.tvText3.setText(list.get(position).getVisitors()+"");
        viewHolder.tvText4.setText(list.get(position).getCommentNum()+"");
        viewHolder.tvText5.setText(list.get(position).getCandle()+"");

        //点击评论
        viewHolder.l1Talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).getCommentNum()==0){
                    Intent intent1 = new Intent(context,StoryNoTalkActivity.class);
                    context.startActivity(intent1);
                }
                else {
                    Intent intent2 = new Intent(context,StoryTalkActivity.class);
                    intent2.putExtra("articleId",list.get(position).getId());
                    context.startActivity(intent2);
                }
            }
        });

        //点击蜡烛
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.l1lazhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null==LoginUser){
                    Intent intent=new Intent(context,LoginActivity.class);
                    context.startActivity(intent);
                }else {
                    if (finalViewHolder.img3.getDrawable().getConstantState().equals(context.getResources().getDrawable(R.drawable.lazhu).getConstantState())){
                        finalViewHolder.img3.setImageResource(R.drawable.lazhu1);
                        finalViewHolder.tvText5.setText(Integer.parseInt(finalViewHolder.tvText5.getText().toString())+1+"");
                        CandleAsync candleAsync=new CandleAsync();
                        candleAsync.execute(Constant.URL+"article/candle",list.get(position).getId(),1);
                    }else {
                        finalViewHolder.img3.setImageResource(R.drawable.lazhu);
                        finalViewHolder.tvText5.setText(Integer.parseInt(finalViewHolder.tvText5.getText().toString())-1+"");
                        CandleAsync candleAsync=new CandleAsync();
                        candleAsync.execute(Constant.URL+"article/candle",list.get(position).getId(),-1);
                    }
                }
            }
        });
        return convertView;
    }

    /**
     * 浏览量加一
     */
    private class ScanAsync extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection=Util.getURLConnection((String) objects[0]);
                OutputStream os=connection.getOutputStream();
                os.write(objects[1].toString().getBytes());

                InputStream is=connection.getInputStream();
                String content=Util.readInputStreamToString(is);
                Util.closeIO(is,os);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    /**
     * 点亮蜡烛或取消
     */
    public class CandleAsync extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection=Util.getURLConnection((String) objects[0]);
                OutputStream os=connection.getOutputStream();
                String str=objects[1]+","+objects[2];
                Log.e("点烛",objects[2]+"");
                os.write(str.getBytes());

                InputStream is=connection.getInputStream();
                String content=Util.readInputStreamToString(is);
                Util.closeIO(is,os);
                return content;
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
    }


    private class ViewHolder{
        private ImageView img1;
        private LinearLayout l1Talk;
        private LinearLayout l1lazhu;
        private ImageView img3;
        private TextView tvText1;
        private TextView tvText2;
        private TextView tvText3;
        private TextView tvText4;
        private TextView tvText5;
    }
}
