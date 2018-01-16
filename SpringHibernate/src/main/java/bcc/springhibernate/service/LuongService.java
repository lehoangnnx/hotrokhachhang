package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Luong;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhomkhachhang;

public interface LuongService {
	void saveOrUpdate(Luong luong);
	
	List<Luong> findAll();
	List<Luong> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Luong> findByTrangthaiNotOrderByIdDesc(String trangthai);
	Luong findByNhanvien(Nhanvien nhanvien);
	Luong findById(Integer id);
	Luong findByNhanvienAndThangAndNam(Nhanvien nhanvien, String thang,String nam);
}
