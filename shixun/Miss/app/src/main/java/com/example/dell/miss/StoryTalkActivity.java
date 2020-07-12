package com.example.dell.miss;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class StoryTalkActivity extends AppCompatActivity {
    private List<Comment> list = new ArrayList<>();
    private ListView listView;
    private StoryTalkAdapter storyTalkAdapter;

    private int articleId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_talk_detail);

        articleId=getIntent().getIntExtra("articleId",0);
        TextView back = findViewById(R.id.tv_alltalkback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initDate();
//        findView();

    }
    private void findView(){
        listView = findViewById(R.id.lv_alltalk);
        storyTalkAdapter = new StoryTalkAdapter(this,list,R.layout.activity_story_talk_detail_item);
        listView.setAdapter(storyTalkAdapter);
    }

    private void initDate(){
        CommentByAidAsync commentByAidAsync=new CommentByAidAsync();
        commentByAidAsync.execute(Constant.URL+"article/scomment",articleId);
    }

    /**
     * 查询某个文章的所有评论
     */
    public class CommentByAidAsync extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection=Util.getURLConnection((String) objects[0]);
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
            if (null==o){
                Toast.makeText(StoryTalkActivity.this,"网络连接不可用，请稍后再试",Toast.LENGTH_SHORT).show();
            }else if ("".equals(o.toString())){
                Toast.makeText(StoryTalkActivity.this,"加载失败，请稍后再试",Toast.LENGTH_SHORT).show();
            }else {
                String o1=o.toString().substring(0,o.toString().length()-1);
                String[] array1=o1.split("Comment");
                for (int i=1;i<array1.length;i++){
                    String[] array2=array1[i].split("``");
                    Comment comment=new Comment();
                    comment.setId(Integer.parseInt(array2[0]));
                    User user=new User();
                    String[] u=array2[1].split(",");
                    user.setName(u[3]);
                    user.setHeader(u[7]);
                    comment.setUser(user);
                    if (i==array1.length-1){
                        comment.setContent(array2[3]);
                    }else {
                        comment.setContent(array2[3].substring(0,array2[3].length()-2));
                    }
                    list.add(comment);
                }
                findView();
            }
        }
    }
}
