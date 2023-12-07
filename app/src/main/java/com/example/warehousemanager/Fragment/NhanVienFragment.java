package com.example.warehousemanager.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.warehousemanager.Adapter.NhaCungCapAdapter;
import com.example.warehousemanager.Adapter.NhanVienAdapter;
import com.example.warehousemanager.Adapter.SanPhamAdapter;
import com.example.warehousemanager.Database.WaveHouseDB;
import com.example.warehousemanager.Model.ChucVu;
import com.example.warehousemanager.Model.LoaiSanPham;
import com.example.warehousemanager.Model.NhaCungCap;
import com.example.warehousemanager.Model.NhanVien;
import com.example.warehousemanager.Model.SanPham;
import com.example.warehousemanager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class NhanVienFragment extends Fragment {

    RecyclerView rc_NV;
    FloatingActionButton fl_btnAdd;

    //component
    ImageView imgBack;
    EditText edTenNhanVien , edNamSinh , edSoDT , edTenTaiKhoan , edMatKhau ;
    RadioButton rbDangLam, rbTamNghi , rbDaNghi;
    Button btnSave;

    Spinner spChucVu ;
    private List<ChucVu> chucVuList;

    private List<NhanVien> list;
    NhanVienAdapter adapter;

    NhanVien nhanVien;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nhan_vien, container, false);
        ((Activity)getContext()).setTitle("Nhân Viên");
        init(view);
        getData();
        openDialog();
        return view;
    }

    public void init(View view){
        rc_NV = view.findViewById(R.id.rc_nhanVien);
        fl_btnAdd = view.findViewById(R.id.nhanvien_btnAdd);
    }
    public void getData(){
        setLayoutAnimation(R.anim.layout_anim);
        list = WaveHouseDB.getInstance(getContext()).dao().getAllNhanVien();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rc_NV.setLayoutManager(manager);
        adapter = new NhanVienAdapter(getContext(), list);
        rc_NV.setAdapter(adapter);
    }
    private void setLayoutAnimation(int animResource){
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),animResource);
        rc_NV.setLayoutAnimation(layoutAnimationController);
    }
    public void openDialog(){
        fl_btnAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingInflatedId")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_nhan_vien, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                nhanVien = new NhanVien();
                imgBack = view.findViewById(R.id.nhanvien_imageBack);
                edTenNhanVien = view.findViewById(R.id.nhanvien_edTenNhanVien);
                edNamSinh = view.findViewById(R.id.nhanvien_edNamSinh);
                edSoDT = view.findViewById(R.id.nhanvien_edSoDT);
                edTenTaiKhoan = view.findViewById(R.id.nhanvien_edTenTaiKhoan);
                edMatKhau = view.findViewById(R.id.nhanvien_edMatKhau);
                rbDangLam = view.findViewById(R.id.nhanvien_rbDangLam);
                rbTamNghi = view.findViewById(R.id.nhanvien_rbTamNghi);
                rbDaNghi = view.findViewById(R.id.nhanvien_rbDaNghi);
                chucVuList = new ArrayList<>();
                chucVuList.add(new ChucVu("Admin","500$"));
                chucVuList.add(new ChucVu("Quản Lý", "300$"));
                chucVuList.add(new ChucVu("Nhân Viên", "200$"));
                spChucVu = view.findViewById(R.id.nhanvien_spChucVu);
                ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, chucVuList);
                spChucVu.setAdapter(adapter);
                spChucVu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String chucVu = chucVuList.get(i).getChucVu();
                        String mucLuong = chucVuList.get(i).getMucLuong();
                        nhanVien.setChucVu(chucVuList.get(i).getChucVu());
                        nhanVien.setMucLuong(chucVuList.get(i).getMucLuong());
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                btnSave = view.findViewById(R.id.nhanvien_btnSave);
                //btnsave
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (rbDangLam.isChecked()){
                            nhanVien.setTrangThai("Đang làm");
                        }else if(rbTamNghi.isChecked()){
                            nhanVien.setTrangThai("Tạm nghỉ");
                        }else{
                            nhanVien.setTrangThai("Đã nghỉ");
                        }
                        //validate check
                        if (edTenNhanVien.getText().toString().isEmpty()){
                            Toast.makeText(getContext(), "Tên không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (edNamSinh.getText().toString().isEmpty()){
                            Toast.makeText(getContext(), "Năm không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (edSoDT.getText().toString().isEmpty()){
                            Toast.makeText(getContext(), "Số điện thoại không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (edTenTaiKhoan.getText().toString().isEmpty()){
                            Toast.makeText(getContext(), "Tên tài khoản không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (edMatKhau.getText().toString().isEmpty()){
                            Toast.makeText(getContext(), "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        nhanVien.setTenNhanVien(edTenNhanVien.getText().toString().trim());
                        nhanVien.setNamSinh(Integer.parseInt(""+edNamSinh.getText().toString().trim()));
                        nhanVien.setSoDT(edSoDT.getText().toString().trim());
                        nhanVien.setTenTaiKhoan(edTenTaiKhoan.getText().toString().trim());
                        nhanVien.setMatKhau(edMatKhau.getText().toString().trim());

                        if (WaveHouseDB.getInstance(getContext()).dao().addNhanVien(nhanVien) > 0){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            edTenNhanVien.setText("");
                            edNamSinh.setText("");
                            edSoDT.setText("");
                            edTenTaiKhoan.setText("");
                            edMatKhau.setText("");
                            getData();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
                imgBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edTenNhanVien.setText("");
                        edNamSinh.setText("");
                        edSoDT.setText("");
                        edTenTaiKhoan.setText("");
                        edMatKhau.setText("");
                        getData();
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}