package bcc.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Bophan;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.repository.BoPhanRepository;
import bcc.springhibernate.repository.LoaiKhachHangRepository;
@Service
public class BoPhanServiceImpl implements BoPhanService {

	@Autowired
	BoPhanRepository boPhanRepository;
	
	@Override
	public void saveOrUpdate(Bophan bophan) {
		boPhanRepository.save(bophan);
		
	}

	@Override
	public List<Bophan> findAll() {
		
		return boPhanRepository.findAll();
	}

	@Override
	public List<Bophan> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return boPhanRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public Bophan findByTenbophan(String tenbophan) {
		
		return boPhanRepository.findByTenbophan(tenbophan);
	}

	@Override
	public Bophan findById(Integer id) {
		
		return boPhanRepository.findById(id);
	}

	@Override
	public List<Bophan> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable) {
		
		return boPhanRepository.findByTrangthaiOrderByIdDesc(trangthai, pageable);
	}
	

}
