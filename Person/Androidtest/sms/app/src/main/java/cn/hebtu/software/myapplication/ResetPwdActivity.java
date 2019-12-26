package cn.hebtu.software.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class ResetPwdActivity extends AppCompatActivity {
    private EditText pwd1;
    private EditText pwd2;
    private Button submmit;
    private Util util = new Util(this);
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
        pwd1 = findViewById(R.id.et_resetPwd_inputPwd);
        pwd2 = findViewById(R.id.et_resetPwd_surePwd);
        submmit = findViewById(R.id.btn_resetPwdSubmmit);
        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("tel");
        submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputPwd = pwd1.getText().toString();
                String surePwd = pwd2.getText().toString();
                if(inputPwd.length()==0){
                    Toast.makeText(ResetPwdActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }else if(surePwd.length()==0){
                    Toast.makeText(ResetPwdActivity.this,"请确认密码",Toast.LENGTH_SHORT).show();
                }else if(!inputPwd.equals(surePwd)){
                    Toast.makeText(ResetPwdActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                }else{
                    ResetUserPwd task = new ResetUserPwd();
                    task.execute(Constant.URL+"ResetUserPwd");
                }
            }
        });
    }

    private class ResetUserPwd extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] objects) {
            String result = "";
            try {
                HttpURLConnection connection = util.getURLConnection((String)objects[0]);
                OutputStream os = connection.getOutputStream();
                JSONObject object = new JSONObject();
                object.put("phoneNumber",phoneNumber);
                object.put("newPwd",pwd1.getText().toString());
                os.write(object.toString().getBytes());
                InputStream is = connection.getInputStream();
                result = util.readInputStreamToString(is);
                util.closeIO(is,os);

            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if(o.equals("ok")){
                startActivity(new Intent(ResetPwdActivity.this,TestOkActivity.class));
            }else{
                Toast.makeText(ResetPwdActivity.this,"修改失败，请重试",Toast.LENGTH_SHORT);
            }
        }
    }

}
