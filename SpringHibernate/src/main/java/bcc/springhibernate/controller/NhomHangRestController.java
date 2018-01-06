package bcc.springhibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.service.LoaiKhachHangService;
import bcc.springhibernate.service.NhomHangService;
import bcc.springhibernate.service.NhomKhachHangService;

@RestController
@RequestMapping("/admin")
public class NhomHangRestController {

	@Autowired
	NhomHangService nhomHangService;
	@PostMapping("/kiemtramanhomhang")
	String kiemTraTenNhomKhachHang(@RequestBody Nhomhang nhomhang
			) {
		
		Nhomhang nhomhangByMaNhom = null;
		try {
			nhomhangByMaNhom =  nhomHangService.findByManhom(nhomhang.getManhom());
			if (nhomhang.getId() == null) {
				
				if(nhomhangByMaNhom == null) {
					
					return "success";
				}
			}else {
				
				if(nhomhang.getId() == nhomhangByMaNhom.getId()) {
					
					return "success";
					
				}
			}
			
		} catch (Exception e) {
			return "error";
		}
		return "error";
	}
}
