package com.example.user.test.Class;

public class Listview_thongbao {
    private String nhan;
    private String chitiet;
    private String hinh;

    public Listview_thongbao() {
    }

    public Listview_thongbao(String nhan, String chitiet, String hinh) {
        this.nhan = nhan;
        this.chitiet = chitiet;
        this.hinh = hinh;
    }

    public String getNhan() {
        return nhan;
    }

    public void setNhan(String nhan) {
        this.nhan = nhan;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
}
