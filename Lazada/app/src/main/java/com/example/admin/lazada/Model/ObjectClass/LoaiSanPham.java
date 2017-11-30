package com.example.admin.lazada.Model.ObjectClass;

import java.util.List;

/**
 * Created by ADMIN on 11/30/2017.
 */

public class LoaiSanPham {

    int maLoaiSanPham, maLoaiCha;

    String tenSanPham;

    List<LoaiSanPham> listChild;

    public int getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(int maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public int getMaLoaiCha() {
        return maLoaiCha;
    }

    public void setMaLoaiCha(int maLoaiCha) {
        this.maLoaiCha = maLoaiCha;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public List<LoaiSanPham> getListChild() {
        return listChild;
    }

    public void setListChild(List<LoaiSanPham> listChild) {
        this.listChild = listChild;
    }
}
