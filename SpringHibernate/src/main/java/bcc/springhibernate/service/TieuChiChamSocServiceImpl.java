package bcc.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Tieuchichamsoc;
import bcc.springhibernate.repository.TieuChiChamSocRepository;

@Service
public class TieuChiChamSocServiceImpl implements TieuChiChamSocService {

	
	@Autowired
	TieuChiChamSocRepository tieuChiChamSocRepository;
	@Override
	public List<Tieuchichamsoc> findAll() {
		
		return tieuChiChamSocRepository.findAll();
	}

	@Override
	public List<Tieuchichamsoc> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return tieuChiChamSocRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public Tieuchichamsoc findByTentieuchi(String tentieuchi) {
		
		return tieuChiChamSocRepository.findByTentieuchi(tentieuchi);
	}

	@Override
	public Tieuchichamsoc findById(Integer id) {
		
		return tieuChiChamSocRepository.findById(id);
	}

	@Override
	public void saveOrUpdate(Tieuchichamsoc tieuchichamsoc) {
		tieuChiChamSocRepository.save(tieuchichamsoc);
		
	}

}
