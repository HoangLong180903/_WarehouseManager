package com.example.warehousemanager.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehousemanager.Adapter.PhieuNhapAdapter;
import com.example.warehousemanager.Adapter.PhieuXuatAdapter;
import com.example.warehousemanager.Adapter.SanPhamSpinner;
import com.example.warehousemanager.Database.WaveHouseDB;
import com.example.warehousemanager.Model.KhachHang;
import com.example.warehousemanager.Model.NhaCungCap;
import com.example.warehousemanager.Model.NhanVien;
import com.example.warehousemanager.Model.PhieuNhap;
import com.example.warehousemanager.Model.PhieuXuat;
import com.example.warehousemanager.Model.SanPham;
import com.example.warehousemanager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

public class PhieuXuatFragment extends Fragment {


    Calendar calendar = Calendar.getInstance();
    RecyclerView rc_PX;
    FloatingActionButton fl_btnAdd;

    //component
    ImageView imgBack;
    EditText edNgayXuat , edThue , edSoLuongXuat ;
    TextView tvTongTien;
    Button btnHoanTat;

    Spinner spNhanVien , spKhachHang  , spSanPham;
    private List<NhanVien> nhanVienList;

    private List<KhachHang> khachHangList;
    private List<SanPham> sanPhamList;

    private List<PhieuXuat> list;
    PhieuXuatAdapter adapter;
    PhieuXuat phieuXuat;
    SanPham sanPham;

    int soLuong , donGiaXuat , result , checkSoLuong;
    double thue;
    int tongTienSauGiamGia;
    private int selectedPosition = -1;
    SanPhamSpinner spinner;

    LinearLayout linearLayoutCheckSoLuong;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phieu_xuat, container, false);
        ((Activity)getContext()).setTitle("Phiếu Xuất");
        init(view);
        getData();
        action();

        return  view;


    }

    public void init(View view){
        rc_PX = view.findViewById(R.id.rc_phieuXuat);
        imgBack = view.findViewById(R.id.phieuxuat_imageBack);
        fl_btnAdd = view.findViewById(R.id.phieuxuat_btnAdd);
    }
    public void getData(){
        setLayoutAnimation(R.anim.layout_anim);
        list = WaveHouseDB.getInstance(getContext()).dao().getAllPhieuXuat();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rc_PX.setLayoutManager(manager);
        adapter = new PhieuXuatAdapter(getContext(),list);
        rc_PX.setAdapter(adapter);
    }
    private void setLayoutAnimation(int animResource){
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),animResource);
        rc_PX.setLayoutAnimation(layoutAnimationController);
    }
    public void action(){
        fl_btnAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingInflatedId")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_phieu_xuat, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                phieuXuat = new PhieuXuat();
                edNgayXuat = view.findViewById(R.id.phieuxuat_edNgayXuat);
                edThue = view.findViewById(R.id.phieuxuat_edThue);
                tvTongTien = view.findViewById(R.id.phieuxuat_tvTongTien);
                edSoLuongXuat = view.findViewById(R.id.phieuxuat_edsoLuong);
                linearLayoutCheckSoLuong = view.findViewById(R.id.phieuxuat_checkSoLuong);
                //spinner sanpham
                spSanPham = view.findViewById(R.id.phieuxuat_spSanPham);
                sanPhamList = WaveHouseDB.getInstance(getContext()).dao().getAllSanPhamWithTrangThai();
                spinner = new SanPhamSpinner(getContext(), sanPhamList);
                spSanPham.setAdapter(spinner);
                spSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String tenSanPham = sanPhamList.get(i).getTenSanPham();
                        phieuXuat.setMaSanPham(sanPhamList.get(i).getIdSanPham());
                        donGiaXuat = sanPhamList.get(i).getGiaXuat();
                        checkSoLuong = sanPhamList.get(i).getSoLuongTon();
                        selectedPosition = i;
                        Toast.makeText(getContext(), "Sản phẩm: " + tenSanPham + " \t " + donGiaXuat, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                //spinner nhacungcap
                spKhachHang = view.findViewById(R.id.phieuxuat_spKhachHang);
                khachHangList = WaveHouseDB.getInstance(getContext()).dao().getAllKhachHang();
                ArrayAdapter khachArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, khachHangList);
                spKhachHang.setAdapter(khachArrayAdapter);
                spKhachHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String tenKhachHang = khachHangList.get(i).getTenKhachHang();
                        phieuXuat.setMaKhachHang(khachHangList.get(i).getMaKH());
                        Toast.makeText(getContext(), "Nhà cung cấp: " + tenKhachHang, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                //spinner nhan vien
                spNhanVien = view.findViewById(R.id.phieuxuat_spNhanVien);
                nhanVienList = WaveHouseDB.getInstance(getContext()).dao().getAllNhanVien();
                ArrayAdapter nhanVienAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, nhanVienList);
                spNhanVien.setAdapter(nhanVienAdapter);
                spNhanVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String tenNhanVien = nhanVienList.get(i).getTenNhanVien();
                        phieuXuat.setMaNhanVien(nhanVienList.get(i).getMaNV());
                        Toast.makeText(getContext(), "Sản phẩm: " + tenNhanVien, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                //set date time
                edNgayXuat = view.findViewById(R.id.phieuxuat_edNgayXuat);
                edNgayXuat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog pickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String mMonth = "";
                                String mDay = "";
                                month += 1;
                                if (month < 10) {
                                    mMonth = "0" + month;
                                } else {
                                    mMonth = "" + month;
                                }
                                if (dayOfMonth < 10) {
                                    mDay = "0" + dayOfMonth;
                                } else {
                                    mDay = "" + dayOfMonth;
                                }
                                edNgayXuat.setText(mDay + "-" + mMonth + "-" + year);
                            }
                        }, calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH));
                        pickerDialog.show();
                    }
                });

                //edSoLuongNhap set tongTien
                edSoLuongXuat.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
//
                        if (s.toString().isEmpty() || s.toString().equals(0)) {
                            soLuong = 0;
                            return;
                        } else if (Integer.parseInt(s.toString()) > checkSoLuong) {
                            linearLayoutCheckSoLuong.setVisibility(View.VISIBLE);
                            soLuong = 0;
                            return;
                        } else {
                            linearLayoutCheckSoLuong.setVisibility(View.GONE);
                            soLuong = Integer.parseInt(String.valueOf(s));
                        }
                        result = soLuong * donGiaXuat;
                        tvTongTien.setText(String.valueOf(result) + " VNĐ");
                    }
                });

                edThue.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.toString().isEmpty() || s.toString().equals(0)) {
                            thue = 0;
                        } else if (Integer.parseInt(s.toString()) > 100) {
                            thue = 0;
                        } else {
                            thue = result * (Double.parseDouble(edThue.getText().toString()) / 100);
                        }
                        tongTienSauGiamGia = (int) (result - thue);
                        tvTongTien.setText(String.valueOf(tongTienSauGiamGia) + " VNĐ");
                    }
                });
                btnHoanTat = view.findViewById(R.id.phieuxuat_btnSave);
                btnHoanTat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edNgayXuat.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Yêu cầu chọn ngày xuất ", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (edSoLuongXuat.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Số lượng nhập không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (edThue.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Thuế không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        phieuXuat.setNgayXuat(edNgayXuat.getText().toString());
                        phieuXuat.setMaThue(edThue.getText().toString());
                        phieuXuat.setTongTien(String.valueOf(tongTienSauGiamGia));

                        if (WaveHouseDB.getInstance(getContext()).dao().addPhieuXuat(phieuXuat) > 0) {
                            if (selectedPosition != -1) {
                                sanPham = sanPhamList.get(selectedPosition);
                                if (WaveHouseDB.getInstance(getContext()).dao().update(sanPham.getSoLuongTon() - soLuong, sanPham.getIdSanPham()) > 0) {
                                    WaveHouseDB.getInstance(getContext()).dao().getAllSanPham();
                                    getData();
                                    edNgayXuat.setText("");
                                    edSoLuongXuat.setText("");
                                    edThue.setText("");
                                    tvTongTien.setText(0 + " VNĐ");
                                    dialog.dismiss();
                                }
                            }
                            Toast.makeText(getContext(), "Tạo phiếu xuất thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Tạo phiếu xuất không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                imgBack = view.findViewById(R.id.phieuxuat_imageBack);
                imgBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}