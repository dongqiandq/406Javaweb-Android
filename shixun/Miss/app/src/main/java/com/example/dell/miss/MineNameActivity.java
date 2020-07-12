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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import static com.example.dell.miss.ConcreteLoginActivity.LoginUser;

public class MineNameActivity extends AppCompatActivity{

    private ImageView imageView;
    private Button button;
    private EditText nickName;
    private MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_layout);

        myApp = (MyApp)getApplication();

        imageView = findViewById(R.id.img_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nickName = findViewById(R.id.et_nickName);
        nickName.setText(LoginUser.getName());

        button = findViewById(R.id.btn_submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(nickName.getText().toString())){
                    Toast.makeText(MineNameActivity.this,"昵称不能为空",Toast.LENGTH_SHORT).show();
                }else if (nickName.getText().toString().contains(",")){
                    Toast.makeText(MineNameActivity.this,"昵称不能包含特殊符号 ， ",Toast.LENGTH_SHORT).show();
                }else {
                    myApp.setNameEdit(1);

                    UpdateNameAsync updateNameAsync=new UpdateNameAsync();
                    updateNameAsync.execute(Constant.URL+"user/name",LoginUser.getId(),nickName.getText().toString());
                }
            }
        });
    }
    /**
     * 修改用户昵称
     */
    public class UpdateNameAsync extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection= Util.getURLConnection((String)objects[0]);
                OutputStream os=connection.getOutputStream();
                String message=objects[1]+","+objects[2];
                os.write(message.getBytes());

                InputStream is=connection.getInputStream();
                String content=Util.readInputStreamToString(is);
                Util.closeIO(is,os);
                return content;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            if (null==o){
                Toast.makeText(MineNameActivity.this,"网络连接不可用，请稍后再试",Toast.LENGTH_SHORT).show();
            }else if("".equals(o.toString())){
                Toast.makeText(MineNameActivity.this,"修改失败，请稍后再试",Toast.LENGTH_SHORT).show();
            }else {
                LoginUser.setName(nickName.getText().toString());
                Intent intent = new Intent(MineNameActivity.this,PersonEditActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
