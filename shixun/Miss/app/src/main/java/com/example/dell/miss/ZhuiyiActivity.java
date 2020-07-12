package com.example.dell.miss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZhuiyiActivity extends AppCompatActivity {
    // 原始数据
    private List<Map<String, Object>> dataSource = null;

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuiyi1_layout);

        // 生成原始数据
        initData();

        ListView listView = findViewById(R.id.lv_zy);
        imageView = findViewById(R.id.img_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhuiyiActivity.this.finish();
            }
        });
        FlowerAdapter adapter = new FlowerAdapter(
                this,
                dataSource,
                R.layout.zhuiyi_item
        );

        listView.setAdapter(adapter);
    }
    private void initData() {
        int[] heads = {R.drawable.wuwang, R.drawable.xing,
                R.drawable.kangnaixin, R.drawable.redrose,
                R.drawable.yellowrose, R.drawable.bairose,
                R.drawable.blue};
        String[] names = {"勿忘我", "满天星", "康乃馨", "红玫瑰",
                "黄玫瑰", "白玫瑰", "蓝色妖姬"};
        String[] abouts = {"请不要忘记我真诚的爱；请想念我，忠贞的希望一切都还没有晚，我会再次归来给你幸福。",
                "深深的思念", "感恩奉献、永远敬爱", "热情真爱",
                "珍重祝福和嫉妒失恋", "爱如昔日纯粹","清纯的爱和敦厚善良的爱"};

        dataSource = new ArrayList<>();
        for(int i=0; i<heads.length; ++i) {
            Map<String, Object> map = new HashMap<>();
            map.put("head", heads[i]);
            map.put("name", names[i]);
            map.put("about", abouts[i]);
            dataSource.add(map);
        }
    }

}
