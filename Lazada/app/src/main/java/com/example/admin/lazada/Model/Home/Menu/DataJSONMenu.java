package com.example.admin.lazada.Model.Home.Menu;

import android.os.Bundle;
import android.provider.FontRequest;
import android.util.Log;

import com.example.admin.lazada.ConnectInternet.DownloadJSON;
import com.example.admin.lazada.Model.ObjectClass.LoaiSanPham;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.ls.LSParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ADMIN on 11/30/2017.
 */

public class DataJSONMenu {

    public List<LoaiSanPham> parserJSONMenu(String dataJson){

        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        try {
            Log.d("kiemtra",dataJson);
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray loaisanpham = jsonObject.getJSONArray("LOAISANPHAM");
            int count = loaisanpham.length();
            for(int i=0;i<count;i++){
                JSONObject value = loaisanpham.getJSONObject(i);

                LoaiSanPham dataloaiSanPham = new LoaiSanPham();
                dataloaiSanPham.setMaLoaiSanPham(Integer.parseInt(value.getString("MALOAISP")));
                dataloaiSanPham.setMaLoaiCha(Integer.parseInt(value.getString("MALOAI_CHA")));
                dataloaiSanPham.setTenSanPham(value.getString("TENLOAISP"));

                loaiSanPhamList.add(dataloaiSanPham);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loaiSanPhamList;
    }

    public List<LoaiSanPham> getListChildByParent(String parentId){

        List<LoaiSanPham> loaiSanPhams = new ArrayList<LoaiSanPham>();
        List<Map<String, String>> attrs = new ArrayList<Map<String, String>>();String dataJSON = "";


        String url = "http://192.168.42.47/weblazada/loaisanpham-cha.php";
        HashMap<String, String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha", parentId);
        attrs.add(hsMaLoaiCha);
        DownloadJSON downloadJSON = new DownloadJSON(url, attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            loaiSanPhams = parserJSONMenu(dataJSON);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return loaiSanPhams;

    }

}
