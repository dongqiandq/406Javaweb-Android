package com.example.dell.miss;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class YinHeFragment extends Fragment {
    private ListView listView;
    private ZhuiyiAdapter zhuiyiAdapter = null;
    private List<ZhuiyiMessage> list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yinhe_layout,container,false);
        listView = view.findViewById(R.id.lv_zyinhe);
        initData();
        zhuiyiAdapter = new ZhuiyiAdapter(list,getContext(),R.layout.zhuiyi_item);
        listView.setAdapter(zhuiyiAdapter);
        return view;
    }
    private void initData(){
        list.clear();

        ZhuiyiMessage zhuiyiMessage3 = new ZhuiyiMessage(R.drawable.second,"周恩来","1898年3月-1976年1月","北京");
        zhuiyiMessage3.setImgId(R.drawable.zhouenlai);
        zhuiyiMessage3.setContext("热爱祖国，热爱人民，为了人民的利益奔走一生。");
        zhuiyiMessage3.setJianjie("伟大的马克思主义者，伟大的无产阶级革命家、政治家、军事家、外交家，党和国家主要领导人之一，中国人民解放军主要创建人之一，中华人民共和国的开国元勋，是以毛泽东同志为核心的党的第一代中央领导集体的重要成员。");
        list.add(zhuiyiMessage3);

        ZhuiyiMessage zhuiyiMessage6 = new ZhuiyiMessage(R.drawable.second,"邓稼先","1924年6月-1986年7月","北京");
        zhuiyiMessage6.setImgId(R.drawable.dengjiaxian);
        zhuiyiMessage6.setContext("两弹元勋。");
        zhuiyiMessage6.setJianjie("邓稼先（1924年6月25日—1986年7月29日），九三学社社员，中国科学院院士，著名核物理学家，中国核武器研制工作的开拓者和奠基者，为中国核武器、原子武器的研发做出了重要贡献。");
        list.add(zhuiyiMessage6);

        ZhuiyiMessage zhuiyiMessage4 = new ZhuiyiMessage(R.drawable.second,"邓小平","1904年8月-1997年2月","北京");
        zhuiyiMessage4.setImgId(R.drawable.dengxiaoping);
        zhuiyiMessage4.setContext("为了中国的统一奔波忙碌，关心人民，伟大的领袖。");
        zhuiyiMessage4.setJianjie("邓小平是全党全军全国各族人民公认的享有崇高威望的卓越领导人，伟大的马克思主义者，伟大的无产阶级革命家、政治家、军事家、外交家，久经考验的共产主义战士，中国社会主义改革开放和现代化建设的总设计师，中国特色社会主义道路的开创者，邓小平理论的主要创立者");
        list.add(zhuiyiMessage4);

        ZhuiyiMessage zhuiyiMessage5 = new ZhuiyiMessage(R.drawable.second,"毛泽东","1893年12月-1976年9月","湖南");
        zhuiyiMessage5.setContext("为了中国的统一奔波忙碌，关心人民，伟大的领袖。");
        zhuiyiMessage5.setImgId(R.drawable.maozedong);
        zhuiyiMessage5.setJianjie("中国人民的领袖，伟大的马克思主义者，无产阶级革命家、战略家和理论家，中国共产党、中国人民解放军和中华人民共和国的主要缔造者和领导人，诗人，书法家。");
        list.add(zhuiyiMessage5);


    }
}
