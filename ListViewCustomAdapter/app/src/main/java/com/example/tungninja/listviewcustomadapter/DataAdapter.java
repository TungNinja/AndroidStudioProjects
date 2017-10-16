package com.example.tungninja.listviewcustomadapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by tungninja on 10/15/17.
 */

public class DataAdapter extends BaseAdapter {

    private Activity activity;
    private String[] items;

    public DataAdapter(Activity activity, String[] items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Gọi layoutInflater ra để bắt đầu ánh xạ view và data.
        LayoutInflater inflater = activity.getLayoutInflater();

        // Đổ dữ liệu vào biến View, view này chính là những gì nằm trong item_name.xml
        view = inflater.inflate(R.layout.item_name, null);

        // Đặt chữ cho từng view trong danh sách.
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        tvName.setText(items[i]);

        // Trả về view kết quả.
        return view;
    }
}
