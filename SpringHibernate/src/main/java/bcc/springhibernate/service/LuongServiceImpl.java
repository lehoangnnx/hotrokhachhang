package bcc.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Luong;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.repository.LuongRepository;
import bcc.springhibernate.repository.NhomKhachHangRepository;
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
	public Luong findByNhanvien(Nhanvien nhanvien) {
		
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
	

}
