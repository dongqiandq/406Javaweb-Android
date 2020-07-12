package com.example.dell.miss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FriendAddrecallActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_addrecall);

        TextView back = findViewById(R.id.tv_recallback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //获取标题
        EditText etTitle = findViewById(R.id.et_recall_title);

        //获取内容
        EditText etNeirong = findViewById(R.id.et_recall_neirong);

        //为按钮添加点击事件
        Button btnOk = findViewById(R.id.btn_recall_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
