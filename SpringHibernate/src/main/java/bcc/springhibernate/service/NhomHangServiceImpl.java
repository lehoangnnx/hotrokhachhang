package bcc.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.repository.NhomHangRepository;
@Service
public class NhomHangServiceImpl implements NhomHangService {

	@Autowired
	NhomHangRepository nhomHangRepository;

	@Override
	public void saveOrUpdate(Nhomhang nhomhang) {
		nhomHangRepository.save(nhomhang);
	}

	@Override
	public List<Nhomhang> findAll() {
		
		return nhomHangRepository.findAll();
	}

	@Override
	public List<Nhomhang> findByTrangthaiOrderByIdDesc(String trangthai) {
	
		return nhomHangRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public Nhomhang findByManhom(String manhom) {
		
		return nhomHangRepository.findByManhom(manhom);
	}

	@Override
	public Nhomhang findById(Integer id) {
		
		return nhomHangRepository.findById(id);
	}

	@Override
	public void deleted(Nhomhang nhomhang) {
		nhomHangRepository.delete(nhomhang);
		
	}

	@Override
	public List<Nhomhang> findByManhomcha(String manhomcha) {
		
		return nhomHangRepository.findByManhomcha(manhomcha);
	}

}
