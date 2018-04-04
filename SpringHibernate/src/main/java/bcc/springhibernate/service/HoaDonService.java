package bcc.springhibernate.service;

import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.HoadonCount;
import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Nhanvien;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface HoaDonService {
	void saveOrUpdate(Hoadon hoadon);
	void deleted(Hoadon hoadon);
	
	List<Hoadon> findAll();
	List<Hoadon> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Hoadon> findByTrangthaiNotOrderByIdDesc(String trangthai);
	List<Hoadon> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable);
	List<Hoadon> findByTrangthaiAndNhanvienByIdnhanvienbanOrderByIdDesc(String trangthai,Nhanvien nhanvienByIdnhanvienban);
	List<Hoadon> findByTrangthaiNotAndNhanvienByIdnhanvienbanOrderByIdDesc(String trangthai,Nhanvien nhanvienByIdnhanvienban);
	List<Hoadon> findByTrangthaiNotAndNhanvienByIdnhanvienbanOrderByIdDesc
			(String trangthai,Nhanvien nhanvienByIdnhanvienban, Pageable pageable);

	List<Hoadon> findByTrangthaiAndNhanvienByIdnhanvienbanOrderByIdDesc(String trangthai,Nhanvien nhanvienByIdnhanvienban, Pageable pageable);

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
	List<Hoadon> findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc(String trangthai,
																								  Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2);
	List<Hoadon> findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc
			(String trangthai,Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2, Pageable pageable);

	List<Hoadon> findByTrangthaiDaThanhToan(String trangthai);
	List<Hoadon> findByTrangthaiDaThanhToan(String trangthai,Pageable pageable);
	List<Hoadon> findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienban(String trangthai,Nhanvien nhanvienByIdnhanvienban);
	List<Hoadon> findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienban(String trangthai,Nhanvien nhanvienByIdnhanvienban,
																	  Pageable pageable);


	List<Hoadon> findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween
			(String trangthai,Nhanvien nhanvienByIdnhanvienban , Date d1, Date d2);
	List<Hoadon> findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween
			(String trangthai,Nhanvien nhanvienByIdnhanvienban , Date d1, Date d2,Pageable pageable);


	List<Hoadon> findByTrangthaiChuaThanhToan(String trangthai);
	List<Hoadon> findByTrangthaiChuaThanhToan(String trangthai,Pageable pageable);
	List<Hoadon> findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienban(String trangthai,Nhanvien nhanvienByIdnhanvienban);
	List<Hoadon> findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienban(String trangthai,Nhanvien nhanvienByIdnhanvienban,
																		Pageable pageable);


	List<Hoadon> findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween
			(String trangthai,Nhanvien nhanvienByIdnhanvienban,Date d1, Date d2);
	List<Hoadon> findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween
			(String trangthai,Nhanvien nhanvienByIdnhanvienban,Date d1, Date d2,Pageable pageable);


	List<Hoadon> findByNhanvienByIdnhanvienbanAndTrangthaiNotAndNgaylapBetweenOrderByIdDesc
	(Nhanvien nhanvien,String trangthai, Date d1, Date d2);
	
	List<Hoadon> findByNhanvienByIdnhanvienlaphoadonOrNhanvienByIdnhanvienbanOrNhanvienByIdnhanviengiaohangOrNhanvienByIdnhanvienchamsoc
	(Nhanvien nhanvienlaphoadon,Nhanvien nhanvienban,
			Nhanvien nhanviengiaohang,Nhanvien nhanvienchamsoc);
	
	List<Hoadon> findByKhachhang(Khachhang khachhang);
	List<Khachhang> findDistinctKhachhangByNhanvienByIdnhanvienbanAndTrangthaiNot(Nhanvien nhanvienByIdnhanvienban, String trangthai);
	List<Hoadon> findKhachhangDistinctByNhanvienByIdnhanvienbanAndTrangthaiNot(Nhanvien nhanvienByIdnhanvienban, String trangthai);

	List<HoadonCount> findHoadonWhereKhachhangChuathanhtoan();
}
