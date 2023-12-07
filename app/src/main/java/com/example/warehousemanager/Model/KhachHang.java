package com.example.warehousemanager.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "KhachHang")
public class KhachHang {

    @PrimaryKey(autoGenerate = true)
    private int maKH;

    private String tenKhachHang;

    private String soDT;
    private String diaChi;

    public KhachHang() {
    }

    public KhachHang(String tenKhachHang, String soDT, String diaChi) {
        this.tenKhachHang = tenKhachHang;
        this.soDT = soDT;
        this.diaChi = diaChi;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return tenKhachHang ;
    }
}
