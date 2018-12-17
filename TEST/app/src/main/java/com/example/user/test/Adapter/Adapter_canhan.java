package com.example.user.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.user.test.Class.Listview_canhan;
import com.example.user.test.R;

import java.util.List;

public class Adapter_canhan extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<Listview_canhan> arraycanhan;

    public Adapter_canhan(Context myContext, int myLayout, List<Listview_canhan> arraycanhan) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.arraycanhan = arraycanhan;
    }
    @Override
    public int getCount() {
        return arraycanhan.size();
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

        TextView txtten = (TextView) convertView.findViewById(R.id.txttenchucnang);
        txtten.setText(arraycanhan.get(position).getTenchucnang());

        ImageView imghinh = convertView.findViewById(R.id.imgchucnang);
        imghinh.setImageResource(arraycanhan.get(position).getHinhchucnang());
        return convertView;
    }
}
