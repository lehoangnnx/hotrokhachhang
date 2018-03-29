package bcc.springhibernate.service;

import bcc.springhibernate.model.Quyen;

import java.util.List;

public interface QuyenService {
	List<Quyen> findAll();
	void saveOrUpdate(Quyen quyen);
	void deleted(Quyen quyen);
	void deleteOne(Integer id);
	Quyen findById(Integer id);
	Quyen findByTenQuyen(String tenquyen);
	Quyen findByMaquyen(String maquyen);
	List<Quyen> findByTrangthaiOrderByIdDesc(String trangthai);
}
