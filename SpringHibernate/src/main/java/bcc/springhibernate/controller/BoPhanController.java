package bcc.springhibernate.controller;


import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import bcc.springhibernate.model.Bophan;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.service.BoPhanService;
import bcc.springhibernate.service.LoaiKhachHangService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin")
public class BoPhanController {
	
	
	@Autowired
	BoPhanService  boPhanService;
	
	
	
    @GetMapping("/bophan")
    String pageDanhSachBoPhan(@RequestParam(value="trangthai",defaultValue = "active") String trangthai , Model model){
    	List<Bophan> listBophan = boPhanService.findByTrangthaiOrderByIdDesc(trangthai);
    	model.addAttribute("listBophan", listBophan);
    	
        return "danhsachbophan";
    }

    @GetMapping("/bophan/add")
    String pageThemBoPhan(Model model ){
    		model.addAttribute("bophan", new Bophan());
        return "thembophan";
    }
    @GetMapping("/bophan/{id}")
    String pageSuaBoPhan(Model model, @PathVariable("id") Integer id ){
    	Bophan bophan= boPhanService.findById(id);
    		model.addAttribute("bophan", bophan);
        return "suabophan";
    }
    
    @PostMapping("/bophan")
    String themBoPhan(@ModelAttribute("bophan") Bophan bophan,RedirectAttributes redirectAttributes) {
    	try {
    	
    	bophan.setTrangthai("active");
    	boPhanService.saveOrUpdate(bophan);
    	redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
    	return "redirect:/admin/bophan?trangthai=active";
    }	
    	
    @PatchMapping(value="/bophan",params="update")
    String suaBoPhan(@ModelAttribute("bophan") Bophan bophan,RedirectAttributes redirectAttributes) {
    	try {
    		bophan.setTrangthai("active");
        	boPhanService.saveOrUpdate(bophan);
        	redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}	
    	return "redirect:/admin/bophan?trangthai=active";
    }	
    
    @PatchMapping(value="/bophan",params="deleted")
    String xoaVinhVienBoPhan(@ModelAttribute("bophan") Bophan bophan,RedirectAttributes redirectAttributes) {
    	try {
    		
        	boPhanService.deleted(bophan);
        	redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
		}	
    	return "redirect:/admin/bophan?trangthai=active";
    }	
    @DeleteMapping("/bophan")
    String xoaBoPhan(@RequestParam("arrayId") List<Integer> arrayId,
    		RedirectAttributes redirectAttributes) {
    	
    	try {
			arrayId.forEach(x -> {

				Bophan bophan= boPhanService.findById(x);
				bophan.setTrangthai("deleted");
	        	boPhanService.saveOrUpdate(bophan);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

    	return "redirect:/admin/bophan?trangthai=active";
    }	
}
