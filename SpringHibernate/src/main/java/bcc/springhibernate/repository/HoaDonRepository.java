package bcc.springhibernate.repository;

import java.util.Date;
import java.util.List;

import bcc.springhibernate.model.HoadonCount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Nhanvien;

@Repository
public interface HoaDonRepository extends JpaRepository<Hoadon, Integer> {

	List<Hoadon> findAll();

	List<Hoadon> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Hoadon> findByTrangthaiNotOrderByIdDesc(String trangthai);
	List<Hoadon> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable);

	List<Hoadon> findByTrangthaiAndNhanvienByIdnhanvienbanOrderByIdDesc
	(String trangthai,Nhanvien nhanvienByIdnhanvienban);
	List<Hoadon> findByTrangthaiNotAndNhanvienByIdnhanvienbanOrderByIdDesc
	(String trangthai,Nhanvien nhanvienByIdnhanvienban);
	List<Hoadon> findByTrangthaiNotAndNhanvienByIdnhanvienbanOrderByIdDesc
			(String trangthai,Nhanvien nhanvienByIdnhanvienban, Pageable pageable);

	List<Hoadon> findByTrangthaiAndNhanvienByIdnhanvienbanOrderByIdDesc
	(String trangthai,Nhanvien nhanvienByIdnhanvienban, Pageable pageable);

	List<Hoadon> findByTrangthaiAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc
			(String trangthai,Nhanvien nhanvienByIdnhanvienban,Date d1, Date d2);
	List<Hoadon> findByTrangthaiAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc
			(String trangthai,Nhanvien nhanvienByIdnhanvienban,Date d1, Date d2,Pageable pageable);

	Hoadon findBySohoadon(String sohoadon);

	Hoadon findById(Integer id);

	List<Hoadon> findByTrangthaiAndNgaylapBetweenOrderByIdDesc(String trangthai, Date d1, Date d2);

	List<Hoadon> findByTrangthaiNotAndNgaylapBetweenOrderByIdDesc(String trangthai, Date d1, Date d2);
	List<Hoadon> findByTrangthaiNotAndNgaythanhtoanBetweenOrderByIdDesc(String trangthai, Date d1, Date d2);

	List<Hoadon> findByTrangthaiAndNhanvienByIdnhanvienbanAndNgaylapBetweenOrderByIdDesc(String trangthai,
			Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2);

	List<Hoadon> findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaylapBetweenOrderByIdDesc(String trangthai,
			Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2);
	List<Hoadon> findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc
	(String trangthai,Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2);
	List<Hoadon> findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc
			(String trangthai,Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2, Pageable pageable);
	
	@Query("select hd from Hoadon hd where hd.tongtien = hd.tiendatra and hd.congno = 0 and hd.trangthai <> ?1")
	List<Hoadon> findByTrangthaiDaThanhToan(String trangthai);

	@Query("select hd from Hoadon hd where hd.tongtien = hd.tiendatra and hd.congno = 0 and hd.trangthai <> ?1")
	List<Hoadon> findByTrangthaiDaThanhToan(String trangthai,Pageable pageable);

	@Query("select hd from Hoadon hd where hd.tongtien = hd.tiendatra and hd.congno = 0 and hd.trangthai <> ?1 " +
			"and hd.nhanvienByIdnhanvienban = ?2 ")
	List<Hoadon> findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienban(String trangthai,Nhanvien nhanvienByIdnhanvienban);

	@Query("select hd from Hoadon hd where hd.tongtien = hd.tiendatra and hd.congno = 0 and hd.trangthai <> ?1 " +
			"and hd.nhanvienByIdnhanvienban = ?2 ")
	List<Hoadon> findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienban(String trangthai,Nhanvien nhanvienByIdnhanvienban,
																	  Pageable pageable);

	@Query("select hd from Hoadon hd where hd.tongtien = hd.tiendatra and hd.congno = 0 and hd.trangthai <> ?1 " +
			"and hd.nhanvienByIdnhanvienban = ?2 and hd.ngaythanhtoan between ?3 and ?4 ")
	List<Hoadon> findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween
			(String trangthai,Nhanvien nhanvienByIdnhanvienban , Date d1, Date d2);
	@Query("select hd from Hoadon hd where hd.tongtien = hd.tiendatra and hd.congno = 0 and hd.trangthai <> ?1 " +
			"and hd.nhanvienByIdnhanvienban = ?2 and hd.ngaythanhtoan between ?3 and ?4 ")
	List<Hoadon> findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween
			(String trangthai,Nhanvien nhanvienByIdnhanvienban , Date d1, Date d2,Pageable pageable);


	@Query("select hd from Hoadon hd where hd.tongtien > hd.tiendatra and hd.congno > 0 and hd.trangthai <> ?1")
	List<Hoadon> findByTrangthaiChuaThanhToan(String trangthai);
	@Query("select hd from Hoadon hd where hd.tongtien > hd.tiendatra and hd.congno > 0 and hd.trangthai <> ?1")
	List<Hoadon> findByTrangthaiChuaThanhToan(String trangthai,Pageable pageable);
	@Query("select hd from Hoadon hd where hd.tongtien > hd.tiendatra and hd.congno > 0 and hd.trangthai <> ?1 " +
			"and hd.nhanvienByIdnhanvienban = ?2")
	List<Hoadon> findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienban(String trangthai,Nhanvien nhanvienByIdnhanvienban);
	@Query("select hd from Hoadon hd where hd.tongtien > hd.tiendatra and hd.congno > 0 and hd.trangthai <> ?1 " +
			"and hd.nhanvienByIdnhanvienban = ?2")
	List<Hoadon> findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienban(String trangthai,Nhanvien nhanvienByIdnhanvienban,
																		Pageable pageable);

	@Query("select hd from Hoadon hd where hd.tongtien > hd.tiendatra and hd.congno > 0 and hd.trangthai <> ?1 " +
			"and hd.nhanvienByIdnhanvienban = ?2 and hd.ngaythanhtoan between ?3 and ?4")
	List<Hoadon> findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween
			(String trangthai,Nhanvien nhanvienByIdnhanvienban,Date d1, Date d2);
	@Query("select hd from Hoadon hd where hd.tongtien > hd.tiendatra and hd.congno > 0 and hd.trangthai <> ?1 " +
			"and hd.nhanvienByIdnhanvienban = ?2 and hd.ngaythanhtoan between ?3 and ?4")
	List<Hoadon> findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween
			(String trangthai,Nhanvien nhanvienByIdnhanvienban,Date d1, Date d2,Pageable pageable);


	List<Hoadon> findByNhanvienByIdnhanvienbanAndTrangthaiNotAndNgaylapBetweenOrderByIdDesc
	(Nhanvien nhanvien,String trangthai, Date d1, Date d2);
	
	List<Hoadon> findByNhanvienByIdnhanvienlaphoadonOrNhanvienByIdnhanvienbanOrNhanvienByIdnhanviengiaohangOrNhanvienByIdnhanvienchamsoc
	(Nhanvien nhanvienlaphoadon,Nhanvien nhanvienban,
			Nhanvien nhanviengiaohang,Nhanvien nhanvienchamsoc);

	List<Hoadon> findByKhachhang(Khachhang khachhang);
	//List<Hoadon> findDistinctKhachhangByNhanvienByIdnhanvienbanAndTrangthaiNot(Nhanvien nhanvienByIdnhanvienban, String trangthai);
	List<Hoadon> findKhachhangDistinctByNhanvienByIdnhanvienbanAndTrangthaiNot(Nhanvien nhanvienByIdnhanvienban, String trangthai);

	@Query("SELECT distinct hd.khachhang from Hoadon hd where hd.nhanvienByIdnhanvienban = ?1 and hd.trangthai <> ?2")
	List<Khachhang> findDistinctKhachhangByNhanvienByIdnhanvienbanAndTrangthaiNot(Nhanvien nhanvienByIdnhanvienban, String trangthai);

	@Query("SELECT new bcc.springhibernate.model.HoadonCount(count(hd.id), hd.khachhang , hd.nhanvienByIdnhanvienban) FROM Hoadon hd " +
			"WHERE hd.tiendatra < hd.tongtien AND hd.congno > 0 GROUP BY hd.khachhang, hd.nhanvienByIdnhanvienban " +
			"HAVING count(hd.id) >=2 ")
	List<HoadonCount> findHoadonWhereKhachhangChuathanhtoan();
}
