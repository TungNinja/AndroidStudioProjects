package com.example.admin.lazada.Pressenter.Home.Menu;

import android.os.Bundle;
import android.util.Log;

import com.example.admin.lazada.Common.MenuPressenterInface;
import com.example.admin.lazada.Common.MenuViewInface;
import com.example.admin.lazada.ConnectInternet.DownloadJSON;
import com.example.admin.lazada.Model.Home.Menu.DataJSONMenu;
import com.example.admin.lazada.Model.ObjectClass.LoaiSanPham;
import com.example.admin.lazada.Model.RegisterLogin.LoginModel;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ADMIN on 11/30/2017.
 */

public class MenuLogicPressenter implements MenuPressenterInface {

    MenuViewInface menuViewHome;

    public MenuLogicPressenter(MenuViewInface menuView) {
        this.menuViewHome = menuView;
    }

    @Override
    public void loadDataMenu() {
        String dataJSON = "";
        List<LoaiSanPham> listLoaiSanPham = null;
        List<Map<String, String>> attrs = new ArrayList<Map<String, String>>();

        /* Lay theo phuong thuc GET*/

        /*String url = "http://192.168.42.47/weblazada/loaisanpham-cha.php?maloaicha=0";
        DownloadJSON downloadJSON = new DownloadJSON(url);
        downloadJSON.execute();
        */

        /*
        * Lay theo phuong thuc POST
        * */
        String url = "http://192.168.42.47/weblazada/loaisanpham-cha.php";
        HashMap<String, String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha", "0");
        attrs.add(hsMaLoaiCha);
        DownloadJSON downloadJSON = new DownloadJSON(url, attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            DataJSONMenu dataJSONMenu = new DataJSONMenu();
            listLoaiSanPham = dataJSONMenu.parserJSONMenu(dataJSON);
            menuViewHome.showListDataMenu(listLoaiSanPham);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccessToken getAccessToken() {

        LoginModel modelDangNhap = new LoginModel();
        AccessToken accessToken = modelDangNhap.getAccessToken();

        if (accessToken == null){
            Log.d("Token: ", "null");
        }else {
            Log.d("Token: ", accessToken.toString());
        }

        return accessToken;

    }
}
