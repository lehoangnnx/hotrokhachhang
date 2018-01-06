package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.model.Nhomkhachhang;
@Repository
public interface NhomHangRepository extends JpaRepository<Nhomhang, Integer> {
	List<Nhomhang> findAll();
	List<Nhomhang> findByTrangthaiOrderByIdDesc(String trangthai);
	Nhomhang findByManhom(String manhom);
	Nhomhang findById(Integer id);
}
