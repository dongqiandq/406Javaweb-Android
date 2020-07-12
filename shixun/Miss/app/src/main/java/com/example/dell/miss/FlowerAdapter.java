package com.example.dell.miss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class FlowerAdapter extends BaseAdapter {
    // 原始数据
    private List<Map<String, Object>> dataSource = null;
    // 上下文环境
    private Context context = null;
    // item对应的布局文件
    private int item_layout_id;
    /**
     *  构造器，完成初始化
     * @param context           上下文环境
     * @param dataSource        原始数据
     * @param item_layout_id    item对应的布局文件
     */
    public FlowerAdapter(Context context,
                       List<Map<String, Object>> dataSource,
                       int item_layout_id) {
        this.context = context;
        this.dataSource = dataSource;
        this.item_layout_id = item_layout_id;
    }
    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 布局填充器，可以根据布局文件生成相应View对象
        LayoutInflater inflater = LayoutInflater.from(context);
        // 使用布局填充器根据布局文件资源ID生成View视图对象
        View newView = inflater.inflate(item_layout_id, parent,false);
        // 从newView中获取显示数据的控件对象
        ImageView imgHead = newView.findViewById(R.id.img_hua);
        TextView tvName = newView.findViewById(R.id.tv_name);
        TextView tvAbout = newView.findViewById(R.id.tv_about);
        // 根据位置从原数据list中获取要显示的数据
        Map<String, Object> map = dataSource.get(position);
        imgHead.setImageResource((int)map.get("head"));
        tvName.setText(map.get("name").toString());
        tvAbout.setText(map.get("about").toString());

        return newView;
    }
}
