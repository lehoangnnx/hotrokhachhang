package bcc.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.repository.NhomKhachHangRepository;
@Service
public class NhomKhachHangServiceImpl implements NhomKhachHangService {
	@Autowired
	NhomKhachHangRepository nhomKhachHangRepository;
	@Override
	public List<Nhomkhachhang> findAll() {
		
		return nhomKhachHangRepository.findAll();
	}

	@Override
	public List<Nhomkhachhang> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return nhomKhachHangRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public Nhomkhachhang findByTennhom(String tennhom) {
		
		return nhomKhachHangRepository.findByTennhom(tennhom);
	}

	@Override
	public Nhomkhachhang findById(Integer id) {
		
		return nhomKhachHangRepository.findById(id);
	}

	@Override
	public void saveOrUpdate(Nhomkhachhang nhomkhachhang) {
		nhomKhachHangRepository.save(nhomkhachhang);
		
	}

}
