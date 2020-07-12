package com.example.dell.miss;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FriendQinyouListDetail extends AppCompatActivity {
    private int ITEM1=0;
    private int ITEM2=0;
    private int ITEM3=0;
    private int ITEM4=0;
    private int ITEMCANDLE;
    private int ITEMFLOWER;
    private String name="";


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_list_detail);
        final Intent intent = getIntent();
        TextView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = intent.getStringExtra("name");
                if(name.equals("小明")){
                    Intent intent1 = new Intent();
                    intent1.putExtra("eye",1);
                    setResult(100,intent);
                }
                FriendQinyouListDetail.this.finish();
            }
        });

        //头像 从数据库获取
        ImageView touxiang = findViewById(R.id.iv_detailPhoto);

        TextView name = findViewById(R.id.tv_detailName);
        TextView time = findViewById(R.id.tv_detailTime);
        TextView description = findViewById(R.id.tv_short);
        final ImageView ivCandle = findViewById(R.id.iv_huannianCandle);
        final TextView candleNum = findViewById(R.id.tv_huaiCandleNum);
        final ImageView ivFlower = findViewById(R.id.iv_huainianFlower);
        final TextView flowerNum = findViewById(R.id.tv_huaiFlowerNum);

        TextView oldPhoto = findViewById(R.id.tv_oldPhoto);
        TextView recall = findViewById(R.id.tv_recall);
        Button addHuainian = findViewById(R.id.btn_huainian);
        TextView lookAllHuainian = findViewById(R.id.tv_lookAllHuainain);
        TextView huainainNum = findViewById(R.id.tv_huainianNum);




        //亲友详情页的 墓地，数据从数据库获取
//        TextView mudi = findViewById(R.id.tv_detail_mudi);
//        mudi.setText("XXX省XXX市XXX县");

        //从数据库获得怀念数据条数
        int num = 2;
        if(intent.getIntExtra("fimg",0)==R.drawable.f3){
            num=0;
        }
        huainainNum.setText(num+"");
        LinearLayout layout = findViewById(R.id.ll_renwudetail_huainian);
        if (num == 0){//数据库中没有怀念的数据
            addNoHuainianTextView(layout);
        }else if (num == 1){//数据库中只有一条怀念的数据
            LinearLayout firstLl = addLinearLayout(layout);
            addImage(firstLl);
            LinearLayout seconeLl = addSecondLinearLayout(firstLl);
            TextView tv_huaiName = addTextView(seconeLl);
            TextView tv_huaiTime = addSecondTextView(seconeLl);
            TextView tv_huaiNeirong =  addThirdTextView(seconeLl);
            //从数据库获得"怀念"的数据，再将昵称、时间、内容存进去TextView
            tv_huaiName.setText("u_0006");
            tv_huaiTime.setText("2020-04-21 19:21:14");
            tv_huaiNeirong.setText("永垂不朽！");

            Drawable drawable = getResources().getDrawable(R.drawable.linearlayout_borderline);
            firstLl.setBackground(drawable);
        }else {
            //第一条怀念
            LinearLayout firstLl = addLinearLayout(layout);
            addImage(firstLl);
            LinearLayout seconeLl = addSecondLinearLayout(firstLl);
            TextView tv_huaiName = addTextView(seconeLl);
            TextView tv_huaiTime = addSecondTextView(seconeLl);
            TextView tv_huaiNeirong =  addThirdTextView(seconeLl);
            //从数据库获得"怀念"的最后一条数据（最后添加进去的），再将昵称、时间、内容存进去TextView
            tv_huaiName.setText("u_0001");
            tv_huaiTime.setText("2020-04-21 19:21:14");
            tv_huaiNeirong.setText("永垂不朽！");
            Drawable drawable = getResources().getDrawable(R.drawable.linearlayout_borderline);
            firstLl.setBackground(drawable);

            //第二条怀念
            LinearLayout firstLler = addLinearLayout(layout);
            addImage(firstLler);
            LinearLayout seconeLler = addSecondLinearLayout(firstLler);
            TextView tv_huaiNameer = addTextView(seconeLler);
            TextView tv_huaiTimeer = addSecondTextView(seconeLler);
            TextView tv_huaiNeironger =  addThirdTextView(seconeLler);
            //从数据库获得"怀念"的最后一条数据（最后添加进去的），再将昵称、时间、内容存进去TextView
            tv_huaiNameer.setText("u_0002");
            tv_huaiTimeer.setText("2020-05-01 09:29:14");
            tv_huaiNeironger.setText("纪念您！");
            //添加分割线
            Drawable drawable1 = getResources().getDrawable(R.drawable.linearlayout_borderline);
            firstLler.setBackground(drawable1);
        }





        String s = intent.getStringExtra("shengping");
        //亲友详情页的 生平，数据从数据库获取
        TextView shengping = findViewById(R.id.tv_shengping);
        if(s!="" && !s.isEmpty()){
            shengping.setText(s);
        }else{
            shengping.setText("热爱生活，乐于助人，积极向上");
        }
        String name1 = intent.getStringExtra("name");
        name.setText(name1);
        time.setText(intent.getStringExtra("time"));
        description.setText(intent.getStringExtra("description"));
        candleNum.setText(intent.getStringExtra("candleNum"));
        flowerNum.setText(intent.getStringExtra("flowerNum"));
        touxiang.setImageBitmap(BitmapFactory.decodeResource(getResources(),intent.getIntExtra("fimg",0)));
        //获取candle值：0,1；0表示列表中item上的蜡烛图标未点亮，1表示列表中item上的蜡烛图标点亮
        ITEMCANDLE = intent.getIntExtra("candle",0);
        //获取flower值：0,1；0表示列表中item上的鲜花图标未点亮，1表示列表中item上的鲜花图标点亮
        ITEMFLOWER = intent.getIntExtra("ITEMFLOWER",0);
        //判断列表中item上的蜡烛图标是否点亮
        //未点亮
        if (ITEMCANDLE==0){
            //添加点击事件，点击次数为奇数，蜡烛图像点亮，蜡烛数量对应加1；点击次数为偶数，蜡烛图像不点亮，蜡烛数量对应减1
            ivCandle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ITEM1==0){
                        ivCandle.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.candle1));
                        candleNum.setText((1 + Integer.parseInt(candleNum.getText().toString()))+"");
                        ITEM1=1;
                    }else {
                        ivCandle.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.candle));
                        candleNum.setText((Integer.parseInt(candleNum.getText().toString()) - 1)+"");
                        ITEM1=0;
                    }
                }
            });
        }else {//点亮
            //将蜡烛图像变为点亮状态
            ivCandle.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.candle1));
            //添加点击事件，点击次数为奇数，蜡烛图像不点亮，蜡烛数量对应减1；点击次数为偶数，蜡烛图像点亮，蜡烛数量对应加1
            ivCandle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ITEM2==0){
                        ivCandle.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.candle));
                        candleNum.setText((1 + Integer.parseInt(candleNum.getText().toString()))+"");
                        ITEM2=1;
                    }else {
                        ivCandle.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.candle1));
                        candleNum.setText((Integer.parseInt(candleNum.getText().toString()) - 1)+"");
                        ITEM2=0;
                    }
                }
            });
        }
        //判断列表中item上的鲜花图标是否点亮
        if (ITEMFLOWER==0){//未点亮
            //详情页中的鲜花图案也不点亮
            ivFlower.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.flower0));
            ITEM3 = 0;
        }else {//点亮
            //详情页中的鲜花图案点亮
            ivFlower.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.flower1));
            ITEM3 = 1;
        }
        //点击事件
        ivFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ITEM3==0){
                    ivFlower.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.flower1));
                    flowerNum.setText((1 + Integer.parseInt(flowerNum.getText().toString()))+"");
                    ITEM3 = 1;
                }else {
                    ivFlower.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.flower0));
                    flowerNum.setText((Integer.parseInt(flowerNum.getText().toString()) - 1)+"");
                    ITEM3 = 0;
                }
            }
        });
        //旧相册点击事件
        oldPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从数据库获得照片数量
                int a=3;
                //如果数据库没有照片，则显示无照片页面
                if(a==0){
                    Intent intent2 = new Intent(FriendQinyouListDetail.this,FriendNophotoActivity.class);
                    startActivity(intent2);
                }else {//数据库有照片
                    Intent intent1 = new Intent(FriendQinyouListDetail.this,FriendOldphotoActivity.class);
                    startActivity(intent1);
                }

            }
        });
        //回忆点击事件
        recall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从数据库获得回忆录数据数量
                int b=1;
                //如果数据库没有回忆录内容，则显示无回忆录页面
                if (b==0){
                    Intent intent1 = new Intent(FriendQinyouListDetail.this,FriendNorecallActivity.class);
                    startActivity(intent1);
                }else {
                    Intent intent2 = new Intent(FriendQinyouListDetail.this,FriendRecallActivity.class);
                    startActivity(intent2);
                }
            }
        });
        addHuainian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FriendQinyouListDetail.this,FriendAddhuainianActivity.class);
                startActivity(intent1);
            }
        });
        lookAllHuainian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从数据库获取怀念文字条数
                int c=4;
                //若数据库没有相关数据,跳转到“暂无怀念”页面
                if (c==0){
                    Intent intent1 = new Intent(FriendQinyouListDetail.this,FriendNoHuainianmessageActivity.class);
                    startActivity(intent1);
                }else {//若数据库有祥光数据，跳到数据页
                    Intent intent1 = new Intent(FriendQinyouListDetail.this,FriendAllhuainianActivity.class);
                    startActivity(intent1);
                }
            }
        });
    }

    //添加第一个LinearLayout
    public LinearLayout addLinearLayout(LinearLayout layout){
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams mm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mm.setMargins(0,30,0,0);
        linearLayout.setLayoutParams(mm);
        linearLayout.setPadding(0,0,0,30);
        layout.addView(linearLayout);
        return linearLayout;
    }

    //添加图片
    public void addImage(LinearLayout linearLayout){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(R.drawable.ping);
        LinearLayout .LayoutParams para = new LinearLayout.LayoutParams(120,120);
        imageView.setLayoutParams(para);
        linearLayout.addView(imageView);
    }

    //添加第二个LinearLayout
    public LinearLayout addSecondLinearLayout(LinearLayout layout){
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams mm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mm.setMargins(30,0,0,10);
        linearLayout.setLayoutParams(mm);
        layout.addView(linearLayout);
        return linearLayout;
    }
    //添加第一个TextView
    @SuppressLint("ResourceAsColor")
    public TextView addTextView(LinearLayout layout){
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams mm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(mm);
        textView.setTextSize(13);
        textView.setTextColor(Color.GRAY);
        layout.addView(textView);
        return textView;
    }

    //添加第二个TextView
    @SuppressLint("ResourceAsColor")
    public TextView addSecondTextView(LinearLayout layout){
        TextView textView = new TextView(this);
        textView.setId(R.id.textview_2);
        LinearLayout.LayoutParams mm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mm.setMargins(0,10,0,0);
        textView.setLayoutParams(mm);
        textView.setTextSize(13);
        textView.setTextColor(Color.GRAY);
        layout.addView(textView);
        return textView;
    }

    //添加第三个TextView
    @SuppressLint("ResourceAsColor")
    public TextView addThirdTextView(LinearLayout layout){
        TextView textView = new TextView(this);
        textView.setId(R.id.textview_3);
        LinearLayout.LayoutParams mm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mm.setMargins(0,20,0,0);
        textView.setLayoutParams(mm);
        textView.setTextSize(15);
        textView.setTextColor(Color.WHITE);
        layout.addView(textView);
        return textView;
    }

    //添加没有怀念的TextView
    @SuppressLint("ResourceAsColor")
    public void addNoHuainianTextView(LinearLayout layout){
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams mm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mm.setMargins(0,40,0,40);
        mm.gravity = Gravity.CENTER;
        textView.setLayoutParams(mm);
        textView.setText("暂无怀念");
        textView.setTextSize(15);
        textView.setTextColor(Color.WHITE);
        layout.addView(textView);
    }

    //添加分割线
    @SuppressLint("ResourceAsColor")
    public void addFengeLine(LinearLayout layout){
        View textView = new View(this);
        LinearLayout.LayoutParams mm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,150);
        mm.setMargins(0,10,0,0);
        textView.setLayoutParams(mm);
        textView.setBackgroundColor(Color.WHITE);

    }
}
