package bcc.springhibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.service.LoaiKhachHangService;

@RestController
@RequestMapping("/admin")
public class LoaiKhachHangRestController {

	@Autowired
	LoaiKhachHangService loaiKhachHangService;
	@PostMapping("/kiemtratenloaikhachhang")
	String kiemTraTenLoaiKhachHang(@RequestBody Loaikhachhang loaikhachhang
			) {
		System.out.println(loaikhachhang.getId());
		Loaikhachhang loaikhachhangByTenLoai = null;
		try {
			loaikhachhangByTenLoai =  loaiKhachHangService.findLoaikhachhangByTenloai(loaikhachhang.getTenloai());
			if (loaikhachhang.getId() == null) {
				System.out.println(1);
				if(loaikhachhangByTenLoai == null) {
					System.out.println(1-1);
					return "success";
				}
			}else {
				System.out.println(2);
				if(loaikhachhang.getId() == loaikhachhangByTenLoai.getId()) {
					System.out.println(2-2);
					return "success";
					
				}
			}
			
		} catch (Exception e) {
			return "error";
		}
		return "error";
	}
}
