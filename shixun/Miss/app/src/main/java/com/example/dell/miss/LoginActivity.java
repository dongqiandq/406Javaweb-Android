package com.example.dell.miss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private Button register;
    private LinearLayout common;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        login = findViewById(R.id.btn_denglu);
        register = findViewById(R.id.btn_zhuce);
        common = findViewById(R.id.tv_common);
        setListeners();//设置事件监听器
    }

    public void setListeners(){
        MyClickListener myClickListener = new MyClickListener();
        login.setOnClickListener(myClickListener);
        register.setOnClickListener(myClickListener);
        common.setOnClickListener(myClickListener);
    }

    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_denglu:
                    Intent intent = new Intent(LoginActivity.this, ConcreteLoginActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("type",1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.btn_zhuce:
                    Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("type",2);
                    intent1.putExtras(bundle1);
                    startActivity(intent1);
                    finish();
                    break;
                case R.id.tv_common:
                    /*Intent intent2 = new Intent(LoginActivity.this,MainActivity.class);
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("type",2);
                    intent2.putExtras(bundle2);
                    startActivity(intent2);*/
                    finish();
                    break;
            }
        }
    }
}
