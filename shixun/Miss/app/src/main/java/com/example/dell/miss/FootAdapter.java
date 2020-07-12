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

public class FootAdapter extends BaseAdapter {
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
    public FootAdapter(Context context,
                         List<Map<String, Object>> dataSource,
                         int item_layout_id) {
        this.context = context;
        this.dataSource = dataSource;
        this.item_layout_id = item_layout_id;
    }


    /**
     * 返回当数据条数，AdapterView（ListView）需要通过它确定item数量
     * @return
     */
    @Override
    public int getCount() {
        return dataSource.size();
    }

    /**
     *  根据对应位置，返回item的原始数据
     * @param position  item在ListView中的位置
     * @return 对应位置的item的原始数据
     */
    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    /**
     *  根据item位置返回item对应的ID，ID在ListView的监听器响应方法中
     *  被使用
     * @param position  item在ListView中的位置，从0开始
     * @return 返回当前item的ID
     */
    @Override
    public long getItemId(int position) {
        // 为了简便起见，我们返回位置，让位置作为id
        // 实际开发中id使用数据的主键，或者唯一标识
        return position;
    }

    /**
     * 根据item位置返回item用来显示的View视图
     * @param position      item位置
     * @param convertView
     * @param parent        item所属的父容器
     * @return              相应位置用来显示的View视图对象
     */
    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {

        // 布局填充器，可以根据布局文件生成相应View对象
        LayoutInflater inflater = LayoutInflater.from(context);
        // 使用布局填充器根据布局文件资源ID生成View视图对象
        View newView = inflater.inflate(item_layout_id, null);
        // 从newView中获取显示数据的控件对象
        ImageView imgHead = newView.findViewById(R.id.img_foot);
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

