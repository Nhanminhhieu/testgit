package com.example.user.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.test.Class.Listview_donhang;
import com.example.user.test.Class.classConnect;
import com.example.user.test.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Adapter_donhang extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<Listview_donhang> arraydonhang;
    //Ket noi SQL

    classConnect connectionDB = new classConnect();
    String z="";
    Connection con = connectionDB.CONN();
    //-----------------------------------
    public Adapter_donhang(Context myContext, int myLayout, List<Listview_donhang> arraydonhang) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.arraydonhang = arraydonhang;
    }
    @Override
    public int getCount() {
        return arraydonhang.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    static class ViewHolder
    {
        TextView name;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(myLayout, null);

            TextView txtten = (TextView) convertView.findViewById(R.id.txtten);
            txtten.setText(arraydonhang.get(position).getTen());

            TextView txtsl = (TextView) convertView.findViewById(R.id.txtsoluong);
            txtsl.setText("Tình trạng: " + arraydonhang.get(position).getTinhtrang());

            TextView txtdiachi = (TextView) convertView.findViewById(R.id.txtdiachi);
            txtdiachi.setText(arraydonhang.get(position).getDiachi());
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}
