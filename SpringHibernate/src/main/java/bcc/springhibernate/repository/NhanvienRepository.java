package bcc.springhibernate.repository;

import bcc.springhibernate.model.Nhanvien;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanvienRepository extends JpaRepository<Nhanvien, Integer> {
	
    Nhanvien findById(Integer id);
    Nhanvien findByManhanvien(String manhanvien);
    List<Nhanvien> findAll();
   List<Nhanvien> findByTrangthaiOrderByIdDesc(String trangthai);
   List<Nhanvien> findByTrangthaiNotOrderByIdDesc(String trangthai);
}
