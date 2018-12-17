package com.example.user.test;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;


import com.example.user.test.Fragment.Fragment_canhan;
import com.example.user.test.Fragment.Fragment_donhang;
import com.example.user.test.Fragment.Fragment_thongbao;
import com.example.user.test.Fragment.Fragment_trangchu;

import java.lang.reflect.Field;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView main_nav;
    FrameLayout main_frame;
    Fragment_trangchu trangchu;
    Fragment_donhang donhang;
    Fragment_thongbao thongbao;
    Fragment_canhan canhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_nav = (BottomNavigationView) findViewById(R.id.main_nav);
        main_frame = (FrameLayout) findViewById(R.id.main_frame);
        thietKeLogo("FAST",true,false);
        trangchu = new Fragment_trangchu();
        setFragment(trangchu);
        main_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_trangchu:
                        thietKeLogo("FAST",true,false);
                        setFragment(trangchu);
                        return true;
                    case R.id.nav_donhang:
                        donhang = new Fragment_donhang();
                        thietKeLogo("ĐƠN HÀNG",false,true);
                        setFragment(donhang);
                        return true;
                    case R.id.nav_thongbao:
                        thongbao = new Fragment_thongbao();
                        thietKeLogo("THÔNG BÁO",false,true);
                        setFragment(thongbao);
                        return true;
                    case R.id.nav_canhan:
                        canhan = new Fragment_canhan();
                        thietKeLogo("CÁ NHÂN",false,true);
                        setFragment(canhan);
                        return true;
                }
                return false;
            }
        });
        disableShiftMode(main_nav);
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

    @SuppressLint("RestrictedApi")
    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        FragmentTransaction replace = fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}

