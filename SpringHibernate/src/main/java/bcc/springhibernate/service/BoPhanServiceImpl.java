package bcc.springhibernate.service;

import bcc.springhibernate.model.Bophan;
import bcc.springhibernate.repository.BoPhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

	@Override
	public void deleted(Bophan bophan) {
		boPhanRepository.delete(bophan);
		
	}
	

}
