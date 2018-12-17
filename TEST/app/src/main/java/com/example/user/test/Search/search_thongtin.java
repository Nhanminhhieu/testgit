package com.example.user.test.Search;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.user.test.Class.Thuc_pham;
import com.example.user.test.R;

import java.util.ArrayList;

import static com.example.user.test.Fragment.Fragment_trangchu.thucPhamArrayList;

public class search_thongtin extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_thongtin);
        recyclerView = (RecyclerView)findViewById(R.id.rcv_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view_tt, menu);
        MenuItem item = menu.findItem(R.id.srchv);
        SearchView searchView = (SearchView)item.getActionView();
        final Adapter_Search_rcv adapter_rcv = new Adapter_Search_rcv(thucPhamArrayList,search_thongtin.this);
        //
        //Trang tri
        LinearLayoutManager layoutManager = new LinearLayoutManager(search_thongtin.this.getApplicationContext());
      //  recyclerView.addItemDecoration(new DividerItemDecoration(search_thongtin.this, LinearLayoutManager.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(search_thongtin.this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        //gan gia tri cho rcv
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter_rcv.getFilter().filter(newText);
                recyclerView.setAdapter(adapter_rcv);
                return false;
            }
        });
        return true;
    }

}
