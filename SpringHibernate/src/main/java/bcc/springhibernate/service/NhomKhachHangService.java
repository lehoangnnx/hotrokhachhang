package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Nhomkhachhang;

public interface NhomKhachHangService {
	void saveOrUpdate(Nhomkhachhang nhomkhachhang);
	
	List<Nhomkhachhang> findAll();
	List<Nhomkhachhang> findByTrangthaiOrderByIdDesc(String trangthai);
	Nhomkhachhang findByTennhom(String tennhom);
	Nhomkhachhang findById(Integer id);
}
