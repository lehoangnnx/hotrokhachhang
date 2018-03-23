package bcc.springhibernate.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bcc.springhibernate.model.Luong;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.service.LuongService;
import bcc.springhibernate.service.NhanVienService;
import bcc.springhibernate.service.TaikhoanService;

@Controller
@RequestMapping("/admin")
public class ThongTinController {
	@Autowired
	TaikhoanService taikhoanService;
	@Autowired
	NhanVienService nhanVienService;
	@Autowired
	LuongService luongService;
	@GetMapping("/thongtin")
	String pageThongTin(@RequestParam(value="thang",defaultValue="null") String thang,
			@RequestParam(value="nam",defaultValue="null") String nam,
			Principal principal, Model model) {
		
		Taikhoan taikhoan = taikhoanService.findByUsername(principal.getName());
		Nhanvien nhanvien = taikhoan.getNhanvien();
		
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String splitDate[] = df.format(date).split("/|-");
		Luong luong = null;
		if(thang.equals("null") && nam.equals("null")) {
			luong = luongService.findOneByNhanvienAndThangAndNam(nhanvien, splitDate[1], splitDate[2]);
		}else {
			luong = luongService.findOneByNhanvienAndThangAndNam(nhanvien, thang, nam);
		}
		
		if(luong == null) {
			model.addAttribute("thang", thang);
			model.addAttribute("nam", nam);
		}else {
			model.addAttribute("thang", luong.getThang());
			model.addAttribute("nam", luong.getNam());
		}
		
		model.addAttribute("nhanvien", nhanvien);
		model.addAttribute("luong", luong);
		return "thongtin";
	}
}
