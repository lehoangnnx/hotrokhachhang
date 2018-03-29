package bcc.springhibernate.service;

import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HoaDonServiceImpl implements HoaDonService {

	@Autowired
	HoaDonRepository hoaDonRepository;

	@Override
	public List<Hoadon> findAll() {

		return hoaDonRepository.findAll();
	}

	@Override
	public List<Hoadon> findByTrangthaiOrderByIdDesc(String trangthai) {

		return hoaDonRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public List<Hoadon> findByTrangthaiNotOrderByIdDesc(String trangthai) {

		return hoaDonRepository.findByTrangthaiNotOrderByIdDesc(trangthai);
	}

	@Override
	public Hoadon findBySohoadon(String sohoadon) {

		return hoaDonRepository.findBySohoadon(sohoadon);
	}

	@Override
	public void saveOrUpdate(Hoadon hoadon) {
		hoaDonRepository.save(hoadon);

	}

	@Override
	public Hoadon findById(Integer id) {

		return hoaDonRepository.findById(id);
	}

	@Override
	public List<Hoadon> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable) {

		return hoaDonRepository.findByTrangthaiOrderByIdDesc(trangthai, pageable);
	}

	@Override
	public List<Hoadon> findByTrangthaiAndNgaylapBetweenOrderByIdDesc(String trangthai, Date d1, Date d2) {

		return hoaDonRepository.findByTrangthaiAndNgaylapBetweenOrderByIdDesc(trangthai, d1, d2);
	}

	@Override
	public List<Hoadon> findByTrangthaiNotAndNgaylapBetweenOrderByIdDesc(String trangthai, Date d1, Date d2) {

		return hoaDonRepository.findByTrangthaiNotAndNgaylapBetweenOrderByIdDesc(trangthai, d1, d2);
	}

	@Override
	public List<Hoadon> findByTrangthaiAndNhanvienByIdnhanvienbanAndNgaylapBetweenOrderByIdDesc(String trangthai,
			Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2) {

		return hoaDonRepository.findByTrangthaiAndNhanvienByIdnhanvienbanAndNgaylapBetweenOrderByIdDesc(trangthai,
				nhanvienByIdnhanvienban, d1, d2);
	}

	@Override
	public List<Hoadon> findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaylapBetweenOrderByIdDesc(String trangthai,
			Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2) {

		return hoaDonRepository.findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaylapBetweenOrderByIdDesc(trangthai,
				nhanvienByIdnhanvienban, d1, d2);
	}

	@Override
	public List<Hoadon> findByTrangthaiDaThanhToan(String trangthai) {
		
		return hoaDonRepository.findByTrangthaiDaThanhToan(trangthai);
	}

	@Override
	public List<Hoadon> findByTrangthaiChuaThanhToan(String trangthai) {
		
		return hoaDonRepository.findByTrangthaiChuaThanhToan(trangthai);
	}

	@Override
	public List<Hoadon> findByTrangthaiDaThanhToan(String trangthai, Pageable pageable) {
		
		return hoaDonRepository.findByTrangthaiDaThanhToan(trangthai, pageable);
	}

	@Override
	public List<Hoadon> findByTrangthaiChuaThanhToan(String trangthai, Pageable pageable) {
		
		return hoaDonRepository.findByTrangthaiChuaThanhToan(trangthai, pageable);
	}

	@Override
	public List<Hoadon> findByNhanvienByIdnhanvienbanAndTrangthaiNotAndNgaylapBetweenOrderByIdDesc(Nhanvien nhanvien,
			String trangthai, Date d1, Date d2) {
		
		return hoaDonRepository.findByNhanvienByIdnhanvienbanAndTrangthaiNotAndNgaylapBetweenOrderByIdDesc(nhanvien, trangthai, d1, d2);
	}

	@Override
	public void deleted(Hoadon hoadon) {
		hoaDonRepository.delete(hoadon);
		
	}

	@Override
	public List<Hoadon> findByNhanvienByIdnhanvienlaphoadonOrNhanvienByIdnhanvienbanOrNhanvienByIdnhanviengiaohangOrNhanvienByIdnhanvienchamsoc(
			Nhanvien nhanvienlaphoadon, Nhanvien nhanvienban, Nhanvien nhanviengiaohang, Nhanvien nhanvienchamsoc) {
		
		return hoaDonRepository.findByNhanvienByIdnhanvienlaphoadonOrNhanvienByIdnhanvienbanOrNhanvienByIdnhanviengiaohangOrNhanvienByIdnhanvienchamsoc
				(nhanvienlaphoadon, nhanvienban, nhanviengiaohang, nhanvienchamsoc);
	}

	@Override
	public List<Hoadon> findByKhachhang(Khachhang khachhang) {
		
		return hoaDonRepository.findByKhachhang(khachhang);
	}

	@Override
	public List<Hoadon> findByTrangthaiAndNhanvienByIdnhanvienbanOrderByIdDesc(String trangthai, Nhanvien nhanvienByIdnhanvienban, Pageable pageable) {
		return hoaDonRepository.findByTrangthaiAndNhanvienByIdnhanvienbanOrderByIdDesc(trangthai,nhanvienByIdnhanvienban,pageable);
	}

	@Override
	public List<Hoadon> findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienban(String trangthai, Nhanvien nhanvienByIdnhanvienban, Pageable pageable) {
		return hoaDonRepository.findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienban(trangthai,nhanvienByIdnhanvienban,pageable);
	}

	@Override
	public List<Hoadon> findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienban(String trangthai, Nhanvien nhanvienByIdnhanvienban, Pageable pageable) {
		return hoaDonRepository.findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienban(trangthai,nhanvienByIdnhanvienban,pageable);
	}

	@Override
	public List<Hoadon> findByTrangthaiAndNhanvienByIdnhanvienbanOrderByIdDesc(String trangthai, Nhanvien nhanvienByIdnhanvienban) {
		return hoaDonRepository.findByTrangthaiAndNhanvienByIdnhanvienbanOrderByIdDesc(trangthai,nhanvienByIdnhanvienban);
	}

	@Override
	public List<Hoadon> findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienban(String trangthai, Nhanvien nhanvienByIdnhanvienban) {
		return hoaDonRepository.findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienban(trangthai,nhanvienByIdnhanvienban);
	}

	@Override
	public List<Hoadon> findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienban(String trangthai, Nhanvien nhanvienByIdnhanvienban) {
		return hoaDonRepository.findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienban(trangthai,nhanvienByIdnhanvienban);
	}

	@Override
	public List<Hoadon> findByTrangthaiNotAndNhanvienByIdnhanvienbanOrderByIdDesc(String trangthai, Nhanvien nhanvienByIdnhanvienban) {
		return hoaDonRepository.findByTrangthaiNotAndNhanvienByIdnhanvienbanOrderByIdDesc(trangthai,nhanvienByIdnhanvienban);
	}

	@Override
	public List<Khachhang> findDistinctKhachhangByNhanvienByIdnhanvienbanAndTrangthaiNot(Nhanvien nhanvienByIdnhanvienban, String trangthai) {
		return hoaDonRepository.findDistinctKhachhangByNhanvienByIdnhanvienbanAndTrangthaiNot(nhanvienByIdnhanvienban,trangthai);
	}

	@Override
	public List<Hoadon> findKhachhangDistinctByNhanvienByIdnhanvienbanAndTrangthaiNot(Nhanvien nhanvienByIdnhanvienban, String trangthai) {
		return hoaDonRepository.findKhachhangDistinctByNhanvienByIdnhanvienbanAndTrangthaiNot(nhanvienByIdnhanvienban,trangthai);
	}

	@Override
	public List<Hoadon> findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc(String trangthai, Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2) {
		return hoaDonRepository.findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc(trangthai,nhanvienByIdnhanvienban,d1,d2);
	}

	@Override
	public List<Hoadon> findByTrangthaiAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc
			(String trangthai, Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2) {
		return hoaDonRepository.findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc
				(trangthai, nhanvienByIdnhanvienban,d1,d2);
	}

	@Override
	public List<Hoadon> findByTrangthaiAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc
			(String trangthai, Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2, Pageable pageable) {
		return hoaDonRepository.findByTrangthaiAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc
				(trangthai,nhanvienByIdnhanvienban,d1,d2,pageable);
	}

	@Override
	public List<Hoadon> findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween
			(String trangthai, Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2) {
		return hoaDonRepository.findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween
				(trangthai,nhanvienByIdnhanvienban,d1,d2);
	}

	@Override
	public List<Hoadon> findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween
			(String trangthai, Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2, Pageable pageable) {
		return hoaDonRepository.findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween
				(trangthai,nhanvienByIdnhanvienban,d1,d2,pageable);
	}

	@Override
	public List<Hoadon> findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween(String trangthai, Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2) {
		return hoaDonRepository.findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween
				(trangthai,nhanvienByIdnhanvienban,d1,d2);
	}

	@Override
	public List<Hoadon> findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween(String trangthai, Nhanvien nhanvienByIdnhanvienban, Date d1, Date d2, Pageable pageable) {
		return hoaDonRepository.findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween
				(trangthai,nhanvienByIdnhanvienban,d1,d2,pageable);
	}

	@Override
	public List<Hoadon> findByTrangthaiNotAndNgaythanhtoanBetweenOrderByIdDesc(String trangthai, Date d1, Date d2) {
		return hoaDonRepository.findByTrangthaiNotAndNgaythanhtoanBetweenOrderByIdDesc(trangthai,d1,d2);
	}
}
