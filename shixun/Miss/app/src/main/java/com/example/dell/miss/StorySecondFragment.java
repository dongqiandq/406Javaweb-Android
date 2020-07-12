package com.example.dell.miss;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StorySecondFragment extends Fragment {
    private List<Article> list = new ArrayList<>();
    private ListView listView;
    private StoryAdapter2 adapter;

    private Integer tag=2;
    private View v;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_story_second,container,false);
        this.v=view;

        initData();
//        findViews(view);
        return view;
    }
    private void findViews(View view){
        listView = view.findViewById(R.id.lv_view);
        adapter = new StoryAdapter2 (list,getContext(),R.layout.activity_story_item2);
        listView.setAdapter(adapter);

    }
    private void initData(){
        Story2Async story2Async=new Story2Async();
        story2Async.execute(Constant.URL+"article/list",tag);
    }

    /**
     * 查询所有的生命之光--故事
     */
    private class Story2Async extends AsyncTask{
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
                Toast.makeText(StorySecondFragment.super.getActivity(),"网络连接不可用，请稍后再试",Toast.LENGTH_SHORT).show();
            }else if (o.toString().equals("")){
                Toast.makeText(StorySecondFragment.super.getActivity(),"暂无内容",Toast.LENGTH_SHORT).show();
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
                    article.setTime(new Date(array2[4]));
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
                findViews(v);
            }
        }
    }
}
