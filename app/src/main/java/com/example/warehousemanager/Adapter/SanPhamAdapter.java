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

import com.example.warehousemanager.Model.NhaCungCap;
import com.example.warehousemanager.Model.SanPham;
import com.example.warehousemanager.R;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.MyViewHolder>{
    Context mContext;
    List<SanPham> list;

    public SanPhamAdapter(Context mContext, List<SanPham> list) {
        this.mContext = mContext;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_san_pham,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SanPham sanPham = list.get(position);
        Bitmap imageContent = BitmapFactory.decodeByteArray(sanPham.getImage(), 0, sanPham.getImage().length);
        holder.imageSP.setImageBitmap(imageContent);
        holder.tvTenSanPham.setText("Sản phẩm: "+sanPham.getTenSanPham());
        holder.tvSoLuongTon.setText("Số lượng tồn: "+sanPham.getSoLuongTon());
        if (sanPham.getTrangThai().equals("Cho phép bán")){
            holder.imgTrangThai.setImageResource(R.drawable.baseline_circle_24_online);

        }else{
            holder.imgTrangThai.setImageResource(R.drawable.baseline_circle_24_offline);
        }
        holder.tvTrangThai.setText(""+sanPham.getTrangThai());
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
        ImageView  imageSP, imageMenu , imgTrangThai;
        TextView tvTenSanPham , tvSoLuongTon , tvTrangThai;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSP = itemView.findViewById(R.id.sanpham_itemImage);
            imgTrangThai = itemView.findViewById(R.id.sanpham_itemImageTrangThai);
            imageMenu = itemView.findViewById(R.id.sanpham_itemMenu);
            tvTenSanPham = itemView.findViewById(R.id.sanpham_itemTenSP);
            tvSoLuongTon = itemView.findViewById(R.id.sanpham_itemSlgTon);
            tvTrangThai = itemView.findViewById(R.id.sanpham_itemTrangThai);
        }
    }
}
