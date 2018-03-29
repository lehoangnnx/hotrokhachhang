package bcc.springhibernate.service;

import bcc.springhibernate.model.Chamsoc;
import bcc.springhibernate.model.Chitietchamsoc;

import java.util.List;

public interface ChiTietChamSocService {
	void saveOrUpdate(Chitietchamsoc chitietchamsoc);
	void delete(Chitietchamsoc chitietchamsoc);
	List<Chitietchamsoc> findAll();
	List<Chitietchamsoc> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Chitietchamsoc> findByTrangthaiNotOrderByIdDesc(String trangthai);
	List<Chitietchamsoc> findByChamsoc(Chamsoc chamsoc);
	List<Chitietchamsoc> findByChamsocAndTrangthaiOrderByIdDesc(Chamsoc chamsoc,String trangthai);
	Chitietchamsoc findById(Integer id);
}
