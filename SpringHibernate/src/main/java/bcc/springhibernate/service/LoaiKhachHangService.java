package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Loaikhachhang;

public interface LoaiKhachHangService {
	List<Loaikhachhang> findAll();
	List<Loaikhachhang> findLoaikhachhangByTrangthaiOrderByIdDesc(String trangthai);
	void saveOrUpdate(Loaikhachhang loaikhachhang);
	Loaikhachhang findLoaikhachhangByTenloai(String tenloai);
	Loaikhachhang findLoaikhachhangById(Integer id);
}
