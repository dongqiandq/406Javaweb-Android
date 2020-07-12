package com.example.dell.miss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FriendAllhuainianActivity extends AppCompatActivity {
    private List<FriendAllhuainianMessage> list = new ArrayList<>();
    private ListView listView;
    private FriendAllhuainianAdapter allhuainianAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_allhuainian);

        TextView back = findViewById(R.id.tv_allhuaiback);
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
        listView = findViewById(R.id.lv_allrecall);
        allhuainianAdapter = new FriendAllhuainianAdapter(this,list,R.layout.friend_allhuainian_list_item);
        listView.setAdapter(allhuainianAdapter);
    }

    private void initDate(){
        FriendAllhuainianMessage message = new FriendAllhuainianMessage();
        message.upName = "u_0001";
        message.upTime = "2020-04-21 19:21:14";
        message.upDescription = "永垂不朽！";
        FriendAllhuainianMessage message1 = new FriendAllhuainianMessage();
        message1.upName = "u_0002";
        message1.upTime = "2020-05-01 09:29:14";
        message1.upDescription = "纪念您！";
        list.add(message);
        list.add(message1);
    }
}
