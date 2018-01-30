package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Nhanvien;

public interface NhanVienService {
	void saveOrUpdate(Nhanvien nhanvien);
	void deleted(Nhanvien nhanvien);
	Nhanvien findById(Integer id);
	 Nhanvien findByManhanvien(String manhanvien);
	    List<Nhanvien> findAll();
	   List<Nhanvien> findByTrangthaiOrderByIdDesc(String trangthai);
	   List<Nhanvien> findByTrangthaiNotOrderByIdDesc(String trangthai);
}
