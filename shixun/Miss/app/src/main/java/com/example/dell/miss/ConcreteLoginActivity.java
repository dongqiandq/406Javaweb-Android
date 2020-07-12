package com.example.dell.miss;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Date;

public class ConcreteLoginActivity extends AppCompatActivity {
    private TextView textView;//忘记密码
    private TextView textView1;//验证码
    private Button login;
    private ImageView imageView;
    private ImageView eye;
    private EditText phone;
    private EditText editText;
    private EditText sure;
    public static User LoginUser;

    private MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        myApp = (MyApp)getApplication();

        textView = findViewById(R.id.tv_forgetWord);
        textView1 = findViewById(R.id.tv_yanzhengma);
        login = findViewById(R.id.btn_login);
        eye = findViewById(R.id.img_eye);
        phone = findViewById(R.id.et_phone);
        editText = findViewById(R.id.et_word);
        imageView = findViewById(R.id.img_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConcreteLoginActivity.this.finish();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConcreteLoginActivity.this,WordForgetActivity.class);
                startActivity(intent);
                ConcreteLoginActivity.this.finish();
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConcreteLoginActivity.this,WordYanZhengActivity.class);
                startActivity(intent);
                ConcreteLoginActivity.this.finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                if (phone.getText().toString().equals("")) {
                    Toast.makeText(ConcreteLoginActivity.this, "请填写电话号码", Toast.LENGTH_SHORT).show();
                }else if(editText.getText().toString().equals("")){
                    Toast.makeText(ConcreteLoginActivity.this, "请填写密码", Toast.LENGTH_SHORT).show();
                }else if (editText.getText().toString().contains(",")){
                    Toast.makeText(ConcreteLoginActivity.this,"密码不能包含特殊字符 ， ",Toast.LENGTH_SHORT).show();
                }else {
//                    myApp.setMyLable(1);
                    LoginAsync loginAsync=new LoginAsync();
                    loginAsync.execute(Constant.URL+"user/normallogin",phone.getText().toString(),editText.getText().toString());

                }
            }
        });

        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPasswordEye(eye,editText);
            }
        });
    }

    public class LoginAsync extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection=Util.getURLConnection((String) objects[0]);
                OutputStream os=connection.getOutputStream();
                String message=objects[1]+","+objects[2];
                os.write(message.getBytes());

                InputStream is=connection.getInputStream();
                String content=Util.readInputStreamToString(is);
                Util.closeIO(is,os);
                return content;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (null==o){
                Toast.makeText(ConcreteLoginActivity.this,"网络连接不可用，请稍后再试",Toast.LENGTH_SHORT).show();
            }else if (o.toString().equals("no")){
                Toast.makeText(ConcreteLoginActivity.this,"手机号或密码错误，请重新登录",Toast.LENGTH_SHORT).show();
            }else {
                LoginUser=new User();
                String o1=o.toString().substring(0,o.toString().length());
                String[] array=o1.split(",");
                LoginUser.setId(Integer.parseInt(array[0]));
                LoginUser.setTelephone(array[1]);
                LoginUser.setPassword(array[2]);
                LoginUser.setName(array[3]);
                LoginUser.setSex(Integer.parseInt(array[4]));
                LoginUser.setArea(array[5]);
                LoginUser.setIntroduction(array[6]);
                LoginUser.setHeader(array[7]);
                Intent intent=new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        }
    }

    //设置密码可见和不可见
    private void setPasswordEye(ImageView imageView,EditText editText) {
        if (InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD == editText.getInputType()) {
            //如果可见就设置为不可见
            editText.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            //修改眼睛图片
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.eye_close));
        } else {
            //如果不可见就设置为可见
            editText.setInputType(EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            //修改图片
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.eye_open));
        }
        //执行上面的代码后光标会处于输入框的最前方,所以把光标位置挪到文字的最后面
        editText.setSelection(editText.getText().toString().length());
    }
}

