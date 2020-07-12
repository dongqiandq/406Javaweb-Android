package com.example.dell.miss;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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

public class MineWordSetActivity extends AppCompatActivity {

    private ImageView imgReturn;
    private Button btnFix;
    private EditText etOldPwd;
    private EditText newPwd;
    private EditText makeSurePwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_word_set);

        //*************修改用户密码******************//
        btnFix=findViewById(R.id.btn_fixPwd);
        etOldPwd = findViewById(R.id.et_oldPwd);
        newPwd = findViewById(R.id.et_newPwd);
        makeSurePwd = findViewById(R.id.et_makeSurePwd);

        imgReturn= findViewById(R.id.img_return);
        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //修改用户密码点击事件
        btnFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPwd=etOldPwd.getText().toString();
                String newPassword = newPwd.getText().toString();
                String makeSurePassword = makeSurePwd.getText().toString();
                if (!oldPwd.equals(LoginUser.getPassword())){
                    Toast.makeText(MineWordSetActivity.this,"原密码输入错误",Toast.LENGTH_SHORT).show();
                }else if(newPassword.equals("")){
                    Toast.makeText(MineWordSetActivity.this,"请输入修改密码",Toast.LENGTH_SHORT).show();
                }else if(makeSurePassword.equals("")){
                    Toast.makeText(MineWordSetActivity.this,"请输入确认密码",Toast.LENGTH_SHORT).show();
                }else if(!newPassword.equals(makeSurePassword)){
                    Toast.makeText(MineWordSetActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                }else if (newPassword.contains(",")){
                    Toast.makeText(MineWordSetActivity.this,"密码不能包含特殊字符 ， ",Toast.LENGTH_SHORT).show();
                }else{
                    FixUserPwdAsync fixUserPwdAsync=new FixUserPwdAsync();
                    fixUserPwdAsync.execute(Constant.URL+"user/resetpwd",LoginUser.getId(),newPassword);
                }
            }
        });
    }

    //用户修改密码的异步任务
    public class FixUserPwdAsync extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection =Util.getURLConnection((String)objects[0]);
                OutputStream os = connection.getOutputStream();
                String message=objects[1]+","+objects[2];
                os.write(message.getBytes());

                InputStream is = connection.getInputStream();
                String content = Util.readInputStreamToString(is);
                Util.closeIO(is,os);
                return content;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Object o){
            super.onPostExecute(o);
            if (null==o){
                Toast.makeText(MineWordSetActivity.this,"网络连接不可用，请稍后再试",Toast.LENGTH_SHORT).show();
            }else if (o.toString().equals("")){
                Toast.makeText(MineWordSetActivity.this,"修改失败，请稍后再试",Toast.LENGTH_SHORT).show();
            }else {
                LoginUser.setPassword(newPwd.getText().toString());
                showDialog();
            }
        }
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage("您的密码已重置，请重新登录！");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MineWordSetActivity.this,ConcreteLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
