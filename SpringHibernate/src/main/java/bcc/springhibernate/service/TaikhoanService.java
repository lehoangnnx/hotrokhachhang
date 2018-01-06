package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Taikhoan;

public interface TaikhoanService {
	List<Taikhoan> findAll();
	void saveOrUpdate(Taikhoan tk);
	void deleteOne(Integer id);
	Taikhoan findById(Integer id);
	Taikhoan findByUsername(String username);
	
}
