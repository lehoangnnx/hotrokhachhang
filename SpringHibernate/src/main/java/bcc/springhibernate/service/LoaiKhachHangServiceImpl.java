package bcc.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.repository.LoaiKhachHangRepository;
@Service
public class LoaiKhachHangServiceImpl implements LoaiKhachHangService {
	@Autowired
	LoaiKhachHangRepository loaiKhachHangRepository;

	@Override
	public List<Loaikhachhang> findAll() {

		return loaiKhachHangRepository.findAll();
	}

	@Override
	public void saveOrUpdate(Loaikhachhang loaikhachhang) {
		loaiKhachHangRepository.save(loaikhachhang);

	}

	@Override
	public Loaikhachhang findLoaikhachhangByTenloai(String tenloai) {
		
		return loaiKhachHangRepository.findLoaikhachhangByTenloai(tenloai);
	}

	@Override
	public Loaikhachhang findLoaikhachhangById(Integer id) {
		
		return loaiKhachHangRepository.findLoaikhachhangById(id);
	}

	@Override
	public List<Loaikhachhang> findLoaikhachhangByTrangthaiOrderByIdDesc(String trangthai) {
		
		return loaiKhachHangRepository.findLoaikhachhangByTrangthaiOrderByIdDesc(trangthai);
	}

}
