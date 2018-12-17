package com.example.user.test.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.test.Class.Listview_donhang;
import com.example.user.test.Class.classConnect;
import com.example.user.test.R;
import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.example.user.test.Activity.DangNhap.makh;

public class thongtin_donhang extends AppCompatActivity {
    TextView txtsp,txtsl,txtgia,txtngay,txtdiachi,txtphi,txttinhtrang,txttongtien;
    Button btnhuy;
    //Ket noi SQL

    classConnect connectionDB = new classConnect();
    String z = "";
    Connection con = connectionDB.CONN();
    //-----------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_donhang);
        txtsp = (TextView)findViewById(R.id.txtsp);
        txtsl = (TextView)findViewById(R.id.txtsl);
        txtgia = (TextView)findViewById(R.id.txtgia);
        txtngay = (TextView)findViewById(R.id.txtngaydat);
        txtdiachi = (TextView)findViewById(R.id.txtdiachi);
        txtphi = (TextView)findViewById(R.id.txtphi);
        txttinhtrang = (TextView)findViewById(R.id.txttinhtrang);
        btnhuy=(Button)findViewById(R.id.btnHuyDH);
        txttongtien = (TextView)findViewById(R.id.txttongtien);
        final String mahd;
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dulieu");

        txtsp.setText(bundle.getString("ten"));
        txtsl.setText(bundle.getInt("soluong")+"");
        txtgia.setText(bundle.getFloat("gia")+"");
        txtngay.setText(bundle.getString("ngaydat"));
        txtdiachi.setText(bundle.getString("diachi"));
        txtphi.setText(bundle.getFloat("phigiao" )+ "");
        txttinhtrang.setText(bundle.getString("tinhtrang"));
        mahd=bundle.getString("mahd");
        txttongtien.setText(bundle.getFloat("tongtien")+"");
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag=0;
                if(txttinhtrang.getText().equals("Chưa nhận"))
                {
                    try {

                        if (con == null) {
                            z = "Error in connection with SQL server";
                        } else {
                            String query = "Delete FROM CHITIETHOADON WHERE MAHD='"+mahd+"'";
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery(query);
                            }


                    } catch (Exception ex) {
                        z = "Exceptions";

                    }
                    try {

                        if (con == null) {
                            z = "Error in connection with SQL server";
                        } else {
                            String query = "Delete FROM HOADON WHERE MAHD='"+mahd+"'";
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery(query);
                        }


                    } catch (Exception ex) {
                        z = "Exceptions";
                        flag=1;
                    }
                }
                else
                    flag=1;
                if(flag==0)
                    Toast.makeText(thongtin_donhang.this,"Bạn chỉ được hủy đơn trong tình trạng chưa nhận",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(thongtin_donhang.this,"Huy don hang thanh cong",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
