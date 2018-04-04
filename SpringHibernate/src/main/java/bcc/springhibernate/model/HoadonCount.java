package bcc.springhibernate.model;

public class HoadonCount {
    private long count;
    private Khachhang khachhang;
    private Nhanvien nhanvien;

    public HoadonCount() {
    }

    public HoadonCount(long count, Khachhang khachhang, Nhanvien nhanvien) {
        this.count = count;
        this.khachhang = khachhang;
        this.nhanvien = nhanvien;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Khachhang getKhachhang() {
        return khachhang;
    }

    public void setKhachhang(Khachhang khachhang) {
        this.khachhang = khachhang;
    }

    public Nhanvien getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(Nhanvien nhanvien) {
        this.nhanvien = nhanvien;
    }
}
