package bcc.springhibernate.service;

import bcc.springhibernate.model.Nhomkhachhang;

import java.util.List;

public interface NhomKhachHangService {
	void saveOrUpdate(Nhomkhachhang nhomkhachhang);
	void deleted(Nhomkhachhang nhomkhachhang);	
	List<Nhomkhachhang> findAll();
	List<Nhomkhachhang> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Nhomkhachhang> findByTrangthaiOrderByIdAsc(String trangthai);
	Nhomkhachhang findByTennhom(String tennhom);
	Nhomkhachhang findById(Integer id);
	Nhomkhachhang findFirst1ByDiemLessThanEqualOrderByDiemDesc(Integer diem);

}
