package cn.hebtu.software.myapplication;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mob.MobSDK;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends AppCompatActivity {

    private EditText phone;
    private Util util = new Util(this);
    private EditText pwd;
    private EditText surePwd;
    private EditText code;
    private Button getCode;
    private Button register;
    private boolean flag = true;
    private int i=30;
    private CheckBox box;
    private EventHandler eh;
    private Handler mainHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == Constant.RESEND_VERIFICATION_CODE_FLAG) {
                getCode.setText("重新发送(" + msg.arg1 + ")");
            } else if (msg.what == Constant.GET_VERIFICATION_CODE_FLAG){
                getCode.setText("获取验证码");
                getCode.setClickable(true);
                i = 30;
            } else {
                super.handleMessage(msg);
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                //回调完成
                if (result == SMSSDK.RESULT_COMPLETE) {
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
                        RegisterUser task = new RegisterUser();
                        task.execute(Constant.URL+"RegisterUser");
                    }
                } else {//其他出错情况
                    util.mainHandlerSetToGetCodeError(flag,getCode,RegisterActivity.this,phone);
                }
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MobSDK.init(RegisterActivity.this, "2d3d674b81b0b", "19918097dff1d3ed4b49407a965658f0");
        ActivityCompat.requestPermissions(RegisterActivity.this,new String[]{Manifest.permission.READ_CONTACTS},1);
        SMSSDK.setAskPermisionOnReadContact(true);
        eh=new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg=new Message();//创建了一个对象
                msg.arg1=event;
                msg.arg2=result;
                msg.obj=data;
                mainHandler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
        findView();
        setListener();
    }

    public void findView(){
        phone = findViewById(R.id.et_register_telephone);
        pwd = findViewById(R.id.et_register_pwd);
        surePwd = findViewById(R.id.et_register_surePwd);
        code = findViewById(R.id.et_register_inputcode);
        getCode = findViewById(R.id.btn_register_getcode);
        register = findViewById(R.id.btn_register);
        box = findViewById(R.id.agreeProtocol);
    }

    public void setListener(){
        //注册设置点击事件
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phone.getText().length()==0){
                    Toast.makeText(RegisterActivity.this,"请填写电话号码",Toast.LENGTH_SHORT).show();
                }else if(pwd.getText().length()==0){
                    Toast.makeText(RegisterActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }else if(surePwd.getText().length()==0){
                    Toast.makeText(RegisterActivity.this,"请确认密码",Toast.LENGTH_SHORT).show();
                }else if(!pwd.getText().toString().equals(surePwd.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                }else if(!box.isChecked()){
                    Toast.makeText(RegisterActivity.this,"请先阅读协议",Toast.LENGTH_SHORT).show();
                }else{
                    if(util.judCord(RegisterActivity.this,code,phone)){//判断验证码
                        String phoneNum = phone.getText().toString();
                        String codeNum = code.getText().toString();
                        SMSSDK.submitVerificationCode("86", phoneNum, codeNum);
                    }
                }

            }
        });


        //获取验证码点击事件
        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 通过规则判断手机号
                if(util.judPhone(RegisterActivity.this,phone))//去掉左右空格获取字符串，是正确的手机号
                {
                    String phoneNum = phone.getText().toString();
                    // 2. 通过sdk发送短信验证
                    SMSSDK.getVerificationCode("86", phoneNum);
                    // 3. 把按钮变成不可点击，并且显示倒计时（正在获取）
                    getCode.setClickable(false);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            util.listenerToGetCode(mainHandler,i);
                        }
                    }).start();
                }
            }
        });

    }

    private class RegisterUser extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            String judge = "";
            try {
                HttpURLConnection connection = util.getURLConnection((String)objects[0]);
                OutputStream os = connection.getOutputStream();
                String telePhone = phone.getText().toString();
                String password = pwd.getText().toString();
                JSONObject object = new JSONObject();
                object.put("tel",telePhone);
                object.put("pwd",password);
                os.write(object.toString().getBytes());
                InputStream is = connection.getInputStream();
                judge = util.readInputStreamToString(is);
                util.closeIO(is,os);

            }catch (Exception e){
                e.printStackTrace();
            }
            return judge;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if(o.equals("no")){
                Toast.makeText(RegisterActivity.this,"注册失败！",Toast.LENGTH_SHORT).show();
            }else{
                Log.e("user",o.toString());
                Intent intent = new Intent(RegisterActivity.this,TestOkActivity.class);
                startActivity(intent);
            }
        }
    }

}
