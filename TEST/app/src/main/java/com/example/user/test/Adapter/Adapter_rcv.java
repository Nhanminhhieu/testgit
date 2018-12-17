package com.example.user.test.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.test.Activity.ThongTinDatHang;
import com.example.user.test.Interface.ItemClickListener;
import com.example.user.test.R;
import com.example.user.test.Class.Thuc_pham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class  RecyclerViewHolder extends  RecyclerView.ViewHolder implements  View.OnClickListener,View.OnLongClickListener
{
    public  TextView txttentp, txtgiatp;
    public ImageView imghinh;


    private ItemClickListener itemClickListener;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        txttentp = (TextView)itemView.findViewById(R.id.txtten);
        txtgiatp = (TextView)itemView.findViewById(R.id.txtgia);
        imghinh = (ImageView)itemView.findViewById(R.id.imghinh);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }
    public  void  setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}
public class Adapter_rcv extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<Thuc_pham> arrayList;
    private Context context;
    public static final String HINH = "HINH";
    public static final String LOAI = "LOAI";
    public static final String TEN ="TEN";
    public static final String TINHTRANG ="TINHTRANG";
    public static final String GIA ="GIA";
    public static final String MOTA ="MOTA";
    public static final String BUNDLE = "BUNDLE";


    public Adapter_rcv(ArrayList<Thuc_pham> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.row_rcv,parent,false);
        return  new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.txttentp.setText(arrayList.get(position).getTenthucpham());
        holder.txtgiatp.setText(arrayList.get(position).getGia() + "");

        Picasso.get().load((arrayList.get(position).getHinhanh())).into(holder.imghinh);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //if(isLongClick)
                   // Toast.makeText(context,"Long "  + arrayList.get(position).getTenthucpham().toString(),Toast.LENGTH_SHORT).show();
                //else
                  //  Toast.makeText(context,"Short "+ arrayList.get(position).getTenthucpham().toString(),Toast.LENGTH_SHORT).show();
                if(isLongClick == false) {
                    Intent intent = new Intent(context, ThongTinDatHang.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(HINH, arrayList.get(position).getHinhanh());
                    bundle.putString(LOAI, arrayList.get(position).getLoai());
                    bundle.putString(TEN, arrayList.get(position).getTenthucpham());
                    bundle.putString(TINHTRANG, arrayList.get(position).getTinhtrang());
                    bundle.putFloat(GIA, arrayList.get(position).getGia());
                    bundle.putString(MOTA, arrayList.get(position).getMota());
                    bundle.putString("MATP",arrayList.get(position).getMathucpham());
                    intent.putExtra(BUNDLE, bundle);
                    context.startActivities(new Intent[]{intent});
                }
            }

        });

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

