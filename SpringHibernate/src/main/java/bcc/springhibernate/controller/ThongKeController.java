package bcc.springhibernate.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.service.HoaDonService;
import bcc.springhibernate.service.NhanVienService;

@Controller
@RequestMapping("/admin")
public class ThongKeController {

	@Autowired
	HoaDonService hoaDonService;
	@Autowired
	NhanVienService nhanVienService;

	/*
	 * @GetMapping("/thongke") String pageThongKE(@RequestParam(value="trangthai",
	 * defaultValue="dathanhtoan") String trangthai, Model model) { Date d2 = new
	 * Date();
	 * 
	 * Date d1 = new Date(d2.getYear(), d2.getMonth(), 01); List<Hoadon>
	 * listHoadonAll = hoaDonService.findByTrangthaiNotOrderByIdDesc("deleted");
	 * List<Hoadon> listHoadon =
	 * hoaDonService.findByTrangthaiNotAndNgaylapBetweenOrderByIdDesc("deleted", d1,
	 * d2); model.addAttribute("listHoadon", listHoadon);
	 * model.addAttribute("listHoadonAll", listHoadonAll); return "thongke"; }
	 */
	@GetMapping("/thongke")
	String thongKe(@RequestParam(value = "tungay", defaultValue = "null") String tungay,
			@RequestParam(value = "denngay", defaultValue = "null") String denngay,
			@RequestParam(value="nhanvienbanhang" , defaultValue="0") Integer nhanvienbanhang,
			Model model,
			RedirectAttributes redirectAttributes) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
				
				
				Date ddenngay = new Date();	
				Date dtungay = 	 new Date(ddenngay.getYear(), ddenngay.getMonth(), 01);
				if(!tungay.equals("null")) {
					dtungay = dateFormat.parse(tungay);
				}
				if(!denngay.equals("null")) {
					ddenngay = dateFormat.parse(denngay);
				}
				List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiNotOrderByIdDesc("deleted");
				List<Hoadon> listHoadonAll = hoaDonService.findByTrangthaiNotOrderByIdDesc("deleted");
				List<Hoadon>  listHoadon;
				if(nhanvienbanhang != 0) {
					Nhanvien nhanvien = nhanVienService.findById(nhanvienbanhang);
					listHoadon = hoaDonService
							.findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaylapBetweenOrderByIdDesc
							("deleted",nhanvien, dtungay, ddenngay);
				}else {
					listHoadon = hoaDonService
							.findByTrangthaiNotAndNgaylapBetweenOrderByIdDesc("deleted", dtungay, ddenngay);
				}
				
				
				model.addAttribute("listNhanvien", listNhanvien);
				model.addAttribute("listHoadon", listHoadon);
				model.addAttribute("listHoadonAll", listHoadonAll);
				model.addAttribute("tungay", dtungay);
				model.addAttribute("denngay", ddenngay);
			
			
			
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return "thongke";
	}

}
