package bcc.springhibernate.service;

import bcc.springhibernate.model.Bophan;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoPhanService {
	
	void saveOrUpdate(Bophan bophan);
	void deleted(Bophan bophan);
	List<Bophan> findAll();
	List<Bophan> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Bophan> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable);
	Bophan findByTenbophan(String tenbophan);
	Bophan findById(Integer id);
}
