package com.example.warehousemanager.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "PhieuNhap",
foreignKeys = {@ForeignKey(
        entity = NhaCungCap.class,
        parentColumns = "maNCC",
        childColumns = "maNhaCungCap",
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
), @ForeignKey(
        entity = NhanVien.class,
        parentColumns = "maNV",
        childColumns = "maNhanVien",
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE),
        @ForeignKey(
                entity = SanPham.class,
                parentColumns = "idSanPham",
                childColumns = "maSanPham",
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE)
        })
public class PhieuNhap {
    @PrimaryKey(autoGenerate = true)
    private int maPhieuNhap;

    private String ngayNhap;

    private String tongTien;

    private int maNhanVien;

    private int maNhaCungCap;
    private int maSanPham;

    private String maKhuyenMai;

    public PhieuNhap() {
    }

    public PhieuNhap(String ngayNhap, String tongTien, int maNhanVien, int maNhaCungCap, int maSanPham, String maKhuyenMai) {
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
        this.maNhanVien = maNhanVien;
        this.maNhaCungCap = maNhaCungCap;
        this.maSanPham = maSanPham;
        this.maKhuyenMai = maKhuyenMai;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }
}
