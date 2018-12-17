package com.example.user.test.Adapter;

import android.content.Context;
import android.media.session.PlaybackState;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.user.test.Class.Listview_Rcv;
import com.example.user.test.R;

import java.util.ArrayList;

public class Adapter_listview extends BaseAdapter{

    Context context;
    int layout;
    ArrayList<Listview_Rcv> listRecyclerViewArrayList;
    Adapter_rcv adapterRecyclerView;
    public Adapter_listview(Context context, int layout, ArrayList<Listview_Rcv> listRecyclerViewArrayList) {
        this.context = context;
        this.layout = layout;
        this.listRecyclerViewArrayList = listRecyclerViewArrayList;
        this.adapterRecyclerView = adapterRecyclerView;
    }

    @Override
    public int getCount() {
        return listRecyclerViewArrayList.size();
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);
        //Anh xa va gan gia tri
        TextView txttieude = (TextView) convertView.findViewById(R.id.txttieude);
        RecyclerView recyclerview = (RecyclerView) convertView.findViewById(R.id.rcv_tt);
        txttieude.setText(listRecyclerViewArrayList.get(position).getTieude());
        adapterRecyclerView = new Adapter_rcv (listRecyclerViewArrayList.get(position).getArrayListRcv(),context);
        //
        //Trang tri
        LinearLayoutManager layoutManager = new LinearLayoutManager(context.getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
       // recyclerview.addItemDecoration(new DividerItemDecoration(this.context, LinearLayoutManager.HORIZONTAL));
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setLayoutManager(layoutManager);
        //gan gia tri cho rcv
        recyclerview.setAdapter(adapterRecyclerView);
        return convertView;
    }
}

