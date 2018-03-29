package bcc.springhibernate.service;

import bcc.springhibernate.model.Loaikhachhang;

import java.util.List;

public interface LoaiKhachHangService {
	void saveOrUpdate(Loaikhachhang loaikhachhang);
	void deleted(Loaikhachhang loaikhachhang);
	List<Loaikhachhang> findAll();
	List<Loaikhachhang> findByTrangthaiOrderByIdDesc(String trangthai);
	
	Loaikhachhang findByTenloai(String tenloai);
	Loaikhachhang findById(Integer id);
}
