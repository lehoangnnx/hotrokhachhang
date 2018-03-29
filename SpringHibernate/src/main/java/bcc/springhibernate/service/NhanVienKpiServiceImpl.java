package bcc.springhibernate.service;

import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhanvienkpi;
import bcc.springhibernate.repository.NhanVienKpiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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

	@Override
	public void deleted(Nhanvienkpi nhanvienkpi) {
		nhanVienKpiRepository.delete(nhanvienkpi);
		
	}

	@Override
	public List<Nhanvienkpi> findByKpi(Kpi kpi) {
		
		return nhanVienKpiRepository.findByKpi(kpi);
	}

	@Override
	public List<Nhanvienkpi> findByNhanvien(Nhanvien nhanvien) {
		
		return nhanVienKpiRepository.findByNhanvien(nhanvien);
	}

	@Override
	public List<Nhanvienkpi> findByNhanvienAndTrangthaiOrderByIdDesc(Nhanvien nhanvien, String trangthai) {
		
		return nhanVienKpiRepository.findByNhanvienAndTrangthaiOrderByIdDesc(nhanvien, trangthai);
	}

	@Override
	public List<Nhanvienkpi> findByTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc(String trangthai, Date d1,
			Date d2) {
		
		return nhanVienKpiRepository.findByTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc(trangthai, d1, d2);
	}


	@Override
	public List<Nhanvienkpi> findByNhanvienAndTrangthaiAndNgaydangkyBetween(Nhanvien nhanvien, String trangthai, Date d1, Date d2) {
		return nhanVienKpiRepository.findByNhanvienAndTrangthaiAndNgaydangkyBetween(nhanvien,trangthai,d1,d2);
	}

	@Override
	public Nhanvienkpi findByTrangthaiAndNhanvienAndKpiAndMonthYearNgaydangky(String trangthai, Nhanvien nhanvien, Kpi kpi, Date d1, Date d2) {
		return nhanVienKpiRepository.findByTrangthaiAndNhanvienAndKpiAndMonthYearNgaydangky(trangthai,nhanvien,kpi,d1,d2);
	}
}
