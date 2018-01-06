package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import bcc.springhibernate.model.Quyen;

@Repository
public interface QuyenRepository extends JpaRepository<Quyen, Integer> {
	Quyen findById(Integer id);
	Quyen findByTenquyen(String tenquyen);
	List<Quyen> findAllByTrangthaiOrderByIdDesc(String trangthai);
	
}
