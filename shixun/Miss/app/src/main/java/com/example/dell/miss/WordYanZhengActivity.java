package com.example.dell.miss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import static com.example.dell.miss.ConcreteLoginActivity.LoginUser;

public class WordYanZhengActivity extends AppCompatActivity{

    private Boolean flag = true;
    private EditText phone;
    private int i = 30;
    private EditText code;
    private ImageView imageView;
    private Button fastLogin;
    private Button getCode;

    private MyApp myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yanzheng_layout);

        myApp = (MyApp)getApplication();

        imageView = findViewById(R.id.img_return);
        phone = findViewById(R.id.et_phone);
        code = findViewById(R.id.et_yanzheng);
        fastLogin = findViewById(R.id.btn_login);
        getCode = findViewById(R.id.btn_getcode);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WordYanZhengActivity.this.finish();
            }
        });

        fastLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myApp.setMyLable(1);
                if (phone.getText().length() == 0) {
                    Toast.makeText(WordYanZhengActivity.this, "请填写电话号码", Toast.LENGTH_SHORT).show();
                } else if (!code.getText().toString().equals("1111")){
                    Toast.makeText(WordYanZhengActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                }else {
                    FindUserByPhoneNumber findUserByPhoneNumber=new FindUserByPhoneNumber();
                    findUserByPhoneNumber.execute();
                    Intent intent = new Intent(WordYanZhengActivity.this, MainActivity.class);
                    startActivity(intent);
                    WordYanZhengActivity.this.finish();
                }
            }
        });
    }

    private class FindUserByPhoneNumber extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            String judge = "";
            try {
                HttpURLConnection connection = Util.getURLConnection((String)objects[0]);
                OutputStream os = connection.getOutputStream();
                os.write("".getBytes());

                InputStream is = connection.getInputStream();
                String content = Util.readInputStreamToString(is);
                Util.closeIO(is,os);
                return content;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (null==o || "".equals(o.toString())){
                Toast.makeText(WordYanZhengActivity.this,"网络连接失败，请稍后再试",Toast.LENGTH_SHORT).show();
            }else if(o.equals("no")){
                Toast.makeText(WordYanZhengActivity.this,"请先注册！",Toast.LENGTH_SHORT).show();
            }else{
//                flag = false;
                /**
                 * 将传过来的user信息存入LoginUser中
                 */

            }
        }

    }
}
