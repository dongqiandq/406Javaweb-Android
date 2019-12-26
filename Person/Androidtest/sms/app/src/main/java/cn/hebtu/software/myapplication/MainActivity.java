package cn.hebtu.software.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //*************修改用户密码******************//
    private TextView oldPwd;
    private EditText newPwd;
    private EditText makeSurePwd;
    private Button btn;
    private Util util = new Util(this);
    //*************修改用户密码******************//

    //*************添加用户的身高、体重、BIM*****************************//
    private EditText height;
    private EditText weight;
    private Button btn_BIM;
    private TextView BIM;
    //*************添加用户的身高、体重、BIM*****************************//

    //*************保存心率值*********************************************//
    private Button btn_saveHeart;
    //*************保存心率值*********************************************//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //*************修改用户密码******************//
        oldPwd = findViewById(R.id.et_oldPwd);
        newPwd = findViewById(R.id.et_newPwd);
        makeSurePwd = findViewById(R.id.et_makeSurePwd);
        btn = findViewById(R.id.btn_fixPwd);
        //*************修改用户密码******************//

        //*************添加用户的身高、体重、BIM*****************************//
        height = findViewById(R.id.et_userHeight);
        weight = findViewById(R.id.et_userWeight);
        btn_BIM = findViewById(R.id.btn_userBIM);
        BIM = findViewById(R.id.tv_userBIM);
        //*************添加用户的身高、体重、BIM*****************************//

        //*************保存心率值*********************************************//
        btn_saveHeart = findViewById(R.id.btn_saveHeart);
        //*************保存心率值*********************************************//

        //检测用户是否登录，若已登录，保存到数据库，提示保存成功；没有登陆，跳到登录界面
        btn_saveHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    SaveHeartRecord task2 = new SaveHeartRecord();
                    task2.execute(Constant.URL+"SaveHeartRecord");
            }
        });


        //添加用户的身高、体重、BIM
        btn_BIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判断用户是否登录，如果登录，则通过用户ID查找出身体指标ID,将该条数据更改
                SaveHeightAndWeight task1 = new SaveHeightAndWeight();
                task1.execute(Constant.URL+"SaveHeightAndWeight");

            }
        });

        //修改用户密码点击事件
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = newPwd.getText().toString();
                String makeSurePassword = makeSurePwd.getText().toString();
                if(newPassword.equals("") && newPassword==null){
                    Toast.makeText(MainActivity.this,"请输入修改密码！",Toast.LENGTH_SHORT).show();
                }else if(makeSurePassword.equals("") && makeSurePassword==null){
                    Toast.makeText(MainActivity.this,"请输入确认密码！",Toast.LENGTH_SHORT).show();
                }else if(!newPassword.equals(makeSurePassword)){
                    Toast.makeText(MainActivity.this,"两次输入的密码不一致！",Toast.LENGTH_SHORT).show();
                }else{
                    FixUserPwd task = new FixUserPwd();
                    task.execute(Constant.URL+"FixUserPwd",newPassword);
                }
            }
        });


    }

    //保存心率值的异步任务，判断是否登录
    public class SaveHeartRecord extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            String info = "";
            try {
                HttpURLConnection connection = util.getURLConnection((String)objects[0]);
                OutputStream os = connection.getOutputStream();
                HeartRecord record = new HeartRecord(1, 100,2, 100,
                100);
                os.write(new Gson().toJson(record).toString().getBytes());
                InputStream inputStream = connection.getInputStream();
                info = util.readInputStreamToString(inputStream);
                Log.e("info",info);
                util.closeIO(inputStream,os);
            }catch (Exception e){
                e.printStackTrace();
            }
            return info;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            switch ((String)o){
                case "nologin":
                    //跳到登录页面
                    Toast.makeText(MainActivity.this,"此处应实现登录的页面跳转",Toast.LENGTH_SHORT).show();
                    break;
                case "ok":
                    Toast.makeText(MainActivity.this,"保存成功！",Toast.LENGTH_SHORT).show();
                    break;
                case "no":
                    Toast.makeText(MainActivity.this,"保存失败！",Toast.LENGTH_SHORT).show();
            }
        }
    }

    //用户修改密码的异步任务
    public class FixUserPwd extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            String judge = "false";
            try {
                HttpURLConnection connection = util.getURLConnection((String)objects[0]);
                OutputStream os = connection.getOutputStream();
                JSONObject object = new JSONObject();
                object.put("userId",1);
                object.put("newPwd",(String)objects[1]);
                os.write(object.toString().getBytes());
                InputStream is = connection.getInputStream();
                judge = util.readInputStreamToString(is);
                Log.e("test",judge);
                util.closeIO(is,os);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return judge;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if(o.equals("ok")){
                Toast.makeText(MainActivity.this,"修改成功！",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this,"修改失败！",Toast.LENGTH_SHORT).show();
            }
        }
    }

    //用户存储身高体重数据的异步任务
    public class SaveHeightAndWeight extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            String judge = "";
            String str = "0.00";
            try {
                HttpURLConnection connection = util.getURLConnection((String)objects[0]);
                OutputStream os = connection.getOutputStream();
                String h = height.getText().toString();
                String w = weight.getText().toString();
                if(h.equals("")||h==null||w.equals("")||w==null){
                    Toast.makeText(MainActivity.this,"请填写数据！",Toast.LENGTH_SHORT).show();
                }else {
                    double hh = Double.parseDouble(h);
                    double ww = Double.parseDouble(w);
                    str = String.format("%.2f",  ww/(hh*hh));
                    JSONObject object = new JSONObject();
                    object.put("height",hh);
                    object.put("weight",ww);
                    object.put("bim",str);
                    os.write(object.toString().getBytes());
                }
                InputStream is = connection.getInputStream();
                judge = util.readInputStreamToString(is);
                Log.e("test",judge);
                util.closeIO(is,os);
            }catch(Exception e){
                e.printStackTrace();
            }
            return str;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            BIM.setText(o.toString());
        }
    }

}
