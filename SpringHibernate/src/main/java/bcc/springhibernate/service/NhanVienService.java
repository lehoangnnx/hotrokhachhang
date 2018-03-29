package bcc.springhibernate.service;

import bcc.springhibernate.model.Nhanvien;

import java.util.List;

public interface NhanVienService {
	void saveOrUpdate(Nhanvien nhanvien);
	void deleted(Nhanvien nhanvien);
	Nhanvien findById(Integer id);
	 Nhanvien findByManhanvien(String manhanvien);
	    List<Nhanvien> findAll();
	   List<Nhanvien> findByTrangthaiOrderByIdDesc(String trangthai);
	   List<Nhanvien> findByTrangthaiNotOrderByIdDesc(String trangthai);
}
