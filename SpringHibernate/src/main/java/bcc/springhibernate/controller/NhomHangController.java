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

import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.service.NhomHangService;

@Controller
@RequestMapping("/admin")
public class NhomHangController {

	@Autowired
	NhomHangService  nhomHangService;
	
    @GetMapping("/nhomhang")
    String pageDanhSachNhomHang(@RequestParam(value="trangthai",defaultValue = "active") String trangthai,
								Model model){
    	List<Nhomhang> listNhomhang = nhomHangService.findByTrangthaiOrderByIdDesc(trangthai);
    	model.addAttribute("listNhomhang", listNhomhang);
        return "danhsachnhomhang";
    }

    @GetMapping("/nhomhang/add")
    String pageThemNhomHang(Model model ){
    	List<Nhomhang> listNhomhang = nhomHangService.findByTrangthaiOrderByIdDesc("active");
    	model.addAttribute("listNhomhang", listNhomhang);
    		model.addAttribute("nhomhang", new Nhomhang());
        return "themnhomhang";
    }
    @GetMapping("/nhomhang/{id}")
    String pageSuaNhomHang(Model model, @PathVariable("id") Integer id ){
    	Nhomhang nhomhang = nhomHangService.findById(id);
    	List<Nhomhang> listNhomhang = nhomHangService.findByTrangthaiOrderByIdDesc("active");
    	model.addAttribute("listNhomhang", listNhomhang);
    		model.addAttribute("nhomhang", nhomhang);
        return "suanhomhang";
    }
    
    @PostMapping("/nhomhang")
    String themNhomHang(@ModelAttribute("nhomhang") Nhomhang nhomhang,
    		RedirectAttributes redirectAttributes) {
    	try {
    		
    		nhomhang.setTrangthai("active");
        	nhomHangService.saveOrUpdate(nhomhang);
        	
        	redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
    	
    	return "redirect:/admin/nhomhang?trangthai=active";
    }	
    	
    @PatchMapping("/nhomhang")
    String suaNhomHang(@ModelAttribute("nhomhang") Nhomhang nhomhang,
    		RedirectAttributes redirectAttributes) {
    	try {
    		nhomhang.setTrangthai("active");
        	nhomHangService.saveOrUpdate(nhomhang);
        	redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}
    	
    	return "redirect:/admin/nhomhang?trangthai=active";
    }	
    @DeleteMapping("/nhomhang")
    String xoaNhomHang(@RequestParam("arrayId") List<Integer> arrayId,
    		RedirectAttributes redirectAttributes) {
    	
    	try {
			arrayId.forEach(x -> {

				Nhomhang nhomhang = nhomHangService.findById(x);
				nhomhang.setTrangthai("deleted");
				nhomHangService.saveOrUpdate(nhomhang);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

    	return "redirect:/admin/nhomhang?trangthai=active";
    }	
}
