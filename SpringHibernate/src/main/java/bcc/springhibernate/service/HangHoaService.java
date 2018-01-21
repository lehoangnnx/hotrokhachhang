package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Hanghoa;
import org.springframework.data.domain.Pageable;

public interface HangHoaService {
	void saveOrUpdate(Hanghoa hanghoa);
	List<Hanghoa> findAll();
	List<Hanghoa> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Hanghoa> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable);
	Hanghoa findByMahang(String mahang);
	Hanghoa findById(Integer id);
}
