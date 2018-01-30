package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Tieuchichamsoc;

public interface TieuChiChamSocService {
	
	void saveOrUpdate(Tieuchichamsoc  tieuchichamsoc);
	void deleted(Tieuchichamsoc  tieuchichamsoc);
	List<Tieuchichamsoc> findAll();
	List<Tieuchichamsoc> findByTrangthaiOrderByIdDesc(String trangthai);
	Tieuchichamsoc findByTentieuchi(String tentieuchi);
	Tieuchichamsoc findById(Integer id);
}
