package com.example.warehousemanager.Fragment;

import static android.app.Activity.RESULT_OK;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.warehousemanager.Adapter.LoaiSanPhamAdapter;
import com.example.warehousemanager.Database.WaveHouseDB;
import com.example.warehousemanager.Model.LoaiSanPham;
import com.example.warehousemanager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class LoaiSanPhamFragment extends Fragment {


    ImageView img, imageBack;
    EditText edTenLoaiSP , edMoTa;
    Button btnSave;
    FloatingActionButton fl_btnAdd;
    RecyclerView rc_loaiSP;
    List<LoaiSanPham> list = new ArrayList<>();
    LoaiSanPhamAdapter adapter;

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
        View view = inflater.inflate(R.layout.fragment_loai_san_pham, container, false);
        ((Activity)getContext()).setTitle("Loại Sản Phẩm");
        init(view);
        getData();
        openDialog();
        return view;
    }

    public void init(View view){
        rc_loaiSP = view.findViewById(R.id.rc_loaiSP);
        fl_btnAdd = view.findViewById(R.id.loaisp_btnAdd);
    }
    public void getData(){
        setLayoutAnimation(R.anim.layout_anim);
        list = WaveHouseDB.getInstance(getContext()).dao().getAllLoaiSanPham();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rc_loaiSP.setLayoutManager(manager);
        adapter = new LoaiSanPhamAdapter(getContext(), list);
        rc_loaiSP.setAdapter(adapter);
    }
    public void openDialog(){
        fl_btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loai_san_pham, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
//                Window window = dialog.getWindow();
//                if (window == null){
//                    return;
//                }
//                window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
//                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                WindowManager.LayoutParams windowAttributes = window.getAttributes();
//                windowAttributes.gravity = gravity;
//                window.setAttributes(windowAttributes);
                LoaiSanPham loaiSanPham = new LoaiSanPham();
                img = view.findViewById(R.id.loaisp_imageView);
                imageBack = view.findViewById(R.id.loaisp_imageBack);
                edTenLoaiSP = view.findViewById(R.id.loaisp_edTenLoaiSP);
                edMoTa = view.findViewById(R.id.loaisp_edMoTaLoaiSP);
                btnSave = view.findViewById(R.id.loaisp_btnSave);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent,PICK_IMAGE_REQUEST);
                    }
                });
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (edTenLoaiSP.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Tên loại sản phẩm không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        loaiSanPham.setTenLoaiSanPham(edTenLoaiSP.getText().toString().trim());
                        loaiSanPham.setMoTaLoaiSanPham(edMoTa.getText().toString().trim());
                        loaiSanPham.setImage(imageContent);

                        if (WaveHouseDB.getInstance(getContext()).dao().addLoaiSanPham(loaiSanPham) > 0){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            edTenLoaiSP.setText("");
                            edMoTa.setText("");
                            img.setImageBitmap(null);
                            getData();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
                imageBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edTenLoaiSP.setText("");
                        edMoTa.setText("");
                        img.setImageBitmap(null);
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
                img.setImageBitmap(bitmap);
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

    private void setLayoutAnimation(int animResource){
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),animResource);
        rc_loaiSP.setLayoutAnimation(layoutAnimationController);
    }
}