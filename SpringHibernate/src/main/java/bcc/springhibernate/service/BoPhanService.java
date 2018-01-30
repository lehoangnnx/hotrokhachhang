package bcc.springhibernate.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import bcc.springhibernate.model.Bophan;
import bcc.springhibernate.model.Loaikhachhang;

public interface BoPhanService {
	
	void saveOrUpdate(Bophan bophan);
	void deleted(Bophan bophan);
	List<Bophan> findAll();
	List<Bophan> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Bophan> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable);
	Bophan findByTenbophan(String tenbophan);
	Bophan findById(Integer id);
}
