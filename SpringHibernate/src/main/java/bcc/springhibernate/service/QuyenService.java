package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Quyen;

public interface QuyenService {
	List<Quyen> findAll();
	void saveOrUpdate(Quyen quyen);
	void deleteOne(Integer id);
	Quyen findById(Integer id);
	Quyen findByTenQuyen(String tenquyen);
	List<Quyen> findAllByTrangThaiOrderByIdDesc(String trangthai);
}
