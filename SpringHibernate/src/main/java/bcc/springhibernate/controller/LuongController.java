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

import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Luong;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.service.LoaiKhachHangService;
import bcc.springhibernate.service.LuongService;
import bcc.springhibernate.service.NhanVienService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin")
public class LuongController {

	@Autowired
	LoaiKhachHangService loaiKhachHangService;
	@Autowired
	LuongService LuongService;
	@Autowired
	NhanVienService nhanVienService;

	@GetMapping("/luong")
	String pageDanhSachLuong(@RequestParam(value="trangthai",defaultValue = "active") String trangthai,
			Model model) {
		List<Luong> listLuong = LuongService.findByTrangthaiOrderByIdDesc(trangthai);
		model.addAttribute("listLuong", listLuong);
		return "danhsachluong";
	}

	@GetMapping("/luong/add")
	String pageThemLuong(Model model) {
		List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
		model.addAttribute("listNhanvien", listNhanvien);
		model.addAttribute("luong", new Luong());
		return "themluong";
	}

	@GetMapping("/luong/{id}")
	String pageSuaLuong(Model model, @PathVariable("id") Integer id) {
		Luong luong = LuongService.findById(id);
		List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
		model.addAttribute("listNhanvien", listNhanvien);
		model.addAttribute("luong", luong);
		return "sualuong";
	}

	@PostMapping("/luong")
	String themLuong(@ModelAttribute("luong") Luong luong, @RequestParam("nhanvien") Integer nhanvien,
			@RequestParam("luong_money") String tienluong, @RequestParam("thuong_money") String thuong,
			@RequestParam("thang") String thang, @RequestParam("nam") String nam,
			@RequestParam("trangthai") String trangthai, RedirectAttributes redirectAttributes) {
		try {
			Nhanvien getNhanvienById = nhanVienService.findById(nhanvien);
			luong.setNhanvien(getNhanvienById);
			luong.setThang(thang);
			luong.setNam(nam);
			luong.setTrangthai(trangthai);
			luong.setLuong(Long.valueOf(tienluong.replace(".","")));
			luong.setThuong(Long.valueOf(thuong.replace(".","")));
			luong.setThuongcuahoadon(0L);
			LuongService.saveOrUpdate(luong);
			redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
		return "redirect:/admin/luong?trangthai=chuatraluong";
	}

	@PatchMapping(value="/luong",params="update")
	String suaLuong(@ModelAttribute("luong") Luong luong, @RequestParam("nhanvien") Integer nhanvien,
					@RequestParam("luong_money") String tienluong, @RequestParam("thuong_money") String thuong,
			@RequestParam("thang") String thang, @RequestParam("nam") String nam,
			@RequestParam("trangthai") String trangthai, RedirectAttributes redirectAttributes) {
		try {
			Nhanvien getNhanvienById = nhanVienService.findById(nhanvien);
			luong.setNhanvien(getNhanvienById);
			luong.setThang(thang);
			luong.setNam(nam);
			luong.setTrangthai(trangthai);
			luong.setLuong(Long.valueOf(tienluong.replace(".","")));
			luong.setThuong(Long.valueOf(thuong.replace(".","")));

			LuongService.saveOrUpdate(luong);
			redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}
		return "redirect:/admin/luong?trangthai=chuatraluong";
	}
	@PatchMapping(value="/luong",params="deleted")
	String xoaVinhVienLuong(@ModelAttribute("luong") Luong luong,  RedirectAttributes redirectAttributes) {
		try {
			
			LuongService.deleted(luong);
			redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
		}
		return "redirect:/admin/luong?trangthai=chuatraluong";
	}

	@DeleteMapping("/luong")
	String xoaLuong(@RequestParam("arrayId") List<Integer> arrayId, RedirectAttributes redirectAttributes) {

		try {
			arrayId.forEach(x -> {

				Luong luong = LuongService.findById(x);
				luong.setTrangthai("deleted");
				LuongService.saveOrUpdate(luong);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

		return "redirect:/admin/luong?trangthai=chuatraluong";
	}
}
