package bcc.springhibernate.controller;


import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.model.Quyen;
import bcc.springhibernate.service.NhomHangService;
import bcc.springhibernate.service.QuyenService;

@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin")
public class QuyenController {

	@Autowired
	QuyenService  quyenService;
	
    @GetMapping("/quyen")
    String pageDanhSachQuyen(@RequestParam(value="trangthai",defaultValue = "active") String trangthai,
							 Model model){
    	List<Quyen> listQuyen = quyenService.findByTrangthaiOrderByIdDesc(trangthai);
    	model.addAttribute("listQuyen", listQuyen);
        return "danhsachquyen";
    }

    @GetMapping("/quyen/add")
    String pageThemQuyen(Model model ){
    	
    		model.addAttribute("quyen", new Quyen());
        return "themquyen";
    }
    @GetMapping("/quyen/{id}")
    String pageSuaQuyen(Model model, @PathVariable("id") Integer id ){
    	Quyen quyen= quyenService.findById(id);
    	
    		model.addAttribute("quyen", quyen);
        return "suaquyen";
    }
    
    @PostMapping("/quyen")
    String themQuyen(@ModelAttribute("quyen") Quyen quyen,
    		RedirectAttributes redirectAttributes) {
    	try {
    		
    		quyen.setTrangthai("active");
        	quyenService.saveOrUpdate(quyen);
        	redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
    	
    	return "redirect:/admin/quyen?trangthai=active";
    }	
    	
    @PatchMapping(value="/quyen",params="update")
    String suaQuyen(@ModelAttribute("quyen") Quyen quyen,
    		RedirectAttributes redirectAttributes) {
    	try {
    		quyen.setTrangthai("active");
        	quyenService.saveOrUpdate(quyen);
        	redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}
    	
    	return "redirect:/admin/quyen?trangthai=active";
    }	
    @PatchMapping(value="/quyen",params="deleted")
    String xoaVinhVienQuyen(@ModelAttribute("quyen") Quyen quyen,
    		RedirectAttributes redirectAttributes) {
    	try {
    		
        	quyenService.deleted(quyen);
        	redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
		}
    	
    	return "redirect:/admin/quyen?trangthai=active";
    }	
    @DeleteMapping("/quyen")
    String xoaQuyen(@RequestParam("arrayId") List<Integer> arrayId,
    		RedirectAttributes redirectAttributes) {
    	
    	try {
			arrayId.forEach(x -> {

				Quyen quyen= quyenService.findById(x);
				quyen.setTrangthai("deleted");
				quyenService.saveOrUpdate(quyen);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

    	return "redirect:/admin/quyen?trangthai=active";
    }	
}
