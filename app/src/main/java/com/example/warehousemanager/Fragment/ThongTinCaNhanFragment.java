package com.example.warehousemanager.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.warehousemanager.Database.WaveHouseDB;
import com.example.warehousemanager.Model.NhanVien;
import com.example.warehousemanager.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class ThongTinCaNhanFragment extends Fragment {


    TextView tvHoTen , tvChucVu , tvMucLuong , tvNamSinh , tvSoDT ;
    ImageView btn_doiMK;
    NhanVien nhanVien;
    String oldMatKhau;
    int id;
    EditText edOldPass, edNewPass , edReNewPass;
    Button btnHoanTat;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_tin_ca_nhan, container, false);
        init(view);
        action();
        return view;
    }

    public void init(View view){
        tvHoTen = view.findViewById(R.id.profile_hoTen);
        tvChucVu = view.findViewById(R.id.profile_chucVu);
        tvMucLuong = view.findViewById(R.id.profile_mucLuong);
        tvNamSinh = view.findViewById(R.id.profile_namSinh);
        tvSoDT = view.findViewById(R.id.profile_soDT);
        btn_doiMK = view.findViewById(R.id.profile_doiMK);
    }

    public void action(){
        btn_doiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChangePass();
            }
        });
        NhanVien nhanVien = (NhanVien) this.getArguments().getSerializable("thongTinNhanVien");
        tvHoTen.setText(""+nhanVien.getTenNhanVien());
        tvChucVu.setText(""+nhanVien.getChucVu());
        tvMucLuong.setText(""+nhanVien.getMucLuong());
        tvNamSinh.setText("Năm sinh: "+nhanVien.getNamSinh());
        tvSoDT.setText("Số điện thoại: "+nhanVien.getSoDT());
        oldMatKhau = nhanVien.getMatKhau();
        id = nhanVien.getMaNV();
    }

    public void DialogChangePass(){
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(R.layout.layout_change_password);

        dialog.show();

        edOldPass = dialog.findViewById(R.id.profile_edOldPass);
        edNewPass = dialog.findViewById(R.id.profile_edNewPass);
        edReNewPass = dialog.findViewById(R.id.profile_edReNewPass);
        btnHoanTat = dialog.findViewById(R.id.profile_btnChangePass);
        btnHoanTat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate() > 0) {
                    if (WaveHouseDB.getInstance(getContext()).dao().updateNhanVien(1234, id) > 0) {
                        Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        WaveHouseDB.getInstance(getContext()).dao().getAllNhanVien();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });
    }

    public int validate(){
        int check = 1;
        if (edOldPass.getText().length() == 0 || edNewPass.getText().length() == 0 || edReNewPass.getText().length() == 0){
            Toast.makeText(getContext(), "Bạn cần nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        //doc user , pass trong SharedPreferences
        String pass = edNewPass.getText().toString();
        String repass = edReNewPass.getText().toString();
        if (!oldMatKhau.equals(edOldPass.getText().toString())){
            Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        if (!pass.equals(repass)){
            Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}