package bcc.springhibernate.service;

import bcc.springhibernate.model.Chitiethoadon;
import bcc.springhibernate.model.Hoadon;

import java.util.List;

public interface ChiTietHoaDonService {
	void saveOrUpdate(Chitiethoadon chitiethoadon);
	void delete(Chitiethoadon chitiethoadon);
	List<Chitiethoadon> findAll();
	List<Chitiethoadon> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Chitiethoadon> findByTrangthaiNotOrderByIdDesc(String trangthai);
	List<Chitiethoadon> findByHoadon(Hoadon hoadon);
	List<Chitiethoadon> findByHoadonAndTrangthaiOrderByIdDesc(Hoadon hoadon,String trangthai);
	Chitiethoadon findById(Integer id);
}
