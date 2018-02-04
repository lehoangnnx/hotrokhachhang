package bcc.springhibernate.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
	List<Nhanvienkpi> findByKpiAndTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc
	(Kpi kpi, String trangthai, Date d1 , Date d2);
	List<Nhanvienkpi> findByNhanvienAndKpiAndTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc
	(Nhanvien nhanvien,Kpi kpi, String trangthai, Date d1 , Date d2);
	
	List<Nhanvienkpi> findByKpi(Kpi kpi);
	List<Nhanvienkpi> findByNhanvien(Nhanvien nhanvien);
}
