package bcc.springhibernate.service;

import bcc.springhibernate.model.Luong;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.repository.LuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LuongServiceImpl implements LuongService {
	@Autowired
	LuongRepository luongRepository;

	@Override
	public void saveOrUpdate(Luong luong) {
		luongRepository.save(luong);
		
	}

	@Override
	public List<Luong> findAll() {
		
		return luongRepository.findAll();
	}

	@Override
	public List<Luong> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return luongRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public List<Luong> findByNhanvien(Nhanvien nhanvien) {
		
		return luongRepository.findByNhanvien(nhanvien);
	}

	@Override
	public Luong findById(Integer id) {
		
		return luongRepository.findById(id);
	}

	@Override
	public List<Luong> findByTrangthaiNotOrderByIdDesc(String trangthai) {
		
		return luongRepository.findByTrangthaiNotOrderByIdDesc(trangthai);
	}

	@Override
	public Luong findOneByNhanvienAndThangAndNam(Nhanvien nhanvien, String thang, String nam) {
		
		return luongRepository.findOneByNhanvienAndThangAndNam(nhanvien, thang, nam);
	}

	@Override
	public void deleted(Luong luong) {
		luongRepository.delete(luong);
		
	}

	@Override
	public Luong findOneByTrangthaiNotAndNhanvienAndThangAndNam(String trangthai, Nhanvien nhanvien, String thang,
			String nam) {
		return luongRepository.findOneByTrangthaiNotAndNhanvienAndThangAndNam(trangthai, nhanvien, thang, nam);
	}

	@Override
	public List<Luong> findByTrangthaiAndThangAndNam(String trangthai, String thang, String nam) {
		return luongRepository.findByTrangthaiAndThangAndNam(trangthai,thang,nam);
	}
}
