package bcc.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.model.Nhanvienkpi;
import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.repository.KpiRepository;
import bcc.springhibernate.repository.NhanVienKpiRepository;
import bcc.springhibernate.repository.NhomKhachHangRepository;
@Service
public class NhanVienKpiServiceImpl implements NhanVienKpiService {
	@Autowired
	NhanVienKpiRepository nhanVienKpiRepository;

	@Override
	public void saveOrUpdate(Nhanvienkpi nhanvienkpi) {
		nhanVienKpiRepository.save(nhanvienkpi);
		
	}

	@Override
	public List<Nhanvienkpi> findAll() {
		
		return nhanVienKpiRepository.findAll();
	}

	@Override
	public List<Nhanvienkpi> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return nhanVienKpiRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public Nhanvienkpi findById(Integer id) {
		
		return nhanVienKpiRepository.findById(id);
	}

	
	

}
