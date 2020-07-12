package com.example.dell.miss;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FootActivity extends AppCompatActivity {
    private ImageView imageView;
    private FragmentTabHost tabHost;
    private List<TextView> textViewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.foot_layout);
        initFragment();
        setTabChangedListener();

        imageView = findViewById(R.id.img_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FootActivity.this.finish();
            }
        });
    }

    private void initFragment(){
        tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("tag1");
        tabSpec1.setIndicator(getTabSpecView("银河"));
        tabHost.addTab(tabSpec1,YinHeFragment.class,null);
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("tag2");
        tabSpec2.setIndicator(getTabSpecView("故事"));
        tabHost.addTab(tabSpec2, GushiFragment.class,null);
        textViewList.get(0).setTextColor(Color.parseColor("#FFA500"));
    }

    private void setTabChangedListener(){
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (tabId){
                    case "tag1":
                        textViewList.get(0).setTextColor(Color.parseColor("#FFA500"));
                        textViewList.get(1).setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    case "tag2":
                        textViewList.get(0).setTextColor(Color.parseColor("#FFFFFF"));
                        textViewList.get(1).setTextColor(Color.parseColor("#FFA500"));
                        break;
                }
            }
        });
    }

    private View getTabSpecView(String title){
        View view = getLayoutInflater().inflate(R.layout.activity_foot_tab,null);
        TextView textView = view.findViewById(R.id.tv_title);
        textView.setText(title);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        textView.setTextSize(20);
        textViewList.add(textView);

        return view;
    }
}
