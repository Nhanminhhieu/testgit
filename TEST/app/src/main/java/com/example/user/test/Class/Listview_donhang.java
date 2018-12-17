package com.example.user.test.Class;

public class Listview_donhang {
    private String ten;
    private int soluong;
    private float gia;
    private String ngaydat;
    private String diachi;
    private float phigiao;
    private String tinhtrang;
    private float tongtien;
    private String madonhang;
    public Listview_donhang() {
    }

    public Listview_donhang(String ten, int soluong, float gia, String ngaydat, String diachi, float phigiao, String tinhtrang, float tongtien) {
        this.ten = ten;
        this.soluong = soluong;
        this.gia = gia;
        this.ngaydat = ngaydat;
        this.diachi = diachi;
        this.phigiao = phigiao;
        this.tinhtrang = tinhtrang;
        this.tongtien = tongtien;
    }
    public Listview_donhang(String madonhang, String ten, int soluong, float gia, String ngaydat, String diachi, float phigiao, String tinhtrang, float tongtien) {
        this.ten = ten;
        this.soluong = soluong;
        this.gia = gia;
        this.ngaydat = ngaydat;
        this.diachi = diachi;
        this.phigiao = phigiao;
        this.tinhtrang = tinhtrang;
        this.tongtien = tongtien;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public String getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(String ngaydat) {
        this.ngaydat = ngaydat;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public float getPhigiao() {
        return phigiao;
    }

    public void setPhigiao(float phigiao) {
        this.phigiao = phigiao;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

    public String getMadonhang() {
        return madonhang;
    }

    public void setMadonhang(String madonhang) {
        this.madonhang = madonhang;
    }
}
