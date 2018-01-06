package bcc.springhibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import bcc.springhibernate.model.Quyen;
import bcc.springhibernate.service.QuyenService;

@Controller
public class AdministratorController {
	
	@Autowired
	QuyenService quyenService;
	
	@RequestMapping(value="/roles")
	public String RoleMNG(Model model){
		List<Quyen> lst = quyenService.findAll();
		model.addAttribute("lstquyen",lst);
		
		return "rolesmng";
	}
}
