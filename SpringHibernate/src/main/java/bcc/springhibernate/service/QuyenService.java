package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Quyen;

public interface QuyenService {
	List<Quyen> findAll();
	void saveOrUpdate(Quyen quyen);
	void deleteOne(Integer id);
	Quyen findById(Integer id);
	Quyen findByTenQuyen(String tenquyen);
	Quyen findByMaquyen(String maquyen);
	List<Quyen> findByTrangthaiOrderByIdDesc(String trangthai);
}
