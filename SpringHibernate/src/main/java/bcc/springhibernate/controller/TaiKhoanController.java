package bcc.springhibernate.controller;


import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.model.Quyen;
import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.service.NhanVienService;
import bcc.springhibernate.service.NhomHangService;
import bcc.springhibernate.service.QuyenService;
import bcc.springhibernate.service.TaikhoanService;

@Controller
@RequestMapping("/admin")
public class TaiKhoanController {

	@Autowired
	TaikhoanService taikhoanService;
	
	@Autowired
	NhanVienService nhanVienService;
	
	@Autowired
	QuyenService quyenService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
    @GetMapping("/taikhoan")
    String pageDanhSachTaiKhoan(Model model){
    	List<Taikhoan> listTaikhoan= taikhoanService.findByTrangthaiOrderByIdDesc("active");
    	model.addAttribute("listTaikhoan", listTaikhoan);
        return "danhsachtaikhoan";
    }

    @GetMapping("/taikhoan/add")
    String pageThemTaiKhoan(Model model ){
    		List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
    		List<Quyen> listQuyen= quyenService.findByTrangthaiOrderByIdDesc("active");
    		
    		model.addAttribute("listNhanvien", listNhanvien);
    		model.addAttribute("listQuyen", listQuyen);
    		model.addAttribute("taikhoan", new Taikhoan());
        return "themtaikhoan";
    }
    @GetMapping("/taikhoan/{id}")
    String pageSuaTaiKhoan(Model model, @PathVariable("id") Integer id ){
    	Taikhoan taikhoan= taikhoanService.findById(id);
    	List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
		List<Quyen> listQuyen= quyenService.findByTrangthaiOrderByIdDesc("active");
		
		model.addAttribute("listNhanvien", listNhanvien);
		model.addAttribute("listQuyen", listQuyen);
    		model.addAttribute("taikhoan", taikhoan);
        return "suataikhoan";
    }
    
    @PostMapping("/taikhoan")
    String themTaiKhoan(@ModelAttribute("taikhoan") Taikhoan taikhoan,
    		@RequestParam("nhanvien") Integer nhanvien,@RequestParam("quyen") List<Integer> quyen,
    		RedirectAttributes redirectAttributes) {
    	try {
    		Nhanvien nhanVienById =  nhanVienService.findById(nhanvien);
    		HashSet<Quyen> hsquyen = new HashSet<>();
    		
    		for(Integer q : quyen) {
    			hsquyen.add(quyenService.findById(q));
    		}
    		
    		taikhoan.setMatkhau(passwordEncoder.encode(taikhoan.getMatkhau()));
    		taikhoan.setTrangthai("active");
    		taikhoan.setNhanvien(nhanVienById);
    		taikhoan.setQuyens(hsquyen);
        	taikhoanService.saveOrUpdate(taikhoan);
        	redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
    	
    	return "redirect:/admin/taikhoan";
    }	
    	
    @PatchMapping("/taikhoan")
    String suaTaiKhoan(@ModelAttribute("taikhoan") Taikhoan taikhoan,
    		@RequestParam("nhanvien") Integer nhanvien,@RequestParam("quyen") List<Integer> quyen,
    		RedirectAttributes redirectAttributes) {
    	try {
    		Nhanvien nhanVienById =  nhanVienService.findById(nhanvien);
    		HashSet<Quyen> hsquyen = new HashSet<>();
    		
    		for(Integer q : quyen) {
    			hsquyen.add(quyenService.findById(q));
    		}
    		taikhoan.setTrangthai("active");
    		taikhoan.setNhanvien(nhanVienById);
    		taikhoan.setQuyens(hsquyen);
        	taikhoanService.saveOrUpdate(taikhoan);
        	redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}
    	
    	return "redirect:/admin/taikhoan";
    }	
    @DeleteMapping("/taikhoan")
    String xoaTaiKhoan(@RequestParam("arrayId") List<Integer> arrayId,
    		RedirectAttributes redirectAttributes) {
    	
    	try {
			arrayId.forEach(x -> {

				Taikhoan taikhoan= taikhoanService.findById(x);
				taikhoan.setTrangthai("deleted");
				taikhoanService.saveOrUpdate(taikhoan);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

    	return "redirect:/admin/taikhoan";
    }	
}
