package bcc.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.repository.KhachHangRepository;
@Service
public class KhachHangServiceImpl implements KhachHangService {

	@Autowired
	KhachHangRepository khachHangRepository;
	
	@Override
	public Khachhang findById(Integer id) {
		
		return khachHangRepository.findById(id);
	}

	@Override
	public Khachhang findByMakh(String makh) {
		
		return khachHangRepository.findByMakh(makh);
	}

	@Override
	public List<Khachhang> findAll() {
		
		return khachHangRepository.findAll();
	}

	@Override
	public List<Khachhang> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return khachHangRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public List<Khachhang> findByTrangthaiNotOrderByIdDesc(String trangthai) {
		
		return khachHangRepository.findByTrangthaiNotOrderByIdDesc(trangthai);
	}

	@Override
	public void saveOrUpdate(Khachhang khachhang) {
		khachHangRepository.save(khachhang);
		
	}

}
