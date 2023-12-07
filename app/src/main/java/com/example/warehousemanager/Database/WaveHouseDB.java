package com.example.warehousemanager.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.warehousemanager.Model.KhachHang;
import com.example.warehousemanager.Model.LoaiSanPham;
import com.example.warehousemanager.Model.NhaCungCap;
import com.example.warehousemanager.Model.NhanVien;
import com.example.warehousemanager.Model.PhieuNhap;
import com.example.warehousemanager.Model.PhieuXuat;
import com.example.warehousemanager.Model.SanPham;

@Database(entities = {LoaiSanPham.class, SanPham.class , NhaCungCap.class,
KhachHang.class , NhanVien.class , PhieuNhap.class , PhieuXuat.class} , version = 1)
public abstract class WaveHouseDB extends RoomDatabase {
    public static final String DBName = "WaveHouse.db";

    public static WaveHouseDB instance;

    public static synchronized WaveHouseDB getInstance(Context mContext) {
        if (instance == null){
            instance = Room.databaseBuilder(mContext.getApplicationContext(), WaveHouseDB.class,DBName)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract DAO dao();
}
