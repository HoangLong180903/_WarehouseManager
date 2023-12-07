package com.example.warehousemanager.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.warehousemanager.Adapter.LoaiSanPhamAdapter;
import com.example.warehousemanager.Adapter.NhaCungCapAdapter;
import com.example.warehousemanager.Database.WaveHouseDB;
import com.example.warehousemanager.Model.LoaiSanPham;
import com.example.warehousemanager.Model.NhaCungCap;
import com.example.warehousemanager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class NhaCungCapFragment extends Fragment {

    ImageView imgBack;
    EditText edTenNCC , edSDT , edDiaChi ;
    CheckBox cbTrangThai;
    Button btnSave;
    RecyclerView rc_NCC;
    FloatingActionButton fl_btnAdd;

    List<NhaCungCap> list = new ArrayList<>();
    NhaCungCapAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_nha_cung_cap, container, false);
        ((Activity)getContext()).setTitle("Nhà Cung Cấp");
        init(view);
        getData();
        openDialog();
        return  view;
    }

    public void init(View view){
        rc_NCC = view.findViewById(R.id.rc_nhaCungCap);
        fl_btnAdd = view.findViewById(R.id.nhacungcap_btnAdd);
    }

    public void getData(){
        setLayoutAnimation(R.anim.layout_anim);
        list = WaveHouseDB.getInstance(getContext()).dao().getAllNhaCungCap();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rc_NCC.setLayoutManager(manager);
        adapter = new NhaCungCapAdapter(getContext(), list);
        rc_NCC.setAdapter(adapter);
    }
    private void setLayoutAnimation(int animResource){
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),animResource);
        rc_NCC.setLayoutAnimation(layoutAnimationController);
    }
    public void openDialog(){
        fl_btnAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingInflatedId")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_nha_cung_cap, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                NhaCungCap nhaCungCap = new NhaCungCap();
                imgBack = view.findViewById(R.id.nhacungcap_imageBack);
                edTenNCC = view.findViewById(R.id.nhacungcap_edTenNCC);
                edSDT = view.findViewById(R.id.nhacungcap_edSoDT);
                edDiaChi = view.findViewById(R.id.nhacungcap_edDiaChi);
                btnSave = view.findViewById(R.id.nhacungcap_btnSave);
                cbTrangThai = view.findViewById(R.id.nhacungcap_cbTrangThai);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (edTenNCC.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Tên không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (edSDT.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Số điện thoại không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (edDiaChi.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Địa chỉ không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (cbTrangThai.isChecked()){
                            nhaCungCap.setTrangThai("Còn hoạt động");
                        }else{
                            nhaCungCap.setTrangThai("Ngưng hoạt động");
                        }
                        nhaCungCap.setTenNhaCungCap(edTenNCC.getText().toString().trim());
                        nhaCungCap.setSoDienThoai(edSDT.getText().toString().trim());
                        nhaCungCap.setDiaChi(edDiaChi.getText().toString().trim());
                        if (WaveHouseDB.getInstance(getContext()).dao().addNhaCungCap(nhaCungCap) > 0){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            edTenNCC.setText("");
                            edSDT.setText("");
                            edDiaChi.setText("");
                            getData();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
                imgBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edTenNCC.setText("");
                        edSDT.setText("");
                        edDiaChi.setText("");
                        getData();
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}