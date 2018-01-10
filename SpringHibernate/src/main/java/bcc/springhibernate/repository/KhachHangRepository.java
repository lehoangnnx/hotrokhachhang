package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Khachhang;

@Repository
public interface KhachHangRepository extends JpaRepository<Khachhang, Integer> {
	Khachhang findById(Integer id);
	Khachhang findByMakh(String makh);
	
	List<Khachhang> findAll();
	List<Khachhang> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Khachhang> findByTrangthaiNotOrderByIdDesc(String trangthai);
	
}
