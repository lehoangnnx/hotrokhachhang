package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Nhomkhachhang;
@Repository
public interface KpiRepository extends JpaRepository<Kpi, Integer> {
	List<Kpi> findAll();
	List<Kpi> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Kpi> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable);
	Kpi findByTen(String ten);
	Kpi findById(Integer id);
}
