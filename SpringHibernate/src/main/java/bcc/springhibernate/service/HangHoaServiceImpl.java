package bcc.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Hanghoa;
import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.repository.HangHoaRepository;
import bcc.springhibernate.repository.NhomHangRepository;
@Service
public class HangHoaServiceImpl implements HangHoaService {
@Autowired
HangHoaRepository hangHoaRepository;

	@Override
	public List<Hanghoa> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable) {
		return hangHoaRepository.findByTrangthaiOrderByIdDesc(trangthai,pageable);
	}

	@Override
	public void saveOrUpdate(Hanghoa hanghoa) {
		hangHoaRepository.save(hanghoa);
		
	}

	@Override
	public List<Hanghoa> findAll() {
		
		return hangHoaRepository.findAll();
	}

	@Override
	public List<Hanghoa> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return hangHoaRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public Hanghoa findByMahang(String mahang) {
		
		return hangHoaRepository.findByMahang(mahang);
	}

	@Override
	public Hanghoa findById(Integer id) {
		
		return hangHoaRepository.findById(id);
	}

	@Override
	public void deleted(Hanghoa hanghoa) {
		hangHoaRepository.delete(hanghoa);
		
	}

	

}
