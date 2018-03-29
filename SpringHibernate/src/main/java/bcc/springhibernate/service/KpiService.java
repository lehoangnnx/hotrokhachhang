package bcc.springhibernate.service;

import bcc.springhibernate.model.Kpi;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KpiService {
	void saveOrUpdate(Kpi kpi);
	void deleted(Kpi kpi);
	List<Kpi> findAll();
	List<Kpi> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Kpi> findByTrangthaiNotOrderByIdDesc(String trangthai);
	List<Kpi> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable);
	Kpi findByTen(String ten);
	Kpi findById(Integer id);
}
