package com.example.user.test.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.example.user.test.Activity.DangKy;
import com.example.user.test.Adapter.Adapter_donhang;
import com.example.user.test.Adapter.Adapter_thongbao;
import com.example.user.test.Class.ClassTaiKhoan;
import com.example.user.test.Class.Listview_donhang;
import com.example.user.test.Class.Listview_thongbao;
import com.example.user.test.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_thongbao extends Fragment {
    ListView list_thongbao;
    ArrayList<Listview_thongbao> listview_thongbaos;


    public Fragment_thongbao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thongbao, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list_thongbao = view.findViewById(R.id.lst_thongbao);
        chenThongTinTB();
    }
    private void  chenThongTinTB()
    {
        listview_thongbaos = new ArrayList<>();
        final Listview_thongbao[] dataThongBao = {new Listview_thongbao()};
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child("ThongBao").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    dataThongBao[0] = child.getValue(Listview_thongbao.class);
                    listview_thongbaos.add(dataThongBao[0]);

                    Adapter_thongbao adapter = new Adapter_thongbao(getContext(), R.layout.row_thongbao, listview_thongbaos);
                    list_thongbao.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
