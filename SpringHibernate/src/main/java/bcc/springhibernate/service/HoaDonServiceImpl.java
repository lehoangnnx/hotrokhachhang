package bcc.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.repository.HoaDonRepository;

@Service
public class HoaDonServiceImpl implements HoaDonService {

	@Autowired
	HoaDonRepository hoaDonRepository;
	
	@Override
	public List<Hoadon> findAll() {
		
		return hoaDonRepository.findAll();
	}

	@Override
	public List<Hoadon> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return hoaDonRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public List<Hoadon> findByTrangthaiNotOrderByIdDesc(String trangthai) {
		
		return hoaDonRepository.findByTrangthaiNotOrderByIdDesc(trangthai);
	}

	@Override
	public Hoadon findBySohoadon(String sohoadon) {
		
		return hoaDonRepository.findBySohoadon(sohoadon);
	}

	@Override
	public void saveOrUpdate(Hoadon hoadon) {
		hoaDonRepository.save(hoadon);
		
	}

	@Override
	public Hoadon findById(Integer id) {
	
		return hoaDonRepository.findById(id);
	}

	@Override
	public List<Hoadon> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable) {
		
		return hoaDonRepository.findByTrangthaiOrderByIdDesc(trangthai,pageable);
	}

}
