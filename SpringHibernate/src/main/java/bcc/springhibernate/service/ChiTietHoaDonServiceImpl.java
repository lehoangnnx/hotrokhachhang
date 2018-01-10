package bcc.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Chitiethoadon;
import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.repository.ChiTietHoaDonRepository;

@Service
public class ChiTietHoaDonServiceImpl implements ChiTietHoaDonService {
	@Autowired
	ChiTietHoaDonRepository chiTietHoaDonRepository;
	@Override
	public void saveOrUpdate(Chitiethoadon chitiethoadon) {
		chiTietHoaDonRepository.save(chitiethoadon);
		
	}

	@Override
	public List<Chitiethoadon> findAll() {
		
		return chiTietHoaDonRepository.findAll();
	}

	@Override
	public List<Chitiethoadon> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return chiTietHoaDonRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public List<Chitiethoadon> findByTrangthaiNotOrderByIdDesc(String trangthai) {
		
		return chiTietHoaDonRepository.findByTrangthaiNotOrderByIdDesc(trangthai);
	}

	@Override
	public List<Chitiethoadon> findByHoadon(Hoadon hoadon) {
		
		return chiTietHoaDonRepository.findByHoadon(hoadon);
	}

	@Override
	public Chitiethoadon findById(Integer id) {
		
		return chiTietHoaDonRepository.findById(id);
	}

	@Override
	public List<Chitiethoadon> findByHoadonAndTrangthaiOrderByIdDesc(Hoadon hoadon, String trangthai) {
		
		return chiTietHoaDonRepository.findByHoadonAndTrangthaiOrderByIdDesc(hoadon, trangthai);
	}

	@Override
	public void delete(Chitiethoadon chitiethoadon) {
		chiTietHoaDonRepository.delete(chitiethoadon);
		
	}

	
}
