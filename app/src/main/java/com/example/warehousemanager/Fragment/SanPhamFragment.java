package com.example.warehousemanager.Fragment;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.warehousemanager.Adapter.SanPhamAdapter;
import com.example.warehousemanager.Database.WaveHouseDB;
import com.example.warehousemanager.Model.LoaiSanPham;
import com.example.warehousemanager.Model.NhaCungCap;
import com.example.warehousemanager.Model.SanPham;
import com.example.warehousemanager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SanPhamFragment extends Fragment {


    RecyclerView rc_SP;
    FloatingActionButton fl_btnAdd;

    //component
    ImageView imgSanPham , imgBack;
    EditText edTenSanPham , edSoLuongTon , edGiaNhap , edGiaXuat ;
    CheckBox cbTrangThai;
    Button btnSave;

    Spinner spNhaCungCap , spLoaiSanPham;
    private List<NhaCungCap> nhaCungCapList;
    private List<LoaiSanPham> loaiSanPhamList;

    private List<SanPham> list;
    SanPhamAdapter adapter;

    SanPham sanPham;

    private static final int PICK_IMAGE_REQUEST = 100;
    static byte[] imageContent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_san_pham, container, false);
        ((Activity)getContext()).setTitle("Sản Phẩm");
        init(view);
        getData();
        openDialog();
        return view;
    }

    public void init(View view){
        rc_SP = view.findViewById(R.id.rc_sanPham);
        fl_btnAdd = view.findViewById(R.id.sanpham_btnAdd);
    }
    public void getData(){
        setLayoutAnimation(R.anim.layout_anim);
        list = WaveHouseDB.getInstance(getContext()).dao().getAllSanPham();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rc_SP.setLayoutManager(manager);
        adapter = new SanPhamAdapter(getContext(), list);
        rc_SP.setAdapter(adapter);
    }
    private void setLayoutAnimation(int animResource){
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),animResource);
        rc_SP.setLayoutAnimation(layoutAnimationController);
    }
    public void openDialog(){
        fl_btnAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingInflatedId")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_san_pham, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                sanPham = new SanPham();
                imgBack = view.findViewById(R.id.sanpham_imageBack);
                edTenSanPham = view.findViewById(R.id.sanpham_edTenSP);
                edSoLuongTon = view.findViewById(R.id.sanpham_edSoLuongTon);
                edGiaNhap = view.findViewById(R.id.sanpham_edGiaNhap);
                edGiaXuat = view.findViewById(R.id.sanpham_edGiaXuat);
                cbTrangThai = view.findViewById(R.id.sanpham_cbTrangThai);
                //khoởi tạo image sản phẩm
                imgSanPham = view.findViewById(R.id.sanpham_imageSanPham);
                imgSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent,PICK_IMAGE_REQUEST);
                    }
                });
                //khởi tạo spinner nhà cung cấp
                spNhaCungCap = view.findViewById(R.id.sanpham_spNhaCungCap);
                nhaCungCapList = new ArrayList<>();
                nhaCungCapList = WaveHouseDB.getInstance(getContext()).dao().getAllNhaCungCap();
                ArrayAdapter nhaCungCapAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, nhaCungCapList);
                spNhaCungCap.setAdapter(nhaCungCapAdapter);
                spNhaCungCap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String tenNhaCungCap = nhaCungCapList.get(i).getTenNhaCungCap();
                        sanPham.setMaNCC(nhaCungCapList.get(i).getMaNCC());
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                //khởi tạo spinner loại sản phẩm
                spLoaiSanPham = view.findViewById(R.id.sanpham_spLoaiSanPham);
                loaiSanPhamList = new ArrayList<>();
                loaiSanPhamList = WaveHouseDB.getInstance(getContext()).dao().getAllLoaiSanPham();
                ArrayAdapter loaiSanPhamAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, loaiSanPhamList);
                spLoaiSanPham.setAdapter(loaiSanPhamAdapter);
                spLoaiSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String tenLoaiSanPham = loaiSanPhamList.get(i).getTenLoaiSanPham();
                        sanPham.setIdLoaiSanPham(loaiSanPhamList.get(i).getIdLoaiSanPham());
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                btnSave = view.findViewById(R.id.sanpham_btnSave);
                //btnsave
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cbTrangThai.isChecked()){
                            sanPham.setTrangThai("Cho phép bán");
                        }else{
                            sanPham.setTrangThai("Ngưng bán");
                        }
                        //validate check
                        if (edTenSanPham.getText().toString().isEmpty()){
                            Toast.makeText(getContext(), "Tên sản phâm không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (edSoLuongTon.getText().toString().isEmpty()){
                            Toast.makeText(getContext(), "Số lượng tồn không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (edGiaNhap.getText().toString().isEmpty()){
                            Toast.makeText(getContext(), "Giá nhập không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (edGiaXuat.getText().toString().isEmpty()){
                            Toast.makeText(getContext(), "Giá xuất không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        sanPham.setImage(imageContent);
                        sanPham.setTenSanPham(edTenSanPham.getText().toString().trim());
                        sanPham.setSoLuongTon(Integer.parseInt(""+edSoLuongTon.getText().toString().trim()));
                        sanPham.setGiaNhap(Integer.parseInt(""+edGiaNhap.getText().toString().trim()));
                        sanPham.setGiaXuat(Integer.parseInt(""+edGiaXuat.getText().toString().trim()));

                        if (WaveHouseDB.getInstance(getContext()).dao().addSanPham(sanPham) > 0){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            edTenSanPham.setText("");
                            edSoLuongTon.setText("");
                            edGiaNhap.setText("");
                            edGiaXuat.setText("");
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
                        edTenSanPham.setText("");
                        edSoLuongTon.setText("");
                        edGiaNhap.setText("");
                        edGiaXuat.setText("");
                        getData();
                        dialog.dismiss();
                    }
                });
            }
        });
    }
    //thiết lập lấy hình ảnh
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri imageUri = intent.getData();
            try {
                InputStream inputStream = getContext().getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgSanPham.setImageBitmap(bitmap);
                imageContent = getBytes(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //lấy loại hình ảnh
    private byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
}