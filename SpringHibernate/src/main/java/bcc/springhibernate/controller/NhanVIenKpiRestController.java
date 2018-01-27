package bcc.springhibernate.controller;

import java.util.HashMap;
import java.util.Map;

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
import bcc.springhibernate.model.Nhanvienkpi;
import bcc.springhibernate.service.KhachHangService;
import bcc.springhibernate.service.LoaiKhachHangService;
import bcc.springhibernate.service.NhanVienKpiService;

@RestController
@RequestMapping("/admin")
public class NhanVIenKpiRestController {

	@Autowired
	NhanVienKpiService nhanVienKpiService;

	
	@PostMapping("/updatetrangthainhanvienkpi")
	String updatetrangthainhanvienkpi(@RequestParam("id") Integer id, @RequestParam("trangthai") String trangthai) {

		try {

			Nhanvienkpi nhanvienkpi = nhanVienKpiService.findById(id);
			nhanvienkpi.setTrangthai(trangthai);
			nhanVienKpiService.saveOrUpdate(nhanvienkpi);
			
			return trangthai;
		} catch (Exception e) {
			return "error";
		}

	}
}
