package bcc.springhibernate.service;

import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.repository.KpiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KpiServiceImpl implements KpiService {
	@Autowired
	KpiRepository kpiRepository;

	@Override
	public void saveOrUpdate(Kpi kpi) {
		kpiRepository.save(kpi);
		
	}

	@Override
	public List<Kpi> findAll() {
		
		return kpiRepository.findAll();
	}

	@Override
	public List<Kpi> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return kpiRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public List<Kpi> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable) {
		return kpiRepository.findByTrangthaiOrderByIdDesc(trangthai,pageable);
	}

	@Override
	public Kpi findByTen(String ten) {
		
		return kpiRepository.findByTen(ten);
	}

	@Override
	public Kpi findById(Integer id) {
		
		return kpiRepository.findById(id);
	}

	@Override
	public List<Kpi> findByTrangthaiNotOrderByIdDesc(String trangthai) {
		
		return kpiRepository.findByTrangthaiNotOrderByIdDesc(trangthai);
	}

	@Override
	public void deleted(Kpi kpi) {
		kpiRepository.delete(kpi);
		
	}
	

}
