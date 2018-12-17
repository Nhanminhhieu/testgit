package com.example.user.test.Class;

public class ClassKhachHang {
   String hoten, sdt,diachi,hinh,taikhoan;

    public ClassKhachHang() {
    }

    public ClassKhachHang(String hoten, String sdt, String diachi, String hinh, String taikhoan) {
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.hinh = hinh;
        this.taikhoan = taikhoan;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }
}
