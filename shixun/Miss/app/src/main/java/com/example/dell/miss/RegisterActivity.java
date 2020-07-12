package com.example.dell.miss;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class RegisterActivity extends AppCompatActivity {

    private EditText phone;
    private EditText pwd;
    private EditText surePwd;
    private EditText code;
    private String userPhone,psw,pswAgain;

    private Button getCode;
    private Button register;
    private ImageView eye;
    private ImageView eyeOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        ImageView imageView = findViewById(R.id.img_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

        eye = findViewById(R.id.img_eye);
        eyeOK = findViewById(R.id.img_eyeOK);
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPasswordEye(eye, pwd);
            }
        });
        eyeOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPasswordEye(eyeOK, surePwd);
            }
        });

        findView();
        setListener();
    }

    public void findView() {
        phone = findViewById(R.id.et_register_phone);
        pwd = findViewById(R.id.et_register_word);
        surePwd = findViewById(R.id.et_register_wordOK);
        code = findViewById(R.id.et_register_yanzheng);
        getCode = findViewById(R.id.btn_register_getcode);
        register = findViewById(R.id.btn_register);

    }

    public void setListener() {
        //注册设置点击事件

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phone.getText().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "请填写电话号码", Toast.LENGTH_SHORT).show();
                } else if (pwd.getText().toString().contains(",")){
                    Toast.makeText(RegisterActivity.this,"密码不能包含特殊字符 ， ",Toast.LENGTH_SHORT).show();;
                }else if (pwd.getText().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (surePwd.getText().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "请确认密码", Toast.LENGTH_SHORT).show();
                } else if(!pwd.getText().toString().equals(surePwd.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                }else if (!code.getText().toString().equals("1111")){
                    Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                }else{
                    RegisterUserAsync registerUserAsync=new RegisterUserAsync();
                    registerUserAsync.execute(Constant.URL+"",phone.getText().toString(),pwd.getText().toString());
                    //                    saveRegisterInfo(userPhone, psw);
//                    //注册成功后把账号传递到LoginActivity.java中
//                    // 返回值到loginActivity显示
//                    Intent data = new Intent();
//                    data.putExtra("userPhone", userPhone);
//                    setResult(RESULT_OK, data);
//                    //RESULT_OK为Activity系统常量，状态码为-1，
//                    // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
//                    RegisterActivity.this.finish();
                }
            }
        });


//        //获取验证码点击事件
//        getCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 1. 通过规则判断手机号
//                if (util.judPhone(RegisterActivity.this, phone))//去掉左右空格获取字符串，是正确的手机号
//                {
//                    String phoneNum = phone.getText().toString();
//                    // 2. 通过sdk发送短信验证
////                    SMSSDK.getVerificationCode("86", phoneNum);
//                    // 3. 把按钮变成不可点击，并且显示倒计时（正在获取）
//                    getCode.setClickable(false);
////                    new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////                            util.listenerToGetCode(mainHandler, i);
////                        }
////                    }).start();
//                }
//            }
//        });

    }

    private class RegisterUserAsync extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection = Util.getURLConnection((String) objects[0]);
                OutputStream os = connection.getOutputStream();
                String message=objects[1]+","+objects[2];
                os.write(message.getBytes());

                InputStream is = connection.getInputStream();
                String content=Util.readInputStreamToString(is);
                Util.closeIO(is, os);
                return content;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (null==o || "".equals(o.toString())) {
                Toast.makeText(RegisterActivity.this,"网络连接不可用，请稍后再试",Toast.LENGTH_SHORT).show();
            }else {
                showDialog();
            }
        }
    }

    //设置密码可见和不可见
    private void setPasswordEye(ImageView imageView, EditText editText) {

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

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage("注册成功，请登录！");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(RegisterActivity.this,ConcreteLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 获取控件中的字符串
     */
    private void getEditString(){
        userPhone=phone.getText().toString().trim();
        psw=pwd.getText().toString().trim();
        pswAgain=surePwd.getText().toString().trim();
    }
    /**
     * 从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
     */
    private boolean isExistUserName(String userName){
        boolean has_userName=false;
        //mode_private SharedPreferences sp = getSharedPreferences( );
        // "loginInfo", MODE_PRIVATE
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取密码
        String spPsw=sp.getString(userName, "");//传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if(!TextUtils.isEmpty(spPsw)) {
            has_userName=true;
        }
        return has_userName;
    }
    /**
     * 保存账号和密码到SharedPreferences中SharedPreferences
     */
    private void saveRegisterInfo(String userName,String psw){
        String md5Psw = MD5Utils.md5(psw);//把密码用MD5加密
        //loginInfo表示文件名, mode_private SharedPreferences sp = getSharedPreferences( );
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取编辑器， SharedPreferences.Editor  editor -> sp.edit();
        SharedPreferences.Editor editor=sp.edit();
        //以用户名为key，密码为value保存在SharedPreferences中
        //key,value,如键值对，editor.putString(用户名，密码）;
        editor.putString(userName, md5Psw);
        //提交修改 editor.commit();
        editor.commit();
    }
}

