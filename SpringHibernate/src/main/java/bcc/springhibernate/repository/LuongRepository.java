package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Luong;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhomkhachhang;
@Repository
public interface LuongRepository extends JpaRepository<Luong, Integer> {
	List<Luong> findAll();
	List<Luong> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Luong> findByTrangthaiNotOrderByIdDesc(String trangthai);
	
	List<Luong> findByNhanvien(Nhanvien nhanvien);
	Luong findById(Integer id);
	Luong findOneByNhanvienAndThangAndNam(Nhanvien nhanvien, String thang,String nam);
}
