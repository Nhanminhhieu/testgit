package com.example.user.test.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.user.test.Class.ClassTaiKhoan;
import com.example.user.test.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.user.test.Activity.DangNhap.makh;

public class DoiMatKhau extends AppCompatActivity {
    EditText edtmkcu,edtmkmoi1,edtmkmoi2;
    Button btnDongYDMK;
    final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        edtmkcu=(EditText)findViewById(R.id.edtMatKhauCu);
        edtmkmoi1=(EditText)findViewById(R.id.edtMatKhauMoi1);
        edtmkmoi2=(EditText)findViewById(R.id.edtMatKhauMoi2);
        btnDongYDMK=(Button)findViewById(R.id.btnDongYDMK);
        //------------------------

        btnDongYDMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] dem = {0};
                myRef.child("TaiKhoan").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot child:dataSnapshot.getChildren()) {
                            ClassTaiKhoan dataTK = child.getValue(ClassTaiKhoan.class);
                            if (dataTK.getHoatdong().equals(makh)) {
                                if (dataTK.getMatkhau().equals(edtmkcu.getText().toString())) {
                                    if (edtmkmoi1.getText().toString().equals(edtmkmoi2.getText().toString())) {
                                        myRef.child("TaiKhoan").child(child.getKey()).child("matkhau").setValue(edtmkmoi1.getText().toString());
                                        dem[0] = 1;
                                        Toast.makeText(DoiMatKhau.this, "Doi mat khau thanh cong", Toast.LENGTH_SHORT).show();
                                        return;
                                    } else if (dem[0] == 0) {
                                        Toast.makeText(DoiMatKhau.this, "Mat khau moi khong trung khop", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            }
                        }
                        if(dem[0]==0){
                            Toast.makeText(DoiMatKhau.this, "Mat khau khong dung", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
