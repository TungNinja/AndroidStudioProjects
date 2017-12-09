package com.example.admin.lazada.Adapter;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.lazada.Model.Home.Menu.DataJSONMenu;
import com.example.admin.lazada.Model.ObjectClass.LoaiSanPham;
import com.example.admin.lazada.R;

import java.util.List;

/**
 * Created by ADMIN on 12/1/2017.
 */

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context context;
    List<LoaiSanPham> loaiSanPhams;
    ViewHolderMenu viewHolderMenu;

    public ExpandAdapter(Context context, List<LoaiSanPham> loaiSanPhams) {
        this.context = context;
        this.loaiSanPhams = loaiSanPhams;

        DataJSONMenu dataJSONMenu = new DataJSONMenu();
        for (LoaiSanPham lsp : loaiSanPhams){
            lsp.setListChild(dataJSONMenu.getListChildByParent(lsp.getMaLoaiSanPham()+""));
            Log.d("List child: " , lsp.getListChild().size()+"");
        }


    }

    @Override
    public int getGroupCount() {
        return loaiSanPhams.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if (loaiSanPhams.get(i).getListChild().size() > 0){
            return 1;
        }
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return loaiSanPhams.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return loaiSanPhams.get(i).getListChild().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return loaiSanPhams.get(i).getMaLoaiSanPham();
    }

    @Override
    public long getChildId(int i, int i1) {
        return loaiSanPhams.get(i).getListChild().get(i1).getMaLoaiSanPham();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolderMenu{
        TextView txtTenLoaiSP;
        ImageView hinhMenu;
    }

    @Override
    public View getGroupView(final int positionParent, boolean isExpand, View view, ViewGroup viewGroup) {

        View viewGroupCha = view;
        if(viewGroupCha == null){
            viewHolderMenu = new ViewHolderMenu();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_expand_parent,viewGroup,false);

            viewHolderMenu.txtTenLoaiSP = (TextView) viewGroupCha.findViewById(R.id.tenloaisanpham);
            viewHolderMenu.hinhMenu = (ImageView) viewGroupCha.findViewById(R.id.imgMenu);

            viewGroupCha.setTag(viewHolderMenu);
        }else{
            viewHolderMenu = (ViewHolderMenu) viewGroupCha.getTag();
        }

        viewHolderMenu.txtTenLoaiSP.setText(loaiSanPhams.get(positionParent).getTenSanPham());

        int demsanphamcon = loaiSanPhams.get(positionParent).getListChild().size();

        if(demsanphamcon > 0){
            viewHolderMenu.hinhMenu.setVisibility(View.VISIBLE);
        }else{
            viewHolderMenu.hinhMenu.setVisibility(View.INVISIBLE);
        }

        if(isExpand){
            viewHolderMenu.hinhMenu.setImageResource(R.drawable.ic_remove_black_24dp);
        }else{
            viewHolderMenu.hinhMenu.setImageResource(R.drawable.ic_add_black_24dp);
        }

        viewGroupCha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
              Log.d("ThonTinSP: ", loaiSanPhams.get(positionParent).getMaLoaiSanPham() + " - "+loaiSanPhams.get(positionParent).getTenSanPham());
                return false;
            }
        });

        return viewGroupCha;

    }

    @Override
    public View getChildView(int positionParent, int positionChild, boolean isExpand, View view, ViewGroup viewGroup) {

        SecondExpanable secondExpanable = new SecondExpanable(context);
        ExpandAdapter secondAdapter = new ExpandAdapter(context, loaiSanPhams.get(positionParent).getListChild());
        secondExpanable.setAdapter(secondAdapter);

        secondExpanable.setGroupIndicator(null);

        notifyDataSetChanged();

        return secondExpanable;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    public class SecondExpanable extends ExpandableListView{

        public SecondExpanable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int width = size.x;
            int height = size.y;
            Log.d("size",width + " - " + height);

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}
