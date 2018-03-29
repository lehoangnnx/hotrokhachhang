package bcc.springhibernate.service;

import bcc.springhibernate.model.Luong;
import bcc.springhibernate.model.Nhanvien;

import java.util.List;

public interface LuongService {
	void saveOrUpdate(Luong luong);
	void deleted(Luong luong);
	List<Luong> findAll();
	List<Luong> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Luong> findByTrangthaiNotOrderByIdDesc(String trangthai);
	List<Luong> findByNhanvien(Nhanvien nhanvien);
	Luong findById(Integer id);
	Luong findOneByNhanvienAndThangAndNam(Nhanvien nhanvien, String thang,String nam);
	Luong findOneByTrangthaiNotAndNhanvienAndThangAndNam
	(String trangthai,Nhanvien nhanvien, String thang,String nam);
}
