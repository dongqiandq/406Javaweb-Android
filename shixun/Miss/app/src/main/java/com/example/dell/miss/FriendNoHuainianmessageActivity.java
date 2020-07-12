package com.example.dell.miss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FriendNoHuainianmessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_nohuainian);

        TextView back = findViewById(R.id.btn_allhuaiback);
        Button addMessage = findViewById(R.id.btn_all_addmessage);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FriendNoHuainianmessageActivity.this,FriendAddhuainianActivity.class);
                startActivity(intent1);
            }
        });
    }
}
