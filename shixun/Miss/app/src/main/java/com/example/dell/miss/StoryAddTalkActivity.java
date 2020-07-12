package com.example.dell.miss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.example.dell.miss.ConcreteLoginActivity.LoginUser;

public class StoryAddTalkActivity extends AppCompatActivity {
    private EditText recall;
    private Button queren;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_addtalk);

        TextView back = findViewById(R.id.tv_huaiback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recall = findViewById(R.id.et_addrecall);
        queren = findViewById(R.id.btn_ok);

        /*final Date date = new Date();
        final SimpleDateFormat alldate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取日期时间*/

        queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recall.getText().toString().contains("``")){
                    Toast.makeText(StoryAddTalkActivity.this,"评论中不能包含特殊字符 ``  ",Toast.LENGTH_SHORT).show();
                }else {
                    AddOneComment addOneComment=new AddOneComment();
                    addOneComment.execute(Constant.URL+"article/icomment",getIntent().getIntExtra("articleId",0),recall.getText().toString());
                }
            }
        });
    }

    /**
     * 在数据库中添加一条评论
     */
    public class AddOneComment extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection=Util.getURLConnection((String)objects[0]);
                OutputStream os=connection.getOutputStream();
                String message=LoginUser.getId()+"`"+objects[1]+"`"+objects[2];
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
                Toast.makeText(StoryAddTalkActivity.this,"网络连接不可用，请稍后再试",Toast.LENGTH_SHORT).show();
            }else if ("".equals(o.toString())){
                Toast.makeText(StoryAddTalkActivity.this,"添加失败，请稍后再试",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(StoryAddTalkActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
