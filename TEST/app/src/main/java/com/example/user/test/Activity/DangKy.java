package com.example.user.test.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.user.test.Class.classConnect;
import com.example.user.test.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DangKy extends AppCompatActivity {
    Button btnDangKy;
    EditText edtTaiKhoanDK,edtMatKhauDK,edtSDTDK,edtEmailDK,edtHoTen;
    RadioButton rdoNam,rdoNu;
    //Ket noi SQL

    classConnect connectionDB = new classConnect();
    String z="";
    Connection con = connectionDB.CONN();
    //-----------------------------------
    ArrayList tammkh = new ArrayList();
    ArrayList tamtk = new ArrayList();
    final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        btnDangKy= (Button)findViewById(R.id.btnDangKy);
        edtTaiKhoanDK=(EditText)findViewById(R.id.edtTaiKhoanDK);
        edtMatKhauDK=(EditText)findViewById(R.id.edtMatKhauDK);
        edtSDTDK=(EditText)findViewById(R.id.edtSDTDK);
        edtEmailDK=(EditText)findViewById(R.id.edtEmailDK);
        edtHoTen=(EditText)findViewById(R.id.edtHoTen);
        rdoNam=(RadioButton)findViewById(R.id.rdoNam);
        rdoNu=(RadioButton)findViewById(R.id.rdoNu);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tk=edtTaiKhoanDK.getText().toString();
                String email= edtEmailDK.getText().toString();
                String mk=edtMatKhauDK.getText().toString();
                String sdt=edtSDTDK.getText().toString();
                int demhd=0;
                int mahd=0;
                int kttk=0;
                try {

                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        String query = "select MAKH,TAIKHOAN_KH from KHACHHANG";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            tammkh.add(rs.getString(1));
                            tamtk.add(rs.getString(2));
                        }

                    }
                } catch (Exception ex) {
                    z = "Exceptions";


                }
                try {

                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        while (demhd<=0) {
                            demhd=1;
                            for (int i = 0; i < tammkh.toArray().length; i++) {
                                if(tk.equals(tamtk.toArray()[i].toString().trim()))
                                    kttk=1;
                                if(("KH0"+mahd).equals(tammkh.toArray()[i].toString().trim())) {
                                    mahd++;
                                    demhd=0;
                                    break;
                                }
                            }
                            if(demhd==1)
                                break;
                        }
                        String gt = "Nữ";
                        if(rdoNam.isChecked())
                        {
                            gt="Nam";
                        }
                        if(kttk==1)
                        {
                            Toast.makeText(DangKy.this,"Tai khoan bi trung", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String query = "insert into KHACHHANG Values ('"+"KH0"+mahd+"',N'"+edtHoTen.getText()+"','"+edtSDTDK.getText()+"',N'"+gt+"','"+edtTaiKhoanDK.getText()+"','"+edtMatKhauDK.getText()+"','"+edtEmailDK.getText()+"')";
                        Statement stmt = con.createStatement();
                        if (stmt.executeUpdate(query) > 0) { // Dùng lệnh executeUpdate cho các lệnh CRUD
                            con.close();
                            Toast.makeText(DangKy.this,"Thanh cong",Toast.LENGTH_SHORT).show();
                        } else {
                            con.close();
                            Toast.makeText(DangKy.this,"That bai",Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception ex) {
                    z = "Exceptions";

                }


            }
        });


    }
}
