package com.example.warehousemanager.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "PhieuXuat",
foreignKeys = {@ForeignKey(
        entity = KhachHang.class,
        parentColumns = "maKH",
        childColumns = "maKhachHang",
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
public class PhieuXuat {
    @PrimaryKey(autoGenerate = true)
    private int maPhieuXuat;

    private String ngayXuat;

    private String tongTien;

    private int maNhanVien;

    private int maKhachHang ;
    private int maSanPham;

    private String maThue;

    public PhieuXuat() {
    }

    public PhieuXuat(String ngayXuat, String tongTien, int maNhanVien, int maKhachHang, int maSanPham, String maThue) {
        this.ngayXuat = ngayXuat;
        this.tongTien = tongTien;
        this.maNhanVien = maNhanVien;
        this.maKhachHang = maKhachHang;
        this.maSanPham = maSanPham;
        this.maThue = maThue;
    }

    public int getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(int maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
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

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getMaThue() {
        return maThue;
    }

    public void setMaThue(String maThue) {
        this.maThue = maThue;
    }
}
