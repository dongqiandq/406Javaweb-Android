package com.example.dell.miss;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.dell.miss.ConcreteLoginActivity.LoginUser;

public class StoryDetailsActivity extends AppCompatActivity {
    private ImageView imgTop;
    private ImageView imgCandle;
    private ImageView imgHeart;
    private ImageView imgTalk;

    private LinearLayout layout;
    private LinearLayout llCandle;
    private LinearLayout llMoving;
    private LinearLayout llComment;

    private TextView tvTitle;
    private TextView tvContent;
    private TextView tvCommentNum;
    private TextView tvLookAllComment;
    private TextView candleNum;
    private TextView heartNum;
    private TextView talkNum;

    //两条评论
    private Comment comment1;
    private Comment comment2;

    private int movingId;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_details);

        imgTop = findViewById(R.id.img_top);
        String img=getIntent().getStringExtra("img");
        Util.getDBImage(StoryDetailsActivity.this,Constant.URL+"images/"+img,imgTop);

        layout = findViewById(R.id.ll_renwudetail_huainian);
        final Intent intent = getIntent();
        NewestTwoCommentAsync newestTwoCommentAsync=new NewestTwoCommentAsync();
        newestTwoCommentAsync.execute(Constant.URL+"article/stwocomment",intent.getIntExtra("articleId",0));

        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(intent.getStringExtra("title"));

        /*tvTime=findViewById(R.id.tv_time);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
        String sdate=sdf.format(new Date(intent.getStringExtra("time")));
        tvTime.setText(sdate);
        tvTime.setTextColor(Color.GRAY);*/

        tvContent = findViewById(R.id.tv_content);
        tvContent.setText(intent.getStringExtra("content"));

        tvCommentNum=findViewById(R.id.tv_celeliuyanNum);
        tvCommentNum.setText(intent.getIntExtra("commentNum",0)+"");

        tvLookAllComment=findViewById(R.id.tv_celelookAllLiuyan);
        tvLookAllComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.getIntExtra("commentNum",0)==0){
                    Intent intent1 = new Intent(StoryDetailsActivity.this,StoryNoTalkActivity.class);
                    startActivity(intent1);
                }else {
                    Intent intent1 = new Intent(StoryDetailsActivity.this,StoryTalkActivity.class);
                    intent1.putExtra("articleId",intent.getIntExtra("articleId",0));
                    startActivity(intent1);
                }
            }
        });

        MyListener myListener=new MyListener();
        llCandle=findViewById(R.id.ll_candle);
        llCandle.setOnClickListener(myListener);
        imgCandle = findViewById(R.id.simg_candle);
        candleNum = findViewById(R.id.stv_candleNum);
        candleNum.setText(intent.getIntExtra("candle",0)+"");

        llMoving=findViewById(R.id.ll_moving);
        llMoving.setOnClickListener(myListener);
        imgHeart = findViewById(R.id.simg_heart);
        heartNum = findViewById(R.id.stv_heartNum);
        MovingNumAsync movingNumAsync=new MovingNumAsync();
        movingNumAsync.execute(Constant.URL+"article/smoving",intent.getIntExtra("articleId",0));

        llComment=findViewById(R.id.ll_comment);
        llComment.setOnClickListener(myListener);
        imgTalk = findViewById(R.id.simg_talk);
        talkNum = findViewById(R.id.stv_talkNum);
        talkNum.setText(intent.getIntExtra("commentNum",0)+"");
        talkNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.getIntExtra("commentNum",0)==0){
                    Intent intent1 = new Intent(StoryDetailsActivity.this,StoryNoTalkActivity.class);
                    intent1.putExtra("articleId",intent.getIntExtra("articleId",0));
                    startActivity(intent1);
                }else {
                    Intent intent1 = new Intent(StoryDetailsActivity.this,StoryTalkActivity.class);
                    intent1.putExtra("articleId",intent.getIntExtra("articleId",0));
                    startActivity(intent1);
                }
            }
        });

        //底部添加留言
        Button addLiuyan = findViewById(R.id.btn_detailAddLiuyan);
        addLiuyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null==LoginUser){
                    Intent intent1=new Intent(StoryDetailsActivity.this,LoginActivity.class);
                    startActivity(intent1);
                    finish();
                }else {
                    Intent intent2 = new Intent(StoryDetailsActivity.this,StoryAddTalkActivity.class);
                    intent2.putExtra("articleId",intent.getIntExtra("articleId",0));
                    startActivity(intent2);
                }
            }
        });

        //添加足迹
        if (null!=LoginUser){
            AddTrackAsync addTrackAsync=new AddTrackAsync();
            addTrackAsync.execute(Constant.URL+"article/itrack",LoginUser.getId(),intent.getIntExtra("articleId",0));
        }
    }

    public class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_candle:
                    if (null==LoginUser){
                        Intent intent=new Intent(StoryDetailsActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }else {
                        if (imgCandle.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.candle).getConstantState())){
                            imgCandle.setImageResource(R.drawable.candle1);
                            candleNum.setText(Integer.parseInt(candleNum.getText().toString())+1+"");
                            CandleAsync candleAsync=new CandleAsync();
                            candleAsync.execute(Constant.URL+"article/candle",getIntent().getIntExtra("articleId",0),1);
                        }else {
                            imgCandle.setImageResource(R.drawable.candle);
                            candleNum.setText(Integer.parseInt(candleNum.getText().toString())-1+"");
                            CandleAsync candleAsync=new CandleAsync();
                            candleAsync.execute(Constant.URL+"article/candle",getIntent().getIntExtra("articleId",0),-1);
                        }
                    }
                    break;
                case R.id.ll_moving:
                    if (null==LoginUser){
                        Intent intent=new Intent(StoryDetailsActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }else {
                        if (imgHeart.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.heart).getConstantState())){
                            imgHeart.setImageResource(R.drawable.heart1);
                            heartNum.setText(Integer.parseInt(heartNum.getText().toString())+1+"");
                            AddMovingAsync addMovingAsync=new AddMovingAsync();
                            addMovingAsync.execute(Constant.URL+"article/imoving",LoginUser.getId(),getIntent().getIntExtra("articleId",0));
                        }else {
                            imgHeart.setImageResource(R.drawable.heart);
                            heartNum.setText(Integer.parseInt(heartNum.getText().toString())-1+"");
                            if (0!=movingId){
                                CancelMovingAsync cancelMovingAsync=new CancelMovingAsync();
                                cancelMovingAsync.execute(Constant.URL+"article/dmoving",movingId);
                            }else {
                                Toast.makeText(StoryDetailsActivity.this,"操作失败，请稍后再试",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    break;
                case R.id.ll_comment:
                    if (getIntent().getIntExtra("commentNum",0)==0){
                        Intent intent1 = new Intent(StoryDetailsActivity.this,StoryNoTalkActivity.class);
                        startActivity(intent1);
                    }else {
                        Intent intent1 = new Intent(StoryDetailsActivity.this,StoryTalkActivity.class);
                        intent1.putExtra("articleId",getIntent().getIntExtra("articleId",0));
                        startActivity(intent1);
                    }
                    break;
            }
        }
    }

    /**
     * 查询最新的两条评论
     */
    public class NewestTwoCommentAsync extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection=Util.getURLConnection((String)objects[0]);
                OutputStream os=connection.getOutputStream();
                os.write(objects[1].toString().getBytes());

                InputStream is=connection.getInputStream();
                String content=Util.readInputStreamToString(is);
                Util.closeIO(is,os);
                return content;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            String o1=o.toString().substring(0,o.toString().length()-1);
            String[] array1=o1.split("Comment");
            //一条评论
            if (array1.length==2){
                comment1=new Comment();
                String[] array2=array1[1].split("``");
                comment1.setId(Integer.parseInt(array2[0]));
                User user=new User();
                String[] u=array2[1].split(",");
                user.setName(u[3]);
                user.setHeader(u[7]);
                comment1.setUser(user);
                comment1.setContent(array2[3]);
            }else if (array1.length==3){//两条评论
                comment1=new Comment();
                String[] array2=array1[1].split("``");
                comment1.setId(Integer.parseInt(array2[0]));
                User user=new User();
                String[] u=array2[1].split(",");
                user.setName(u[3]);
                user.setHeader(u[7]);
                comment1.setUser(user);
                comment1.setContent(array2[3].substring(0,array2[3].length()-2));
                Log.e("comment1内容",comment1.getContent());

                comment2=new Comment();
                String[] array3=array1[2].split("``");
                comment2.setId(Integer.parseInt(array2[0]));
                User user2=new User();
                String[] u2=array3[1].split(",");
                user2.setName(u2[3]);
                user2.setHeader(u2[7]);
                comment2.setUser(user2);
                comment2.setContent(array3[3]);
                Log.e("comment2内容",comment2.getContent());
            }

            //添加第一个LinearLayout
            if (null!=comment1){
                LinearLayout linearLayout = new LinearLayout(StoryDetailsActivity.this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams mm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mm.setMargins(0,30,0,0);
                linearLayout.setLayoutParams(mm);
                linearLayout.setPadding(0,0,0,30);
                layout.addView(linearLayout);

                //用户头像
                ImageView imageView = new ImageView(StoryDetailsActivity.this);
                Util.getDBImage(StoryDetailsActivity.this,Constant.URL+"images/"+comment1.getUser().getHeader(),imageView);
                LinearLayout .LayoutParams para = new LinearLayout.LayoutParams(100,100);
                imageView.setLayoutParams(para);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                linearLayout.addView(imageView);

                LinearLayout linearLayout2 = new LinearLayout(StoryDetailsActivity.this);
                linearLayout2.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams mm22 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mm22.setMargins(30,0,0,10);
                linearLayout2.setLayoutParams(mm22);
                linearLayout.addView(linearLayout2);

                //用户名
                TextView textView = new TextView(StoryDetailsActivity.this);
                LinearLayout.LayoutParams mm2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(mm2);
                textView.setText(comment1.getUser().getName());
                textView.setTextSize(13);
                textView.setTextColor(Color.GRAY);
                linearLayout2.addView(textView);

                //评论内容
                TextView textView3 = new TextView(StoryDetailsActivity.this);
                textView3.setId(R.id.textview_3);
                LinearLayout.LayoutParams mm3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mm3.setMargins(0,20,0,0);
                textView3.setLayoutParams(mm3);
                textView3.setText(comment1.getContent());
                textView3.setTextSize(15);
                textView3.setTextColor(Color.WHITE);
                linearLayout2.addView(textView3);

                //分割线
                View textViewLine = new View(StoryDetailsActivity.this);
                LinearLayout.LayoutParams mmLine = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,150);
                mmLine.setMargins(0,10,0,0);
                textViewLine.setLayoutParams(mmLine);
                textViewLine.setBackgroundColor(Color.WHITE);
                /*Drawable drawable1 = getResources().getDrawable(R.drawable.linearlayout_borderline);
                linearLayout.setBackground(drawable1);*/
            }if (null!=comment2){
                LinearLayout linearLayout = new LinearLayout(StoryDetailsActivity.this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams mm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mm.setMargins(0,30,0,0);
                linearLayout.setLayoutParams(mm);
                linearLayout.setPadding(0,0,0,30);
                layout.addView(linearLayout);

                //用户头像
                ImageView imageView = new ImageView(StoryDetailsActivity.this);
                Util.getDBImage(StoryDetailsActivity.this,Constant.URL+"images/"+comment2.getUser().getHeader(),imageView);
                LinearLayout .LayoutParams para = new LinearLayout.LayoutParams(120,120);
                imageView.setLayoutParams(para);
                linearLayout.addView(imageView);

                LinearLayout linearLayout2 = new LinearLayout(StoryDetailsActivity.this);
                linearLayout2.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams mm22 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mm22.setMargins(30,0,0,10);
                linearLayout2.setLayoutParams(mm22);
                linearLayout.addView(linearLayout2);

                //用户名
                TextView textView = new TextView(StoryDetailsActivity.this);
                LinearLayout.LayoutParams mm2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(mm2);
                textView.setText(comment2.getUser().getName());
                textView.setTextSize(13);
                textView.setTextColor(Color.GRAY);
                linearLayout2.addView(textView);

                //评论内容
                TextView textView3 = new TextView(StoryDetailsActivity.this);
                textView3.setId(R.id.textview_3);
                LinearLayout.LayoutParams mm3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mm3.setMargins(0,20,0,0);
                textView3.setLayoutParams(mm3);
                textView3.setText(comment2.getContent());
                textView3.setTextSize(15);
                textView3.setTextColor(Color.WHITE);
                linearLayout2.addView(textView3);

                //分割线
                View textViewLine = new View(StoryDetailsActivity.this);
                LinearLayout.LayoutParams mmLine = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,150);
                mmLine.setMargins(0,10,0,0);
                textViewLine.setLayoutParams(mmLine);
                textViewLine.setBackgroundColor(Color.WHITE);
            }if (null==comment1 && null==comment2){
                TextView textView = new TextView(StoryDetailsActivity.this);
                LinearLayout.LayoutParams mm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mm.setMargins(0,40,0,40);
                mm.gravity = Gravity.CENTER;
                textView.setLayoutParams(mm);
                textView.setText("暂无留言");
                textView.setTextSize(15);
                textView.setTextColor(Color.WHITE);
                layout.addView(textView);
            }
        }
    }

    /**
     * 查询某个文章被添加 “感动”的数量
     */
    public class MovingNumAsync extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection=Util.getURLConnection((String)objects[0]);
                OutputStream os=connection.getOutputStream();
                os.write(objects[1].toString().getBytes());

                InputStream is=connection.getInputStream();
                String content=Util.readInputStreamToString(is);
                Util.closeIO(is,os);
                return content;
            } catch (IOException e) {
                e.printStackTrace();
            }return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (null!=o){
                heartNum.setText(Integer.parseInt(o.toString())+"");
            }
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

    /**
     * 添加感动
     */
    public class AddMovingAsync extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection=Util.getURLConnection((String)objects[0]);
                OutputStream os=connection.getOutputStream();
                String message=objects[1]+","+objects[2];
                os.write(message.getBytes());

                InputStream is=connection.getInputStream();
                String content=Util.readInputStreamToString(is);
                Util.closeIO(is,os);
                return content;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (null==o){
                Toast.makeText(StoryDetailsActivity.this,"网络连接不可用，请稍后再试",Toast.LENGTH_SHORT).show();
            }else if ("0".equals(o.toString())){
                Toast.makeText(StoryDetailsActivity.this,"添加失败，请稍后再试",Toast.LENGTH_SHORT).show();
            }else {
                movingId=Integer.parseInt(o.toString());
                Log.e("感动值",movingId+"");
            }
        }
    }

    /**
     * 取消感动
     */
    public class CancelMovingAsync extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection=Util.getURLConnection((String)objects[0]);
                OutputStream os=connection.getOutputStream();
                os.write(objects[1].toString().getBytes());

                InputStream is=connection.getInputStream();
                String content=Util.readInputStreamToString(is);
                Util.closeIO(is,os);
                return content;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (null==o || "".equals(o.toString())){
                Toast.makeText(StoryDetailsActivity.this,"网络连接不可用，请稍后再试",Toast.LENGTH_SHORT).show();
            }else {
                movingId=0;
            }
        }
    }

    /**
     * 添加足迹
     */
    public class AddTrackAsync extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection=Util.getURLConnection((String)objects[0]);
                OutputStream os=connection.getOutputStream();
                String message=objects[1]+","+objects[2];
                os.write(message.getBytes());

                InputStream is=connection.getInputStream();
                Util.readInputStreamToString(is);
                Util.closeIO(is,os);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
