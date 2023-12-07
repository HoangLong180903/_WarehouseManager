package com.example.warehousemanager.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehousemanager.Model.LoaiSanPham;
import com.example.warehousemanager.R;

import java.util.List;

public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.MyViewHolder>{
    Context mContext;
    List<LoaiSanPham> list;

    public LoaiSanPhamAdapter(Context mContext, List<LoaiSanPham> list) {
        this.mContext = mContext;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_loai_sp,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LoaiSanPham loaiSanPham = list.get(position);
        Bitmap imageContent = BitmapFactory.decodeByteArray(loaiSanPham.getImage(), 0, loaiSanPham.getImage().length);
        holder.imageLoaiSP.setImageBitmap(imageContent);
        holder.tvTenLoai.setText("Tên Loại: "+loaiSanPham.getTenLoaiSanPham());
        holder.tvMota.setText("Mô tả: "+loaiSanPham.getMoTaLoaiSanPham());
        holder.imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static final class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageLoaiSP , imageMenu;
        TextView tvTenLoai , tvMota;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageLoaiSP = itemView.findViewById(R.id.loaisp_itemImage);
            imageMenu = itemView.findViewById(R.id.loaisp_itemMenu);
            tvTenLoai = itemView.findViewById(R.id.loaisp_itemTenLoaiSP);
            tvMota = itemView.findViewById(R.id.loaisp_itemMota);
        }
    }
}
