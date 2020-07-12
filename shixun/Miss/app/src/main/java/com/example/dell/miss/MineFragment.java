package com.example.dell.miss;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import static android.app.Activity.RESULT_OK;
import static com.example.dell.miss.ConcreteLoginActivity.LoginUser;

public class MineFragment extends Fragment {
    private ImageView imgHead;
    private TextView tvClick;

    private ImageView imgMessage;//消息提示
    private RelativeLayout personmes;//登录
    private RelativeLayout gandong;//感动
    private RelativeLayout zuji;//足迹
    private RelativeLayout zhuiyi;//追忆
    private RelativeLayout hualan;//花篮
    private RelativeLayout geren;
    private RelativeLayout set;//设置
    private RelativeLayout help;//帮助与反馈

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mine_layout,container,false);

        imgHead=view.findViewById(R.id.iv_photo);
        if (null!=LoginUser){
            Bitmap bitmap= BitmapFactory.decodeFile("data/user/0/com.example.dell.miss/files/a.png");
            if (null!=bitmap){
                imgHead.setImageBitmap(bitmap);
            }else {
                Util.getDBImage(MineFragment.super.getContext(),Constant.URL+"images/"+LoginUser.getHeader(),imgHead);
            }
        }
        tvClick=view.findViewById(R.id.tv_click);
        if (null!=LoginUser){
            tvClick.setText(LoginUser.getName());
        }

        imgMessage = view.findViewById(R.id.img_message);//修改为fragment后改为view.find////
        personmes = view.findViewById(R.id.rl_personmes);
        gandong = view.findViewById(R.id.rl_gandong);
        zuji = view.findViewById(R.id.rl_zuji);
        zhuiyi = view.findViewById(R.id.rl_zhuiyi);
        hualan = view.findViewById(R.id.rl_hualan);
        geren = view.findViewById(R.id.rl_geren);
        set = view.findViewById(R.id.rl_shezhi);
        help = view.findViewById(R.id.rl_help);

        setListeners();//设置事件监听器
        return view;
    }

    /**
     * 设置视图控件的事件监听器
     */
    public void setListeners(){
        MyClickListener myClickListener = new MyClickListener();
        imgMessage.setOnClickListener(myClickListener);
        personmes.setOnClickListener(myClickListener);
        gandong.setOnClickListener(myClickListener);
        zuji.setOnClickListener(myClickListener);
        zhuiyi.setOnClickListener(myClickListener);
        hualan.setOnClickListener(myClickListener);
        geren.setOnClickListener(myClickListener);
        set.setOnClickListener(myClickListener);
        help.setOnClickListener(myClickListener);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && null!=LoginUser){
            Bitmap bitmap=BitmapFactory.decodeFile("data/user/0/com.example.dell.miss/files/a.png");
            if (null!=bitmap){
                imgHead.setImageBitmap(bitmap);
            }else {
                Util.getDBImage(MineFragment.super.getContext(),Constant.URL+"images/"+LoginUser.getHeader(),imgHead);
            }
            tvClick.setText(LoginUser.getName());
        }
        if (requestCode==2 && resultCode==RESULT_OK){
            imgHead.setImageResource(R.drawable.unlogin);
            tvClick.setText("");
        }
        if (requestCode==3 && null!=LoginUser){
            tvClick.setText(LoginUser.getName());
            Bitmap bitmap=BitmapFactory.decodeFile("data/user/0/com.example.dell.miss/files/a.png");
            imgHead.setImageBitmap(bitmap);
        }
    }

    private class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.img_message:
                    if (null==LoginUser){
                        Intent intent = new Intent(getActivity(),LoginActivity.class);
                        startActivityForResult(intent,1);
                    }else {
                        Intent intent = new Intent(getActivity(),MessageActivity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.rl_personmes:
                    if (null==LoginUser){
                        Intent intent1 = new Intent(getActivity(),LoginActivity.class);
                        startActivityForResult(intent1,1);
                    }else {
                        Intent intent1 = new Intent(getActivity(),PersonEditActivity.class);
                        startActivityForResult(intent1,3);
                    }
                    break;
                case R.id.rl_gandong:
                    if (null==LoginUser){
                        Intent intent2 = new Intent(getActivity(),LoginActivity.class);
                        startActivityForResult(intent2,1);
                    }else {
                        MovingNumAsync movingNumAsync=new MovingNumAsync();
                        movingNumAsync.execute(Constant.URL+"article/smovnum",LoginUser.getId());
                    }
                    break;
                case R.id.rl_zuji:
                    if (null==LoginUser){
                        Intent intent3 = new Intent(getActivity(),LoginActivity.class);
                        startActivityForResult(intent3,1);
                    }else {
                        Intent intent3 = new Intent(getActivity(),FootActivity.class);
                        startActivity(intent3);
                    }
                    break;
                case R.id.rl_zhuiyi:
                    if (null==LoginUser){
                        Intent intent4 = new Intent(getActivity(),LoginActivity.class);
                        startActivityForResult(intent4,1);
                    }else {
                        Intent intent4 = new Intent(getActivity(),RecallActivity.class);
                        startActivity(intent4);
                    }
                    break;
                case R.id.rl_hualan:
                    if (null==LoginUser){
                        Intent intent5 = new Intent(getActivity(),LoginActivity.class);
                        startActivityForResult(intent5,1);
                    }else {
                        Intent intent5 = new Intent(getActivity(),FlowerActivity.class);
                        startActivity(intent5);
                    }
                    break;
                case R.id.rl_geren:
                    if (null==LoginUser){
                        Intent intent8 = new Intent(getActivity(),LoginActivity.class);
                        startActivityForResult(intent8,1);
                    }else {
                        Intent intent8 = new Intent(getActivity(),PersonEditActivity.class);
                        startActivityForResult(intent8,3);
                    }
                    break;
                case R.id.rl_shezhi:
                    if (null==LoginUser){
                        Intent intent6 = new Intent(getActivity(),LoginActivity.class);
                        startActivityForResult(intent6,1);
                    }else {
                        Intent intent6 = new Intent(getActivity(),SetActivity.class);
                        startActivityForResult(intent6,2);
                    }
                    break;
                case R.id.rl_help:
                    if (null==LoginUser){
                        Intent intent7 = new Intent(getActivity(),LoginActivity.class);
                        startActivityForResult(intent7,1);
                    }else {
                        Intent intent7 = new Intent(getActivity(),HelpActivity.class);
                        startActivity(intent7);
                    }
                    break;
            }
        }
    }

    /**
     * 查询某用户添加“感动”的条数
     */
    public class MovingNumAsync extends AsyncTask{

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
            if (null!=o && !"0".equals(o.toString())){
                Intent intent2 = new Intent(getActivity(),TouchActivity1.class);
                startActivity(intent2);
            }else {
                Intent intent2 = new Intent(getActivity(),TouchActivity.class);
                startActivity(intent2);
            }
        }
    }
}
