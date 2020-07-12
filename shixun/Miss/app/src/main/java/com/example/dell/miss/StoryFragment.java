//package com.example.dell.miss;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTabHost;
//import android.support.v4.view.ViewPager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TabHost;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class StoryFragment extends Fragment{
//    private FragmentTabHost tabHost;
//    private List<TextView> textViewList = new ArrayList<>();
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.artivity_story_top,container,false);
//        initFragment(view);
//        setTabChangedListener();
//        return view;
//    }
//
//    private void initFragment(View view){
//        tabHost = view.findViewById(android.R.id.tabhost);
//        tabHost.setup(getContext(),getActivity().getSupportFragmentManager(),android.R.id.tabcontent);
//        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("tag1");
//        tabSpec1.setIndicator(getTabSpecView("推荐"));
//        tabHost.addTab(tabSpec1,StoryFirstFragment.class,null);
//        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("tag2");
//        tabSpec2.setIndicator(getTabSpecView("生命之光"));
//        tabHost.addTab(tabSpec2, StorySecondFragment.class,null);
//        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("tag3");
//        tabSpec3.setIndicator(getTabSpecView("英雄事迹"));
//        tabHost.addTab(tabSpec3, StoryThirdFragment.class,null);
//        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("tag4");
//        tabSpec4.setIndicator(getTabSpecView("时下热议"));
//        tabHost.addTab(tabSpec4, StoryFourthFragment.class,null);
//        textViewList.get(0).setTextColor(Color.parseColor("#FFFFFF"));
//    }
//
//    private void setTabChangedListener(){
//        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//            @Override
//            public void onTabChanged(String tabId) {
//                switch (tabId){
//                    case "tag1":
//                        textViewList.get(0).setTextColor(Color.parseColor("#FFFFFF"));
//                        textViewList.get(1).setTextColor(Color.parseColor("#707070"));
//                        textViewList.get(2).setTextColor(Color.parseColor("#707070"));
//                        textViewList.get(3).setTextColor(Color.parseColor("#707070"));
//                        break;
//                    case "tag2":
//                        textViewList.get(0).setTextColor(Color.parseColor("#707070"));
//                        textViewList.get(1).setTextColor(Color.parseColor("#FFFFFF"));
//                        textViewList.get(2).setTextColor(Color.parseColor("#707070"));
//                        textViewList.get(3).setTextColor(Color.parseColor("#707070"));
//                        break;
//                    case "tag3":
//                        textViewList.get(0).setTextColor(Color.parseColor("#707070"));
//                        textViewList.get(1).setTextColor(Color.parseColor("#707070"));
//                        textViewList.get(2).setTextColor(Color.parseColor("#FFFFFF"));
//                        textViewList.get(3).setTextColor(Color.parseColor("#707070"));
//                        break;
//                    case "tag4":
//                        textViewList.get(0).setTextColor(Color.parseColor("#707070"));
//                        textViewList.get(1).setTextColor(Color.parseColor("#707070"));
//                        textViewList.get(2).setTextColor(Color.parseColor("#707070"));
//                        textViewList.get(3).setTextColor(Color.parseColor("#FFFFFF"));
//                        break;
//                }
//            }
//        });
//    }
//
//    private View getTabSpecView(String title){
//        View view = getLayoutInflater().inflate(R.layout.activity_story_tab,null);
//        TextView textView = view.findViewById(R.id.tv_title);
//        textView.setText(title);
//        textView.setTextSize(15);
//        textViewList.add(textView);
//
//        return view;
//    }
//}

package com.example.dell.miss;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StoryFragment extends Fragment{
    private FragmentTabHost tabHost;
    private List<TextView> textViewList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.artivity_story_top,container,false);
        initFragment(view);
        setTabChangedListener();
        return view;
    }

    private void initFragment(View view){
        tabHost = view.findViewById(android.R.id.tabhost);
        tabHost.setup(getContext(),getActivity().getSupportFragmentManager(),android.R.id.tabcontent);
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("tag1");
        tabSpec1.setIndicator(getTabSpecView("推荐"));
        tabHost.addTab(tabSpec1,StoryFirstFragment.class,null);
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("tag2");
        tabSpec2.setIndicator(getTabSpecView("生命之光"));
        tabHost.addTab(tabSpec2, StorySecondFragment.class,null);
        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("tag3");
        tabSpec3.setIndicator(getTabSpecView("英雄事迹"));
        tabHost.addTab(tabSpec3, StoryThirdFragment.class,null);
        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("tag4");
        tabSpec4.setIndicator(getTabSpecView("时下热议"));
        tabHost.addTab(tabSpec4, StoryFourthFragment.class,null);
        textViewList.get(0).setTextColor(Color.parseColor("#FFA500"));
        textViewList.get(1).setTextColor(Color.parseColor("#FFFFFF"));
        textViewList.get(2).setTextColor(Color.parseColor("#FFFFFF"));
        textViewList.get(3).setTextColor(Color.parseColor("#FFFFFF"));
    }

    private void setTabChangedListener(){
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (tabId){
                    case "tag1":
                        textViewList.get(0).setTextColor(Color.parseColor("#FFA500"));
                        textViewList.get(1).setTextColor(Color.parseColor("#FFFFFF"));
                        textViewList.get(2).setTextColor(Color.parseColor("#FFFFFF"));
                        textViewList.get(3).setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    case "tag2":
                        textViewList.get(0).setTextColor(Color.parseColor("#FFFFFF"));
                        textViewList.get(1).setTextColor(Color.parseColor("#FFA500"));
                        textViewList.get(2).setTextColor(Color.parseColor("#FFFFFF"));
                        textViewList.get(3).setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    case "tag3":
                        textViewList.get(0).setTextColor(Color.parseColor("#FFFFFF"));
                        textViewList.get(1).setTextColor(Color.parseColor("#FFFFFF"));
                        textViewList.get(2).setTextColor(Color.parseColor("#FFA500"));
                        textViewList.get(3).setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    case "tag4":
                        textViewList.get(0).setTextColor(Color.parseColor("#FFFFFF"));
                        textViewList.get(1).setTextColor(Color.parseColor("#FFFFFF"));
                        textViewList.get(2).setTextColor(Color.parseColor("#FFFFFF"));
                        textViewList.get(3).setTextColor(Color.parseColor("#FFA500"));
                        break;
                }
            }
        });
    }

    private View getTabSpecView(String title){
        View view = getLayoutInflater().inflate(R.layout.activity_story_tab,null);
        TextView textView = view.findViewById(R.id.tv_title);
        textView.setText(title);
        textView.setTextSize(15);
        textViewList.add(textView);

        return view;
    }
}

