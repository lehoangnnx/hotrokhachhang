package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.model.Nhanvienkpi;

public interface NhanVienKpiService {
	void saveOrUpdate(Nhanvienkpi nhanvienkpi);
	List<Nhanvienkpi> findAll();
	List<Nhanvienkpi> findByTrangthaiOrderByIdDesc(String trangthai);
	
	Nhanvienkpi findById(Integer id);
}
