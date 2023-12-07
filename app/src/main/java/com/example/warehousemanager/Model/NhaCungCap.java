package com.example.warehousemanager.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NhaCungCap")
public class NhaCungCap {
    @PrimaryKey(autoGenerate = true)
    private int maNCC;

    private String tenNhaCungCap;

    private String soDienThoai;

    private String diaChi;

    private String trangThai;

    public NhaCungCap() {
    }

    public NhaCungCap(String tenNhaCungCap, String soDienThoai, String diaChi, String trangThai) {
        this.tenNhaCungCap = tenNhaCungCap;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return  tenNhaCungCap ;
    }
}
