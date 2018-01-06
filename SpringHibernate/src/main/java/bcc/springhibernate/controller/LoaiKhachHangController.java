package bcc.springhibernate.controller;


import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import bcc.springhibernate.model.Loaikhachhang;

import bcc.springhibernate.service.LoaiKhachHangService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class LoaiKhachHangController {

	@Autowired
	LoaiKhachHangService  loaiKhachHangService;
	
    @GetMapping("/loaikhachhang")
    String pageDanhSachLoaiKhachHangg(Model model){
    	List<Loaikhachhang> listLoaiKhachHang = loaiKhachHangService.findLoaikhachhangByTrangthaiOrderByIdDesc("active");
    	model.addAttribute("listLoaiKhachHang", listLoaiKhachHang);
        return "danhsachloaikhachhang";
    }

    @GetMapping("/loaikhachhang/add")
    String pageThemLoaiKhachHangg(Model model ){
    		model.addAttribute("loaikhachhang", new Loaikhachhang());
        return "themloaikhachhang";
    }
    @GetMapping("/loaikhachhang/{id}")
    String pageSuaLoaiKhachHangg(Model model, @PathVariable("id") Integer id ){
    	Loaikhachhang loaikhachhang = loaiKhachHangService.findLoaikhachhangById(id);
    		model.addAttribute("loaikhachhang", loaikhachhang);
        return "sualoaikhachhang";
    }
    
    @PostMapping("/loaikhachhang")
    String themLoaiKhachHang(@ModelAttribute("loaikhachhang") Loaikhachhang loaikhachhang,RedirectAttributes redirectAttributes) {
    	try {
    	
    	loaikhachhang.setTrangthai("active");
    	loaiKhachHangService.saveOrUpdate(loaikhachhang);
    	redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
    	return "redirect:/admin/loaikhachhang";
    }	
    	
    @PatchMapping("/loaikhachhang")
    String suaLoaiKhachHang(@ModelAttribute("loaikhachhang") Loaikhachhang loaikhachhang,RedirectAttributes redirectAttributes) {
    	try {
    		loaikhachhang.setTrangthai("active");
        	loaiKhachHangService.saveOrUpdate(loaikhachhang);
        	redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}	
    	return "redirect:/admin/loaikhachhang";
    }	
    @DeleteMapping("/loaikhachhang")
    String xoaLoaiKhachHang(@RequestParam("arrayId") List<Integer> arrayId,
    		RedirectAttributes redirectAttributes) {
    	
    	try {
			arrayId.forEach(x -> {

				Loaikhachhang loaikhachhang = loaiKhachHangService.findLoaikhachhangById(x);
				loaikhachhang.setTrangthai("deleted");
				loaiKhachHangService.saveOrUpdate(loaikhachhang);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

    	return "redirect:/admin/loaikhachhang";
    }	
}
