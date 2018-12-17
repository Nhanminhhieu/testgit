package com.example.user.test.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.test.Activity.DangNhap;
import com.example.user.test.Activity.MainActivity;
import com.example.user.test.Activity.Nhanvien;
import com.example.user.test.Adapter.Adapter_listview;
import com.example.user.test.Class.ClassTaiKhoan;
import com.example.user.test.Class.Listview_Rcv;
import com.example.user.test.Class.Nhanlabel;
import com.example.user.test.Class.classConnect;
import com.example.user.test.Class.classMang;
import com.example.user.test.R;
import com.example.user.test.Class.Thuc_pham;
import com.example.user.test.Search.search_thongtin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.user.test.R.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_trangchu extends Fragment {
    ListView list_trangchu;
    RecyclerView rcv;
    public  static ArrayList<Thuc_pham> thucPhamArrayList;
    ArrayList<Listview_Rcv> listview_rcvs;
    Adapter_listview adapter;
    ArrayList<Nhanlabel> lstNhan = new ArrayList<>();
    final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    //Ket noi SQL

    classConnect connectionDB = new classConnect();
    String z="";
    Connection con = connectionDB.CONN();
    //-----------------------------------
    public Fragment_trangchu() {
        // Required empty public constructor
        nhanLabel();
        chenThongTinThucPham();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(layout.fragment_trangchu, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list_trangchu = view.findViewById(id.list_trangchu);
        nhanLabel();
        chenThongTinThucPham();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case id.action_search:
                Intent intent = new Intent(getActivity(),search_thongtin.class);
                startActivity(intent);
                return false;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
}
    private void chenThongTinThucPham()
    {
        final ArrayList<classMang> arraynhan  = new ArrayList<>();
        thucPhamArrayList = new ArrayList<>();
        for(int i =0;i<lstNhan.toArray().length;i++) {
            classMang a = new classMang();
            arraynhan.add(a);
        }

        listview_rcvs = new ArrayList<>();
        try {

            if (con == null) {
                z = "Error in connection with SQL server";
            } else {
                String query = "select * from THUCPHAM";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()) {
                    Thuc_pham thuc_pham = new Thuc_pham();
                    thuc_pham = new Thuc_pham(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getFloat(4));
                    for(int i =0;i<lstNhan.toArray().length;i++) {
                        if (thuc_pham.getLoai().equals(((Nhanlabel)lstNhan.toArray()[i]).getMalabel())) {
                            arraynhan.get(i).setPTMang(thuc_pham);
                            thucPhamArrayList.add(thuc_pham);
                            if (arraynhan.get(i).getMang().toArray().length == 1) {
                                listview_rcvs.add(new Listview_Rcv(((Nhanlabel)lstNhan.toArray()[i]).getTenlabel(), arraynhan.get(i).getMang()));
                            }
                            adapter = new Adapter_listview(getActivity(), R.layout.row_listview, listview_rcvs);
                            list_trangchu.setAdapter(adapter);

                        }
                    }

                }

            }
        } catch (Exception ex) {
            z = "Exceptions";

        }
    }
    private void nhanLabel()
    {
        lstNhan=new ArrayList<>();
        try {

            if (con == null) {
                z = "Error in connection with SQL server";
            } else {
                String query = "select * from LOAI";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()) {

                    Nhanlabel label = new Nhanlabel();
                    label = new Nhanlabel(rs.getString(1),rs.getString(2));


                        lstNhan.add(label);                }

            }
        } catch (Exception ex) {
            z = "Exceptions";

        }
    }

}
