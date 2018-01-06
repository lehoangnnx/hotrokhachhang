package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Nhanvien;

public interface NhanVienService {
	 Nhanvien findById(Integer id);
	    List<Nhanvien> findAll();
	   List<Nhanvien> findByTrangthaiOrderByIdDesc(String trangthai);
}
