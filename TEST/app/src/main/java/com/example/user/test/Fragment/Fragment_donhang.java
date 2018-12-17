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
import com.example.user.test.Adapter.Adapter_donhang;
import com.example.user.test.Adapter.Adapter_listview;
import com.example.user.test.Class.Listview_Rcv;
import com.example.user.test.Class.Listview_donhang;
import com.example.user.test.Class.Thuc_pham;
import com.example.user.test.Class.classConnect;
import com.example.user.test.R;
import com.example.user.test.Activity.thongtin_donhang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.user.test.Activity.DangNhap.makh;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_donhang extends Fragment {
    ListView list_donhang;
    ArrayList<Listview_donhang> listview_donhangs;
    //Ket noi SQL

    classConnect connectionDB = new classConnect();
    String z = "";
    Connection con = connectionDB.CONN();
    //-----------------------------------
    final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

    public Fragment_donhang() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donhang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list_donhang = view.findViewById(R.id.lst_donhang);
        chenThongTinDH();
        list_donhang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), thongtin_donhang.class);
                Bundle bundle = new Bundle();
                bundle.putString("ten", listview_donhangs.get(position).getTen());
                bundle.putInt("soluong", listview_donhangs.get(position).getSoluong());
                bundle.putFloat("gia", listview_donhangs.get(position).getGia());
                bundle.putString("ngaydat", listview_donhangs.get(position).getNgaydat());
                bundle.putString("diachi", listview_donhangs.get(position).getDiachi());
                bundle.putFloat("phigiao", listview_donhangs.get(position).getPhigiao());
                bundle.putString("tinhtrang", listview_donhangs.get(position).getTinhtrang());
                bundle.putFloat("tongtien", listview_donhangs.get(position).getTongtien());
                bundle.putString("mahd",listview_donhangs.get(position).getMadonhang());
                intent.putExtra("dulieu", bundle);
                startActivity(intent);
            }
        });
    }

    private void chenThongTinDH() {
        listview_donhangs = new ArrayList<>();
        try {

            if (con == null) {
                z = "Error in connection with SQL server";
            } else {
                String query = "select TENTHUCPHAM,SOLUONG,GIABAN,NGAYDAT,DIACHIGIAO,PHIKC,TINHTRANGTHANHTOAN,THANHTIEN,HOADON.MAHD from THUCPHAM,HOADON,CHITIETHOADON WHERE THUCPHAM.MATHUCPHAM=CHITIETHOADON.MATHUCPHAM AND CHITIETHOADON.MAHD=HOADON.MAHD AND MAKH='"+makh+"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Listview_donhang donhang = new Listview_donhang();
                    donhang.setTen( rs.getString(1));
                    donhang.setSoluong(rs.getInt(2));
                    donhang.setGia( rs.getFloat(3));
                    donhang.setNgaydat(rs.getString(4));
                    donhang.setDiachi(rs.getString(5));
                    donhang.setPhigiao(rs.getFloat(6));
                    donhang.setTinhtrang(rs.getString(7));
                    donhang.setTongtien(rs.getFloat(8));
                    donhang.setMadonhang(rs.getString(9));
                    listview_donhangs.add(donhang);
                }

            }
        } catch (Exception ex) {
            z = "Exceptions";

        }
        Adapter_donhang adapter = new Adapter_donhang(getContext(), R.layout.row_donhang, listview_donhangs);
        list_donhang.setAdapter(adapter);
    }

}
