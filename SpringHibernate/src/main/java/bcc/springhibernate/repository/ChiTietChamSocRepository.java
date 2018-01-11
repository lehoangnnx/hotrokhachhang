package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Chamsoc;
import bcc.springhibernate.model.Chitietchamsoc;
import bcc.springhibernate.model.Chitiethoadon;
import bcc.springhibernate.model.Hoadon;

@Repository
public interface ChiTietChamSocRepository extends JpaRepository<Chitietchamsoc, Integer> {
	List<Chitietchamsoc> findAll();
	List<Chitietchamsoc> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Chitietchamsoc> findByTrangthaiNotOrderByIdDesc(String trangthai);
	List<Chitietchamsoc> findByChamsoc(Chamsoc chamsoc);
	List<Chitietchamsoc> findByChamsocAndTrangthaiOrderByIdDesc(Chamsoc chamsoc,String trangthai);
	Chitietchamsoc findById(Integer id);
	
}
