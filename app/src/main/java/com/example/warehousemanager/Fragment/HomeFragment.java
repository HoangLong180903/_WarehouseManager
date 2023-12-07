package com.example.warehousemanager.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;


import com.example.warehousemanager.Database.WaveHouseDB;
import com.example.warehousemanager.R;

public class HomeFragment extends Fragment {

    TextView tvTenNhanVien , tvNgayHienTai , tvDoanhThu , tvTonKho , tvPhieuNhap , tvPhieuXuat;
    Date currentDate = new Date();
    int soLuongTon , soLuongPhieuNhap , soLuongPhieuXuat , tongDoanhThu;
    String username;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ((Activity)getContext()).setTitle("Màn Hình Chính");
        init(view);
        action();
        String tenNhanVien = this.getArguments().getString("tenNhanVien");
        tvTenNhanVien.setText(tenNhanVien);
        return view;
    }

    public void init(View view){
        tvTenNhanVien = view.findViewById(R.id.home_tvTenNhanVien);
        tvNgayHienTai = view.findViewById(R.id.home_tvNgayHienTai);
        tvDoanhThu = view.findViewById(R.id.home_tvDoanhThuHomNay);
        tvTonKho = view.findViewById(R.id.home_tvTonKhoHomNay);
        tvPhieuNhap = view.findViewById(R.id.home_tvPhieuNhapHomNay);
        tvPhieuXuat = view.findViewById(R.id.home_tvPhieuXuatHomNay);
    }

    public void action(){
        //set text ngày hiện tại
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateString = dateFormat.format(currentDate);
        tvNgayHienTai.setText(""+dateString);
        //set số lượng tồn kho
        soLuongTon = WaveHouseDB.getInstance(getContext()).dao().getSoLuongTon();
        tvTonKho.setText(""+soLuongTon);
        //set số lượng phiếu nhập ngày hôm nay
        soLuongPhieuNhap = WaveHouseDB.getInstance(getContext()).dao().getPhieuNhapHomNay(dateString);
        tvPhieuNhap.setText(""+soLuongPhieuNhap);

        //set số lượng phiếu xuất ngày hôm nay
        soLuongPhieuXuat = WaveHouseDB.getInstance(getContext()).dao().getPhieuXuatHomNay(dateString);
        tvPhieuXuat.setText(""+soLuongPhieuXuat);
        //set tong tien hom nay
        tongDoanhThu = WaveHouseDB.getInstance(getContext()).dao().tienLaiHomNay(dateString);
        tvDoanhThu.setText(""+tongDoanhThu + " đ");
    }
}