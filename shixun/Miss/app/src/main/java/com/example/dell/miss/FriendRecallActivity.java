package com.example.dell.miss;

import android.support.v7.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FriendRecallActivity extends AppCompatActivity {
    private List<FriendRecall> list = new ArrayList<>();
    private ListView listView;
    private FriendRecallAdapter recallAdapter;

    private ImageView addReacall;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_recall);
        TextView back = findViewById(R.id.btn_reback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initData();
        findView();

        addReacall = findViewById(R.id.img_addrecall);
        addReacall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FriendRecallActivity.this,FriendAddrecallActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void findView(){
        listView = findViewById(R.id.lv_recall);
        recallAdapter = new FriendRecallAdapter(this,list,R.layout.friend_recall_list_item);
        listView.setAdapter(recallAdapter);
    }

    private void initData(){
        FriendRecall recall = new FriendRecall();
        recall.title = "您是大英雄";
        recall.time = "2020-04-04 21:25:19";
        recall.description = "时间过的真快，从疫情开始到现在已经四个月了，转眼就到了五月份，而您也已经离开我四个月了，是您及时发现疫情并上报，才使得疫情及时得以控制，减少了感染人的数量，您是真正的英雄，您实至名归。";
        FriendRecall recall1 = new FriendRecall();
        recall1.title = "生命永存";
        recall1.time = "2019-11-11 10:21:10";
        recall1.description = "你年幼的生命定格在了2019年的夏天，你那稚嫩的脸庞仿佛还在脑海中，莲莲，虽然你已经不在了，但是你的角膜等组织使得其他几位遭受病魔折磨的人获得新生，你的生命因而也得到延续，我们不会忘记你，你永远在人们的记忆中。";
        list.add(recall);
        list.add(recall1);
    }
}

