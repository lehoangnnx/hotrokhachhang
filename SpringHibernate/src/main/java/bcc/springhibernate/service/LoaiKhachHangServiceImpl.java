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
	public Loaikhachhang findByTenloai(String tenloai) {
		
		return loaiKhachHangRepository.findByTenloai(tenloai);
	}

	@Override
	public Loaikhachhang findById(Integer id) {
		
		return loaiKhachHangRepository.findById(id);
	}

	@Override
	public List<Loaikhachhang> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return loaiKhachHangRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public void deleted(Loaikhachhang loaikhachhang) {
		loaiKhachHangRepository.delete(loaikhachhang);
		
	}

}
