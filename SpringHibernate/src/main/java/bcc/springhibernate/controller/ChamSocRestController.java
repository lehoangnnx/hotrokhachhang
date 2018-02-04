package bcc.springhibernate.controller;

import bcc.springhibernate.model.*;
import bcc.springhibernate.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/admin")
public class ChamSocRestController {

	@Autowired
	ChamSocService chamSocService;
	@Autowired
	TaikhoanService taikhoanService;
	/*@PostMapping("/updatethongbaochamsoc")
	String updateThongBaoChamSoc(@RequestParam("id") Integer id, Principal principal) {
		
		
		try {
			System.out.println(id);
			Chamsoc chamsoc = chamSocService.findById(id);

			Taikhoan getTaiKhoanByUserName = taikhoanService.findByUsername(principal.getName());
			Nhanvien getNhanVienChamSocById = getTaiKhoanByUserName.getNhanvien();
			Chamsoc chamsocnew = new Chamsoc();
			chamsocnew.setNhanvienbanhang(chamsoc.getNhanvienbanhang());
			chamsocnew.setNhanviengiaohang(chamsoc.getNhanviengiaohang());
			chamsocnew.setNhanvienchamsoc(getNhanVienChamSocById.getId());
			chamsocnew.setKhachhang(chamsoc.getKhachhang());
			chamsocnew.setHoadonId(chamsoc.getHoadonId());
			chamsocnew.setNgay(new Date());

			Date dt = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(dt);
			c.add(Calendar.DATE, 7);
			dt = c.getTime();
			chamsocnew.setNgaycstiep(dt);
			chamsocnew.setLan(chamsoc.getLan() + 1);
			chamsocnew.setTrangthai("dachamsoc");


			chamsoc.setTrangthai("dachamsoc");
			chamSocService.saveOrUpdate(chamsoc);
			chamSocService.saveOrUpdate(chamsocnew);
			return ""+chamsocnew.getId()+"";
		} catch (Exception e) {
			return "error";
		}
		
	}*/
}
