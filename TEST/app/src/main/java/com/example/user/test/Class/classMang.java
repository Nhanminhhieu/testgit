package com.example.user.test.Class;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class classMang {
    ArrayList mang = new ArrayList();
    Integer sopt=0;

    public classMang() {
    }

    public classMang(ArrayList mang, Integer sopt) {
        this.mang = mang;
        this.sopt = sopt;
    }

    public ArrayList getMang() {
        return mang;
    }

    public void setMang(ArrayList mang) {
        this.mang = mang;
    }

    public Integer getSopt() {
        return sopt;
    }

    public void setSopt(Integer sopt) {
        this.sopt = sopt;
    }
    public void setPTMang(Thuc_pham a)
    {
        this.mang.add(a);
    }
}
