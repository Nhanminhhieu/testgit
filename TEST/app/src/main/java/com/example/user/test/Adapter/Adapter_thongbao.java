package com.example.user.test.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.test.Class.Listview_donhang;
import com.example.user.test.Class.Listview_thongbao;
import com.example.user.test.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Adapter_thongbao extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<Listview_thongbao> arraythongbao;

    public Adapter_thongbao(Context myContext, int myLayout, List<Listview_thongbao> arraythongbao) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.arraythongbao = arraythongbao;
    }
    @Override
    public int getCount() {
        return arraythongbao.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout, null);

        TextView txtten = (TextView) convertView.findViewById(R.id.txtnhanthongbao);
        txtten.setText(arraythongbao.get(position).getNhan());

        TextView txtchitiet = (TextView) convertView.findViewById(R.id.txtchitietthongbao);
        txtchitiet.setText(arraythongbao.get(position).getChitiet());
        ImageView hinh = (ImageView)convertView.findViewById(R.id.imgThongBao);
        Picasso.get().load(arraythongbao.get(position).getHinh()).into(hinh);
        return convertView;
    }
}
