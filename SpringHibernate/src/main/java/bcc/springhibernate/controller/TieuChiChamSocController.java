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
import bcc.springhibernate.model.Tieuchichamsoc;
import bcc.springhibernate.service.NhomKhachHangService;
import bcc.springhibernate.service.TieuChiChamSocService;

@Controller
@RequestMapping("/admin")
public class TieuChiChamSocController {

	
	@Autowired
	TieuChiChamSocService tieuChiChamSocService;
    @GetMapping("/tieuchichamsoc")
    String pageDanhSachTieuChiChamSoc(@RequestParam(value="trangthai",defaultValue = "active") String trangthai,
									  Model model){
    	List<Tieuchichamsoc> listTieuchichamsoc = tieuChiChamSocService.findByTrangthaiOrderByIdDesc(trangthai);
    	model.addAttribute("listTieuchichamsoc", listTieuchichamsoc);
        return "danhsachtieuchichamsoc";
    }

    @GetMapping("/tieuchichamsoc/add")
    String pageThemTieuChiChamSoc(Model model ){
    		model.addAttribute("tieuchichamsoc", new Tieuchichamsoc());
        return "themtieuchichamsoc";
    }
    @GetMapping("/tieuchichamsoc/{id}")
    String pageSuaTieuChiChamSoc(Model model, @PathVariable("id") Integer id ){
    	Tieuchichamsoc tieuchichamsoc= tieuChiChamSocService.findById(id);
    	
    		model.addAttribute("tieuchichamsoc", tieuchichamsoc);
        return "suatieuchichamsoc";
    }
    
    @PostMapping("/tieuchichamsoc")
    String themTieuChiChamSoc(@ModelAttribute("tieuchichamsoc") Tieuchichamsoc tieuchichamsoc,
    		@RequestParam("kieutieuchi") String kieutieuchi,
    		RedirectAttributes redirectAttributes) {
    	try {
    		
    		tieuchichamsoc.setKieutieuchi(kieutieuchi);
    		tieuchichamsoc.setTrangthai("active");
    		
    		tieuChiChamSocService.saveOrUpdate(tieuchichamsoc);
        	redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
    	
    	return "redirect:/admin/tieuchichamsoc";
    }	
    	
    @PatchMapping("/tieuchichamsoc")
    String suaTieuChiChamSoc(@ModelAttribute("tieuchichamsoc") Tieuchichamsoc tieuchichamsoc,
    		@RequestParam("kieutieuchi") String kieutieuchi,
    		RedirectAttributes redirectAttributes) {
    	try {
    		tieuchichamsoc.setKieutieuchi(kieutieuchi);
    		tieuchichamsoc.setTrangthai("active");
    		
    		tieuChiChamSocService.saveOrUpdate(tieuchichamsoc);
        	redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}
    	
    	return "redirect:/admin/tieuchichamsoc";
    }	
    @DeleteMapping("/tieuchichamsoc")
    String xoaTieuChiChamSoc(@RequestParam("arrayId") List<Integer> arrayId,
    		RedirectAttributes redirectAttributes) {
    	
    	try {
			arrayId.forEach(x -> {

				Tieuchichamsoc tieuchichamsoc= tieuChiChamSocService.findById(x);
				tieuchichamsoc.setTrangthai("deleted");
	    		
	    		tieuChiChamSocService.saveOrUpdate(tieuchichamsoc);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

    	return "redirect:/admin/tieuchichamsoc";
    }	
}
