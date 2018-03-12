package bcc.springhibernate.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import bcc.springhibernate.model.Chamsoc;
import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Luong;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhanvienkpi;
import bcc.springhibernate.service.ChamSocService;
import bcc.springhibernate.service.HoaDonService;
import bcc.springhibernate.service.KhachHangService;
import bcc.springhibernate.service.LuongService;
import bcc.springhibernate.service.NhanVienKpiService;
import bcc.springhibernate.service.NhanVienService;
import bcc.springhibernate.service.TaikhoanService;

@Component
public class ThongBao {
	
	@Autowired
	NhanVienService nhanVienService;
	@Autowired
	KhachHangService khachHangService;

	@Autowired
	ChamSocService chamSocService;
	@Autowired
	HoaDonService hoaDonService;
	@Autowired
	NhanVienKpiService nhanVienKpiService;
	@Autowired
	LuongService luongService;

	public void thongbao(Model model,HttpServletRequest request) {
		List<Khachhang> khachhangs = khachHangService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Chamsoc> chamsocs = chamSocService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Nhanvien> nhanviens = nhanVienService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Hoadon> hoadons = hoaDonService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Nhanvienkpi> nhanvienkpis = nhanVienKpiService.findByTrangthaiOrderByIdDesc("inactive");
		
		request.getServletContext().setAttribute("khachhangs", khachhangs);
		request.getServletContext().setAttribute("chamsocs", chamsocs);
		request.getServletContext().setAttribute("nhanviens", nhanviens);
		request.getServletContext().setAttribute("hoadons", hoadons);
		List<Map<String, Object>> listChamSoc = new ArrayList<Map<String, Object>>();

		List<Map<String, Object>> listKhachHang = new ArrayList<Map<String, Object>>();

		List<Map<String, Object>> listNhanVienKpi = new ArrayList<Map<String, Object>>();
		for (Khachhang kh : khachhangs) {
			Date date = new Date();
			int dayn = date.getDate();
			int monthn = date.getMonth();
			if (kh.getNgaysinhnhatndd() != null) {
				int daydd = kh.getNgaysinhnhatndd().getDate();
				int monthdd = kh.getNgaysinhnhatndd().getMonth();
				int dayconlaidd = daydd - dayn;
				if (monthdd == monthn && (dayconlaidd <= 14) && (dayconlaidd > 7)
						&& kh.getTrangthainhac().equals("dasinhnhat")) {
					kh.setTrangthainhac("chosinhnhat");
					khachHangService.saveOrUpdate(kh);
					// && (dayconlaidd >= 0)
				} else if (monthdd == monthn && (dayconlaidd <= 7) && kh.getTrangthainhac().equals("chosinhnhat")) {

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("ngay", kh.getNgaysinhnhatndd());
					map.put("ngaysinhnhat", dayconlaidd);
					map.put("id", kh.getId());
					map.put("makh", kh.getMakh());
					map.put("ten", kh.getTen());
					listKhachHang.add(map);
				}
			}
			if (kh.getNgaysinhphutrach() != null) {
				int daypt = kh.getNgaysinhphutrach().getDate();
				int monthpt = kh.getNgaysinhphutrach().getMonth();

				int dayconlaipt = daypt - dayn;

				if (monthpt == monthn && (dayconlaipt <= 14) && (dayconlaipt > 7)
						&& kh.getTrangthainhac().equals("dasinhnhat")) {
					kh.setTrangthainhac("chosinhnhat");
					khachHangService.saveOrUpdate(kh);
					// && (dayconlaipt >= 0)
				} else if (monthpt == monthn && (dayconlaipt <= 7) && kh.getTrangthainhac().equals("chosinhnhat")) {

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("ngay", kh.getNgaysinhphutrach());
					map.put("ngaysinhnhat", dayconlaipt);
					map.put("id", kh.getId());
					map.put("makh", kh.getMakh());
					map.put("ten", kh.getTen());
					listKhachHang.add(map);
				}
			}
		}

		for (Chamsoc cs : chamsocs) {

			Date date = new Date();
			int day = cs.getNgaycstiep().getDate();
			int month = cs.getNgaycstiep().getMonth();
			int year = cs.getNgaycstiep().getYear();
			int dayn = date.getDate();
			int monthn = date.getMonth();
			int yearn = date.getYear();

			int dayconlai = day - dayn;
			//&& (dayconlai > 7)
			if (year == yearn && month == monthn && (dayconlai <= 14) 
					&& cs.getTrangthai().equals("dachamsoc") && cs.getTrangthainhac().equals("chuanhac")) {
				cs.setTrangthai("chochamsoc");
				chamSocService.saveOrUpdate(cs);
				//&& (dayconlai >= 0)
			} 
			if (year == yearn && month == monthn && (dayconlai <= 7)
					&& cs.getTrangthai().equals("chochamsoc")  && cs.getTrangthainhac().equals("chuanhac")) {

				Map<String, Object> map = new HashMap<String, Object>();

				map.put("id", cs.getId());
				map.put("ngay", cs.getNgaycstiep());
				map.put("ngaycstiep", dayconlai);
				map.put("khachhang", cs.getKhachhang().getTen());

				listChamSoc.add(map);
			}
		}

		for (Nhanvienkpi nvk : nhanvienkpis) {
			if(!nvk.getKpi().getTrangthai().equals("deleted")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", nvk.getId());
				map.put("ngaydangky", nvk.getNgaydangky());
				map.put("manhanvien", nvk.getNhanvien().getManhanvien());
				map.put("tennhanvien", nvk.getNhanvien().getTennhanvien());
				map.put("tenkpi", nvk.getKpi().getTen());
				listNhanVienKpi.add(map);
			}
		}

		listChamSoc.sort(Comparator.comparing(s -> (int) s.get("ngaycstiep")));
		listKhachHang.sort(Comparator.comparing(s -> (int) s.get("ngaysinhnhat")));
		listNhanVienKpi.sort(Comparator.comparing(s -> (Date) s.get("ngaydangky")));
		Collections.reverse(listNhanVienKpi);
		request.getServletContext().setAttribute("listChamSoc", listChamSoc);
		request.getServletContext().setAttribute("listKhachHang", listKhachHang);
		request.getServletContext().setAttribute("listNhanVienKpi", listNhanVienKpi);

		// Tao Luong
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String splitDate[] = df.format(date).split("/");

		// if(new Date(date.getYear(),date.getMonth(),date.getDate()).equals(new
		// Date(date.getYear(),date.getMonth(),01))){
		List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiNotOrderByIdDesc("deleted");
		listNhanvien.forEach(x -> {
			Luong luong = null;
			try {
				luong = luongService.findOneByNhanvienAndThangAndNam(x, splitDate[1], splitDate[2]);
				if (luong == null) {
					luong = new Luong(x, x.getLuong(), 0L, splitDate[1], splitDate[2], "chuatraluong", "");
					luongService.saveOrUpdate(luong);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		// }
	}
}
