package cn.hebtu.software.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LeftDeleteAdapter extends BaseAdapter {
    private Context context;
    private List<String> dataSource;
    private int item_layout_id;

    public LeftDeleteAdapter(Context context,List<String> list,int item_layout_id){
        this.context = context;
        this.dataSource = list;
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
        ViewHolder holder = null;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(item_layout_id,null);
            holder.text = convertView.findViewById(R.id.adapter_tv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.text.setText(dataSource.get(position));
        return convertView;
    }

    private class ViewHolder{
        private TextView text;
    }
}
