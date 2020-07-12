package com.example.dell.miss;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class GushiFragment extends Fragment {
    private ListView listView;
    private TouchAdapter touchAdapter = null;
    private List<Article> list = new ArrayList<>();

    private View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gushi_layout,container,false);
        this.v=view;

        StoryTrackAsync storyTrackAsync=new StoryTrackAsync();
        storyTrackAsync.execute(Constant.URL+"article/strack",LoginUser.getId());

        return view;
    }

    private void findViews(View view){
        listView = view.findViewById(R.id.lv_zgushi);
        touchAdapter = new TouchAdapter(list,getContext(),R.layout.touch_item);
        listView.setAdapter(touchAdapter);
    }
    /**
     * 查看某用户的足迹-->故事
     */
    public class StoryTrackAsync extends AsyncTask{

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
            if (null==o){
                Toast.makeText(GushiFragment.super.getActivity(),"网络连接不可用，请稍后再试",Toast.LENGTH_SHORT).show();
            }else if ("".equals(o.toString())){
                Toast.makeText(GushiFragment.super.getActivity(),"暂无足迹",Toast.LENGTH_SHORT).show();
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
                findViews(v);
            }
        }
    }
}
