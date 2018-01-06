package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Taikhoan;

@Repository
public interface TaikhoanRepository extends JpaRepository<Taikhoan, Integer>{
	Taikhoan findById(int id);
	Taikhoan findByUsername(String username);
	
	    Nhanvien findByEmail(String email);
	List<Taikhoan> findByTrangthaiOrderByIdDesc(String trangthai);
}
