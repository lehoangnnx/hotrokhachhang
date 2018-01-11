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
import bcc.springhibernate.model.Chitietchamsoc;
import bcc.springhibernate.model.Chitiethoadon;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.service.BoPhanService;
import bcc.springhibernate.service.ChiTietChamSocService;
import bcc.springhibernate.service.ChiTietHoaDonService;
import bcc.springhibernate.service.LoaiKhachHangService;

@RestController
@RequestMapping("/admin")
public class ChiTietChamSocRestController {

	@Autowired
	ChiTietChamSocService chiTietChamSocService;
	@PostMapping("/updatechitietchamsoc")
	String updateChiTietChamSoc(@RequestBody Chitietchamsoc chitietchamsoc) {
		
		
		try {
			
			Chitietchamsoc getchitietchamsoc = chiTietChamSocService.findById(chitietchamsoc.getId());
			chiTietChamSocService.delete(getchitietchamsoc);
			return "success";
		} catch (Exception e) {
			return "error";
		}
		
	}
}
