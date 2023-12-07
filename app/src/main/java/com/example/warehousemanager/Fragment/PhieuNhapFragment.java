package com.example.warehousemanager.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.warehousemanager.Adapter.NhanVienAdapter;
import com.example.warehousemanager.Adapter.PhieuNhapAdapter;
import com.example.warehousemanager.Adapter.SanPhamSpinner;
import com.example.warehousemanager.Database.WaveHouseDB;
import com.example.warehousemanager.Model.ChucVu;
import com.example.warehousemanager.Model.NhaCungCap;
import com.example.warehousemanager.Model.NhanVien;
import com.example.warehousemanager.Model.PhieuNhap;
import com.example.warehousemanager.Model.SanPham;
import com.example.warehousemanager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

public class PhieuNhapFragment extends Fragment {


    Calendar calendar = Calendar.getInstance();
    RecyclerView rc_PN;
    FloatingActionButton fl_btnAdd;

    //component
    ImageView imgBack;
    EditText edNgayNhap, edGiamGia, edSoLuongNhap;
    TextView tvChonSanPham, tvTongTien;
    Button btnHoanTat;

    Spinner spNhanVien, spNhaCungCap, spSanPham;
    private List<NhanVien> nhanVienList;

    private List<NhaCungCap> nhaCungCapList;
    private List<SanPham> sanPhamList;

    private List<PhieuNhap> list;
    PhieuNhapAdapter adapter;
    PhieuNhap phieuNhap;
    SanPham sanPham;

    int soLuong, donGiaNhap, result;
    double giamGia;
    int tongTienSauGiamGia;
    private int selectedPosition = -1;
    SanPhamSpinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phieu_nhap, container, false);
        ((Activity) getContext()).setTitle("Phiếu Nhập");
        init(view);
        getData();
        action();

        return view;


    }

    public void init(View view) {
        rc_PN = view.findViewById(R.id.rc_phieuNhap);
        imgBack = view.findViewById(R.id.phieunhap_imageBack);
        fl_btnAdd = view.findViewById(R.id.phieunhap_btnAdd);
    }

    public void getData() {
        setLayoutAnimation(R.anim.layout_anim);
        list = WaveHouseDB.getInstance(getContext()).dao().getAllPhieuNhap();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rc_PN.setLayoutManager(manager);
        adapter = new PhieuNhapAdapter(getContext(), list);
        rc_PN.setAdapter(adapter);
    }
    private void setLayoutAnimation(int animResource){
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),animResource);
        rc_PN.setLayoutAnimation(layoutAnimationController);
    }
    public void action() {
        fl_btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_phieu_nhap, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                phieuNhap = new PhieuNhap();
                edNgayNhap = view.findViewById(R.id.phieunhap_edNgayNhap);
                edGiamGia = view.findViewById(R.id.phieunhap_edGiamGia);
                tvTongTien = view.findViewById(R.id.phieunhap_tvTongTien);
                edSoLuongNhap = view.findViewById(R.id.phieunhap_edsoLuong);

                //spinner sanpham
                spSanPham = view.findViewById(R.id.phieunhap_spSanPham);
                sanPhamList = WaveHouseDB.getInstance(getContext()).dao().getAllSanPham();
                spinner = new SanPhamSpinner(getContext(), sanPhamList);
                spSanPham.setAdapter(spinner);
                spSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String tenSanPham = sanPhamList.get(i).getTenSanPham();
                        phieuNhap.setMaSanPham(sanPhamList.get(i).getIdSanPham());
                        donGiaNhap = sanPhamList.get(i).getGiaNhap();
                        selectedPosition = i;
                        Toast.makeText(getContext(), "Sản phẩm: " + tenSanPham + "\t" + donGiaNhap, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
                //spinner nhacungcap
                spNhaCungCap = view.findViewById(R.id.phieunhap_spNhaCungCap);
                nhaCungCapList = WaveHouseDB.getInstance(getContext()).dao().getAllNhaCungCap();
                ArrayAdapter nhaCungCapAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, nhaCungCapList);
                spNhaCungCap.setAdapter(nhaCungCapAdapter);
                spNhaCungCap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String tenNhaCungCap = nhaCungCapList.get(i).getTenNhaCungCap();
                        phieuNhap.setMaNhaCungCap(nhaCungCapList.get(i).getMaNCC());
                        Toast.makeText(getContext(), "Nhà cung cấp: " + tenNhaCungCap, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                //spinner nhan vien
                spNhanVien = view.findViewById(R.id.phieunhap_spNhanVien);
                nhanVienList = WaveHouseDB.getInstance(getContext()).dao().getAllNhanVien();
                ArrayAdapter nhanVienAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, nhanVienList);
                spNhanVien.setAdapter(nhanVienAdapter);
                spNhanVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String tenNhanVien = nhanVienList.get(i).getTenNhanVien();
                        phieuNhap.setMaNhanVien(nhanVienList.get(i).getMaNV());
                        Toast.makeText(getContext(), "Sản phẩm: " + tenNhanVien, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
                //set date time
                edNgayNhap = view.findViewById(R.id.phieunhap_edNgayNhap);
                edNgayNhap.setOnClickListener(new View.OnClickListener() {
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
                                edNgayNhap.setText(mDay + "-" + mMonth + "-" + year);
                            }
                        }, calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH));
                        pickerDialog.show();
                    }
                });

                //edSoLuongNhap set tongTien
                edSoLuongNhap.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.toString().isEmpty() || s.toString().equals(0)) {
                            soLuong = 0;
                        } else {
                            soLuong = Integer.parseInt(String.valueOf(s));
                        }
                        result = soLuong * donGiaNhap;
                        tvTongTien.setText(String.valueOf(result) + " VNĐ");
                    }
                });
                edGiamGia.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.toString().isEmpty() || s.toString().equals(0)) {
                            giamGia = 0;
                        } else if (Integer.parseInt(s.toString()) > 100) {
                            giamGia = 0;
                        } else {
                            giamGia = result * (Double.parseDouble(edGiamGia.getText().toString()) / 100);
                        }
                        tongTienSauGiamGia = (int) (result - giamGia);
                        tvTongTien.setText(String.valueOf(tongTienSauGiamGia) + " VNĐ");
                    }
                });
                btnHoanTat = view.findViewById(R.id.phieunhap_btnSave);
                btnHoanTat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edNgayNhap.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Yêu cầu chọn ngày nhập ", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (edSoLuongNhap.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Số lượng nhập không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (edGiamGia.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Giảm giá không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        phieuNhap.setNgayNhap(edNgayNhap.getText().toString());
                        phieuNhap.setMaKhuyenMai(edGiamGia.getText().toString());
                        phieuNhap.setTongTien(String.valueOf(tongTienSauGiamGia));

                        if (WaveHouseDB.getInstance(getContext()).dao().addPhieuNhap(phieuNhap) > 0) {
                            if (selectedPosition != -1) {
                                sanPham = sanPhamList.get(selectedPosition);
                                if (WaveHouseDB.getInstance(getContext()).dao().update(sanPham.getSoLuongTon() + soLuong, sanPham.getIdSanPham()) > 0) {
                                    WaveHouseDB.getInstance(getContext()).dao().getAllSanPham();
                                    getData();
                                    edNgayNhap.setText("");
                                    edSoLuongNhap.setText("");
                                    edGiamGia.setText("");
                                    tvTongTien.setText(0 + " VNĐ");
                                    dialog.dismiss();
                                }
                            }
                            Toast.makeText(getContext(), "Tạo phiếu nhập thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Tạo phiếu nhập không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                imgBack = view.findViewById(R.id.phieunhap_imageBack);
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