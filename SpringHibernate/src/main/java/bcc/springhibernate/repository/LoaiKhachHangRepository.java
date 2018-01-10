package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Loaikhachhang;

@Repository
public interface LoaiKhachHangRepository extends JpaRepository<Loaikhachhang, Integer> {

	List<Loaikhachhang> findAll();
	List<Loaikhachhang> findByTrangthaiOrderByIdDesc(String trangthai);
	Loaikhachhang findByTenloai(String tenloai);
	Loaikhachhang findById(Integer id);
}
