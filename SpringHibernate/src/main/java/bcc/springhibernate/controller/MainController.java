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
	
	
	@RequestMapping("/")
	public String index(Model model){
		
		
		return "login";
	}
	@RequestMapping("/admin")
	public String home(Model model){
		
		
		return "index";
	}
	@GetMapping(value = {"login"})
	public String pageLogin(Model model){


		return "login";
	}
	@GetMapping(value = {"403"})
	public String page403(Model model){


		return "403";
	}
}
