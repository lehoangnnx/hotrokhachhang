package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Hanghoa;

public interface HangHoaService {
	void saveOrUpdate(Hanghoa hanghoa);
	List<Hanghoa> findAll();
	List<Hanghoa> findByTrangthaiOrderByIdDesc(String trangthai);
	Hanghoa findByMahang(String mahang);
	Hanghoa findById(Integer id);
}
