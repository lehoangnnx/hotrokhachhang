package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Loaikhachhang;

@Repository
public interface LoaiKhachHangRepository extends JpaRepository<Loaikhachhang, Integer> {

	List<Loaikhachhang> findAll();
	List<Loaikhachhang> findLoaikhachhangByTrangthaiOrderByIdDesc(String trangthai);
	Loaikhachhang findLoaikhachhangByTenloai(String tenloai);
	Loaikhachhang findLoaikhachhangById(Integer id);
}
