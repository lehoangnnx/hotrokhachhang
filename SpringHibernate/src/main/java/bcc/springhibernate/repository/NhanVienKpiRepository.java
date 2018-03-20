package bcc.springhibernate.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhanvienkpi;
import bcc.springhibernate.model.Nhomkhachhang;
@Repository
public interface NhanVienKpiRepository extends JpaRepository<Nhanvienkpi, Integer> {
	List<Nhanvienkpi> findAll();
	List<Nhanvienkpi> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Nhanvienkpi> findByTrangthaiNotOrderByIdDesc(String trangthai);
	Nhanvienkpi findById(Integer id);
	
	List<Nhanvienkpi> findByKpiAndTrangthaiNotOrderByNgaydangkyDesc(Kpi kpi, String trangthai);
	
	List<Nhanvienkpi> findByTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc
	( String trangthai, Date d1 , Date d2);
	
	List<Nhanvienkpi> findByKpiAndTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc
	(Kpi kpi, String trangthai, Date d1 , Date d2);
	List<Nhanvienkpi> findByNhanvienAndKpiAndTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc
	(Nhanvien nhanvien,Kpi kpi, String trangthai, Date d1 , Date d2);
	
	List<Nhanvienkpi> findByKpi(Kpi kpi);
	List<Nhanvienkpi> findByNhanvien(Nhanvien nhanvien);
	
	List<Nhanvienkpi> findByNhanvienAndTrangthaiOrderByIdDesc(Nhanvien  nhanvien,String trangthai);

	@Query("select nvk from Nhanvienkpi nvk where nvk.nhanvien = ?1 and nvk.trangthai = ?2 and nvk.ngaydangky between ?3 and ?4")
	List<Nhanvienkpi> findByNhanvienAndTrangthaiAndNgaydangkyBetween(Nhanvien  nhanvien,String trangthai, Date d1, Date d2);

	@Query("select nvk from Nhanvienkpi nvk where nvk.trangthai <> ?1 and nvk.nhanvien = ?2 and nvk.kpi = ?3 " +
			"and  nvk.ngaydangky between ?4 and ?5")
	Nhanvienkpi findByTrangthaiAndNhanvienAndKpiAndMonthYearNgaydangky
			(String trangthai,Nhanvien nhanvien, Kpi kpi, Date d1,Date d2);
}
