package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Nhomkhachhang;
@Repository
public interface NhomKhachHangRepository extends JpaRepository<Nhomkhachhang, Integer> {
	List<Nhomkhachhang> findAll();
	List<Nhomkhachhang> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Nhomkhachhang> findByTrangthaiOrderByIdAsc(String trangthai);
	Nhomkhachhang findByTennhom(String tennhom);
	Nhomkhachhang findById(Integer id);
}
