package bcc.springhibernate.controller;


import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bcc.springhibernate.model.Feedback;
import bcc.springhibernate.model.Hanghoa;
import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.service.FeedBackService;
import bcc.springhibernate.service.HangHoaService;
import bcc.springhibernate.service.KhachHangService;
import bcc.springhibernate.service.NhanVienService;
import bcc.springhibernate.service.NhomHangService;
import bcc.springhibernate.service.TaikhoanService;

@Controller
@RequestMapping("/admin")
public class FeedBackController {

	@Autowired
	FeedBackService  feedBackService;
	@Autowired
	HangHoaService  hangHoaService;
	@Autowired
	KhachHangService khachHangService;
	@Autowired
	NhanVienService nhanVienService;
	@Autowired
	TaikhoanService taikhoanService;
    @GetMapping("/feedback")
    String pageDanhSachFeedBack(@RequestParam(value="trangthai",defaultValue = "active") String trangthai,
								Model model){
    	List<Feedback> listFeedback = feedBackService.findByTrangthaiOrderByIdDesc(trangthai);
    	List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
    	List<Khachhang> listKhachhang = khachHangService.findByTrangthaiOrderByIdDesc("active");
    	List<Hanghoa> listHanghoa = hangHoaService.findByTrangthaiOrderByIdDesc("active");
    	
    	model.addAttribute("listNhanvien", listNhanvien);
    	model.addAttribute("listKhachhang", listKhachhang);
    	model.addAttribute("listHanghoa", listHanghoa);
    	model.addAttribute("listFeedback", listFeedback);
        return "danhsachfeedback";
    }

    @GetMapping("/feedback/add")
    String pageThemFeedBack(Model model ){
    	List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
    	List<Khachhang> listKhachhang = khachHangService.findByTrangthaiOrderByIdDesc("active");
    	List<Hanghoa> listHanghoa = hangHoaService.findByTrangthaiOrderByIdDesc("active");
    	
    	model.addAttribute("listNhanvien", listNhanvien);
    	model.addAttribute("listKhachhang", listKhachhang);
    	model.addAttribute("listHanghoa", listHanghoa);
    	model.addAttribute("feedback", new Feedback());
        return "themfeedback";
    }
    @GetMapping("/feedback/{id}")
    String pageSuaFeedBack(Model model, @PathVariable("id") Integer id ){
    	Feedback feedback= feedBackService.findById(id);
    	List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
    	List<Khachhang> listKhachhang = khachHangService.findByTrangthaiOrderByIdDesc("active");
    	List<Hanghoa> listHanghoa = hangHoaService.findByTrangthaiOrderByIdDesc("active");
    	
    	model.addAttribute("listNhanvien", listNhanvien);
    	model.addAttribute("listKhachhang", listKhachhang);
    	model.addAttribute("listHanghoa", listHanghoa);
    		model.addAttribute("feedback", feedback);
        return "suafeedback";
    }
    
    @PostMapping("/feedback")
    String themFeedBack(@ModelAttribute("feedback") Feedback feedback,
    		@RequestParam("nhanvienId") Integer nhanvienId,
    		@RequestParam("hanghoaId") Integer hanghoaId,
    		@RequestParam("khachhangId") Integer khachhangId,
    		Principal principal,
    		RedirectAttributes redirectAttributes) {
    	try {
    		Taikhoan getTaiKhoanByUserName = taikhoanService.findByUsername(principal.getName());
    		feedback.setNhanvienId(nhanvienId);
    		feedback.setHanghoaId(hanghoaId);
    		feedback.setKhachhangId(khachhangId);
    		feedback.setNhavientaoId(getTaiKhoanByUserName.getNhanvien().getId());
    		feedback.setNgaytao(new Date());
    		feedback.setTrangthai("active");
    		feedBackService.saveOrUpdate(feedback);
        	redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
    	
    	return "redirect:/admin/feedback";
    }	
    	
    @PatchMapping("/feedback")
    String suaFeedBack(@ModelAttribute("feedback") Feedback feedback,
    		@RequestParam("nhanvienId") Integer nhanvienId,
    		@RequestParam("hanghoaId") Integer hanghoaId,
    		@RequestParam("khachhangId") Integer khachhangId,
    		Principal principal,
    		RedirectAttributes redirectAttributes) {
    	try {
    		Taikhoan getTaiKhoanByUserName = taikhoanService.findByUsername(principal.getName());
    		feedback.setNhanvienId(nhanvienId);
    		feedback.setHanghoaId(hanghoaId);
    		feedback.setKhachhangId(khachhangId);
    		feedback.setNhavientaoId(getTaiKhoanByUserName.getNhanvien().getId());
    		feedback.setNgaytao(new Date());
    		feedback.setTrangthai("active");
    		feedBackService.saveOrUpdate(feedback);
        	redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}
    	
    	return "redirect:/admin/feedback";
    }	
    @DeleteMapping("/feedback")
    String xoaFeedBack(@RequestParam("arrayId") List<Integer> arrayId,
    		RedirectAttributes redirectAttributes) {
    	
    	try {
			arrayId.forEach(x -> {

				Feedback feedback= feedBackService.findById(x);
				feedback.setTrangthai("deleted");
				feedBackService.saveOrUpdate(feedback);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

    	return "redirect:/admin/feedback";
    }	
}
