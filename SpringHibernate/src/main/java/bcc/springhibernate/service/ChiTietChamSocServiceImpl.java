package bcc.springhibernate.service;

import bcc.springhibernate.model.Chamsoc;
import bcc.springhibernate.model.Chitietchamsoc;
import bcc.springhibernate.repository.ChiTietChamSocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietChamSocServiceImpl implements ChiTietChamSocService {
	@Autowired
	ChiTietChamSocRepository chiTietChamSocRepository;

	@Override
	public void saveOrUpdate(Chitietchamsoc chitietchamsoc) {
		chiTietChamSocRepository.save(chitietchamsoc);
		
	}

	@Override
	public List<Chitietchamsoc> findAll() {
		
		return chiTietChamSocRepository.findAll();
	}

	@Override
	public List<Chitietchamsoc> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return chiTietChamSocRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public List<Chitietchamsoc> findByTrangthaiNotOrderByIdDesc(String trangthai) {
		
		return chiTietChamSocRepository.findByTrangthaiNotOrderByIdDesc(trangthai);
	}

	@Override
	public List<Chitietchamsoc> findByChamsoc(Chamsoc chamsoc) {
		
		return chiTietChamSocRepository.findByChamsoc(chamsoc);
	}

	@Override
	public List<Chitietchamsoc> findByChamsocAndTrangthaiOrderByIdDesc(Chamsoc chamsoc, String trangthai) {
		
		return chiTietChamSocRepository.findByChamsocAndTrangthaiOrderByIdDesc(chamsoc, trangthai);
	}

	@Override
	public Chitietchamsoc findById(Integer id) {
		
		return chiTietChamSocRepository.findById(id);
	}

	@Override
	public void delete(Chitietchamsoc chitietchamsoc) {
		chiTietChamSocRepository.delete(chitietchamsoc);
	}
	

	
}
