package com.example.admin.lazada.Pressenter.Home.Menu;

import com.example.admin.lazada.Common.MenuPressenterInface;
import com.example.admin.lazada.Common.MenuViewInface;
import com.example.admin.lazada.ConnectInternet.DownloadJSON;
import com.example.admin.lazada.Model.Home.Menu.DataJSONMenu;
import com.example.admin.lazada.Model.ObjectClass.LoaiSanPham;

import java.util.List;
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

        String url = "http://192.168.1.9/weblazada/loaisanpham.php?maloaicha=0";
        DownloadJSON downloadJSON = new DownloadJSON(url);
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
}
