package com.example.warehousemanager.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "SanPham" ,
foreignKeys = {@ForeignKey(
        entity = LoaiSanPham.class,
        parentColumns = "idLoaiSanPham",
        childColumns = "idLoaiSanPham",
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
) , @ForeignKey(
        entity = NhaCungCap.class,
        parentColumns = "maNCC",
        childColumns = "maNCC",
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE)
})
public class SanPham {
    @PrimaryKey(autoGenerate = true)
    private int idSanPham;

    private String tenSanPham;

    private int giaNhap;

    private int giaXuat;

    private int soLuongTon;

    private String trangThai;

    private byte[] image;

    private int idLoaiSanPham;

    private int maNCC;

    public SanPham(String tenSanPham, int giaNhap, int giaXuat, int soLuongTon, String trangThai, byte[] image, int idLoaiSanPham, int maNCC) {
        this.tenSanPham = tenSanPham;
        this.giaNhap = giaNhap;
        this.giaXuat = giaXuat;
        this.soLuongTon = soLuongTon;
        this.trangThai = trangThai;
        this.image = image;
        this.idLoaiSanPham = idLoaiSanPham;
        this.maNCC = maNCC;
    }

    public SanPham() {
    }


    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
    }

    public int getGiaXuat() {
        return giaXuat;
    }

    public void setGiaXuat(int giaXuat) {
        this.giaXuat = giaXuat;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getIdLoaiSanPham() {
        return idLoaiSanPham;
    }

    public void setIdLoaiSanPham(int idLoaiSanPham) {
        this.idLoaiSanPham = idLoaiSanPham;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
}
