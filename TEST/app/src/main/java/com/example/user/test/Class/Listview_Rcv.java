package com.example.user.test.Class;

import java.util.ArrayList;

public class Listview_Rcv {
    private String tieude;
    private ArrayList arrayListRcv;

    public Listview_Rcv(String tieude, ArrayList arrayListRcv) {
        this.tieude = tieude;
        this.arrayListRcv = arrayListRcv;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public ArrayList getArrayListRcv() {
        return arrayListRcv;
    }

    public void setArrayListRcv(ArrayList arrayListRcv) {
        this.arrayListRcv = arrayListRcv;
    }
}
