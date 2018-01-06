package bcc.springhibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import bcc.springhibernate.model.Quyen;
import bcc.springhibernate.service.QuyenService;

@RestController
@RequestMapping("/admin")
public class RolesRestController {
	
	@Autowired
	QuyenService quyenService;
	
	@PostMapping("/validator-roles")
	// Kiểm tra tên quyền bị trùng
	public String validatorRoles(@RequestBody Quyen quyen) {
		Quyen findByName = null;

		
		try {
			findByName = quyenService.findByTenQuyen(HtmlUtils.htmlEscape(quyen.getTenquyen().trim()));

			
			if (quyen.getId() == null) {
				if (findByName != null) {
					return "errorname";
				} else {
					return "msgsuccess";
				}
			}else {
				if (findByName != null
						&& !findByName.getId().equals(quyen.getId())) {

					return "errorname";
				} else {
					return "msgsuccess";
				}
			}
		} catch (Exception e) {
			return "msgsuccess";
		}
		
	}
}
