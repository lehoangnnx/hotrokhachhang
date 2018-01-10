package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Chitiethoadon;
import bcc.springhibernate.model.Hoadon;

@Repository
public interface ChiTietHoaDonRepository extends JpaRepository<Chitiethoadon, Integer> {
	List<Chitiethoadon> findAll();
	List<Chitiethoadon> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Chitiethoadon> findByTrangthaiNotOrderByIdDesc(String trangthai);
	List<Chitiethoadon> findByHoadon(Hoadon hoadon);
	List<Chitiethoadon> findByHoadonAndTrangthaiOrderByIdDesc(Hoadon hoadon,String trangthai);
	Chitiethoadon findById(Integer id);
	
}
