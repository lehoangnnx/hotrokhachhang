package bcc.springhibernate.service;

import bcc.springhibernate.model.Tieuchichamsoc;

import java.util.List;

public interface TieuChiChamSocService {
	
	void saveOrUpdate(Tieuchichamsoc  tieuchichamsoc);
	void deleted(Tieuchichamsoc  tieuchichamsoc);
	List<Tieuchichamsoc> findAll();
	List<Tieuchichamsoc> findByTrangthaiOrderByIdDesc(String trangthai);
	Tieuchichamsoc findByTentieuchi(String tentieuchi);
	Tieuchichamsoc findById(Integer id);
}
