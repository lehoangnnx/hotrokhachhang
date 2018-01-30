package bcc.springhibernate.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.RequestHandledEvent;
import org.springframework.web.servlet.ModelAndView;

import bcc.springhibernate.model.Chamsoc;
import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Luong;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhanvienkpi;
import bcc.springhibernate.service.ChamSocService;
import bcc.springhibernate.service.HoaDonService;
import bcc.springhibernate.service.KhachHangService;
import bcc.springhibernate.service.LuongService;
import bcc.springhibernate.service.NhanVienKpiService;
import bcc.springhibernate.service.NhanVienService;
import bcc.springhibernate.service.TaikhoanService;


public class ThongBaoListener {
	@Autowired
	TaikhoanService taikhoanService;
	@Autowired
	NhanVienService nhanVienService;
	@Autowired
	KhachHangService khachHangService;

	@Autowired
	ChamSocService chamSocService;
	@Autowired
	HoaDonService hoaDonService;
	@Autowired
	NhanVienKpiService nhanVienKpiService;
	@Autowired
	LuongService luongService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public void crossOriginTest(HttpServletRequest request) {
	    String userHost = request.getRemoteHost();
	    int userPort = request.getRemotePort();

	    int serverPort = request.getServerPort();
	    String serverName = request.getServerName();
	    System.out.println(userHost+ "-"+userPort);
	    // compare them
	    // Maybe get the X-Forwarded-For
	} 
}
