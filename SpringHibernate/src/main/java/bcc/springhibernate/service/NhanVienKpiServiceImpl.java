package bcc.springhibernate.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.model.Nhanvien;
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

	@Override
	public List<Nhanvienkpi> findByTrangthaiNotOrderByIdDesc(String trangthai) {
		
		return nhanVienKpiRepository.findByTrangthaiNotOrderByIdDesc(trangthai);
	}

	@Override
	public List<Nhanvienkpi> findByKpiAndTrangthaiNotOrderByNgaydangkyDesc(Kpi kpi, String trangthai) {
		
		return nhanVienKpiRepository.findByKpiAndTrangthaiNotOrderByNgaydangkyDesc(kpi, trangthai);
	}

	@Override
	public List<Nhanvienkpi> findByKpiAndTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc(Kpi kpi,
			String trangthai, Date d1, Date d2) {
		
		return nhanVienKpiRepository.findByKpiAndTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc(kpi, trangthai, d1, d2);
	}

	@Override
	public List<Nhanvienkpi> findByNhanvienAndKpiAndTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc(
			Nhanvien nhanvien, Kpi kpi, String trangthai, Date d1, Date d2) {
		
		return nhanVienKpiRepository.findByNhanvienAndKpiAndTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc
				(nhanvien, kpi, trangthai, d1, d2);
	}

	
	

}
