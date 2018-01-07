package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Nhanvien;

public interface NhanVienService {
	void saveOrUpdate(Nhanvien nhanvien);
	 Nhanvien findById(Integer id);
	 Nhanvien findByManhanvien(String manhanvien);
	    List<Nhanvien> findAll();
	   List<Nhanvien> findByTrangthaiOrderByIdDesc(String trangthai);
}
