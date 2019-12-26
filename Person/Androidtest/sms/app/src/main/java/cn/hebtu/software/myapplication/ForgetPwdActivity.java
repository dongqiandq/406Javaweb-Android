package cn.hebtu.software.myapplication;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mob.MobSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class ForgetPwdActivity extends AppCompatActivity {
    private Boolean flag = true;
    private Util util = new Util(this);
    private EditText phone;
    private int i = 30;
    private User user;
    private EditText code;
    private Button getCode;
    private Button forgetPwd;
    private EventHandler eh;
    private Handler mainHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == Constant.RESEND_VERIFICATION_CODE_FLAG){
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
                        Intent intent = new Intent(ForgetPwdActivity.this,ResetPwdActivity.class);
                        intent.putExtra("tel",phone.getText().toString());
                        startActivity(intent);
                    }
                } else {//其他出错情况
                    util.mainHandlerSetToGetCodeError(flag,getCode,ForgetPwdActivity.this,phone);
                }
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        findView();
        MobSDK.init(this, "2d3d674b81b0b", "19918097dff1d3ed4b49407a965658f0");
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
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

        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 通过规则判断手机号
                if(util.judPhone(ForgetPwdActivity.this,phone))//去掉左右空格获取字符串，是正确的手机号
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

        forgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(util.judCord(ForgetPwdActivity.this,code,phone)){//判断验证码
                    String phoneNum = phone.getText().toString();
                    String codeNum = code.getText().toString();
                    SMSSDK.submitVerificationCode("86", phoneNum, codeNum);
                }
            }
        });

    }

    public void findView(){
        //**快速登录**//
        phone = findViewById(R.id.et_forgetPwd_telephone);
        code = findViewById(R.id.et_forgetPwd_inputcode);
        getCode = findViewById(R.id.btn_forgetPwd_getcode);
        forgetPwd = findViewById(R.id.btn_forgetPwd);
        //**快速登录**//
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
}
