package com.example.dell.miss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class FriendRecallItemDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_recall_item_detail);
        TextView back = findViewById(R.id.ttv_rdetailback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.tv_rdetailTitle);
        TextView time = findViewById(R.id.tv_rdetailTime);
        TextView description = findViewById(R.id.tv_rdetailDescription);
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        time.setText(intent.getStringExtra("time"));
        description.setText(intent.getStringExtra("rde"));
    }
}
