package bcc.springhibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcc.springhibernate.model.Bophan;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.service.BoPhanService;
import bcc.springhibernate.service.LoaiKhachHangService;

@RestController
@RequestMapping("/admin")
public class BoPhanRestController {

	@Autowired
	BoPhanService boPhanService;

	@PostMapping("/kiemtratenbophan")
	String kiemTraTenBoPhan(@RequestBody Bophan bophan) {

		Bophan bophanByTenBoPhan = null;
		try {
			bophanByTenBoPhan = boPhanService.findByTenbophan(bophan.getTenbophan());
			if (bophan.getId() == null) {

				if (bophanByTenBoPhan == null) {

					return "success";
				}
			} else {
				if (bophanByTenBoPhan != null) {
					if (bophan.getId() == bophanByTenBoPhan.getId()) {

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
