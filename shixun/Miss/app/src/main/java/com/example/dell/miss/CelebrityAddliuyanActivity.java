package com.example.dell.miss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CelebrityAddliuyanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrity_addliuyan);

        TextView back = findViewById(R.id.tv_liuback);
        final EditText liuyan = findViewById(R.id.et_addLiuyan);
        Button ok = findViewById(R.id.btn_ok);
        final Date date = new Date();
        final SimpleDateFormat alldate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取日期时间


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
