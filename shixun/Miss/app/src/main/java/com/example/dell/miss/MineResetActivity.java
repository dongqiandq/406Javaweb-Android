package com.example.dell.miss;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import static com.example.dell.miss.ConcreteLoginActivity.LoginUser;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

//重置密码
public class MineResetActivity extends AppCompatActivity {

    private EditText pwd1;
    private EditText pwd2;
    private Button submit;
    private ImageView eye;
    private ImageView eyeOK;

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_reset);

        pwd1 = findViewById(R.id.et_reset_word);
        pwd2 = findViewById(R.id.et_reset_rewordOK);
        submit = findViewById(R.id.btn_reset_submit);

        imageView = findViewById(R.id.img_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputPwd = pwd1.getText().toString();
                String surePwd = pwd2.getText().toString();
                if(inputPwd.length()==0){
                    Toast.makeText(MineResetActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }else if(surePwd.length()==0){
                    Toast.makeText(MineResetActivity.this,"请输入确认密码",Toast.LENGTH_SHORT).show();
                }else if(!inputPwd.equals(surePwd)){
                    Toast.makeText(MineResetActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                }else if (inputPwd.contains(",")){
                    Toast.makeText(MineResetActivity.this,"密码不能包含特殊符号 ， ",Toast.LENGTH_SHORT).show();
                }else{
                    ResetUserPwd resetUserPwd=new ResetUserPwd();
                    resetUserPwd.execute(Constant.URL+"user/resetpwd",LoginUser.getId(),inputPwd);
                }
            }
        });

        eye = findViewById(R.id.img_eye);
        eyeOK = findViewById(R.id.img_eyeOK);

        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPasswordEye(eye,pwd1);
            }
        });
        eyeOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPasswordEye(eyeOK,pwd2);
            }
        });
    }

    private class ResetUserPwd extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection = Util.getURLConnection((String)objects[0]);
                OutputStream os = connection.getOutputStream();
                String message=objects[1]+","+objects[2];
                os.write(message.getBytes());

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
            if (null==o){
                Toast.makeText(MineResetActivity.this,"网络连接不可用，请稍后再试",Toast.LENGTH_SHORT).show();
            }else if (o.toString().equals("")){
                Toast.makeText(MineResetActivity.this,"修改失败，请稍后再试",Toast.LENGTH_SHORT).show();
            }else {
                LoginUser.setPassword(pwd1.getText().toString());
                showDialog();
//                Toast.makeText(MineResetActivity.this,"密码修改成功",Toast.LENGTH_SHORT).show();
//                Intent intent1 = new Intent(MineResetActivity.this,ConcreteLoginActivity.class);
//                startActivity(intent1);
//                finish();
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

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage("您的密码已重置，请重新登录！");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MineResetActivity.this,ConcreteLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

