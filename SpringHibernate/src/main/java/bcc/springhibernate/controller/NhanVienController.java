package bcc.springhibernate.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
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

import bcc.springhibernate.model.Bophan;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.model.Quyen;
import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.service.BoPhanService;
import bcc.springhibernate.service.NhanVienService;
import bcc.springhibernate.service.NhomHangService;
import bcc.springhibernate.service.QuyenService;
import bcc.springhibernate.service.TaikhoanService;


@Controller
@RequestMapping("/admin")
public class NhanVienController {

	@Autowired
	BoPhanService boPhanService;
	
	@Autowired
	TaikhoanService taikhoanService;
	
	@Autowired
	NhanVienService nhanVienService;
	
	@Autowired
	QuyenService quyenService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
    @GetMapping("/nhanvien")
    String pageDanhSachNhanVien(Model model){
    	List<Nhanvien> listNhanvien= nhanVienService.findByTrangthaiOrderByIdDesc("active");
    	model.addAttribute("listNhanvien", listNhanvien);
        return "danhsachnhanvien";
    }

    @GetMapping("/nhanvien/add")
    String pageThemNhanVien(Model model ){
    		List<Bophan> listBophan = boPhanService.findByTrangthaiOrderByIdDesc("active");
    		
    		model.addAttribute("listBophan", listBophan);
    		
    		model.addAttribute("nhanvien", new Nhanvien());
        return "themnhanvien";
    }
    @GetMapping("/nhanvien/{id}")
    String pageSuaNhanVien(Model model, @PathVariable("id") Integer id ){
    	Nhanvien nhanvien= nhanVienService.findById(id);
    	
		
    	List<Bophan> listBophan = boPhanService.findByTrangthaiOrderByIdDesc("active");
		
		model.addAttribute("listBophan", listBophan);
		
    		model.addAttribute("nhanvien", nhanvien);
        return "suanhanvien";
    }
   
	
    @PostMapping("/nhanvien")
    String themNhanVien(@RequestParam("manhanvien") String manhanvien,
    		@RequestParam("tennhanvien") String tennhanvien,
    		@RequestParam("socmnd") String socmnd,
    		@RequestParam("ngaycap") Date ngaycap,
    		@RequestParam("noicap") String noicap,
    		@RequestParam("sodienthoai") String sodienthoai,
    		@RequestParam("diachi") String diachi,
    		@RequestParam("ngayvaolam") Date ngayvaolam,
    		@RequestParam(value="trangthai", defaultValue="active" ) String trangthai,
    		@RequestParam("loainhanvien") Integer loainhanvien,
    		@RequestParam("thongtinkhac") String thongtinkhac,
    		@RequestParam("ghichu") String ghichu,
    		@RequestParam("bophan") Integer bophan,
    		RedirectAttributes redirectAttributes) {
    	try {
    		
    		Bophan bophanById = boPhanService.findById(bophan);
    		Nhanvien nhanvien = new Nhanvien(bophanById, manhanvien, tennhanvien, socmnd, ngaycap, noicap, sodienthoai.replace("_", ""), diachi, ngayvaolam, trangthai, loainhanvien, thongtinkhac, ghichu);
    		nhanVienService.saveOrUpdate(nhanvien);
        	redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
    	//return "redirect:/admin/nhanvien/add";
    	return "redirect:/admin/nhanvien";
    }	
    	
    @PatchMapping("/nhanvien")
    String suaNhanVien(@RequestParam("id") Integer id,
    		@RequestParam("manhanvien") String manhanvien,
    		@RequestParam("tennhanvien") String tennhanvien,
    		@RequestParam("socmnd") String socmnd,
    		@RequestParam("ngaycap") Date ngaycap,
    		@RequestParam("noicap") String noicap,
    		@RequestParam("sodienthoai") String sodienthoai,
    		@RequestParam("diachi") String diachi,
    		@RequestParam("ngayvaolam") Date ngayvaolam,
    		@RequestParam(value="trangthai", defaultValue="active" ) String trangthai,
    		@RequestParam("loainhanvien") Integer loainhanvien,
    		@RequestParam("thongtinkhac") String thongtinkhac,
    		@RequestParam("ghichu") String ghichu,
    		@RequestParam("bophan") Integer bophan,
    		RedirectAttributes redirectAttributes) {
    	try {
    		
    		Bophan bophanById = boPhanService.findById(bophan);
    		Nhanvien nhanvien = new Nhanvien(id,bophanById, manhanvien, tennhanvien, socmnd, ngaycap, noicap, sodienthoai.replace("_", ""), diachi, ngayvaolam, trangthai, loainhanvien, thongtinkhac, ghichu);
    		nhanVienService.saveOrUpdate(nhanvien);
        	redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}
    	
    	return "redirect:/admin/nhanvien";
    }	
    @DeleteMapping("/nhanvien")
    String xoaNhanVien(@RequestParam("arrayId") List<Integer> arrayId,
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

    	return "redirect:/admin/nhanvien";
    }	
}
