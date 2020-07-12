package com.example.dell.miss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CelerityRecallActivity extends AppCompatActivity {
    private List<FriendRecall> list = new ArrayList<>();
    private ListView listView;
    private FriendRecallAdapter recallAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celerity_recall);
        TextView back = findViewById(R.id.tv_celereback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initData();
        findView();
    }

    private void findView(){
        listView = findViewById(R.id.lv_celerecall);
        recallAdapter = new FriendRecallAdapter(this,list,R.layout.friend_recall_list_item);
        listView.setAdapter(recallAdapter);
    }

    private void initData(){
        FriendRecall recall = new FriendRecall();
        recall.title = "伟大领袖";
        recall.time = "1997-04-04 21:25:19";
        recall.description = "是您提出一国两制重要决策，使得两岸问题得到重大改善，你做出一些列有益于国家人民的决定，促进了中国的发展。";
        list.add(recall);
    }
}
