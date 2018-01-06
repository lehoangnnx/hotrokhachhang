package bcc.springhibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Taikhoan;

@Repository
public interface TaikhoanRepository extends JpaRepository<Taikhoan, Integer>{
	Taikhoan findById(int id);
	Taikhoan findByUsername(String username);
}
