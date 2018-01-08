package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Kpi;

public interface KpiService {
	void saveOrUpdate(Kpi kpi);
	List<Kpi> findAll();
	List<Kpi> findByTrangthaiOrderByIdDesc(String trangthai);
	Kpi findByTen(String ten);
	Kpi findById(Integer id);
}
