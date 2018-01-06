package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Bophan;
import bcc.springhibernate.model.Loaikhachhang;

@Repository
public interface BoPhanRepository extends JpaRepository<Bophan, Integer> {

	List<Bophan> findAll();
	List<Bophan> findByTrangthaiOrderByIdDesc(String trangthai);
	Bophan findByTenbophan(String tenbophan);
	Bophan findById(Integer id);
}
