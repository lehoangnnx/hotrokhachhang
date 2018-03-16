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

import bcc.springhibernate.model.Hanghoa;
import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.service.HangHoaService;
import bcc.springhibernate.service.NhomHangService;

@Controller
@PreAuthorize("hasAnyRole('ADMIN','BANHANG')")
@RequestMapping("/admin")
public class HangHoaController {

	@Autowired
	HangHoaService  hangHoaService;
	@Autowired
	NhomHangService nhomHangService;
    @GetMapping("/hanghoa")
    String pageDanhSachHangHoa(@RequestParam(value="trangthai",defaultValue = "active") String trangthai,Model model){
    	List<Hanghoa> listHanghoa = hangHoaService.findByTrangthaiOrderByIdDesc(trangthai);
    	model.addAttribute("listHanghoa", listHanghoa);
        return "danhsachhanghoa";
    }

    @GetMapping("/hanghoa/add")
    String pageThemHangHoa(Model model ){
    	List<Nhomhang> listNhomhang = nhomHangService.findByTrangthaiOrderByIdDesc("active");
    	model.addAttribute("listNhomhang", listNhomhang);
    		model.addAttribute("hanghoa", new Hanghoa());
        return "themhanghoa";
    }
    @GetMapping("/hanghoa/{id}")
    String pageSuaHangHoa(Model model, @PathVariable("id") Integer id ){
    	Hanghoa hanghoa = hangHoaService.findById(id);
    	List<Nhomhang> listNhomhang = nhomHangService.findByTrangthaiOrderByIdDesc("active");
    	model.addAttribute("listNhomhang", listNhomhang);
    		model.addAttribute("hanghoa", hanghoa);
        return "suahanghoa";
    }
    
    @PostMapping("/hanghoa")
    String themHangHoa(@ModelAttribute("hanghoa") Hanghoa hanghoa,
					   @RequestParam("giaban_money") String giaban,
					   @RequestParam("gianhap_money") String gianhap,
					   @RequestParam(value="_giakhuyenmai",defaultValue = "") String giakhuyenmai,
					   @RequestParam("nhomhang") Integer nhomhang,
    		RedirectAttributes redirectAttributes) {
    	try {
    		Nhomhang nhomhangById = nhomHangService.findById(nhomhang);
    		hanghoa.setTrangthai("active");
    		hanghoa.setNhomhang(nhomhangById);
    		hanghoa.setGiaban(Long.valueOf(giaban.replace(".","")));
    		hanghoa.setGianhap(Long.valueOf(gianhap.replace(".","")));
    		if(!giakhuyenmai.equals("")){
				hanghoa.setGiakhuyenmai(Long.valueOf(giakhuyenmai.replace(".","")));
			}
    		hangHoaService.saveOrUpdate(hanghoa);
        	redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
    	
    	return "redirect:/admin/hanghoa?trangthai=active";
    }	
    	
    @PatchMapping(value="/hanghoa",params="update")
    String suaHangHoa(@ModelAttribute("hanghoa") Hanghoa hanghoa,
					  @RequestParam("giaban_money") String giaban,
					  @RequestParam("gianhap_money") String gianhap,
					  @RequestParam(value="_giakhuyenmai",defaultValue = "") String giakhuyenmai,
					  @RequestParam("nhomhang") Integer nhomhang,
    		RedirectAttributes redirectAttributes) {
    	try {
    		Nhomhang nhomhangById = nhomHangService.findById(nhomhang);
    		//hanghoa.setTrangthai("active");
    		hanghoa.setNhomhang(nhomhangById);
			hanghoa.setGiaban(Long.valueOf(giaban.replace(".","")));
			hanghoa.setGianhap(Long.valueOf(gianhap.replace(".","")));
			if(!giakhuyenmai.equals("")){
				hanghoa.setGiakhuyenmai(Long.valueOf(giakhuyenmai.replace(".","")));
			}
    		hangHoaService.saveOrUpdate(hanghoa);
        	redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}
    	
    	return "redirect:/admin/hanghoa?trangthai=active";
    }	
    @PatchMapping(value="/hanghoa",params="deleted")
    String xoaVinhVienHangHoa(@ModelAttribute("hanghoa") Hanghoa hanghoa,
    		RedirectAttributes redirectAttributes) {
    	try {
    		
    		hangHoaService.deleted(hanghoa);
        	redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
		}
    	
    	return "redirect:/admin/hanghoa?trangthai=active";
    }	
    @DeleteMapping("/hanghoa")
    String xoaHangHoa(@RequestParam("arrayId") List<Integer> arrayId,
    		RedirectAttributes redirectAttributes) {
    	
    	try {
			arrayId.forEach(x -> {

				Hanghoa hanghoa = hangHoaService.findById(x);
				hanghoa.setTrangthai("deleted");
				hangHoaService.saveOrUpdate(hanghoa);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

    	return "redirect:/admin/hanghoa?trangthai=active";
    }	
}
