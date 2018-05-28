package bcc.springhibernate.repository;

import java.util.List;

import bcc.springhibernate.model.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<Khachhang, Integer> {
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

	@Query("SELECT distinct kh FROM Khachhang kh, Hoadon hd where kh.id = hd.khachhang and hd.nhanvienByIdnhanvienban = ?1 " +
			"and kh.loaikhachhang = ?2 and kh.nhomkhachhang = ?3 and kh.trangthai = ?4 ORDER BY kh.id DESC ")
	List<Khachhang> findByNhanvienbanhangAndLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc
			(Nhanvien nhanvienByIdnhanvienban,Loaikhachhang loaikhachhang, Nhomkhachhang nhomkhachhang ,String trangthai);
	@Query("SELECT distinct kh FROM Khachhang kh, Hoadon hd where kh.id = hd.khachhang and hd.nhanvienByIdnhanvienban = ?1 " +
			"and kh.loaikhachhang = ?2 and kh.nhomkhachhang = ?3 and kh.trangthai = ?4 ORDER BY kh.id DESC ")
	List<Khachhang> findByNhanvienbanhangAndLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc
			(Nhanvien nhanvienByIdnhanvienban,Loaikhachhang loaikhachhang, Nhomkhachhang nhomkhachhang ,String trangthai,Pageable pageable);

    @Query("SELECT distinct kh FROM Khachhang kh, Hoadon hd where kh.id = hd.khachhang and hd.nhanvienByIdnhanvienban = ?1 " +
            "and kh.loaikhachhang = ?2 and kh.trangthai = ?3 ORDER BY kh.id DESC ")
    List<Khachhang> findByNhanvienbanhangLoaikhachhangAndTrangthaiOrderByIdDesc
            (Nhanvien nhanvienByIdnhanvienban,Loaikhachhang loaikhachhang,String trangthai);
    @Query("SELECT distinct kh FROM Khachhang kh, Hoadon hd where kh.id = hd.khachhang and hd.nhanvienByIdnhanvienban = ?1 " +
            "and kh.loaikhachhang = ?2 and kh.trangthai = ?3 ORDER BY kh.id DESC ")
    List<Khachhang> findByNhanvienbanhangLoaikhachhangAndTrangthaiOrderByIdDesc
            (Nhanvien nhanvienByIdnhanvienban,Loaikhachhang loaikhachhang,String trangthai,Pageable pageable);

    @Query("SELECT distinct kh FROM Khachhang kh, Hoadon hd where kh.id = hd.khachhang and hd.nhanvienByIdnhanvienban = ?1 " +
            "and kh.nhomkhachhang = ?2 and kh.trangthai = ?3 ORDER BY kh.id DESC ")
    List<Khachhang> findByNhanvienbanhangNhomkhachhangAndTrangthaiOrderByIdDesc
            (Nhanvien nhanvienByIdnhanvienban,Nhomkhachhang nhomkhachhang ,String trangthai);
    @Query("SELECT distinct kh FROM Khachhang kh, Hoadon hd where kh.id = hd.khachhang and hd.nhanvienByIdnhanvienban = ?1 " +
            "and kh.nhomkhachhang = ?2 and kh.trangthai = ?3 ORDER BY kh.id DESC ")
    List<Khachhang> findByNhanvienbanhangNhomkhachhangAndTrangthaiOrderByIdDesc
            (Nhanvien nhanvienByIdnhanvienban,Nhomkhachhang nhomkhachhang ,String trangthai,Pageable pageable);

    @Query("SELECT distinct kh FROM Khachhang kh, Hoadon hd where kh.id = hd.khachhang and hd.nhanvienByIdnhanvienban = ?1 " +
            "and kh.trangthai = ?2 ORDER BY kh.id DESC ")
    List<Khachhang> findByNhanvienbanhangTrangthaiOrderByIdDesc(Nhanvien nhanvienByIdnhanvienban,String trangthai);
    @Query("SELECT distinct kh FROM Khachhang kh, Hoadon hd where kh.id = hd.khachhang and hd.nhanvienByIdnhanvienban = ?1 " +
            "and kh.trangthai = ?2 ORDER BY kh.id DESC ")
    List<Khachhang> findByNhanvienbanhangTrangthaiOrderByIdDesc(Nhanvien nhanvienByIdnhanvienban,String trangthai,Pageable pageable);
}
