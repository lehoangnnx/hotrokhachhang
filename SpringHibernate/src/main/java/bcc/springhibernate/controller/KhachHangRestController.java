package bcc.springhibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bcc.springhibernate.model.Chamsoc;
import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.service.KhachHangService;
import bcc.springhibernate.service.LoaiKhachHangService;

@RestController
@RequestMapping("/admin")
public class KhachHangRestController {

	@Autowired
	KhachHangService khachHangService;
	@PostMapping("/kiemtramakhachhang")
	String kiemTraMaKhachHang(@RequestBody Khachhang khachhang
			) {
		
		Khachhang khachHangByMaKh= null;
		try {
			khachHangByMaKh =  khachHangService.findByMakh(khachhang.getMakh());
			if (khachhang.getId() == null) {
				
				if(khachHangByMaKh == null) {
					
					return "success";
				}
			}else {
				
				if(khachhang.getId() == khachHangByMaKh.getId()) {
					
					return "success";
					
				}
			}
			
		} catch (Exception e) {
			return "error";
		}
		return "error";
	}
	@PostMapping("/updatethongbaokhachhang")
	String updateThongBaoKhachHang(@RequestParam("id") Integer id) {
		
		
		try {
			
			Khachhang khachhang= khachHangService.findById(id);
			
			khachhang.setTrangthainhac("dasinhnhat");
			khachHangService.saveOrUpdate(khachhang);
			return "success";
		} catch (Exception e) {
			return "error";
		}
		
	}
}
