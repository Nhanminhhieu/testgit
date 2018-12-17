package com.example.user.test.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.test.Activity.DangNhap;
import com.example.user.test.Activity.GiaoHang;
import com.example.user.test.Activity.Nhanvien;
import com.example.user.test.Activity.ThongTinGiaoHang;
import com.example.user.test.Adapter.Adapter_donhang;
import com.example.user.test.Class.ClassTaiKhoan;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_giaohang extends Fragment {
    ListView listview_giao;
    ArrayList<Listview_donhang> listview_donhangs;
    //Ket noi SQL

    classConnect connectionDB = new classConnect();
    String z = "";
    Connection con = connectionDB.CONN();
    //-----------------------------------
    final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    public Fragment_giaohang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_giaohang, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listview_donhangs=new ArrayList<Listview_donhang>();
        listview_giao = view.findViewById(R.id.listview_giao);
        try {

            if (con == null) {
                z = "Error in connection with SQL server";
            } else {
                String query = "select  HOADON.MAHD,TENTHUCPHAM,SOLUONG,GIABAN,NGAYDAT,DIACHIGIAO,PHIKC,TINHTRANGTHANHTOAN,THANHTIEN from THUCPHAM,HOADON,CHITIETHOADON,GIAOHANG WHERE THUCPHAM.MATHUCPHAM=CHITIETHOADON.MATHUCPHAM AND CHITIETHOADON.MAHD=HOADON.MAHD AND GIAOHANG.MAHD=HOADON.MAHD AND MANV='"+manv+"' AND TINHTRANGTHANHTOAN=N'Chưa giao'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Listview_donhang donhang = new Listview_donhang();
                    donhang.setMadonhang(rs.getString(1));
                    donhang.setTen( rs.getString(2));
                    donhang.setSoluong(rs.getInt(3));
                    donhang.setGia( rs.getFloat(4));
                    donhang.setNgaydat(rs.getString(5));
                    donhang.setDiachi(rs.getString(6));
                    donhang.setPhigiao(rs.getFloat(7));
                    donhang.setTinhtrang(rs.getString(8));
                    donhang.setTongtien(rs.getFloat(9));
                    listview_donhangs.add(donhang);
                }

            }
        } catch (Exception ex) {
            z = "Exceptions";

        }
        Adapter_donhang adapter = new Adapter_donhang(getContext(), R.layout.row_donhang, listview_donhangs);
        listview_giao.setAdapter(adapter);
        listview_giao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GiaoHang.class);
                Bundle bundle = new Bundle();
                bundle.putString("mahd",listview_donhangs.get(position).getMadonhang());
                bundle.putString("ten",listview_donhangs.get(position).getTen());
                bundle.putInt("soluong",listview_donhangs.get(position).getSoluong());
                bundle.putFloat("gia",listview_donhangs.get(position).getGia());
                bundle.putString("ngaydat",listview_donhangs.get(position).getNgaydat());
                bundle.putString("diachi",listview_donhangs.get(position).getDiachi());
                bundle.putFloat("phigiao",listview_donhangs.get(position).getPhigiao());
                bundle.putString("tinhtrang",listview_donhangs.get(position).getTinhtrang());
                bundle.putFloat("tongtien",listview_donhangs.get(position).getTongtien());
                intent.putExtra("dulieu",bundle);
                startActivity(intent);
            }
        });
    }

}
