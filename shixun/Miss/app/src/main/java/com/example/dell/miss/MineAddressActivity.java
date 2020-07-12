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

public class MineAddressActivity extends AppCompatActivity{
    private ImageView imageView;

    private Button btnSubmit;
    private EditText etAddress;

    private MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_layout);

        myApp = (MyApp)getApplication();

        etAddress = findViewById(R.id.et_address);
        etAddress.setText(LoginUser.getArea());

        btnSubmit = findViewById(R.id.btn_submit);

        imageView = findViewById(R.id.img_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(etAddress.getText().toString())){
                    Toast.makeText(MineAddressActivity.this,"地址不能为空",Toast.LENGTH_SHORT).show();
                }else if (etAddress.getText().toString().contains(",")){
                    Toast.makeText(MineAddressActivity.this,"地址中不能包含特殊字符 ， ",Toast.LENGTH_SHORT).show();
                }else {
                    myApp.setAddressEdit(1);

                    UpdateAddressAsync updateAddressAsync=new UpdateAddressAsync();
                    updateAddressAsync.execute(Constant.URL+"user/address",LoginUser.getId(),etAddress.getText().toString());
                }
            }
        });
    }
    /**
     * 修改用户地址
     */
    public class UpdateAddressAsync extends AsyncTask {

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
                Toast.makeText(MineAddressActivity.this,"网络连接不可用，请稍后再试",Toast.LENGTH_SHORT).show();
            }else if ("".equals(o.toString())){
                Toast.makeText(MineAddressActivity.this,"修改失败，请稍后再试",Toast.LENGTH_SHORT).show();
            }else {
                LoginUser.setArea(etAddress.getText().toString());
                Intent intent = new Intent(MineAddressActivity.this,PersonEditActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
