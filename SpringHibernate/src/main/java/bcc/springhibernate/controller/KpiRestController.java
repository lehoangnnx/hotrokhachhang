package bcc.springhibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.service.KpiService;
import bcc.springhibernate.service.LoaiKhachHangService;
import bcc.springhibernate.service.NhomKhachHangService;

@RestController
@RequestMapping("/admin")
public class KpiRestController {

	@Autowired
	KpiService kpiService;

	@PostMapping("/kiemtratenkpi")
	String kiemTraTenKpi(@RequestBody Kpi kpi) {

		Kpi kpiByTen = null;
		try {
			kpiByTen = kpiService.findByTen(kpi.getTen());
			if (kpi.getId() == null) {

				if (kpiByTen == null) {

					return "success";
				}
			} else {
				if (kpiByTen != null) {
					if (kpi.getId() == kpiByTen.getId()) {

						return "success";

					}
				} else {

					return "success";

				}
			}

		} catch (Exception e) {
			return "error";
		}
		return "error";
	}
}
