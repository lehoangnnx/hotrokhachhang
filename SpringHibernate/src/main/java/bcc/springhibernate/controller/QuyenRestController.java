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
import bcc.springhibernate.service.LoaiKhachHangService;
import bcc.springhibernate.service.NhomHangService;
import bcc.springhibernate.service.NhomKhachHangService;
import bcc.springhibernate.service.QuyenService;

@RestController
@RequestMapping("/admin")
public class QuyenRestController {

	@Autowired
	QuyenService quyenService;

	@PostMapping("/kiemtramaquyen")
	String kiemTraMaQuyen(@RequestBody Quyen quyen) {

		Quyen quyenByMaQuyen = null;
		try {
			quyenByMaQuyen = quyenService.findByMaquyen(quyen.getMaquyen());
			if (quyen.getId() == null) {

				if (quyenByMaQuyen == null) {

					return "success";
				}
			} else {
				if (quyenByMaQuyen != null) {
					if (quyen.getId() == quyenByMaQuyen.getId()) {

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
