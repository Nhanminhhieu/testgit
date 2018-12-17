package com.example.user.test.Class;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.test.R;

public class Thietkelogo extends AppCompatActivity {
    public void thietKeLogo(String chuoi, boolean hinh,boolean canhvi)
    {
        TextView textView;
        ImageView imageView;
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
}
