package com.example.dell.miss;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CelebrityFragment extends Fragment {
    private List<CelebrityMessage> list = new ArrayList<>();
    private ListView listView;
    private CelebrityAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_celebrity,container,false);
        initData();
        findView(view);

        return view;
    }


    private void findView(View view){
        listView = view.findViewById(R.id.lv_celebrity);
        adapter = new CelebrityAdapter(list,R.layout.friend_list_item,getContext());
        listView.setAdapter(adapter);
    }

    private void initData(){
        CelebrityMessage message = new CelebrityMessage();
        message.img = R.drawable.zhouenlai;
        message.cename = "周恩来";
        message.looknum=154;
        message.cetime = "1898-1976";
        message.cepossition = "北京市";
        message.cedescription = "热爱祖国，热爱人民，为了人民的利益奔走一生。";
        message.jianjie = "伟大的马克思主义者，伟大的无产阶级革命家、政治家、军事家、外交家，党和国家主要领导人之一，中国人民解放军主要创建人之一，中华人民共和国的开国元勋，是以毛泽东同志为核心的党的第一代中央领导集体的重要成员。";
        CelebrityMessage message1 = new CelebrityMessage();
        message1.img = R.drawable.dengxiaoping;
        message1.cename = "邓小平";
        message1.looknum = 246;
        message1.cetime = "1904-1997";
        message1.cepossition = "北京";
        message1.cedescription = "为了中国的统一奔波忙碌，关心人民，伟大的领袖。";
        message1.jianjie = "邓小平是全党全军全国各族人民公认的享有崇高威望的卓越领导人，伟大的马克思主义者，伟大的无产阶级革命家、政治家、军事家、外交家，久经考验的共产主义战士，中国社会主义改革开放和现代化建设的总设计师，中国特色社会主义道路的开创者，邓小平理论的主要创立者";
        CelebrityMessage message3 = new CelebrityMessage();
        message3.img = R.drawable.maozedong;
        message3.cename = "毛泽东";
        message3.cetime = "1893-1976";
        message3.looknum = 421;
        message3.cepossition = "湖南";
        message3.cedescription = "为了中国的统一奔波忙碌，关心人民，伟大的领袖。";
        message3.jianjie = "中国人民的领袖，伟大的马克思主义者，无产阶级革命家、战略家和理论家，中国共产党、中国人民解放军和中华人民共和国的主要缔造者和领导人，诗人，书法家。";
        CelebrityMessage message4 = new CelebrityMessage();
        message4.img = R.drawable.dengjiaxian;
        message4.cename = "邓稼先";
        message4.cetime = "1924-1986";
        message4.looknum = 234;
        message4.cepossition = "北京";
        message4.cedescription = "两弹元勋。";
        message4.jianjie = "邓稼先（1924年6月25日—1986年7月29日），九三学社社员，中国科学院院士，著名核物理学家，中国核武器研制工作的开拓者和奠基者，为中国核武器、原子武器的研发做出了重要贡献。";
        list.add(message);
        list.add(message1);
        list.add(message3);
        list.add(message4);
    }
}
