package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Tieuchichamsoc;

@Repository
public interface TieuChiChamSocRepository extends JpaRepository<Tieuchichamsoc, Integer> {
	List<Tieuchichamsoc> findAll();
	List<Tieuchichamsoc> findByTrangthaiOrderByIdDesc(String trangthai);
	Tieuchichamsoc findByTentieuchi(String tentieuchi);
	Tieuchichamsoc findById(Integer id);
}
