package com.example.user.test.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.test.Class.Listview_donhang;
import com.example.user.test.Class.classConnect;
import com.example.user.test.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.user.test.Activity.DangNhap.manv;

public class GiaoHang extends AppCompatActivity {
    TextView txtsp,txtsl,txtgia,txtngay,txtdiachi,txtphi,txttinhtrang,txttongtien;
    Button btnDaGiao,btnThatBai;
    final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    ListView listview_giao;
    ArrayList<Listview_donhang> listview_donhangs;
    String key="";
    //Ket noi SQL

    classConnect connectionDB = new classConnect();
    String z = "";
    Connection con = connectionDB.CONN();
    //-----------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_hang);
        con = connectionDB.CONN();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dulieu");
        txtsp=(TextView)findViewById(R.id.txtspgh);
        txtsl=(TextView)findViewById(R.id.txtslgh);
        txtdiachi=(TextView)findViewById(R.id.txtdiachigh);
        txtgia=(TextView)findViewById(R.id.txtgiagh);
        txtphi=(TextView)findViewById(R.id.txtphigh);
        txtngay=(TextView)findViewById(R.id.txtngaydatgh);
        txttinhtrang=(TextView)findViewById(R.id.txttinhtranggh);
        txttongtien=(TextView)findViewById(R.id.txttongtiengh);
        btnDaGiao= (Button)findViewById(R.id.btnDaGiao);
        btnThatBai=(Button)findViewById(R.id.btnThatBai);
        //---------------------------------------------------
        final TextView txtmahd = (TextView)findViewById(R.id.txtmahd);
        txtmahd.setText(bundle.getString("mahd"));
        txtsp.setText(bundle.getString("ten"));
        txtsl.setText(bundle.getInt("soluong")+"");
        txtgia.setText(bundle.getFloat("gia")+"");
        txtngay.setText(bundle.getString("ngaydat"));
        txtdiachi.setText(bundle.getString("diachi"));
        txtphi.setText(bundle.getFloat("phigiao" )+ "");
        txttinhtrang.setText(bundle.getString("tinhtrang"));
        txttongtien.setText(bundle.getFloat("tongtien")+"");
        btnDaGiao.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             try {

                                                 if (con == null) {
                                                     z = "Error in connection with SQL server";
                                                 } else {
                                                     String query = "UPDATE HOADON SET TINHTRANGTHANHTOAN=N'Đã giao' where MAHD='" + txtmahd.getText() + "'";
                                                     Statement stmt = con.createStatement();
                                                     if (stmt.executeUpdate(query) > 0) { // Dùng lệnh executeUpdate cho các lệnh CRUD
                                                         con.close();
                                                         Toast.makeText(GiaoHang.this,"Thanh cong",Toast.LENGTH_SHORT).show();
                                                     } else {
                                                         con.close();
                                                         Toast.makeText(GiaoHang.this,"That bai",Toast.LENGTH_SHORT).show();
                                                     }

                                                 }
                                             } catch (Exception ex) {
                                                 z = "Exceptions";

                                             }
                                         }
        });
        btnThatBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        String query = "UPDATE HOADON\n" +
                                "SET TINHTRANGTHANHTOAN=N'Thất bại'\n" +
                                "where MAHD='" + txtmahd.getText() + "'";
                        Statement stmt = con.createStatement();
                        if (stmt.executeUpdate(query) > 0) { // Dùng lệnh executeUpdate cho các lệnh CRUD
                            con.close();
                            Toast.makeText(GiaoHang.this,"Thanh cong",Toast.LENGTH_SHORT).show();
                        } else {
                            con.close();
                            Toast.makeText(GiaoHang.this,"That bai",Toast.LENGTH_SHORT).show();
                        }

                    }
                } catch (Exception ex) {
                    z = "Exceptions";
                }
            }
        });

    }
}
