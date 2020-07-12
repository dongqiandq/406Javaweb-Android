package com.example.dell.miss;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.dell.miss.ConcreteLoginActivity.LoginUser;

public class TouchActivity1 extends AppCompatActivity {
    private ImageView imageView;
    private ListView listView;
    private TouchAdapter touchAdapter = null;
    private List<Article> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch1_layout);

        imageView = findViewById(R.id.img_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TouchActivity1.this.finish();
            }
        });

        listView = findViewById(R.id.lv_touch);

        MovingStoryAsync movingStoryAsync=new MovingStoryAsync();
        movingStoryAsync.execute(Constant.URL+"article/sminemov",LoginUser.getId());
    }

    public void findViews(){
        touchAdapter = new TouchAdapter(list,this,R.layout.touch_item);
        listView.setAdapter(touchAdapter);
    }

    /**
     * 查询某用户感动中的所有故事
     */
    public class MovingStoryAsync extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection=Util.getURLConnection((String)objects[0]);
                OutputStream os=connection.getOutputStream();
                os.write(objects[1].toString().getBytes());

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
            if (null==o || o.toString().equals("")){
                Toast.makeText(TouchActivity1.this,"网络连接不可用，请稍后再试",Toast.LENGTH_SHORT).show();
            }else {
                list.clear();
                String o1=o.toString().substring(0,o.toString().length()-1);
                String[] array1=o1.toString().split("Article");
                for (int i=1;i<array1.length;i++){
                    String[] array2=array1[i].split("`");
                    Article article=new Article();
                    article.setId(Integer.parseInt(array2[0]));
                    article.setTag(Integer.parseInt(array2[1]));
                    article.setPicture(array2[2]);
                    article.setTitle(array2[3]);
//                    article.setTime(new Date(array2[4]));
                    article.setContent(array2[5]);
                    article.setVisitors(Integer.parseInt(array2[6]));
                    article.setCandle(Integer.parseInt(array2[7]));
                    if (i==array1.length-1){
                        article.setCommentNum(Integer.parseInt(array2[8]));
                    }else {
                        article.setCommentNum(Integer.parseInt(array2[8].substring(0,array2[8].length()-2)));
                    }
                    list.add(article);
                }
                findViews();
            }
        }
    }
}
