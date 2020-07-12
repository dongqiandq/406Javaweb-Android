package com.example.dell.miss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import static com.example.dell.miss.ConcreteLoginActivity.LoginUser;

public class SetActivity extends AppCompatActivity {

    private ImageView imageView;
    private RelativeLayout zhanghao;
    private RelativeLayout rlabout;
    private RelativeLayout exitdeng;

    private MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        myApp = (MyApp)getApplication();

//        LoginUser=util.getUserInfo();

        imageView = findViewById(R.id.img_return);
        zhanghao = findViewById(R.id.rl_zhanghao);
        rlabout = findViewById(R.id.rl_about);
        exitdeng = findViewById(R.id.rl_exit);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetActivity.this.finish();
            }
        });
        zhanghao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this,MineSafeSetActivity.class);
                startActivity(intent);
            }
        });
        rlabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this,MineAboutActivity.class);
                startActivity(intent);
            }
        });
        exitdeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myApp.setMyLable(0);
//                util.clearUserInfo();
                LoginUser=null;
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
