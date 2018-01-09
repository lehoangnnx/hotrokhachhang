package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Hoadon;

public interface HoaDonService {
	void saveOrUpdate(Hoadon hoadon);
	List<Hoadon> findAll();
	List<Hoadon> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Hoadon> findByTrangthaiNotOrderByIdDesc(String trangthai);
	Hoadon findBySohoadon(String sohoadon);
	Hoadon findById(Integer id);
}
