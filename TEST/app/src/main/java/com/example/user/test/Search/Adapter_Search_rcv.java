package com.example.user.test.Search;

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
import com.example.user.test.Class.Thuc_pham;
import com.example.user.test.Interface.ItemClickListener;
import com.example.user.test.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class  RecyclerViewHolder extends  RecyclerView.ViewHolder implements  View.OnClickListener,View.OnLongClickListener
{
    public  TextView txttentp;
    public ImageView imghinh;


    private ItemClickListener itemClickListener;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        txttentp = (TextView)itemView.findViewById(R.id.txttensearch);
        imghinh = (ImageView)itemView.findViewById(R.id.imgsearch);
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

public class Adapter_Search_rcv extends RecyclerView.Adapter<RecyclerViewHolder> implements Filterable{

    private ArrayList<Thuc_pham> arrayList;
    private Context context;
    CustomFilter filter;
    ArrayList<Thuc_pham> filterList;

    public Adapter_Search_rcv(ArrayList<Thuc_pham> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        this.filterList=arrayList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.row_search,parent,false);
        return  new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.txttentp.setText(arrayList.get(position).getTenthucpham());
        Picasso.get().load(arrayList.get(position).getHinhanh()).into(holder.imghinh);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
//                if(isLongClick)
//                    Toast.makeText(context,"Long "  + arrayList.get(position).getTenthucpham().toString(),Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(context,"Short "+ arrayList.get(position).getTenthucpham().toString(),Toast.LENGTH_SHORT).show();
                if(isLongClick == false) {
                    Intent intent = new Intent(context, ThongTinDatHang.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("HINH", arrayList.get(position).getHinhanh());
                    bundle.putString("LOAI", arrayList.get(position).getLoai());
                    bundle.putString("TEN", arrayList.get(position).getTenthucpham());
                    bundle.putString("TINHTRANG", arrayList.get(position).getTinhtrang());
                    bundle.putFloat("GIA", arrayList.get(position).getGia());
                    bundle.putString("MOTA", arrayList.get(position).getMota());
                    intent.putExtra("BUNDLE", bundle);
                    context.startActivities(new Intent[]{intent});
                }
            }

        });

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public Filter getFilter() {
        if(filter == null)
        {
            filter=new CustomFilter();
        }

        return filter;
    }
    class CustomFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // TODO Auto-generated method stub

            FilterResults results=new FilterResults();

            if(constraint != null && constraint.length()>0)
            {
                //CONSTARINT TO UPPER
                constraint=constraint.toString().toUpperCase();

                ArrayList<Thuc_pham> filters=new ArrayList<Thuc_pham>();

                //get specific items
                for(int i=0;i<filterList.size();i++)
                {
                    if(filterList.get(i).getTenthucpham().toUpperCase().contains(constraint))
                    {
                        Thuc_pham p=new Thuc_pham(filterList.get(i).getMathucpham(),filterList.get(i).getTenthucpham(),filterList.get(i).getSize(),filterList.get(i).getMota(),filterList.get(i).getTinhtrang(), filterList.get(i).getHinhanh(),filterList.get(i).getLoai(),filterList.get(i).getGia());

                        filters.add(p);
                    }
                }
                results.count=filters.size();
                results.values=filters;
            }else
            {
                results.count=filterList.size();
                results.values=filterList;

            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // TODO Auto-generated method stub

            arrayList=(ArrayList<Thuc_pham>) results.values;
            notifyDataSetChanged();
        }
    }
}

