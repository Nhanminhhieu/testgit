package com.example.user.test.Activity;

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

public class QuenMatKhau extends AppCompatActivity {
    EditText edtQuenMK;
    Button btnGui;
    final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);
        edtQuenMK=(EditText)findViewById(R.id.edtEmailQMK);
        btnGui=(Button)findViewById(R.id.btnGui);


        //--------------------------------------------

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child("TaiKhoan").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot child:dataSnapshot.getChildren())
                        {
                            ClassTaiKhoan dataTK = child.getValue(ClassTaiKhoan.class);
                            if(dataTK.getHoatdong().equals(edtQuenMK.getText().toString()))
                            {
                                Toast.makeText(QuenMatKhau.this,dataTK.getMatkhau(),Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else {
                                Toast.makeText(QuenMatKhau.this, "Email khong chinh xac", Toast.LENGTH_SHORT).show();
                                return;
                            }
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
