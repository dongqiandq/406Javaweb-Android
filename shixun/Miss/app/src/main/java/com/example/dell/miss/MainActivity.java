package com.example.dell.miss;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private MyApp myApp;

    private Map<String,MyTabSpec> map = new HashMap<>();
    private  String [] tabStrId = {"故事","名人馆","亲友","我的"};
    private Fragment curFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myApp = (MyApp)getApplication();

        initData();

        setListener();

        changeTab(tabStrId[0]);

        int id = getIntent().getIntExtra("id", 0);
        if (id == 1) {
            changeTab(tabStrId[1]);
        }else if (id==3){
            changeTab(tabStrId[3]);
        }else {
            changeTab(tabStrId[0]);
        }

    }


    @SuppressLint("ResourceAsColor")
    private void findView() {
        ImageView iv1 = findViewById(R.id.img_1);
        ImageView iv2 = findViewById(R.id.img_2);
        ImageView iv3 = findViewById(R.id.img_3);
        ImageView iv4 = findViewById(R.id.img_4);
        TextView tv1 = findViewById(R.id.tv_1);
        TextView tv2 = findViewById(R.id.tv_2);
        TextView tv3 = findViewById(R.id.tv_3);
        TextView tv4 = findViewById(R.id.tv_4);

        tv1.setTextColor(android.R.color.white);
        tv2.setTextColor(android.R.color.white);
        tv3.setTextColor(android.R.color.white);
        tv4.setTextColor(android.R.color.white);

        map.get(tabStrId[0]).setImageView(iv1);
        map.get(tabStrId[0]).setTextView(tv1);

        map.get(tabStrId[1]).setImageView(iv2);
        map.get(tabStrId[1]).setTextView(tv2);

        map.get(tabStrId[2]).setImageView(iv3);
        map.get(tabStrId[2]).setTextView(tv3);

        map.get(tabStrId[3]).setImageView(iv4);
        map.get(tabStrId[3]).setTextView(tv4);
    }

    private void setListener() {
        LinearLayout layout1 = findViewById(R.id.tab_spec_1);
        LinearLayout layout2 = findViewById(R.id.tab_spec_2);
        LinearLayout layout3 = findViewById(R.id.tab_spec_3);
        LinearLayout layout4 = findViewById(R.id.tab_spec_4);

        MyListener listener = new MyListener();
        layout1.setOnClickListener(listener);
        layout2.setOnClickListener(listener);
        layout3.setOnClickListener(listener);
        layout4.setOnClickListener(listener);

    }

    private void initData() {
        map.put(tabStrId[0],new MyTabSpec());
        map.put(tabStrId[1],new MyTabSpec());
        map.put(tabStrId[2],new MyTabSpec());
        map.put(tabStrId[3],new MyTabSpec());

        setFragment();
        /*if (myApp.getMyLable()==0){
            setFragment();
        }else {
            setFragment1();
        }*/

        findView();

        setImage();

    }


    //每个tab对应的界面
    private void setFragment() {
        map.get(tabStrId[0]).setFragment(new StoryFragment());
        map.get(tabStrId[1]).setFragment(new CelebrityFragment());

        map.get(tabStrId[2]).setFragment(new FriendFragment());
        map.get(tabStrId[3]).setFragment(new MineFragment());
//        map.get(tabStrId[4]).setFragment(new HeartFragment());
    }
    /*private void setFragment1() {
        map.get(tabStrId[0]).setFragment(new StoryFragment());
        map.get(tabStrId[1]).setFragment(new CelebrityFragment());

        map.get(tabStrId[2]).setFragment(new FriendFragment());
        map.get(tabStrId[3]).setFragment(new MineFragment1());
//        map.get(tabStrId[4]).setFragment(new HeartFragment());
    }*/

    //选中和未选中时的图标颜色变化
    private void setImage() {
        map.get(tabStrId[0]).setNormalImage(R.drawable.story);
        map.get(tabStrId[0]).setSelectImage(R.drawable.story1);
        map.get(tabStrId[1]).setNormalImage(R.drawable.house);
        map.get(tabStrId[1]).setSelectImage(R.drawable.house1);
        map.get(tabStrId[2]).setNormalImage(R.drawable.friend);
        map.get(tabStrId[2]).setSelectImage(R.drawable.friend1);
        map.get(tabStrId[3]).setNormalImage(R.drawable.mine);
        map.get(tabStrId[3]).setSelectImage(R.drawable.mine1);
//        map.get(tabStrId[4]).set(R.drawable.heart2);
    }

//    //选中和未选中时的字体颜色变化
//    private void setText() {
//        map.get(tabStrId[0]).setTextView(tv1););
//        map.get(tabStrId[0]).setSelectImage(R.drawable.story1);
//        map.get(tabStrId[1]).setNormalImage(R.drawable.house);
//        map.get(tabStrId[1]).setSelectImage(R.drawable.house1);
//        map.get(tabStrId[2]).setNormalImage(R.drawable.friend);
//        map.get(tabStrId[2]).setSelectImage(R.drawable.friend1);
//        map.get(tabStrId[3]).setNormalImage(R.drawable.mine);
//        map.get(tabStrId[3]).setSelectImage(R.drawable.mine1);
////        map.get(tabStrId[4]).set(R.drawable.heart2);
//    }

    private void changeTab(String s) {

        changeFragment(s);

        changeImage(s);
    }
    private void changeFragment(String s){
        Fragment fragment = map.get(s).getFragment();

        if (curFragment == fragment) return;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (curFragment!=null)
            transaction.hide(curFragment);
        if (!fragment.isAdded()){
            transaction.add(R.id.tab_content,fragment);
        }
        transaction.show(fragment);
        curFragment = fragment;

        transaction.commit();
    }

    private void changeImage(String s){
        for (String key:map.keySet()){
            map.get(key).setSelect(false);
        }

        map.get(s).setSelect(true);
    }
    private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tab_spec_1:
                    changeTab(tabStrId[0]);
                    break;
                case R.id.tab_spec_2:
                    changeTab(tabStrId[1]);
                    break;
                case R.id.tab_spec_3:
                    changeTab(tabStrId[2]);
                    break;
                case R.id.tab_spec_4:
                    changeTab(tabStrId[3]);
                    break;

            }
        }
    }

    private class MyTabSpec {
        private ImageView imageView = null;
        private TextView textView = null;
        private int normalImage;
        private int selectImage;
        private Fragment fragment = null;


        private void setSelect(boolean b){
            if(b){
                imageView.setImageResource(selectImage);
                textView.setTextColor(
                        Color.parseColor("#5ab1e3")
                );
            }else {
                imageView.setImageResource(normalImage);
                textView.setTextColor(
                        Color.parseColor("#000000")
                );
            }
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }

        public int getNormalImage() {
            return normalImage;
        }

        public void setNormalImage(int normalImage) {
            this.normalImage = normalImage;
        }

        public int getSelectImage() {
            return selectImage;
        }

        public void setSelectImage(int selectImage) {
            this.selectImage = selectImage;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public void setFragment(Fragment fragment) {
            this.fragment = fragment;
        }
//        public void setGIFImage(){
//            Glide.with(MainActivity.this).asGif().load().into();
//        }
    }
}
