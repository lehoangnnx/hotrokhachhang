package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Nhomhang;

public interface NhomHangService {
	void saveOrUpdate(Nhomhang nhomhang);
	void deleted(Nhomhang nhomhang);
	List<Nhomhang> findAll();
	List<Nhomhang> findByTrangthaiOrderByIdDesc(String trangthai);
	Nhomhang findByManhom(String manhom);
	Nhomhang findById(Integer id);
	List<Nhomhang> findByManhomcha(String manhomcha);
}
