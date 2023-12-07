package com.example.warehousemanager.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehousemanager.Database.WaveHouseDB;
import com.example.warehousemanager.Model.NhaCungCap;
import com.example.warehousemanager.Model.NhanVien;
import com.example.warehousemanager.Model.PhieuXuat;
import com.example.warehousemanager.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.MyViewHolder>{
    Context mContext;
    List<NhanVien> list;

    public NhanVienAdapter(Context mContext, List<NhanVien> list) {
        this.mContext = mContext;
        this.list = list;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_nhan_vien,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NhanVien nhanVien = list.get(position);
        holder.tvTenNhanVien.setText("Nhân viên: "+nhanVien.getTenNhanVien());
        holder.tvSoDienThoai.setText(""+nhanVien.getSoDT());
        if (nhanVien.getTrangThai().equals("Đang làm")){
            holder.tvTrangThai.setTypeface(null, Typeface.BOLD);
        }else if (nhanVien.getTrangThai().equals("Tạm nghỉ")){
            holder.tvTrangThai.setTextColor(Color.BLUE);
        }else{
            holder.tvTrangThai.setTextColor(Color.RED);
        }
        holder.tvTrangThai.setText("Trạng thái: "+nhanVien.getTrangThai());
        holder.tvMucLuong.setText("Mức lương: "+nhanVien.getMucLuong());
        holder.imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChiTietNhanVien(nhanVien);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static final class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView  imageMenu ;
        TextView tvTenNhanVien , tvSoDienThoai , tvTrangThai , tvMucLuong;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMenu = itemView.findViewById(R.id.nhanvien_itemMenu);
            tvTenNhanVien = itemView.findViewById(R.id.nhanvien_itemTenNV);
            tvSoDienThoai = itemView.findViewById(R.id.nhanvien_itemSDT);
            tvTrangThai = itemView.findViewById(R.id.nhanvien_itemTrangThai);
            tvMucLuong = itemView.findViewById(R.id.nhanvien_itemMucLuong);
        }
    }
    public void DialogChiTietNhanVien(NhanVien nhanVien) {
        BottomSheetDialog dialog = new BottomSheetDialog(mContext);
        dialog.setContentView(R.layout.layout_chi_tiet_nhan_vien);

        dialog.show();

        TextView tvTenNV , tvNamSinh , tvTaiKhoan , tvMatKhau  , tvChucVu , tvTrangThai;
        Button btnResetPassword;

        tvTenNV = dialog.findViewById(R.id.ctnhanvien_tenNhanVien);
        tvNamSinh = dialog.findViewById(R.id.ctnhanvien_namSinh);
        tvMatKhau = dialog.findViewById(R.id.ctnhanvien_matKhau);
        tvTaiKhoan = dialog.findViewById(R.id.ctnhanvien_tenTaiKhoan);
        tvChucVu = dialog.findViewById(R.id.ctnhanvien_chucVu);
        tvTrangThai  = dialog.findViewById(R.id.ctnhanvien_trangThai);
        btnResetPassword = dialog.findViewById(R.id.ctnhanvien_btnResetMatKhau);

        tvTenNV.setText("Tên nhân viên: "+nhanVien.getTenNhanVien());
        tvNamSinh.setText("Năm sinh: "+nhanVien.getNamSinh());
        tvTaiKhoan.setText("Tài khoản: "+nhanVien.getTenTaiKhoan());
        tvChucVu.setText("Chúc vụ: "+nhanVien.getChucVu());
        tvMatKhau.setText(""+nhanVien.getMatKhau());
        if (nhanVien.getTrangThai().equals("Đang làm")){
            tvTrangThai.setTypeface(null, Typeface.BOLD);
        }else if (nhanVien.getTrangThai().equals("Tạm nghỉ")){
            tvTrangThai.setTextColor(Color.BLUE);
        }else{
            tvTrangThai.setTextColor(Color.RED);
        }
        tvTrangThai.setText("Trạng thái: "+nhanVien.getTrangThai());

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                View layout = ((Activity)mContext).getLayoutInflater().inflate(R.layout.test_custom, null, false);
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.getContext().getTheme().applyStyle(R.style.MyAlertDialog, true);
                builder.setView(layout);
                Dialog dialogResetPassword = builder.create();
                dialogResetPassword.show();
                Button btn_done;
                btn_done = layout.findViewById(R.id.btn_done);
                btn_done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (WaveHouseDB.getInstance(mContext).dao().updateNhanVien(123456,nhanVien.getMaNV()) > 0){
                            Toast.makeText(mContext, "Reset password thành công", Toast.LENGTH_SHORT).show();
                            dialogResetPassword.dismiss();
                            list.clear();
                            list.addAll(WaveHouseDB.getInstance(mContext).dao().getAllNhanVien());
                            notifyDataSetChanged();
                        }else{
                            Toast.makeText(mContext, "Reset password thất bại", Toast.LENGTH_SHORT).show();
                            dialogResetPassword.dismiss();
                        }
                    }
                });
            }
        });
    }
}
