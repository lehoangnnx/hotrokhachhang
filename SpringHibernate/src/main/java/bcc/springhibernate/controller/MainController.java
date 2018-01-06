package bcc.springhibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.service.TaikhoanService;

@Controller
public class MainController {
	@Autowired
	TaikhoanService taikhoanService;
	
	
	@RequestMapping(value = {"/", "/home"})
	public String index(Model model){
		
		
		return "index";
	}
	@GetMapping(value = {"taikhoan"})
	public String themtaikhoan(Model model){


		return "themtaikhoan";
	}

	@RequestMapping(value = "/usersmng")
	public String adminindex(Model model){
		List<Taikhoan> lstTaiKhoan = taikhoanService.findAll();
		model.addAttribute("lsttaikhoan",lstTaiKhoan);
		return "usersmng";
	}
	
	@GetMapping(value = "/login")
	public String loginpage(Model model){
		
		return "login";
	}
	
	@GetMapping(value = {"403"})
	public String page403(Model model){


		return "403";
	}
}
