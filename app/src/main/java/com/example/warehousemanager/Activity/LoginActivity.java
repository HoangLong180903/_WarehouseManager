package com.example.warehousemanager.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.warehousemanager.Database.WaveHouseDB;
import com.example.warehousemanager.MainActivity;
import com.example.warehousemanager.Model.NhanVien;
import com.example.warehousemanager.R;

public class LoginActivity extends AppCompatActivity {
    Button btn_Login;
    EditText edUsername , edPassword;
    CheckBox cbRemember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        action();
    }

    public void init(){
        edUsername = findViewById(R.id.login_edUsername);
        edPassword = findViewById(R.id.login_edPassword);
        cbRemember = findViewById(R.id.login_remember);
        btn_Login = findViewById(R.id.login_btn);
    }

    public void action(){
        SharedPreferences pref = getSharedPreferences("GHINHO_FILE", MODE_PRIVATE);
        edUsername.setText(pref.getString("TAIKHOAN",""));
        edPassword.setText(pref.getString("MATKHAU",""));
        cbRemember.setChecked(pref.getBoolean("GHINHO",false));
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (WaveHouseDB.getInstance(LoginActivity.this).dao().addNhanVien(new NhanVien("admin",2003,"098123123","admin","admin","","Admin","5000")) > 0){
                }

                if (edUsername.getText().toString().isEmpty() || edPassword.getText().toString().isEmpty()) {
                    edUsername.setError("Username Không được để trống");
                    edPassword.setError("Password không được để trống");
                } else {
                    if (WaveHouseDB.getInstance(LoginActivity.this).dao().checkLogin(edUsername.getText().toString(), edPassword.getText().toString()) > 0) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                        mIntent.putExtra("tentaikhoan", edUsername.getText().toString());
                        remember(edUsername.getText().toString(),edPassword.getText().toString(),cbRemember.isChecked());
                        startActivity(mIntent);
                        finish();
                    } else if (edUsername.getText().toString().equalsIgnoreCase("admin") && edPassword.getText().toString().equalsIgnoreCase("admin")) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                        remember(edUsername.getText().toString(),edPassword.getText().toString(),cbRemember.isChecked());
                        mIntent.putExtra("tentaikhoan", edUsername.getText().toString());
                        startActivity(mIntent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void remember(String u,String m  ,boolean status){
        SharedPreferences preferences= getSharedPreferences("GHINHO_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit =preferences.edit();

        if (!status){
            edit.clear();
        }else {
            edit.putString("TAIKHOAN",u);
            edit.putString("MATKHAU",m);
            edit.putBoolean("GHINHO",status);
        }
        edit.commit();
    }
}