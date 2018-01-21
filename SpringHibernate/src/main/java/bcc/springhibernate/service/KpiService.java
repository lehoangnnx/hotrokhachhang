package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Kpi;
import org.springframework.data.domain.Pageable;

public interface KpiService {
	void saveOrUpdate(Kpi kpi);
	List<Kpi> findAll();
	List<Kpi> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Kpi> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable);
	Kpi findByTen(String ten);
	Kpi findById(Integer id);
}
