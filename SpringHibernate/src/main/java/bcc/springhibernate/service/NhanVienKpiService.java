package bcc.springhibernate.service;

import java.util.Date;
import java.util.List;

import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhanvienkpi;

public interface NhanVienKpiService {
	void saveOrUpdate(Nhanvienkpi nhanvienkpi);
	void deleted(Nhanvienkpi nhanvienkpi);
	List<Nhanvienkpi> findAll();
	List<Nhanvienkpi> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Nhanvienkpi> findByTrangthaiNotOrderByIdDesc(String trangthai);
	Nhanvienkpi findById(Integer id);
	
	List<Nhanvienkpi> findByKpiAndTrangthaiNotOrderByNgaydangkyDesc(Kpi kpi, String trangthai);
	
	List<Nhanvienkpi> findByKpiAndTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc
	(Kpi kpi, String trangthai, Date d1 , Date d2);
	List<Nhanvienkpi> findByNhanvienAndKpiAndTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc
	(Nhanvien nhanvien,Kpi kpi, String trangthai, Date d1 , Date d2);
	
	List<Nhanvienkpi> findByKpi(Kpi kpi);
	List<Nhanvienkpi> findByNhanvien(Nhanvien nhanvien);
	
	List<Nhanvienkpi> findByNhanvienAndTrangthaiOrderByIdDesc(Nhanvien  nhanvien,String trangthai);
}
