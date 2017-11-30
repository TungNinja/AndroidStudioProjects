package com.example.admin.lazada.Model.Home.Menu;

import android.provider.FontRequest;

import com.example.admin.lazada.Model.ObjectClass.LoaiSanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.ls.LSParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 11/30/2017.
 */

public class DataJSONMenu {

    public List<LoaiSanPham> parserJSONMenu(String dataJson){

        List<LoaiSanPham> loaiSanPhams = new ArrayList<LoaiSanPham>();

        try {
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray jsonArray = jsonObject.getJSONArray("LOAISANPHAM");

            for (int i=0; i< jsonArray.length(); i++){
                JSONObject value = jsonArray.getJSONObject(i);

                LoaiSanPham lsp = new LoaiSanPham();
                lsp.setMaLoaiSanPham(Integer.parseInt(value.getString("MALOAISP")));
                lsp.setMaLoaiCha(Integer.parseInt(value.getString("MALOAI_CHA")));
                lsp.setTenSanPham(value.getString("TENLOAISANPHAM"));

                loaiSanPhams.add(lsp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loaiSanPhams;
    }
}
