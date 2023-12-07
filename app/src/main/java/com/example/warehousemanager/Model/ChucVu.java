package com.example.warehousemanager.Model;

public class ChucVu {

    private String chucVu;

    private String mucLuong;

    public ChucVu() {
    }

    public ChucVu(String chucVu, String mucLuong) {
        this.chucVu = chucVu;
        this.mucLuong = mucLuong;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getMucLuong() {
        return mucLuong;
    }

    public void setMucLuong(String mucLuong) {
        this.mucLuong = mucLuong;
    }

    @Override
    public String toString() {
        return chucVu ;
    }
}
