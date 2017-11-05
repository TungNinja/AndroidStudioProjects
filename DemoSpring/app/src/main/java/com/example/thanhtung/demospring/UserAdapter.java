package com.example.thanhtung.demospring;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThanhTung on 05/08/2017.
 */

public class UserAdapter extends BaseAdapter {

    Context context;
    int myLayout;
    List<User> listUser = new ArrayList<User>();

    public UserAdapter(Context context, int myLayout, List<User> listUser) {
        this.context = context;
        this.myLayout = myLayout;
        this.listUser = listUser;
    }

    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(myLayout, null);

        // anh xa gan gia tri

        TextView id = (TextView) convertView.findViewById(R.id.txtID);
        id.setText(listUser.get(position).getName());

        TextView name = (TextView) convertView.findViewById(R.id.txtName);
        name.setText(listUser.get(position).getAge());

        TextView createDate = (TextView) convertView.findViewById(R.id.txtCreateDate);
        createDate.setText(listUser.get(position).getCreatedDate()+"");

        /*
        ImageView imgHinh = (ImageView) convertView.findViewById(R.id.imageView);
        String_to_ImageView(listUser.get(position).getHinhAnh(), imgHinh);
    */
        return convertView;
    }

    public void String_to_ImageView(String strBase64, ImageView iv){

        byte[] decodedString = Base64.decode(strBase64, Base64.DEFAULT);
        Bitmap decodeByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        iv.setImageBitmap(decodeByte);
    }
}
