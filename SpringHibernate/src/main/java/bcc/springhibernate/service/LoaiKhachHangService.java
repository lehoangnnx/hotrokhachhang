package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Loaikhachhang;

public interface LoaiKhachHangService {
	void saveOrUpdate(Loaikhachhang loaikhachhang);
	void deleted(Loaikhachhang loaikhachhang);
	List<Loaikhachhang> findAll();
	List<Loaikhachhang> findByTrangthaiOrderByIdDesc(String trangthai);
	
	Loaikhachhang findByTenloai(String tenloai);
	Loaikhachhang findById(Integer id);
}
