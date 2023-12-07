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
import com.example.warehousemanager.Model.NhaCungCap;
import com.example.warehousemanager.R;

import java.util.List;

public class NhaCungCapAdapter extends RecyclerView.Adapter<NhaCungCapAdapter.MyViewHolder>{
    Context mContext;
    List<NhaCungCap> list;

    public NhaCungCapAdapter(Context mContext, List<NhaCungCap> list) {
        this.mContext = mContext;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_nha_cung_cap,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NhaCungCap nhaCungCap = list.get(position);

        holder.tvTenNhaCungCap.setText("Nhà Cung Cấp: "+nhaCungCap.getTenNhaCungCap());
        holder.tvSoDienThoai.setText(""+nhaCungCap.getSoDienThoai());
        if (nhaCungCap.getTrangThai().equals("Còn hoạt động")){
            holder.imgTrangThai.setImageResource(R.drawable.baseline_circle_24_online);

        }else{
            holder.imgTrangThai.setImageResource(R.drawable.baseline_circle_24_offline);
        }
        holder.tvTrangThai.setText(""+nhaCungCap.getTrangThai());
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
        ImageView  imageMenu , imgTrangThai;
        TextView tvTenNhaCungCap , tvSoDienThoai , tvTrangThai;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTrangThai = itemView.findViewById(R.id.nhacungcap_itemImageTrangThai);
            imageMenu = itemView.findViewById(R.id.nhacungcap_itemMenu);
            tvTenNhaCungCap = itemView.findViewById(R.id.nhacungcap_itemTenNCC);
            tvSoDienThoai = itemView.findViewById(R.id.nhacungcap_itemSDT);
            tvTrangThai = itemView.findViewById(R.id.nhacungcap_itemTrangThai);
        }
    }
}
