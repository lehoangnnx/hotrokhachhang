package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Chamsoc;
import bcc.springhibernate.model.Hoadon;

@Repository
public interface ChamSocRepository extends JpaRepository<Chamsoc, Integer> {
	List<Chamsoc> findAll();
	List<Chamsoc> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Chamsoc> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable);
	List<Chamsoc> findByTrangthaiNotOrderByIdDesc(String trangthai);
	
	Chamsoc findById(Integer id);
	
}
