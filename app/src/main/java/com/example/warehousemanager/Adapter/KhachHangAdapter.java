package com.example.warehousemanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehousemanager.Model.KhachHang;
import com.example.warehousemanager.Model.NhaCungCap;
import com.example.warehousemanager.R;

import java.util.List;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.MyViewHolder>{
    Context mContext;
    List<KhachHang> list;

    public KhachHangAdapter(Context mContext, List<KhachHang> list) {
        this.mContext = mContext;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_khach_hang,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        KhachHang khachHang = list.get(position);
        holder.tvTenKhachHang.setText("Nhà Cung Cấp: "+khachHang.getTenKhachHang());
        holder.tvSoDienThoai.setText(""+khachHang.getSoDT());
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
        ImageView  imageMenu ;
        TextView tvTenKhachHang , tvSoDienThoai ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMenu = itemView.findViewById(R.id.khachhang_itemMenu);
            tvTenKhachHang = itemView.findViewById(R.id.khachhang_itemTenKH);
            tvSoDienThoai = itemView.findViewById(R.id.khachhang_itemSDT);
        }
    }
}
