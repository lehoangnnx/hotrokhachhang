package bcc.springhibernate.service;

import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.repository.TaikhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
	public Taikhoan findById(Integer id) {
		
		return taikhoanRepository.findOne(id);
	}

	@Override
	public Taikhoan findByUsername(String username) {
		
		return taikhoanRepository.findByUsername(username);
	}

	@Override
	public List<Taikhoan> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return taikhoanRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public Taikhoan findByEmail(String email) {
		
		return taikhoanRepository.findByEmail(email);
	}

	@Override
	public void deleted(Taikhoan tk) {
		taikhoanRepository.delete(tk);
		
	}

}
