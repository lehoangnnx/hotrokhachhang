package bcc.springhibernate.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import bcc.springhibernate.model.Chamsoc;
import bcc.springhibernate.model.Hoadon;

public interface ChamSocService {
	void saveOrUpdate(Chamsoc chamsoc);
	void deleted(Chamsoc chamsoc);
	List<Chamsoc> findAll();
	List<Chamsoc> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Chamsoc> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable);
	List<Chamsoc> findByTrangthaiNotOrderByIdDesc(String trangthai);
	List<Chamsoc> findByTrangthaiNotAndNhanvienbanhangOrderByIdDesc(String trangthai, Integer nhanvienbanhang);
	
	Chamsoc findById(Integer id);
	
	List<Chamsoc> findByNhanvienchamsocAndTrangthaiNotAndNgayBetweenOrderByIdDesc
	(Integer nhanvien,String trangthai, Date d1, Date d2);
}
