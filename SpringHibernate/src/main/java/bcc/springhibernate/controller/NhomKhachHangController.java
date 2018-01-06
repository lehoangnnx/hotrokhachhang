package bcc.springhibernate.controller;


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

import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.service.NhomKhachHangService;

@Controller
@RequestMapping("/admin")
public class NhomKhachHangController {

	@Autowired
	NhomKhachHangService  nhomKhachHangService;
	
    @GetMapping("/nhomkhachhang")
    String pageDanhSachLoaiKhachHangg(Model model){
    	List<Nhomkhachhang> listNhomkhachhang = nhomKhachHangService.findByTrangthaiOrderByIdDesc("active");
    	model.addAttribute("listNhomkhachhang", listNhomkhachhang);
        return "danhsachnhomkhachhang";
    }

    @GetMapping("/nhomkhachhang/add")
    String pageThemLoaiKhachHangg(Model model ){
    		model.addAttribute("nhomkhachhang", new Nhomkhachhang());
        return "themnhomkhachhang";
    }
    @GetMapping("/nhomkhachhang/{id}")
    String pageSuaLoaiKhachHangg(Model model, @PathVariable("id") Integer id ){
    	Nhomkhachhang nhomkhachhang = nhomKhachHangService.findById(id);
    		model.addAttribute("nhomkhachhang", nhomkhachhang);
        return "suanhomkhachhang";
    }
    
    @PostMapping("/nhomkhachhang")
    String themLoaiKhachHang(@ModelAttribute("nhomkhachhang") Nhomkhachhang nhomkhachhang,
    		RedirectAttributes redirectAttributes) {
    	try {
    		nhomkhachhang.setTrangthai("active");
        	nhomKhachHangService.saveOrUpdate(nhomkhachhang);
        	redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
    	
    	return "redirect:/admin/nhomkhachhang";
    }	
    	
    @PatchMapping("/nhomkhachhang")
    String suaLoaiKhachHang(@ModelAttribute("nhomkhachhang") Nhomkhachhang nhomkhachhang,
    		RedirectAttributes redirectAttributes) {
    	try {
    		nhomkhachhang.setTrangthai("active");
        	nhomKhachHangService.saveOrUpdate(nhomkhachhang);
        	redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}
    	
    	return "redirect:/admin/nhomkhachhang";
    }	
    @DeleteMapping("/nhomkhachhang")
    String xoaLoaiKhachHang(@RequestParam("arrayId") List<Integer> arrayId,
    		RedirectAttributes redirectAttributes) {
    	
    	try {
			arrayId.forEach(x -> {

				Nhomkhachhang nhomkhachhang = nhomKhachHangService.findById(x);
				nhomkhachhang.setTrangthai("deleted");
				nhomKhachHangService.saveOrUpdate(nhomkhachhang);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

    	return "redirect:/admin/nhomkhachhang";
    }	
}
