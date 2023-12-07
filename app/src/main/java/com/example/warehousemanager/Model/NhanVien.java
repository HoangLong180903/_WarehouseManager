package com.example.warehousemanager.Model;

import android.text.TextUtils;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "NhanVien")
public class NhanVien implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int maNV;

    private String tenNhanVien;

    private int namSinh;

    private String soDT;

    private String tenTaiKhoan;

    private String matKhau;

    private String trangThai;

    private String chucVu;

    private String mucLuong;

    public NhanVien() {
    }

    public NhanVien(String tenNhanVien, int namSinh, String soDT, String tenTaiKhoan, String matKhau, String trangThai, String chucVu, String mucLuong) {
        this.tenNhanVien = tenNhanVien;
        this.namSinh = namSinh;
        this.soDT = soDT;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.chucVu = chucVu;
        this.mucLuong = mucLuong;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
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
        return tenNhanVien ;
    }


    public boolean isValidate(){
        return !TextUtils.isEmpty(tenTaiKhoan) && !TextUtils.isEmpty(matKhau);
    }

}
