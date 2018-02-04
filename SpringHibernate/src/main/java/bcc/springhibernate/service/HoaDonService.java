package bcc.springhibernate.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Nhanvien;

public interface HoaDonService {
	void saveOrUpdate(Hoadon hoadon);
	void deleted(Hoadon hoadon);
	
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
	
	List<Hoadon> findByTrangthaiDaThanhToan(String trangthai);
	List<Hoadon> findByTrangthaiDaThanhToan(String trangthai,Pageable pageable);
	
	List<Hoadon> findByTrangthaiChuaThanhToan(String trangthai);
	List<Hoadon> findByTrangthaiChuaThanhToan(String trangthai,Pageable pageable);
	
	List<Hoadon> findByNhanvienByIdnhanvienbanAndTrangthaiNotAndNgaylapBetweenOrderByIdDesc
	(Nhanvien nhanvien,String trangthai, Date d1, Date d2);
	
	List<Hoadon> findByNhanvienByIdnhanvienlaphoadonOrNhanvienByIdnhanvienbanOrNhanvienByIdnhanviengiaohangOrNhanvienByIdnhanvienchamsoc
	(Nhanvien nhanvienlaphoadon,Nhanvien nhanvienban,
			Nhanvien nhanviengiaohang,Nhanvien nhanvienchamsoc);
	
	List<Hoadon> findByKhachhang(Khachhang khachhang);
}
