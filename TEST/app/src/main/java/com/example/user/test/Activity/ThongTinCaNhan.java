package com.example.user.test.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.test.Class.ClassKhachHang;
import com.example.user.test.Class.ClassTaiKhoan;
import com.example.user.test.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static com.example.user.test.Activity.DangNhap.makh;

public class ThongTinCaNhan extends AppCompatActivity {

    final Intent intent = getIntent();
    EditText edtHinh,edtHoTen,edtDiaChi,edtSDT;
    ImageView imgHinh;
    Button btnCapNhat,btnLuu;
    ClassTaiKhoan TaiKhoan= new ClassTaiKhoan();

    final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
        edtHinh=(EditText)findViewById(R.id.edtHinh);
        edtDiaChi=(EditText)findViewById(R.id.edtDiaChi);
        edtHoTen=(EditText)findViewById(R.id.edtHoTen);
        edtSDT=(EditText)findViewById(R.id.edtSDT);
        imgHinh=(ImageView)findViewById(R.id.imgHinhTT);
        btnCapNhat=(Button)findViewById(R.id.btnCapNhatTT);
        btnLuu=(Button)findViewById(R.id.btnLuuTT);
        myRef.child("KhachHang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    ClassKhachHang dataKH=child.getValue(ClassKhachHang.class);
                    if(dataKH.getTaikhoan().equals(makh))
                    {
                        edtHinh.setText(dataKH.getHinh());
                        edtDiaChi.setText(dataKH.getDiachi());
                        edtHoTen.setText(dataKH.getHoten());
                        edtSDT.setText(dataKH.getSdt().toString());
                        Picasso.get().load(dataKH.getHinh()).into(imgHinh);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtHinh.setEnabled(true);
                edtDiaChi.setEnabled(true);
                edtHoTen.setEnabled(true);
                edtSDT.setEnabled(true);
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtHinh.setEnabled(false);
                edtDiaChi.setEnabled(false);
                edtHoTen.setEnabled(false);
                edtSDT.setEnabled(false);
                if(edtSDT.getText().length()<1||edtHoTen.getText().length()<1||edtDiaChi.getText().length()<1||edtHinh.getText().length()<1)
                {
                    Toast.makeText(ThongTinCaNhan.this,"Vui long nhap day du thong tin",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final DatabaseReference myRef1 = FirebaseDatabase.getInstance().getReference();
                    myRef1.child("KhachHang").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                ClassKhachHang dataKH=child.getValue(ClassKhachHang.class);
                                if(dataKH.getTaikhoan().equals(makh))
                                {
                                    String a=child.getKey();
                                    myRef1.child("KhachHang").child(a).setValue(new ClassKhachHang(edtHoTen.getText().toString(),edtSDT.getText().toString(),edtDiaChi.getText().toString(),edtHinh.getText().toString(),makh));
                                    return;
                                }
                            }
                            myRef1.child("KhachHang").push().setValue(new ClassKhachHang(edtHoTen.getText().toString(),edtSDT.getText().toString(),edtDiaChi.getText().toString(),edtHinh.getText().toString(),makh));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
}
