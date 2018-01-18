package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Nhomkhachhang;

public interface KhachHangService {
	
	void saveOrUpdate(Khachhang khachhang);
	Khachhang findById(Integer id);
	Khachhang findByMakh(String makh);
	
	List<Khachhang> findAll();
	List<Khachhang> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Khachhang> findByTrangthaiNotOrderByIdDesc(String trangthai);
	List<Khachhang> findByNhomkhachhangAndTrangthaiOrderByIdDesc(Nhomkhachhang nhomkhachhang ,String trangthai);
	List<Khachhang> findByLoaikhachhangAndTrangthaiOrderByIdDesc(Loaikhachhang loaikhachhang,String trangthai);
	
	List<Khachhang> findByLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc(Loaikhachhang loaikhachhang,
			Nhomkhachhang nhomkhachhang ,String trangthai);
}
