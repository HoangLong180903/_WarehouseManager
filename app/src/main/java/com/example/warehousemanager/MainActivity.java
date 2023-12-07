package com.example.warehousemanager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.warehousemanager.Activity.LoginActivity;
import com.example.warehousemanager.Database.WaveHouseDB;
import com.example.warehousemanager.Fragment.BaoCaoThongKeFragment;
import com.example.warehousemanager.Fragment.HomeFragment;
import com.example.warehousemanager.Fragment.KhachHangFragment;
import com.example.warehousemanager.Fragment.LoaiSanPhamFragment;
import com.example.warehousemanager.Fragment.NhaCungCapFragment;
import com.example.warehousemanager.Fragment.NhanVienFragment;
import com.example.warehousemanager.Fragment.PhieuNhapFragment;
import com.example.warehousemanager.Fragment.PhieuXuatFragment;
import com.example.warehousemanager.Fragment.SanPhamFragment;
import com.example.warehousemanager.Fragment.ThongTinCaNhanFragment;
import com.example.warehousemanager.Model.NhanVien;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView nav;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setNhanVien();
        setDraw();
    }

    //ánh xạ
    public void init(){
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.tool_bar);
        nav = findViewById(R.id.navigationView);

    }
    public void setNhanVien(){
        Intent intent = getIntent();
        user = intent.getStringExtra("tentaikhoan");
        NhanVien nhanVien = WaveHouseDB.getInstance(this).dao().getIdLogin(user);
        if (nhanVien.getChucVu().equals("Nhân Viên")){
            nav.getMenu().findItem(R.id.action_NV).setVisible(false);
            nav.getMenu().findItem(R.id.action_KH).setVisible(false);
            nav.getMenu().findItem(R.id.action_NCC).setVisible(false);
            nav.getMenu().findItem(R.id.action_baoCaoThongKe).setVisible(false);
        }else if (nhanVien.getChucVu().equals("Quản Lý")){
            nav.getMenu().findItem(R.id.action_NV).setVisible(false);
            nav.getMenu().findItem(R.id.action_KH).setVisible(false);
            nav.getMenu().findItem(R.id.action_NCC).setVisible(false);
        }
    }

    //thiết lập drawable , toolbar , navigation
    public void setDraw(){
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_draw,R.string.close_draw);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        bundleData();
        nav.setItemIconTintList(null);
        nav.setVerticalScrollBarEnabled(false);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home){
                    bundleData();
                }else if(item.getItemId() == R.id.action_LoaiSP){
                    replaceFragment(new LoaiSanPhamFragment());
                }else if(item.getItemId() == R.id.action_SP){
                    replaceFragment(new SanPhamFragment());
                }else if(item.getItemId() == R.id.action_NCC){
                    replaceFragment(new NhaCungCapFragment());
                }
                else if(item.getItemId() == R.id.action_KH){
                    replaceFragment(new KhachHangFragment());
                }
                else if(item.getItemId() == R.id.action_NV){
                    replaceFragment(new NhanVienFragment());
                }
                else if(item.getItemId() == R.id.action_hoaDonNhapKho){
                    replaceFragment(new PhieuNhapFragment());
                }
                else if(item.getItemId() == R.id.action_hoaDonXuatKho){
                    replaceFragment(new PhieuXuatFragment());
                }else if(item.getItemId() == R.id.action_baoCaoThongKe){
                    replaceFragment(new BaoCaoThongKeFragment());
                }
                else if(item.getItemId() == R.id.action_TTCN){
                    ThongTinCaNhanFragment thongTinCaNhanFragment = new ThongTinCaNhanFragment();
                    NhanVien nhanVien = WaveHouseDB.getInstance(MainActivity.this).dao().getIdLogin(user);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("thongTinNhanVien", nhanVien);
                    thongTinCaNhanFragment.setArguments(bundle);
                    replaceFragment(thongTinCaNhanFragment);
                }
                else if(item.getItemId() == R.id.action_dangXuat){
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.linerLayout,fragment);
        transaction.commit();
    }

    public void bundleData(){
        Intent intentThongTin = getIntent();
        user = intentThongTin.getStringExtra("tentaikhoan");
        NhanVien nhanVien = WaveHouseDB.getInstance(this).dao().getIdLogin(user);
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tenNhanVien", nhanVien.getTenNhanVien());
        fragment.setArguments(bundle);
        replaceFragment(fragment);
    }
}