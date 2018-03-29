package bcc.springhibernate.service;

import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.repository.NhomKhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

	@Override
	public void deleted(Nhomkhachhang nhomkhachhang) {
		nhomKhachHangRepository.delete(nhomkhachhang);
		
	}

	@Override
	public List<Nhomkhachhang> findByTrangthaiOrderByIdAsc(String trangthai) {
		return nhomKhachHangRepository.findByTrangthaiOrderByIdAsc(trangthai);
	}
}
