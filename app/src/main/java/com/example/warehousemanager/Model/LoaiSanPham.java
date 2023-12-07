package com.example.warehousemanager.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "LoaiSanPham")
public class LoaiSanPham {
    @PrimaryKey(autoGenerate = true)
    private int idLoaiSanPham;

    private String tenLoaiSanPham;

    private String moTaLoaiSanPham;

    private byte[] image;

    public LoaiSanPham() {
    }

    public LoaiSanPham(String tenLoaiSanPham, String moTaLoaiSanPham, byte[] image) {
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.moTaLoaiSanPham = moTaLoaiSanPham;
        this.image = image;
    }

    public int getIdLoaiSanPham() {
        return idLoaiSanPham;
    }

    public void setIdLoaiSanPham(int idLoaiSanPham) {
        this.idLoaiSanPham = idLoaiSanPham;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public String getMoTaLoaiSanPham() {
        return moTaLoaiSanPham;
    }

    public void setMoTaLoaiSanPham(String moTaLoaiSanPham) {
        this.moTaLoaiSanPham = moTaLoaiSanPham;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return tenLoaiSanPham ;
    }
}
