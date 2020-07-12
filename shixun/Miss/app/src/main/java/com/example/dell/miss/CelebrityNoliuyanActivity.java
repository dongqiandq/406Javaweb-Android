package com.example.dell.miss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CelebrityNoliuyanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrity_noliuyan);
        TextView back = findViewById(R.id.tv_allliuaynback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button addLiuyan = findViewById(R.id.btn_all_addLiuyan);
        addLiuyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CelebrityNoliuyanActivity.this,CelebrityAddliuyanActivity.class);
                startActivity(intent);
            }
        });
    }
}
