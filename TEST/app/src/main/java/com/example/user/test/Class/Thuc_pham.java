package com.example.user.test.Class;

public class Thuc_pham {
    String mathucpham,tenthucpham,size,mota,tinhtrang,hinhanh,loai;
    Float gia;

    public Thuc_pham(String mathucpham, String tenthucpham, String size, String mota, String tinhtrang, String hinhanh, String loai, Float gia) {
        this.mathucpham = mathucpham;
        this.tenthucpham = tenthucpham;
        this.size = size;
        this.mota = mota;
        this.tinhtrang = tinhtrang;
        this.hinhanh = hinhanh;
        this.loai = loai;
        this.gia = gia;
    }

    public Thuc_pham() {
    }

    public String getMathucpham() {
        return mathucpham;
    }

    public void setMathucpham(String mathucpham) {
        this.mathucpham = mathucpham;
    }

    public String getTenthucpham() {
        return tenthucpham;
    }

    public void setTenthucpham(String tenthucpham) {
        this.tenthucpham = tenthucpham;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }
}
