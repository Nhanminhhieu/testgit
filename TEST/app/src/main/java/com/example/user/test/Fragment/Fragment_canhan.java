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

import com.example.user.test.Activity.DangNhap;
import com.example.user.test.Activity.DoiMatKhau;
import com.example.user.test.Activity.ThongTinCaNhan;
import com.example.user.test.Adapter.Adapter_canhan;
import com.example.user.test.Adapter.Adapter_listview;
import com.example.user.test.Class.Listview_Rcv;
import com.example.user.test.Class.Listview_canhan;
import com.example.user.test.Class.Thuc_pham;
import com.example.user.test.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_canhan extends Fragment {
    ListView list_cannhan;
    ArrayList<Listview_canhan> listview_canhans;

    public Fragment_canhan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_canhan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list_cannhan = view.findViewById(R.id.lst_canhan);
        chenThongTinCN();
        list_cannhan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listview_canhans.get(position).getTenchucnang().equals("Đăng xuất") == true) {
                    Intent intent = new Intent(getActivity(), DangNhap.class);
                    startActivity(intent);
                }
                if(listview_canhans.get(position).getTenchucnang().equals("Mật khẩu") == true) {
                    Intent intent = new Intent(getActivity(), DoiMatKhau.class);
                    startActivity(intent);
                }
                if(listview_canhans.get(position).getTenchucnang().equals("Thông tin cá nhân"))
                {
                    Intent intent = new Intent(getActivity(), ThongTinCaNhan.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void  chenThongTinCN()
    {
        listview_canhans = new ArrayList<>();
        listview_canhans.add(new Listview_canhan("Thông tin cá nhân",R.drawable.round_account_circle_black_24dp));
        listview_canhans.add(new Listview_canhan("Mật khẩu",R.drawable.round_lock_black_24dp));
        listview_canhans.add(new Listview_canhan("Đăng xuất",R.drawable.round_keyboard_backspace_black_18dp));
        Adapter_canhan adapter = new Adapter_canhan(getContext(), R.layout.row_canhan, listview_canhans);
        list_cannhan.setAdapter(adapter);
    }
}
