package com.example.user.test.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.test.Class.classConnect;
import com.example.user.test.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DangNhap extends AppCompatActivity {
    private Connection connection;
    EditText edtTaiKhoan;
    EditText edtMatKhau;
    Button btnDangNhap, btnDangKy, btnQuenMK;
    classConnect connectionDB = new classConnect();
    String z = "";
    Connection con = connectionDB.CONN();
    CheckBox checkNV;
    public static String makh;
    public static String manv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtTaiKhoan = (EditText) findViewById(R.id.edtTaiKhoan);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDangKy = (Button) findViewById(R.id.btnDangKyDN);
        btnQuenMK = (Button) findViewById(R.id.btnQuenMK);
        checkNV = (CheckBox) findViewById(R.id.checkBox);
        //------------------------------------------------------
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ktKetNoi();


            }
        });
        btnQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this, QuenMatKhau.class);
                startActivity(intent);
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);
            }
        });

    }

    public int ktKetNoi() {
        try {

            if (con == null) {
                z = "Error in connection with SQL server";
            } else {
                if (checkNV.isChecked()) {
                    String query = "select * from QL_NGUOIDUNG WHERE TENDANGNHAP='" + edtTaiKhoan.getText() + "' AND MATKHAU='" + edtMatKhau.getText() + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        Toast.makeText(DangNhap.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                        manv=rs.getString(1);
                        Intent intent = new Intent(DangNhap.this, Nhanvien.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(DangNhap.this, "Vui long kiểm tra lại tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
                } else {
                    String query = "select * from KHACHHANG WHERE TAIKHOAN_KH='" + edtTaiKhoan.getText() + "' AND MATKHAU='" + edtMatKhau.getText() + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        Toast.makeText(DangNhap.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                        makh=rs.getString(1);
                        Intent intent = new Intent(DangNhap.this, MainActivity.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(DangNhap.this, "Vui long kiểm tra lại tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
                }


            }
        } catch (Exception ex) {
            z = "Exceptions";
        }
        return 0;

    }

}

