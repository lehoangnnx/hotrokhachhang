package bcc.springhibernate.controller;


import java.text.SimpleDateFormat;
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

import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhanvienkpi;
import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.repository.KpiRepository;
import bcc.springhibernate.service.KpiService;
import bcc.springhibernate.service.NhanVienKpiService;
import bcc.springhibernate.service.NhanVienService;
import bcc.springhibernate.service.NhomKhachHangService;


@Controller
@RequestMapping("/admin")
public class NhanVienKpiController {

	@Autowired
	NhanVienKpiService nhanVienKpiService;
	@Autowired
	NhanVienService nhanVienService;
	@Autowired
	KpiService	kpiService; 
    @GetMapping("/nhanvienkpi")
    String pageDanhSachNhanVienKpi(@RequestParam(value="trangthai",defaultValue = "active") String trangthai,
								   Model model){
    	List<Nhanvienkpi> listNhanvienkpi = nhanVienKpiService.findByTrangthaiOrderByIdDesc(trangthai);
    	model.addAttribute("listNhanvienkpi", listNhanvienkpi);
        return "danhsachnhanvienkpi";
    }

    @GetMapping("/nhanvienkpi/add")
    String pageThemNhanVienKpi(Model model ){
    	List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
    	List<Kpi> listKpi = kpiService.findByTrangthaiOrderByIdDesc("active");
    	
    	model.addAttribute("listNhanvien", listNhanvien);
    	model.addAttribute("listKpi",listKpi);
    		model.addAttribute("nhanvienkpi", new Nhanvienkpi());
        return "themnhanvienkpi";
    }
    @GetMapping("/nhanvienkpi/{id}")
    String pageSuaNhanVienKpi(Model model, @PathVariable("id") Integer id ){
    	Nhanvienkpi nhanvienkpi= nhanVienKpiService.findById(id);
    	List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
    	List<Kpi> listKpi = kpiService.findByTrangthaiOrderByIdDesc("active");
    	
    	model.addAttribute("listNhanvien", listNhanvien);
    	model.addAttribute("listKpi",listKpi);
    		model.addAttribute("nhanvienkpi", nhanvienkpi);
        return "suanhanvienkpi";
    }
    
    @PostMapping("/nhanvienkpi")
    String themNhanVienKpi(@ModelAttribute("nhanvienkpi") Nhanvienkpi nhanvienkpi,
    		@RequestParam("nhanvien") Integer nhanvien,@RequestParam("kpi") Integer kpi,
    		@RequestParam("ngayhoanthanh") String ngayhoanthanh,
    		RedirectAttributes redirectAttributes) {
    	try {
    		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    		Nhanvien nhanVienById = nhanVienService.findById(nhanvien);
    		Kpi kpiById = kpiService.findById(kpi);
    		
    		
    		nhanvienkpi.setNhanvien(nhanVienById);
    		nhanvienkpi.setKpi(kpiById);
    		nhanvienkpi.setNgaydangky(new Date());
    		nhanvienkpi.setNgayhoanthanh(df.parse(ngayhoanthanh));
    		
        	nhanVienKpiService.saveOrUpdate(nhanvienkpi);
        	redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
    	
    	return "redirect:/admin/nhanvienkpi";
    }	
    	
    @PatchMapping("/nhanvienkpi")
    String suaNhanVienKpi(@ModelAttribute("nhanvienkpi") Nhanvienkpi nhanvienkpi,
    		@RequestParam("nhanvien") Integer nhanvien,@RequestParam("kpi") Integer kpi,
    		@RequestParam("ngayhoanthanh") String ngayhoanthanh,
    		RedirectAttributes redirectAttributes) {
    	try {
    		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    		Nhanvien nhanVienById = nhanVienService.findById(nhanvien);
    		Kpi kpiById = kpiService.findById(kpi);
    		
    		
    		nhanvienkpi.setNhanvien(nhanVienById);
    		nhanvienkpi.setKpi(kpiById);
    		nhanvienkpi.setNgaydangky(new Date());
    		nhanvienkpi.setNgayhoanthanh(df.parse(ngayhoanthanh));
    		
        	nhanVienKpiService.saveOrUpdate(nhanvienkpi);
        	redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}
    	
    	return "redirect:/admin/nhanvienkpi";
    }	
    @DeleteMapping("/nhanvienkpi")
    String xoaNhanVienKpi(@RequestParam("arrayId") List<Integer> arrayId,
    		RedirectAttributes redirectAttributes) {
    	
    	try {
			arrayId.forEach(x -> {

				Nhanvienkpi nhanvienkpi= nhanVienKpiService.findById(x);
				nhanvienkpi.setTrangthai("deleted");
				nhanVienKpiService.saveOrUpdate(nhanvienkpi);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

    	return "redirect:/admin/nhanvienkpi";
    }	
}
