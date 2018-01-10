package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Loaikhachhang;

public interface LoaiKhachHangService {
	List<Loaikhachhang> findAll();
	List<Loaikhachhang> findByTrangthaiOrderByIdDesc(String trangthai);
	void saveOrUpdate(Loaikhachhang loaikhachhang);
	Loaikhachhang findByTenloai(String tenloai);
	Loaikhachhang findById(Integer id);
}
