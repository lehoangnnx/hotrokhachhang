package bcc.springhibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bcc.springhibernate.model.Bophan;
import bcc.springhibernate.model.Chamsoc;
import bcc.springhibernate.model.Chitiethoadon;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.service.BoPhanService;
import bcc.springhibernate.service.ChamSocService;
import bcc.springhibernate.service.ChiTietHoaDonService;
import bcc.springhibernate.service.LoaiKhachHangService;

@RestController
@RequestMapping("/admin")
public class ChamSocRestController {

	@Autowired
	ChamSocService chamSocService;
	@PostMapping("/updatethongbaochamsoc")
	String updateThongBaoChamSoc(@RequestParam("id") Integer id) {
		
		
		try {
			
			Chamsoc chamsoc = chamSocService.findById(id);
			
			chamsoc.setTrangthai("dachamsoc");
			chamSocService.saveOrUpdate(chamsoc);
			return "success";
		} catch (Exception e) {
			return "error";
		}
		
	}
}
