package bcc.springhibernate.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bcc.springhibernate.model.Chamsoc;
import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.service.ChamSocService;
import bcc.springhibernate.service.HoaDonService;
import bcc.springhibernate.service.KhachHangService;
import bcc.springhibernate.service.NhanVienService;
import bcc.springhibernate.service.TaikhoanService;

@Controller
public class MainController {
	@Autowired
	TaikhoanService taikhoanService;
	@Autowired
	NhanVienService NhanVienService;
	@Autowired
	KhachHangService khachHangService;

	@Autowired
	ChamSocService chamSocService;
@Autowired
HoaDonService hoaDonService;
	@RequestMapping("/")
	public String index(Model model) {

		return "login";
	}

	@RequestMapping("/admin")
	public String home(
			Model model) {
		List<Khachhang> khachhangs = khachHangService.findByTrangthaiNotOrderByIdDesc("deleted");

		List<Chamsoc> chamsocs = chamSocService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Map<String, Object>> listChamSoc = new ArrayList<Map<String, Object>>();

		List<Map<String, Object>> listKhachHang = new ArrayList<Map<String, Object>>();
		for (Khachhang kh : khachhangs) {
			Date date = new Date();
			int daydd = kh.getNgaysinhnhatndd().getDate();
			int monthdd = kh.getNgaysinhnhatndd().getMonth();
			int daypt = kh.getNgaysinhphutrach().getDate();
			int monthpt = kh.getNgaysinhphutrach().getMonth();
			int dayn = date.getDate();
			int monthn = date.getMonth();

			int dayconlaidd = daydd - dayn;
			int dayconlaipt = daypt - dayn;
			if (monthdd == monthn && (dayconlaidd <= 14) && (dayconlaidd > 7)
					&& kh.getTrangthainhac().equals("dasinhnhat")) {
				kh.setTrangthainhac("chosinhnhat");
				khachHangService.saveOrUpdate(kh);
			} else if (monthdd == monthn && (dayconlaidd <= 7) && (dayconlaidd >= 0)
					&& kh.getTrangthainhac().equals("chosinhnhat")) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ngaysinhnhat", dayconlaidd);
				map.put("id", kh.getId());
				map.put("makh", kh.getMakh());
				listKhachHang.add(map);
			}
			if (monthpt == monthn && (dayconlaipt <= 14) && (dayconlaipt > 7)
					&& kh.getTrangthainhac().equals("dasinhnhat")) {
				kh.setTrangthainhac("chosinhnhat");
				khachHangService.saveOrUpdate(kh);
			} else if (monthpt == monthn && (dayconlaipt <= 7) && (dayconlaipt >= 0)
					&& kh.getTrangthainhac().equals("chosinhnhat")) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ngaysinhnhat", dayconlaipt);
				map.put("id", kh.getId());
				map.put("makh", kh.getMakh());
				map.put("ten", kh.getTen());
				listKhachHang.add(map);
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
			if (year == yearn && month == monthn && (dayconlai <= 14) && (dayconlai > 7)
					&& cs.getTrangthai().equals("dachamsoc")) {
				cs.setTrangthai("chochamsoc");
				chamSocService.saveOrUpdate(cs);
			} else if (year == yearn && month == monthn && (dayconlai <= 7) && (dayconlai >= 0)
					&& cs.getTrangthai().equals("chochamsoc")) {

				Map<String, Object> map = new HashMap<String, Object>();

				map.put("id", cs.getId());
				map.put("ngaycstiep", dayconlai);
				map.put("khachhang", cs.getKhachhang().getTen());

				listChamSoc.add(map);
			}
		}
		listChamSoc.sort(Comparator.comparing(s -> (int) s.get("ngaycstiep")));
		listKhachHang.sort(Comparator.comparing(s -> (int) s.get("ngaysinhnhat")));

		model.addAttribute("listChamSoc", listChamSoc);
		model.addAttribute("listKhachHang", listKhachHang);
		
		return "index";
	}

	
	
	
	@GetMapping(value = { "login" })
	public String pageLogin(Model model) {

		return "login";
	}

	@GetMapping(value = { "403" })
	public String page403(Model model) {

		return "403";
	}
}
