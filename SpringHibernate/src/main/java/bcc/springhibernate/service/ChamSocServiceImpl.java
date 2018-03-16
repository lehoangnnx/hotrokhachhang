package bcc.springhibernate.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Chamsoc;
import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.repository.ChamSocRepository;
import bcc.springhibernate.repository.HoaDonRepository;

@Service
public class ChamSocServiceImpl implements ChamSocService {

	@Autowired
	ChamSocRepository chamSocRepository;

	@Override
	public void saveOrUpdate(Chamsoc chamsoc) {
		chamSocRepository.save(chamsoc);
		
	}

	@Override
	public List<Chamsoc> findAll() {
		
		return chamSocRepository.findAll();
	}

	@Override
	public List<Chamsoc> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return chamSocRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public List<Chamsoc> findByTrangthaiNotOrderByIdDesc(String trangthai) {
		
		return chamSocRepository.findByTrangthaiNotOrderByIdDesc(trangthai);
	}

	@Override
	public Chamsoc findById(Integer id) {
		
		return chamSocRepository.findById(id);
	}

	@Override
	public List<Chamsoc> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable) {
		
		return chamSocRepository.findByTrangthaiOrderByIdDesc(trangthai, pageable);
	}

	@Override
	public List<Chamsoc> findByNhanvienchamsocAndTrangthaiNotAndNgayBetweenOrderByIdDesc(Integer nhanvien,
			String trangthai, Date d1, Date d2) {
		
		return chamSocRepository.findByNhanvienchamsocAndTrangthaiNotAndNgayBetweenOrderByIdDesc(nhanvien, trangthai, d1, d2);
	}

	@Override
	public void deleted(Chamsoc chamsoc) {
		chamSocRepository.delete(chamsoc);
		
	}

	@Override
	public List<Chamsoc> findByTrangthaiNotAndNhanvienbanhangOrderByIdDesc(String trangthai, Integer nhanvienbanhang) {
		return chamSocRepository.findByTrangthaiNotAndNhanvienbanhangOrderByIdDesc(trangthai,nhanvienbanhang);
	}
}
