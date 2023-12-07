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

import com.example.warehousemanager.Database.WaveHouseDB;
import com.example.warehousemanager.Model.LoaiSanPham;
import com.example.warehousemanager.Model.SanPham;
import com.example.warehousemanager.Model.ThongKe;
import com.example.warehousemanager.R;

import java.util.List;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.MyViewHolder>{
    Context mContext;
    List<ThongKe> list;

    public Top10Adapter(Context mContext, List<ThongKe> list) {
        this.mContext = mContext;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_top10,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ThongKe thongKe = list.get(position);
        SanPham sanPham = WaveHouseDB.getInstance(mContext).dao().getIdSanPham(String.valueOf(thongKe.getMaSanPham()));
        Bitmap imageContent = BitmapFactory.decodeByteArray(sanPham.getImage(), 0, sanPham.getImage().length);
        holder.imageSP.setImageBitmap(imageContent);
        holder.tvTenSanPham.setText("Tên Sản Phẩm: "+sanPham.getTenSanPham());
        holder.tvSoLuong.setText("Số Lượng: "+thongKe.getSoLuong());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static final class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageSP;
        TextView tvTenSanPham , tvSoLuong;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageSP = itemView.findViewById(R.id.top10_itemImage);
            tvTenSanPham = itemView.findViewById(R.id.top10_itemTenSP);
            tvSoLuong = itemView.findViewById(R.id.top10_itemSoLuong);
        }
    }
}
