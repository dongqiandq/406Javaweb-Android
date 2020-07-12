package com.example.dell.miss;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FriendFragment extends Fragment  {
    private List<FriendQinyouMessage> list = new ArrayList<>();
    private ListView listView;
    private FriendQinyouAdapter adapter;
    FriendQinyouMessage message11;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.friend,container,false);

        Log.e("test","bbbbbbbbbbbb");
        findView(view);
        initData();
        ImageView add = view.findViewById(R.id.img_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FriendAddqinyouActivity.class);
                startActivityForResult(intent,1);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==11){
            Bundle bundle = data.getBundleExtra("add");
            message11 = (FriendQinyouMessage) bundle.getSerializable("message");
            Log.e("test",message11.toString());
            list.add(message11);
            adapter.notifyDataSetChanged();
        }
        if(requestCode==1 && resultCode==100){
            message11.lookNum=data.getIntExtra("eye",1);
            list.add(message11);
            adapter.notifyDataSetChanged();
        }
    }

    private void findView(View view){
        listView = view.findViewById(R.id.lv_qinyou);
        adapter = new FriendQinyouAdapter(getContext(),list,R.layout.friend_list_item);
        listView.setAdapter(adapter);
    }

    private void initData(){
        FriendQinyouMessage qinyouMessage = new FriendQinyouMessage();
        qinyouMessage.img = R.drawable.f1;
        qinyouMessage.lookNum=213;
        qinyouMessage.name = "王烁";
        qinyouMessage.time = "1984 - 2020";
        qinyouMessage.possition = "辽宁省";
        qinyouMessage.description = "精忠报国，大爱无疆，19年来，他恪守“24小时上门服务”的出诊承诺，一个电话，随叫随到，骑坏7辆摩托车，用烂12个行医包，累计出诊约17万人次，免收出诊费35万余元，为五保户患者免费贴药达4万元，深受患者的赞誉。他的英雄事迹鼓舞、激励了一代代青少年爱国爱家、奋发向上。";

        FriendQinyouMessage qinyouMessage1 = new FriendQinyouMessage();
        qinyouMessage1.img = R.drawable.f2;
        qinyouMessage.lookNum=453;
        qinyouMessage1.name = "李文亮";
        qinyouMessage1.time = "1974 - 2020";
        qinyouMessage1.possition = "湖北省";
        qinyouMessage1.description = "医德高尚，救死扶伤，他的英雄事迹鼓舞、激励了一代代青少年爱国爱家、奋发向上。";
        list.add(qinyouMessage);
        list.add(qinyouMessage1);
        adapter.notifyDataSetChanged();

    }
}
