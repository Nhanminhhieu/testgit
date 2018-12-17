package com.example.user.test.Class;

public class ClassTaiKhoan {
    String tendangnhap,manv,matkhau,hoatdong;

    public ClassTaiKhoan() {
    }

    public ClassTaiKhoan(String tendangnhap, String manv, String matkhau, String hoatdong) {
        this.tendangnhap = tendangnhap;
        this.manv = manv;
        this.matkhau = matkhau;
        this.hoatdong = hoatdong;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getHoatdong() {
        return hoatdong;
    }

    public void setHoatdong(String hoatdong) {
        this.hoatdong = hoatdong;
    }
}
