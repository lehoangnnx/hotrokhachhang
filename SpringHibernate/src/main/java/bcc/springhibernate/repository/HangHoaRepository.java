package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Hanghoa;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.model.Nhomkhachhang;
@Repository
public interface HangHoaRepository extends JpaRepository<Hanghoa, Integer> {
	List<Hanghoa> findAll();
	List<Hanghoa> findByTrangthaiOrderByIdDesc(String trangthai);
	Hanghoa findByMahang(String mahang);
	Hanghoa findById(Integer id);
}
