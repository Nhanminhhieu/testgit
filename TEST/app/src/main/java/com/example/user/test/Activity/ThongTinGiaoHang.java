package com.example.user.test.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.test.Adapter.Adapter_listview;
import com.example.user.test.Class.ClassTaiKhoan;
import com.example.user.test.Class.Listview_Rcv;
import com.example.user.test.Class.Listview_donhang;
import com.example.user.test.Class.Thuc_pham;
import com.example.user.test.Class.classConnect;
import com.example.user.test.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.user.test.Activity.DangNhap.makh;

public class ThongTinGiaoHang extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    float gia;
    float tongtien;
    float phidichvu;
    float phivanchuyen;
    float tongcong;
    TextView txtNgayGio;
    TextView txtDiachi;
    ImageButton imagebtnDiachi;
    TextView txtTenthucpham;
    String matp;
    TextView txtMota;
    TextView txtGia;
    TextView txtSoluong;
    ImageButton imagebtnTang;
    ImageButton imagebtnGiam;
    TextView txtTongtien;
    TextView txtTongcong;
    TextView txtPhidichvu;
    ImageButton imagebtnPhivanchuyen;
    ImageButton imagebtnPhidichvu;
    TextView txtPhivanchuyen;
    CheckBox checkBoxTienmat;
    CheckBox checkBoxChuyenkhoan;
    Button btnDathang;
    ArrayList tamcthd = new ArrayList();
    ArrayList tamhd = new ArrayList();
    //Ket noi SQL

    classConnect connectionDB = new classConnect();
    String z="";
    Connection con = connectionDB.CONN();
    //-----------------------------------

    int mahd=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_giao_hang);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(this);
        AnhXa();
        SetNgayGio();
        NhapDiaChi();
        LayDuLieu();
        TongTien();
        TangGiamSoLuong();
        PhiDichVu();
        PhiVanChuyen();
        TongCong();
        ThongBaoPhiDichVu();
        ThongBaoPhiVanChuyen();
        LuuDonDatHang();
        Check();
    }

    private void Check(){
        checkBoxTienmat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBoxTienmat.isChecked()== true){
                    checkBoxChuyenkhoan.setEnabled(false);
                }
                else{
                    checkBoxChuyenkhoan.setEnabled(true);
                }
            }
        });

        checkBoxChuyenkhoan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBoxChuyenkhoan.isChecked() == true){
                    checkBoxTienmat.setEnabled(false);
                }else
                {
                    checkBoxTienmat.setEnabled(true);
                }
            }
        });
    }

    private void LuuDonDatHang() {
        btnDathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] dem = {0};
                con = connectionDB.CONN();
                if (checkBoxTienmat.isChecked() == false && checkBoxChuyenkhoan.isChecked() == false) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ThongTinGiaoHang.this);
                    alertDialog.setTitle("Thông báo!");
                    alertDialog.setMessage("Vui lòng chọn hình thức để thanh toán.");
                    alertDialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                    return;
                }
                //lay danh sach khoa
                //-------------------------------------

                if(Integer.parseInt(txtSoluong.getText().toString())<=0)
                {
                    Toast.makeText(ThongTinGiaoHang.this," So luong phai lon hon 0",Toast.LENGTH_SHORT).show();

                    return;
                }
                tamcthd = new ArrayList();
                tamhd = new ArrayList();
                try {

                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        String query = "select MACTHOADON from CHITIETHOADON";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            tamcthd.add(rs.getString(1));
                        }

                    }
                } catch (Exception ex) {
                    z = "Exceptions";

                }
                try {

                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        String query = "select MAHD from HOADON";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            tamhd.add(rs.getString(1));
                        }

                    }
                } catch (Exception ex) {
                    z = "Exceptions";

                }
                //--------------------------------------
                //Them
                //--------------------------------------
                themHD();
                themCTHD("HD0"+mahd,matp);

            }
        });
    }

    private void TongCong(){
        tongcong = tongtien + phivanchuyen + phidichvu;
        txtTongcong.setText(tongcong+"");
    }

    private void PhiVanChuyen(){
        if(tongtien != 0){
            phivanchuyen = 0;
            txtPhivanchuyen.setText(phivanchuyen+"");
        }
        else {
            phivanchuyen = 0;
            txtPhivanchuyen.setText(phivanchuyen + "");
        }
    }

    private void PhiDichVu() {
        if (tongtien >= 100000) {
            phidichvu = 0;
            txtPhidichvu.setText(phidichvu + "");
        } else if (tongtien == 0) {
            phidichvu = 0;
            txtPhidichvu.setText(phidichvu + "");
        } else {
            phidichvu = 0;
            txtPhidichvu.setText(phidichvu + "");
        }
    }

    private void TongTien(){
        String temp = txtSoluong.getText().toString().trim();
        int tong = Integer.parseInt(temp);
        tongtien = tong * gia;
        txtTongtien.setText(tongtien + "");
    }

    private void TangGiamSoLuong(){
        imagebtnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = txtSoluong.getText().toString().trim();
                int tong = Integer.parseInt(temp) + 1;
                txtSoluong.setText(tong + "");
                tongtien = tong * gia;
                txtTongtien.setText(tongtien + "");
                PhiDichVu();
                PhiVanChuyen();
                TongCong();
            }
        });

        imagebtnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = txtSoluong.getText().toString().trim();
                if(Integer.parseInt(temp) > 0) {
                    int tong = Integer.parseInt(temp) - 1;
                    txtSoluong.setText(tong + "");
                    tongtien = tong * gia;
                    txtTongtien.setText(tongtien + "");
                    PhiDichVu();
                    PhiVanChuyen();
                    TongCong();
                }
            }
        });
    }

    private void LayDuLieu(){
        final Intent intent = getIntent();
        if(intent != null){
            Bundle bundle = intent.getBundleExtra(ThongTinDatHang.BUNDLE);
            if(bundle != null){
                txtTenthucpham.setText(bundle.getString(ThongTinDatHang.TENTHUCPHAM));
                gia = bundle.getFloat(ThongTinDatHang.GIATIEN);
                txtGia.setText(gia + "");
                txtMota.setText(bundle.getString(ThongTinDatHang.MOTATHUCPHAM));
                matp=bundle.getString("MATP");
            }
        }
    }

    private void SetNgayGio(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        int gio = calendar.get(Calendar.HOUR);
        int phut = calendar.get(Calendar.MINUTE);
        txtNgayGio.setText(gio + ":" + phut + " " + day + "-" + month + "-" +year);
    }

    private void NhapDiaChi(){
        imagebtnDiachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDiaChiGiao();
            }
        });
    }

    private void ThongBaoPhiDichVu(){
        imagebtnPhidichvu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ThongTinGiaoHang.this);
                alertDialog.setTitle("Thông báo phí dịch vụ!");
                alertDialog.setMessage("Đơn hàng của bạn có tổng tiền thực phẩm là "+tongtien+"VNĐ"+" chưa đặt mức tối thiểu để giao hàng là 100k. Chấp nhận phí dịch vụ là 10,000VNĐ và tiến hành thanh toán");
                alertDialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });
    }

    private  void ThongBaoPhiVanChuyen(){
        imagebtnPhivanchuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ThongTinGiaoHang.this);
                alertDialog.setTitle("Thông báo phí vận chuyển!");
                alertDialog.setMessage("Tất cả những đơn hàng trong khu vực Thành Phố Hồ Chí Minh FastFood Shop đều tính phí vận chuyển là 20,000VNĐ");
                alertDialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });
    }

    private void DialogDiaChiGiao(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_diachigiao_ngaygio);
        final EditText edtDiachigiao = (EditText)dialog.findViewById(R.id.edtDiachigiao);
        final EditText edtSodienthoai = (EditText)dialog.findViewById(R.id.edtSodienthoai);
        final EditText edtTinhtp = (EditText)dialog.findViewById(R.id.edtTinhtp);
        final EditText edtPhuongxa = (EditText)dialog.findViewById(R.id.edtPhuongxa);
        final EditText edtQuanhuyen = (EditText)dialog.findViewById(R.id.edtQuanhuyen);
        Button btnOK = (Button)dialog.findViewById(R.id.btnOk);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String diachigiao = edtDiachigiao.getText().toString().trim();
                String tinhtp = edtTinhtp.getText().toString().trim();
                String quanhuyen = edtQuanhuyen.getText().toString().trim();
                String phuongxa = edtPhuongxa.getText().toString().trim();
                String sodt = edtSodienthoai.getText().toString().trim();
                if(diachigiao.length() > 0 || tinhtp.length() > 0 || quanhuyen.length() > 0 || phuongxa.length() > 0 || sodt.length() > 0){
                    txtDiachi.setText("Số: "+diachigiao+",Phường "+phuongxa+",Quận "+quanhuyen+",Tỉnh "+tinhtp+"-Số điện thoại: "+sodt);
                    dialog.cancel();
                }
                else
                {
                    Toast.makeText(dialog.getContext(),"Yêu cầu điền đầy đủ thông tin bao gồm: số điện thoại, tỉnh/thành phố, quận/huyện, số địa chỉ",Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng latLng = new LatLng(10.8045636, 106.6290167);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,16);
        map.moveCamera(cameraUpdate);
        map.addMarker(new MarkerOptions().title("FastFood Shop")
                .snippet("Welcome to FastFood")
                .position(latLng));
    }

    void AnhXa(){
        txtDiachi = (TextView)findViewById(R.id.txtDiachigiao);
        txtDiachi.setText("");
        txtNgayGio = (TextView)findViewById(R.id.txtNgayGio);
        imagebtnDiachi = (ImageButton)findViewById(R.id.imagebtnDiachi);
        txtTenthucpham = (TextView)findViewById(R.id.txtTenthucpham);
        txtMota = (TextView)findViewById(R.id.txtMotathucphamgiao);
        txtGia = (TextView)findViewById(R.id.txtGiatien);
        txtSoluong = (TextView)findViewById(R.id.txtSoluong);
        imagebtnTang = (ImageButton)findViewById(R.id.imagebtnTang);
        imagebtnGiam =(ImageButton)findViewById(R.id.imagebtnGiam);
        txtTongtien = (TextView)findViewById(R.id.txtTongtien);
        txtTongcong = (TextView)findViewById(R.id.txtTongcong);
        txtPhidichvu = (TextView)findViewById(R.id.txtPhidichvu);
        txtPhivanchuyen = (TextView)findViewById(R.id.txtPhivanchuyen);
        imagebtnPhidichvu = (ImageButton)findViewById(R.id.imagebtnPhidichvu);
        imagebtnPhivanchuyen = (ImageButton)findViewById(R.id.imagebtnPhivanchuyen);
        checkBoxChuyenkhoan = (CheckBox)findViewById(R.id.checkBoxChuyenkhoan);
        checkBoxTienmat = (CheckBox)findViewById(R.id.checkBoxTienmat);
        btnDathang = (Button)findViewById(R.id.btnDathang);
    }
    public void themCTHD(String mahd,String matp)
    {
        try {

            if (con == null) {
                z = "Error in connection with SQL server";
            } else {
                int demcthd = 0;
                Integer mact=0;
                while (demcthd<=0) {
                    demcthd=1;
                    for (int i = 0; i < tamcthd.toArray().length; i++) {
                        if(("CTHD0"+mact).equals(tamcthd.toArray()[i].toString().trim())){
                            mact++;
                            demcthd=0;
                            break;
                        }
                    }

                    if(demcthd==1)
                        break;
                }
                con = connectionDB.CONN();
                String query = "insert into CHITIETHOADON Values ('"+"CTHD0"+mact+"','"+mahd+"','"+matp+"',"+txtSoluong.getText()+")";
                Statement stmt = con.createStatement();
                if (stmt.executeUpdate(query) > 0) { // Dùng lệnh executeUpdate cho các lệnh CRUD
                    con.close();
                    Toast.makeText(ThongTinGiaoHang.this,"Thanh cong",Toast.LENGTH_SHORT).show();
                } else {
                    con.close();
                    Toast.makeText(ThongTinGiaoHang.this,"That bai",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            z = "Exceptions";

        }
    }
    public void themHD()
    {
        int demhd=0;
        try {

            if (con == null) {
                z = "Error in connection with SQL server";
            } else {
                while (demhd<=0) {
                    demhd=1;
                    for (int i = 0; i < tamhd.toArray().length; i++) {
                        if(("HD0"+mahd).equals(tamhd.toArray()[i].toString().trim())) {
                            mahd++;
                            demhd=0;
                            break;
                        }
                    }
                    if(demhd==1)
                        break;
                }
                String hinhthuc="HT01";
                if(checkBoxChuyenkhoan.isChecked()==true)
                {
                    hinhthuc="HT02";
                }
                if(checkBoxChuyenkhoan.isChecked()==true)
                {
                    hinhthuc="HT02";
                }
                if(txtDiachi.getText().equals("")) {
                    Toast.makeText(ThongTinGiaoHang.this, "Vui long nhạp dia chi", Toast.LENGTH_SHORT).show();
                    return;
                }
                String query = "insert into HOADON Values ('"+"HD0"+mahd+"','"+makh+"','"+hinhthuc+"','KM00','"+txtNgayGio.getText()+"',N'"+txtDiachi.getText()+"',0,0,"+txtTongtien.getText()+",N'Chưa nhận')";
                Statement stmt = con.createStatement();
                if (stmt.executeUpdate(query) > 0) { // Dùng lệnh executeUpdate cho các lệnh CRUD
                    con.close();
                    Toast.makeText(ThongTinGiaoHang.this,"Thanh cong",Toast.LENGTH_SHORT).show();
                } else {
                    con.close();
                    Toast.makeText(ThongTinGiaoHang.this,"That bai",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            z = "Exceptions";
        }
    }
}
