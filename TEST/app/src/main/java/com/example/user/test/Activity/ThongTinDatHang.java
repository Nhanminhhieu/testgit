package com.example.user.test.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.test.Adapter.Adapter_rcv;
import com.example.user.test.R;
import com.squareup.picasso.Picasso;

public class ThongTinDatHang extends AppCompatActivity {

    ImageView imageView;
    TextView txtLoai;
    TextView txtTen;
    TextView txtTinhtrang;
    TextView txtGia;
    TextView txtMota;
    Button btnDathang;
    String matp;
    float gia;
    public static final String TENTHUCPHAM = "TENTHUCPHAM";
    public static final String MOTATHUCPHAM = "MOTATHUCPHAM";
    public static final String GIATIEN = "GIATIEN";
    public static final String BUNDLE = "BUNDLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_dat_hang);
        imageView = (ImageView)findViewById(R.id.imageviewThongtindathang);
        txtLoai = (TextView)findViewById(R.id.txtLoai);
        txtTen = (TextView)findViewById(R.id.txtTen);
        txtTinhtrang = (TextView)findViewById(R.id.txtTinhtrang);
        txtGia = (TextView)findViewById(R.id.txtGiatien);
        txtMota = (TextView)findViewById(R.id.txtMotathucphamgiao);
        btnDathang = (Button)findViewById(R.id.btnDathang);
        final Intent intent = getIntent();
        if(intent != null){
            Bundle bundle = intent.getBundleExtra(Adapter_rcv.BUNDLE);
            if(bundle != null){
                Picasso.get().load(bundle.getString(Adapter_rcv.HINH)).into(imageView);
                txtLoai.setText(bundle.getString(Adapter_rcv.LOAI));
                txtTen.setText(bundle.getString(Adapter_rcv.TEN));
                txtTinhtrang.setText(bundle.getString(Adapter_rcv.TINHTRANG));
                gia = bundle.getFloat(Adapter_rcv.GIA);
                txtGia.setText(gia + "VNĐ");
                txtMota.setText(bundle.getString(Adapter_rcv.MOTA));
                matp=bundle.getString("MATP");
            }
        }
        btnDathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ThongTinDatHang.this, ThongTinGiaoHang.class);
                Bundle bundle = new Bundle();
                bundle.putString(TENTHUCPHAM,txtTen.getText().toString());
                bundle.putString(MOTATHUCPHAM,txtMota.getText().toString());
                bundle.putFloat(GIATIEN,gia);
                bundle.putString("MATP",matp);
                intent1.putExtra(BUNDLE,bundle);
                startActivity(intent1);
            }
        });
    }
}
