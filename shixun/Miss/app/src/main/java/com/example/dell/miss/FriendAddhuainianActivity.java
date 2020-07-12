package com.example.dell.miss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FriendAddhuainianActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_addhuainian);

        TextView back = findViewById(R.id.tv_huaiback);
        final EditText recall = findViewById(R.id.et_addrecall);
        Button queren = findViewById(R.id.btn_ok);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final Date date = new Date();
        final SimpleDateFormat alldate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取日期时间

        queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FriendAddhuainianActivity.this,FriendQinyouListDetail.class);
                intent1.putExtra("recall",recall.getText().toString());
                intent1.putExtra("time",alldate.format(date).toString());
                startActivity(intent1);
                finish();
            }
        });
    }
}
