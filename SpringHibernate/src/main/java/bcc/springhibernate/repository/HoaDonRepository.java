package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Hoadon;

@Repository
public interface HoaDonRepository extends JpaRepository<Hoadon, Integer> {

	List<Hoadon> findAll();
	List<Hoadon> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Hoadon> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable);
	List<Hoadon> findByTrangthaiNotOrderByIdDesc(String trangthai);
	Hoadon findBySohoadon(String sohoadon);
	Hoadon findById(Integer id);
	
	
}
