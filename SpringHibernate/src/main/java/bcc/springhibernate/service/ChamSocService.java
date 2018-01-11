package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Chamsoc;
import bcc.springhibernate.model.Hoadon;

public interface ChamSocService {
	void saveOrUpdate(Chamsoc chamsoc);
	List<Chamsoc> findAll();
	List<Chamsoc> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Chamsoc> findByTrangthaiNotOrderByIdDesc(String trangthai);
	
	Chamsoc findById(Integer id);
}
