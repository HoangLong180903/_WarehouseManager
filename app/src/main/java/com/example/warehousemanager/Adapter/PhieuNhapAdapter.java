package com.example.warehousemanager.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehousemanager.Database.WaveHouseDB;
import com.example.warehousemanager.Model.KhachHang;
import com.example.warehousemanager.Model.LoaiSanPham;
import com.example.warehousemanager.Model.NhaCungCap;
import com.example.warehousemanager.Model.NhanVien;
import com.example.warehousemanager.Model.PhieuNhap;
import com.example.warehousemanager.Model.PhieuXuat;
import com.example.warehousemanager.Model.SanPham;
import com.example.warehousemanager.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class PhieuNhapAdapter extends RecyclerView.Adapter<PhieuNhapAdapter.MyViewHolder>{
    Context mContext;
    List<PhieuNhap> list;

    public PhieuNhapAdapter(Context mContext, List<PhieuNhap> list) {
        this.mContext = mContext;
        this.list = list;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_phieu_nhap,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PhieuNhap phieuNhap = list.get(position);
        holder.tvMaPhieu.setText("Hóa Đơn: PN180903"+phieuNhap.getMaPhieuNhap());
        NhanVien nhanVien = WaveHouseDB.getInstance(mContext).dao().getIdNhanVien(String.valueOf(phieuNhap.getMaNhanVien()));
        holder.tvNguoiTao.setText("Người Tạo: "+nhanVien.getTenNhanVien());
        holder.tvNgayNhap.setText("Ngày Nhập: "+phieuNhap.getNgayNhap());
        holder.tvTongTien.setText("Tổng Tiền: "+phieuNhap.getTongTien() + " VNĐ");
        holder.imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChiTietPhieu(phieuNhap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static final class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView  imageMenu ;
        TextView tvMaPhieu , tvNguoiTao , tvNgayNhap , tvTongTien;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMenu = itemView.findViewById(R.id.phieunhap_itemMenu);
            tvMaPhieu = itemView.findViewById(R.id.phieunhap_itemMaPhieu);
            tvNguoiTao = itemView.findViewById(R.id.phieunhap_itemNhanVien);
            tvNgayNhap = itemView.findViewById(R.id.phieunhap_itemNgay);
            tvTongTien = itemView.findViewById(R.id.phieunhap_itemTongTien);
        }
    }

    public void DialogChiTietPhieu(PhieuNhap phieuNhap) {
        BottomSheetDialog dialog = new BottomSheetDialog(mContext);
        dialog.setContentView(R.layout.layout_chi_tiet_phieu);

        dialog.show();


        TextView tvTitle, tvNhanVien, tvTenDonVi, tvSDT, tvDiaChi, tvTenSP, tvSoLuong, tvGiaTien, tvTongTien;
        ImageView imgSanPham;
        Button btnDong;
        tvTitle = dialog.findViewById(R.id.ctphieu_tvTitle);
        tvNhanVien = dialog.findViewById(R.id.ctphieu_tvNhanVien);
        tvTenDonVi = dialog.findViewById(R.id.ctphieu_tenDonVi);
        tvSDT = dialog.findViewById(R.id.ctphieu_sdtDonVi);
        tvDiaChi = dialog.findViewById(R.id.ctphieu_diaChiDonVi);
        tvTenSP = dialog.findViewById(R.id.ctphieu_tenSanPham);
        tvSoLuong = dialog.findViewById(R.id.ctphieu_soLuongSanPham);
        tvGiaTien = dialog.findViewById(R.id.ctphieu_giaTienSanPham);
        tvTongTien = dialog.findViewById(R.id.ctphieu_tongTien);
        imgSanPham = dialog.findViewById(R.id.ctphieu_imageSanPham);
        btnDong = dialog.findViewById(R.id.ctphieu_btnDong);

        tvTitle.setText("Chi Tiết Phiếu Nhập");

        NhanVien nhanVien = WaveHouseDB.getInstance(mContext).dao().getIdNhanVien(String.valueOf(phieuNhap.getMaNhanVien()));
        tvNhanVien.setText("Người tạo: " + nhanVien.getTenNhanVien());

        NhaCungCap nhaCungCap = WaveHouseDB.getInstance(mContext).dao().getIdNhaCungCap(String.valueOf(phieuNhap.getMaNhaCungCap()));
        tvTenDonVi.setText("Nhà cung cấp: " + nhaCungCap.getTenNhaCungCap());
        tvSDT.setText("Số điện thoại: " + nhaCungCap.getSoDienThoai());
        tvDiaChi.setText("Địa chỉ: " + nhaCungCap.getDiaChi());

        SanPham sanPham = WaveHouseDB.getInstance(mContext).dao().getIdSanPham(String.valueOf(phieuNhap.getMaSanPham()));
        Bitmap imageContent = BitmapFactory.decodeByteArray(sanPham.getImage(), 0, sanPham.getImage().length);
        imgSanPham.setImageBitmap(imageContent);

        tvTenSP.setText("Tên sản phẩm: " + sanPham.getTenSanPham());
        tvGiaTien.setText("Đơn giá nhập: " + sanPham.getGiaNhap());
        tvTongTien.setText("" + phieuNhap.getTongTien()+ " Vnđ");
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
