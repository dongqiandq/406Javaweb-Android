package com.example.dell.miss;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.dell.miss.ConcreteLoginActivity.LoginUser;

public class StoryNoTalkActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_notalk);
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
                if (null==LoginUser){
                    Intent intent=new Intent(StoryNoTalkActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent1 = new Intent(StoryNoTalkActivity.this,StoryAddTalkActivity.class);
                    intent1.putExtra("articleId",getIntent().getIntExtra("articleId",0));
                    startActivity(intent1);
                    finish();
                }
            }
        });
    }
}
