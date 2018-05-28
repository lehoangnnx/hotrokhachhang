package bcc.springhibernate.service;

import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhomkhachhang;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KhachHangService {
	
	void saveOrUpdate(Khachhang khachhang);
	void deleted(Khachhang khachhang);
	Khachhang findById(Integer id);
	Khachhang findByMakh(String makh);
	
	List<Khachhang> findAll();
	List<Khachhang> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Khachhang> findByTrangthaiNotOrderByIdDesc(String trangthai);
	List<Khachhang> findByNhomkhachhangAndTrangthaiOrderByIdDesc(Nhomkhachhang nhomkhachhang ,String trangthai);
	List<Khachhang> findByLoaikhachhangAndTrangthaiOrderByIdDesc(Loaikhachhang loaikhachhang,String trangthai);
	
	List<Khachhang> findByLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc(Loaikhachhang loaikhachhang,
			Nhomkhachhang nhomkhachhang ,String trangthai);
	
	List<Khachhang> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable);
	List<Khachhang> findByNhomkhachhangAndTrangthaiOrderByIdDesc(Nhomkhachhang nhomkhachhang ,String trangthai, Pageable pageable);
	List<Khachhang> findByLoaikhachhangAndTrangthaiOrderByIdDesc(Loaikhachhang loaikhachhang,String trangthai, Pageable pageable);
	
	List<Khachhang> findByLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc(Loaikhachhang loaikhachhang,
			Nhomkhachhang nhomkhachhang ,String trangthai, Pageable pageable);


	List<Khachhang> findByNhanvienbanhangAndLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc
			(Nhanvien nhanvienByIdnhanvienban,Loaikhachhang loaikhachhang, Nhomkhachhang nhomkhachhang ,String trangthai);
	List<Khachhang> findByNhanvienbanhangAndLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc
			(Nhanvien nhanvienByIdnhanvienban,Loaikhachhang loaikhachhang, Nhomkhachhang nhomkhachhang ,String trangthai,Pageable pageable);

    List<Khachhang> findByNhanvienbanhangLoaikhachhangAndTrangthaiOrderByIdDesc
            (Nhanvien nhanvienByIdnhanvienban,Loaikhachhang loaikhachhang,String trangthai);
    List<Khachhang> findByNhanvienbanhangLoaikhachhangAndTrangthaiOrderByIdDesc
            (Nhanvien nhanvienByIdnhanvienban,Loaikhachhang loaikhachhang,String trangthai,Pageable pageable);

    List<Khachhang> findByNhanvienbanhangNhomkhachhangAndTrangthaiOrderByIdDesc
            (Nhanvien nhanvienByIdnhanvienban,Nhomkhachhang nhomkhachhang ,String trangthai);
    List<Khachhang> findByNhanvienbanhangNhomkhachhangAndTrangthaiOrderByIdDesc
            (Nhanvien nhanvienByIdnhanvienban,Nhomkhachhang nhomkhachhang ,String trangthai,Pageable pageable);

    List<Khachhang> findByNhanvienbanhangTrangthaiOrderByIdDesc(Nhanvien nhanvienByIdnhanvienban,String trangthai);
    List<Khachhang> findByNhanvienbanhangTrangthaiOrderByIdDesc(Nhanvien nhanvienByIdnhanvienban,String trangthai,Pageable pageable);
}
