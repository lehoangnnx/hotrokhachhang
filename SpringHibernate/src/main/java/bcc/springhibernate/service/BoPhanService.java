package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Bophan;
import bcc.springhibernate.model.Loaikhachhang;

public interface BoPhanService {
	
	void saveOrUpdate(Bophan bophan);
	
	List<Bophan> findAll();
	List<Bophan> findByTrangthaiOrderByIdDesc(String trangthai);
	Bophan findByTenbophan(String tenbophan);
	Bophan findById(Integer id);
}
