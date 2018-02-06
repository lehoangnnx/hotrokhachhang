package bcc.springhibernate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.service.HoaDonService;
import bcc.springhibernate.service.KhachHangService;

@RestController
@RequestMapping("/admin")
public class HoaDonRestController {
@Autowired
KhachHangService khachHangService;
	@Autowired
	HoaDonService hoaDonService;
	
	@PostMapping("/gethoadontheokhachhang")
	List<Map<String, Object>>  gethoadontheokhachhang(@RequestBody Integer id) {
		
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		try {
			
			Khachhang khachhang = khachHangService.findById(id);
			List<Hoadon> hoadons = hoaDonService.findByKhachhang(khachhang);
			hoadons.forEach(x -> {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", x.getId());
				map.put("sohoadon", x.getSohoadon());
				listmap.add(map);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listmap;
	}
	
}
