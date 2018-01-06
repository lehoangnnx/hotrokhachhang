package bcc.springhibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcc.springhibernate.model.Hanghoa;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.service.HangHoaService;
import bcc.springhibernate.service.LoaiKhachHangService;
import bcc.springhibernate.service.NhomHangService;
import bcc.springhibernate.service.NhomKhachHangService;

@RestController
@RequestMapping("/admin")
public class HangHoaRestController {

	@Autowired
	HangHoaService hangHoaService;
	@PostMapping("/kiemtramahang")
	String kiemTraTenNhomKhachHang(@RequestBody Hanghoa hanghoa
			) {
		
		Hanghoa hangHoaByMaHang = null;
		try {
			hangHoaByMaHang =  hangHoaService.findByMahang(hanghoa.getMahang());
			if (hanghoa.getId() == null) {
				
				if(hangHoaByMaHang == null) {
					
					return "success";
				}
			}else {
				
				if(hanghoa.getId() == hangHoaByMaHang.getId()) {
					
					return "success";
					
				}
			}
			
		} catch (Exception e) {
			return "error";
		}
		return "error";
	}
}
