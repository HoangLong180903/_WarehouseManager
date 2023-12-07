package com.example.warehousemanager.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehousemanager.Database.WaveHouseDB;
import com.example.warehousemanager.Model.KhachHang;
import com.example.warehousemanager.Model.NhaCungCap;
import com.example.warehousemanager.Model.NhanVien;
import com.example.warehousemanager.Model.PhieuNhap;
import com.example.warehousemanager.Model.PhieuXuat;
import com.example.warehousemanager.Model.SanPham;
import com.example.warehousemanager.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class PhieuXuatAdapter extends RecyclerView.Adapter<PhieuXuatAdapter.MyViewHolder>{
    Context mContext;
    List<PhieuXuat> list;

    public PhieuXuatAdapter(Context mContext, List<PhieuXuat> list) {
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
        PhieuXuat phieuXuat = list.get(position);
        holder.tvMaPhieu.setText("Hóa Đơn: PN180903"+phieuXuat.getMaPhieuXuat());
        NhanVien nhanVien = WaveHouseDB.getInstance(mContext).dao().getIdNhanVien(String.valueOf(phieuXuat.getMaNhanVien()));
        holder.tvNguoiTao.setText("Người Tạo: "+nhanVien.getTenNhanVien());
        holder.tvNgayNhap.setText("Ngày Xuất: "+phieuXuat.getNgayXuat());
        holder.tvTongTien.setText("Tổng Tiền: "+phieuXuat.getTongTien() + " VNĐ");
        holder.imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChiTietPhieu(phieuXuat);
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

    public void DialogChiTietPhieu(PhieuXuat phieuXuat) {
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

        tvTitle.setText("Chi Tiết Phiếu Xuất");

        NhanVien nhanVien = WaveHouseDB.getInstance(mContext).dao().getIdNhanVien(String.valueOf(phieuXuat.getMaNhanVien()));
        tvNhanVien.setText("Người tạo: " + nhanVien.getTenNhanVien());

        KhachHang khachHang = WaveHouseDB.getInstance(mContext).dao().getIdKhachHang(String.valueOf(phieuXuat.getMaKhachHang()));
        tvTenDonVi.setText("Khách hàng: " + khachHang.getTenKhachHang());
        tvSDT.setText("Số điện thoại: " + khachHang.getSoDT());
        tvDiaChi.setText("Địa chỉ: " + khachHang.getDiaChi());

        SanPham sanPham = WaveHouseDB.getInstance(mContext).dao().getIdSanPham(String.valueOf(phieuXuat.getMaSanPham()));
        Bitmap imageContent = BitmapFactory.decodeByteArray(sanPham.getImage(), 0, sanPham.getImage().length);
        imgSanPham.setImageBitmap(imageContent);

        tvTenSP.setText("Tên sản phẩm: " + sanPham.getTenSanPham());
        tvGiaTien.setText("Đơn giá nhập: " + sanPham.getGiaNhap());
        tvTongTien.setText("" + phieuXuat.getTongTien()+ " Vnđ");
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
