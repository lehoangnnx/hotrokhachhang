package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Khachhang;

public interface KhachHangService {
	
	void saveOrUpdate(Khachhang khachhang);
	Khachhang findById(Integer id);
	Khachhang findByMakh(String makh);
	
	List<Khachhang> findAll();
	List<Khachhang> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Khachhang> findByTrangthaiNotOrderByIdDesc(String trangthai);
}
