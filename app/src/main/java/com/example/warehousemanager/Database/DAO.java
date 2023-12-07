package com.example.warehousemanager.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.warehousemanager.Model.KhachHang;
import com.example.warehousemanager.Model.LoaiSanPham;
import com.example.warehousemanager.Model.NhaCungCap;
import com.example.warehousemanager.Model.NhanVien;
import com.example.warehousemanager.Model.PhieuNhap;
import com.example.warehousemanager.Model.PhieuXuat;
import com.example.warehousemanager.Model.SanPham;
import com.example.warehousemanager.Model.ThongKe;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    long addLoaiSanPham(LoaiSanPham loaiSanPham);

    @Insert
    long addSanPham(SanPham sanPham);
    @Insert
    long addNhaCungCap(NhaCungCap nhaCungCap);
    @Insert
    long addKhachHang(KhachHang khachHang);

    @Insert
    long addNhanVien(NhanVien nhanVien);

    @Insert
    long addPhieuNhap(PhieuNhap phieuNhap);

    @Insert
    long addPhieuXuat(PhieuXuat phieuXuat);

    @Query("UPDATE SanPham SET soLuongTon = :soLuong WHERE idSanPham =:id")
    int update(int soLuong, int id);

    @Query("UPDATE NhanVien SET matKhau = :matKhau WHERE maNV =:id")
    int updateNhanVien(int matKhau, int id);

    @Query("Select * from LoaiSanPham")
    List<LoaiSanPham> getAllLoaiSanPham();

    @Query("Select * from SanPham")
    List<SanPham> getAllSanPham();

    @Query("Select * from SanPham WHERE trangThai LIKE '%Cho phép bán%'")
    List<SanPham> getAllSanPhamWithTrangThai();

    @Query("Select * from NhaCungCap")
    List<NhaCungCap> getAllNhaCungCap();

    @Query("Select * from KhachHang")
    List<KhachHang> getAllKhachHang();

    @Query("Select * from NhanVien")
    List<NhanVien> getAllNhanVien();

    @Query("Select * from PhieuNhap")
    List<PhieuNhap> getAllPhieuNhap();

    @Query("Select * from PhieuXuat")
    List<PhieuXuat> getAllPhieuXuat();

    @Query("Select * from NhanVien where maNV=:id")
    NhanVien getIdNhanVien(String id);

    @Query("Select * from NhanVien where tenTaiKhoan=:username")
    NhanVien getIdLogin(String username);

    @Query("Select * from SanPham where idSanPham=:id")
    SanPham getIdSanPham(String id);

    @Query("Select * from NhaCungCap where maNCC=:id")
    NhaCungCap getIdNhaCungCap(String id);

    @Query("Select * from KhachHang where maKH=:id")
    KhachHang getIdKhachHang(String id);

    //thong ke top 10
    @Query("Select maSanPham , count(maSanPham) as soLuong from PhieuXuat group by maSanPham order by soLuong desc Limit 10")
    List<ThongKe> getTop10SanPham();

    @Query("Select maSanPham , count(maSanPham) as soLuong from PhieuXuat where  ngayXuat =:ngayHomNay  group by maSanPham order by soLuong desc Limit 10")
    List<ThongKe> getTop10SanPhamHomNay(String ngayHomNay);

//    so luong phieu nhap ngay hom nay
    @Query("Select count(maPhieuNhap) as soLuong from PhieuNhap where  ngayNhap =:ngayHomNay ")
    int getPhieuNhapHomNay(String ngayHomNay);
    //    so luong phieu xuat ngay hom nay
    @Query("Select count(maPhieuXuat) as soLuong from PhieuXuat where  ngayXuat =:ngayHomNay ")
    int getPhieuXuatHomNay(String ngayHomNay);

    @Query("Select sum(tongTien) from PhieuXuat where  ngayXuat =:ngayHomNay")
    int tienLaiHomNay(String ngayHomNay);
    @Query("Select sum(tongTien) from PhieuNhap where  ngayNhap =:ngayHomNay")
    int tienLoHomNay(String ngayHomNay);
    //thong ke doanh thu
    @Query("SELECT sum(tongTien) FROM PhieuXuat WHERE ngayXuat BETWEEN :tuNgay AND :denNgay")
    int getDoanhThuPhieuXuat(String tuNgay , String denNgay);
    @Query("Select sum(tongTien) as doanhthu from PhieuNhap where ngayNhap between :tuNgay AND :denNgay")
    int getDoanhThuPhieuNhap(String tuNgay , String denNgay);

    //thong ke doanh thu hom nay

    //so phieu nhap
    @Query("Select count(maPhieuNhap) from PhieuNhap")
    int getCountPhieuNhap();
    //tổng tiền phiếu
    @Query("Select sum(tongTien) from PhieuNhap")
    int getTongTienPhieuNhap();

    //so phieu xuat
    @Query("Select count(maPhieuXuat) from PhieuXuat")
    int getCountPhieuXuat();
    //tổng tiền phiếu
    @Query("Select sum(tongTien) from PhieuXuat")
    int getTongTienPhieuXuat();

    @Query("Select sum(soLuongTon) from SanPham")
    int getSoLuongTon();

    @Query("Select sum(soLuongTon * giaNhap) from SanPham")
    int getTongTienTonKho();

    @Query("Select * from nhanvien where tenTaiKhoan =:username and matKhau =:password ")
    int checkLogin(String username , String password);
}

