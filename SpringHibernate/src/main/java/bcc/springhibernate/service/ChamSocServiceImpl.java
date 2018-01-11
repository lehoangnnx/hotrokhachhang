package bcc.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Chamsoc;
import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.repository.ChamSocRepository;
import bcc.springhibernate.repository.HoaDonRepository;

@Service
public class ChamSocServiceImpl implements ChamSocService {

	@Autowired
	ChamSocRepository chamSocRepository;

	@Override
	public void saveOrUpdate(Chamsoc chamsoc) {
		chamSocRepository.save(chamsoc);
		
	}

	@Override
	public List<Chamsoc> findAll() {
		
		return chamSocRepository.findAll();
	}

	@Override
	public List<Chamsoc> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return chamSocRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public List<Chamsoc> findByTrangthaiNotOrderByIdDesc(String trangthai) {
		
		return chamSocRepository.findByTrangthaiNotOrderByIdDesc(trangthai);
	}

	@Override
	public Chamsoc findById(Integer id) {
		
		return chamSocRepository.findById(id);
	}
	
	
}