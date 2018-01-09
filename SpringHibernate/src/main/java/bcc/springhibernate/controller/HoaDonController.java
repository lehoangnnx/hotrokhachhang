package bcc.springhibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bcc.springhibernate.model.Hanghoa;
import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.service.HangHoaService;
import bcc.springhibernate.service.HoaDonService;

@Controller
@RequestMapping("/admin")
public class HoaDonController {

	@Autowired
	HoaDonService hoaDonService;
	@Autowired
	HangHoaService hangHoaService;
	@GetMapping("/hoadon")
	String pageDanhSachHoaDon(Model model) {
		List<Hoadon> listHoadon = hoaDonService.findByTrangthaiNotOrderByIdDesc("deleted");
		model.addAttribute("listHoadon", listHoadon);
		return "danhsachhoadon";
	}
	
	@GetMapping("/hoadon/add")
	String pageThemHoaDon(Model model) {
		
		List<Hanghoa> listHanghoa = hangHoaService.findByTrangthaiOrderByIdDesc("active");
		model.addAttribute("listHanghoa", listHanghoa);
		model.addAttribute("hoadon", new Hoadon());
		return "themhoadon";
	}
}
