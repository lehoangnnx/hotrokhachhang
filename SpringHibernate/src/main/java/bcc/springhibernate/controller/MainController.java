package bcc.springhibernate.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bcc.springhibernate.model.*;
import bcc.springhibernate.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
	@Autowired
	TaikhoanService taikhoanService;
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
	@ModelAttribute("taikhoan")
	// Lưu Session Thông tin người dùng đăng nhập
	public void sessionUser(Principal principal, HttpSession session) {
		if (principal != null) {
			System.out.println(principal.getName());
			session.setAttribute("taikhoan", taikhoanService.findByUsername(principal.getName()));

		}
	}

	@RequestMapping("/")
	public String index(Principal principal,Model model) {
		if (principal != null) {
			
			return "redirect:/admin";
		}
		return "redirect:/login";
	}

	@RequestMapping("/admin")
	public String home(Model model) {
		List<Khachhang> khachhangs = khachHangService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Chamsoc> chamsocs = chamSocService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Nhanvien> nhanviens = nhanVienService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Hoadon> hoadons = hoaDonService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Nhanvienkpi> nhanvienkpis = nhanVienKpiService.findByTrangthaiOrderByIdDesc("inactive");
		model.addAttribute("khachhangs", khachhangs);
		model.addAttribute("chamsocs", chamsocs);
		model.addAttribute("nhanviens", nhanviens);
		model.addAttribute("hoadons", hoadons);
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
				} else if (monthdd == monthn && (dayconlaidd <= 7) && (dayconlaidd >= 0)
						&& kh.getTrangthainhac().equals("chosinhnhat")) {

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("ngaysinhnhat", dayconlaidd);
					map.put("id", kh.getId());
					map.put("makh", kh.getMakh());
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

		for (Nhanvienkpi nvk : nhanvienkpis) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", nvk.getId());
			map.put("ngaydangky", nvk.getNgaydangky());
			map.put("manhanvien", nvk.getNhanvien().getManhanvien());
			map.put("tennhanvien", nvk.getNhanvien().getTennhanvien());
			map.put("tenkpi", nvk.getKpi().getTen());
			listNhanVienKpi.add(map);
		}

		listChamSoc.sort(Comparator.comparing(s -> (int) s.get("ngaycstiep")));
		listKhachHang.sort(Comparator.comparing(s -> (int) s.get("ngaysinhnhat")));
		listNhanVienKpi.sort(Comparator.comparing(s -> (Date) s.get("ngaydangky")));
		Collections.reverse(listNhanVienKpi);
		model.addAttribute("listChamSoc", listChamSoc);
		model.addAttribute("listKhachHang", listKhachHang);
		model.addAttribute("listNhanVienKpi", listNhanVienKpi);


		// Tao Luong
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String splitDate[] = df.format(date).split("/");

		//if(new Date(date.getYear(),date.getMonth(),date.getDate()).equals(new Date(date.getYear(),date.getMonth(),01))){
			List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiNotOrderByIdDesc("deleted");
			listNhanvien.forEach(x -> {
				Luong luong = null;
				try {
					luong = luongService.findOneByNhanvienAndThangAndNam(x,splitDate[1],splitDate[2]);
					if(luong == null){
						luong =  new Luong(x, x.getLuong(), 0L, splitDate[1], splitDate[2], "chuatraluong",
								"");
						luongService.saveOrUpdate(luong);
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			});
		//}
		return "index";
	}

	@GetMapping(value = { "login" })
	public String pageLogin(Model model) {

		return "login";
	}

	@GetMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@GetMapping(value = { "403" })
	public String page403(Model model) {

		return "403";
	}
}
