package bcc.springhibernate.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.Nhanvien;

@Repository
public interface HoaDonRepository extends JpaRepository<Hoadon, Integer> {

	List<Hoadon> findAll();

	List<Hoadon> findByTrangthaiOrderByIdDesc(String trangthai);

	List<Hoadon> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable);

	List<Hoadon> findByTrangthaiNotOrderByIdDesc(String trangthai);

	Hoadon findBySohoadon(String sohoadon);

	Hoadon findById(Integer id);

	List<Hoadon> findByTrangthaiAndNgaylapBetweenOrderByIdDesc(String trangthai, Date d1, Date d2);

	List<Hoadon> findByTrangthaiNotAndNgaylapBetweenOrderByIdDesc(String trangthai, Date d1, Date d2);

	List<Hoadon> findByTrangthaiAndNhanvienByIdnhanvienbanAndNgaylapBetweenOrderByIdDesc(String trangthai,
			Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2);

	List<Hoadon> findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaylapBetweenOrderByIdDesc(String trangthai,
			Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2);
	
	@Query("select hd from Hoadon hd where hd.tongtien = hd.tiendatra and hd.congno = 0 and trangthai <> ?1")
	List<Hoadon> findByTrangthaiDaThanhToan(String trangthai);
	@Query("select hd from Hoadon hd where hd.tongtien = hd.tiendatra and hd.congno = 0 and trangthai <> ?1")
	List<Hoadon> findByTrangthaiDaThanhToan(String trangthai,Pageable pageable);
	
	@Query("select hd from Hoadon hd where hd.tongtien > hd.tiendatra and hd.congno > 0 and trangthai <> ?1")
	List<Hoadon> findByTrangthaiChuaThanhToan(String trangthai);
	@Query("select hd from Hoadon hd where hd.tongtien > hd.tiendatra and hd.congno > 0 and trangthai <> ?1")
	List<Hoadon> findByTrangthaiChuaThanhToan(String trangthai,Pageable pageable);
	
	List<Hoadon> findByNhanvienByIdnhanvienbanAndTrangthaiNotAndNgaylapBetweenOrderByIdDesc
	(Nhanvien nhanvien,String trangthai, Date d1, Date d2);
}
