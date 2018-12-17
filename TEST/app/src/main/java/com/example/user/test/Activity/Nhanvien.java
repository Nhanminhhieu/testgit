package com.example.user.test.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.user.test.Fragment.Fragment_canhan;
import com.example.user.test.Fragment.Fragment_donhang;
import com.example.user.test.Fragment.Fragment_giaohang;
import com.example.user.test.Fragment.Fragment_thongbao;
import com.example.user.test.Fragment.Fragment_trangchu;
import com.example.user.test.R;

public class Nhanvien extends AppCompatActivity {

    BottomNavigationView main_nav;
    FrameLayout main_frame;
    Fragment_giaohang giaohang;
    Fragment_canhan canhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien);
        main_nav = (BottomNavigationView) findViewById(R.id.main_nav);
        main_frame = (FrameLayout) findViewById(R.id.main_frame);
        thietKeLogo("FAST GIAO HÀNG",true,false);
        giaohang = new Fragment_giaohang();
        setFragment(giaohang);
        main_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_giaohang1:
                        thietKeLogo("FAST GIAO HÀNG",true,false);
                        setFragment(giaohang);
                        return true;
                    case R.id.nav_canhan1:
                        canhan = new Fragment_canhan();
                        thietKeLogo("CÁ NHÂN",false,true);
                        setFragment(canhan);
                        return true;
                }
                return false;
            }
        });
    }

    private void thietKeLogo(String chuoi, boolean hinh,boolean canhvi)
    {
        TextView textView;
        ImageView imageView;
        SearchView searchView;
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        textView =  (TextView)findViewById(R.id.action_bar_title);
        textView.setText(chuoi);
        if(canhvi == true)
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        if(hinh == false) {
            imageView = (ImageView) findViewById(R.id.action_bar_img);
            imageView.setVisibility(View.GONE);
        }
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        FragmentTransaction replace = fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
