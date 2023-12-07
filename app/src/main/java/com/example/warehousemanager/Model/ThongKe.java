package com.example.warehousemanager.Model;

public class ThongKe {
    public int soLuong;

    public int maSanPham;

    public ThongKe(int soLuong, int maSanPham) {
        this.soLuong = soLuong;
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }
}
