package bcc.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.repository.TaikhoanRepository;

@Service
public class TaikhoanServiceImpl implements TaikhoanService {

	@Autowired
	TaikhoanRepository taikhoanRepository;
	 
	@Override
	public List<Taikhoan> findAll() {
		
		return taikhoanRepository.findAll();
	}

	@Override
	public void saveOrUpdate(Taikhoan tk) {
		taikhoanRepository.save(tk);
		
	}

	@Override
	public void deleteOne(Integer id) {
		taikhoanRepository.delete(id);
		
	}

	@Override
	public Taikhoan findById(Integer id) {
		
		return taikhoanRepository.findOne(id);
	}

	@Override
	public Taikhoan findByUsername(String username) {
		
		return taikhoanRepository.findByUsername(username);
	}

}
