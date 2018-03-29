package bcc.springhibernate.service;

import bcc.springhibernate.model.Hanghoa;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HangHoaService {
	void saveOrUpdate(Hanghoa hanghoa);
	void deleted(Hanghoa hanghoa);
	List<Hanghoa> findAll();
	List<Hanghoa> findByTrangthaiOrderByIdDesc(String trangthai);
	List<Hanghoa> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable);
	Hanghoa findByMahang(String mahang);
	Hanghoa findById(Integer id);
}
