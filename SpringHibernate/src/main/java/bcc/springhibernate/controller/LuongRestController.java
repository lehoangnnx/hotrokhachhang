package bcc.springhibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Luong;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.service.LoaiKhachHangService;
import bcc.springhibernate.service.LuongService;
import bcc.springhibernate.service.NhanVienService;

@RestController
@RequestMapping("/admin")
public class LuongRestController {

	@Autowired
	LuongService luongService;
	@Autowired
	NhanVienService nhanVienService; 
	@PostMapping("/kiemtraluong")
	String kiemTraLuong( @RequestParam("thang") String thang,  @RequestParam("nam") String nam,
			@RequestParam(value="id",defaultValue="0") Integer id,@RequestParam("nhanvien") Integer nhanvien
			) {
		Luong luong = null;
		try {
			Nhanvien nhanVienById = nhanVienService.findById(nhanvien);
			 luong = luongService.findByNhanvienAndThangAndNam(nhanVienById, thang, nam);
			if(id == 0) {
				if(luong == null) {
					return "success";
				}
			}else {
				if(id == luong.getId()) {
					return "success";
				}
			}
		} catch (Exception e) {
			return "error";
		}
		return "error";
	}
}
