package bcc.springhibernate.service;

import bcc.springhibernate.model.Tieuchichamsoc;
import bcc.springhibernate.repository.TieuChiChamSocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

	@Override
	public void deleted(Tieuchichamsoc tieuchichamsoc) {
		tieuChiChamSocRepository.delete(tieuchichamsoc);
		
	}

}
