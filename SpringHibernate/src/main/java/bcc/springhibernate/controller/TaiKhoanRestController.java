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
import bcc.springhibernate.model.Quyen;
import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.service.LoaiKhachHangService;
import bcc.springhibernate.service.NhomHangService;
import bcc.springhibernate.service.NhomKhachHangService;
import bcc.springhibernate.service.QuyenService;
import bcc.springhibernate.service.TaikhoanService;

@RestController
@RequestMapping("/admin")
public class TaiKhoanRestController {

	@Autowired
	TaikhoanService taikhoanService;
	
	@PostMapping("/kiemtrausernamevaemailtaikhoan")
	String kiemTraUserNameVaEmailtaiKhoan(@RequestBody Taikhoan taikhoan
			) {
		
		Taikhoan taiKhoanByUserName= null;
		Taikhoan taiKhoanByEmail= null;
		try {
			taiKhoanByUserName =  taikhoanService.findByUsername(taikhoan.getUsername());
			taiKhoanByEmail =  taikhoanService.findByEmail(taikhoan.getEmail());
			System.out.println(taikhoan.getEmail());
			if (taikhoan.getId() == null) {
				System.out.println("101010010110");
				if(taiKhoanByUserName != null && taiKhoanByEmail != null) {
					System.out.println(1);
					return "error";
				}else if(taiKhoanByUserName != null) {
					System.out.println(2);
					return "errorusername";
					
				}else if(taiKhoanByEmail != null) {
					System.out.println(3);
					return "erroremail";
					
				}else {
					
					return "success";
				}
			}else {
				System.out.println("1111111111111111111111");
				if(taiKhoanByUserName != null && taiKhoanByEmail != null &&
						taikhoan.getId() != taiKhoanByUserName.getId() && 
						taikhoan.getId() != taiKhoanByEmail.getId()) {
					System.out.println("4");
					return "error";
					
				} else if( taiKhoanByUserName != null &&  taikhoan.getId() != taiKhoanByUserName.getId()) {
					System.out.println("5");
					return "errorusername";
					
				}else if( taiKhoanByEmail != null && taikhoan.getId() != taiKhoanByEmail.getId()) {
					System.out.println("6");
					return "erroremail";
					
				}else {
					System.out.println("7");
					return "success";
				}
			}
			
		} catch (Exception e) {
			return "error";
		}
		
	}
	@PostMapping("/kiemtraemailtaikhoan")
	String kiemTraEmailtaiKhoan(@RequestBody Taikhoan taikhoan
			) {
		
		Taikhoan taiKhoanByEmail= null;
		try {
			taiKhoanByEmail =  taikhoanService.findByEmail(taikhoan.getEmail());
			if (taikhoan.getId() == null) {
				
				if(taiKhoanByEmail == null) {
					
					return "success";
				}
			}else {
				
				if(taikhoan.getId() == taiKhoanByEmail.getId()) {
					
					return "success";
					
				}
			}
			
		} catch (Exception e) {
			return "error";
		}
		return "error";
	}
}
