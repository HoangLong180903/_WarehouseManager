package com.example.warehousemanager.Adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.warehousemanager.Model.SanPham;
import com.example.warehousemanager.R;

import java.util.List;


public class SanPhamSpinner implements SpinnerAdapter {
    Context mContext;
    List<SanPham> list;

    public SanPhamSpinner(Context mContext, List<SanPham> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_custom, null);
        TextView tv_tenSanPham = convertView.findViewById(R.id.spinner_tenSanPham);
        TextView tv_donGia = convertView.findViewById(R.id.spinner_donGiaNhap);
        ImageView imgSanPham = convertView.findViewById(R.id.spinner_imageSanPham);
        String tenSanPham = list.get(position).getTenSanPham();
        int donGia = list.get(position).getGiaNhap();
        tv_tenSanPham.setText(tenSanPham);
        tv_donGia.setText(""+donGia);
        Bitmap imageContent = BitmapFactory.decodeByteArray(list.get(position).getImage(), 0, list.get(position).getImage().length);
        imgSanPham.setImageBitmap(imageContent);
        return convertView;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_custom, null);
        TextView tv_tenSanPham = convertView.findViewById(R.id.spinner_tenSanPham);
        TextView tv_donGia = convertView.findViewById(R.id.spinner_donGiaNhap);
        ImageView imgSanPham = convertView.findViewById(R.id.spinner_imageSanPham);
        String tenSanPham = list.get(position).getTenSanPham();
        int donGia = list.get(position).getGiaNhap();
        tv_tenSanPham.setText(tenSanPham);
        tv_donGia.setText(""+donGia);
        Bitmap imageContent = BitmapFactory.decodeByteArray(list.get(position).getImage(), 0, list.get(position).getImage().length);
        imgSanPham.setImageBitmap(imageContent);
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
