package com.example.dell.miss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CelebrityAllliuyanActivity extends AppCompatActivity {
    private List<FriendAllhuainianMessage> list = new ArrayList<>();
    private ListView listView;
    private FriendAllhuainianAdapter allhuainianAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrity_allliuyan);

        TextView back = findViewById(R.id.tv_allliuyan);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initDate();
        findView();
    }

    private void findView(){
        listView = findViewById(R.id.lv_allliuyan);
        allhuainianAdapter = new FriendAllhuainianAdapter(this,list,R.layout.friend_allhuainian_list_item);
        listView.setAdapter(allhuainianAdapter);
    }

    private void initDate(){
        FriendAllhuainianMessage message = new FriendAllhuainianMessage();
        message.upName = "a_0001";
        message.upTime = "2020-04-04 11:21:30";
        message.upDescription = "怀念您！";
        FriendAllhuainianMessage message1 = new FriendAllhuainianMessage();
        message1.upName = "a_0002";
        message1.upTime = "2020-04-05 09:45:31";
        message1.upDescription = "您永远使我们伟大的领袖！";
        list.add(message);
        list.add(message1);
    }
}
