package bcc.springhibernate.controller;

import java.util.Date;
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

import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.service.KhachHangService;
import bcc.springhibernate.service.LoaiKhachHangService;
import bcc.springhibernate.service.NhomHangService;
import bcc.springhibernate.service.NhomKhachHangService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class KhachHangController {

	@Autowired
	LoaiKhachHangService loaiKhachHangService;

	@Autowired
	NhomKhachHangService nhomKhachHangService;
	@Autowired
	KhachHangService khachHangService;

	@GetMapping("/khachhang")
	String pageDanhSachKhachHangg(Model model) {
		List<Khachhang> listKhachhang = khachHangService.findByTrangthaiOrderByIdDesc("active");

		model.addAttribute("listKhachhang", listKhachhang);

		return "danhsachkhachhang";
	}

	@GetMapping("/khachhang/add")
	String pageThemKhachHangg(Model model) {
		List<Loaikhachhang> listLoaikhachhang = loaiKhachHangService.findByTrangthaiOrderByIdDesc("active");
		List<Nhomkhachhang> listNhomkhachhang = nhomKhachHangService.findByTrangthaiOrderByIdDesc("active");
		model.addAttribute("listLoaikhachhang", listLoaikhachhang);
		model.addAttribute("listNhomkhachhang", listNhomkhachhang);
		model.addAttribute("khachhang", new Khachhang());
		return "themkhachhang";
	}

	@GetMapping("/khachhang/{id}")
	String pageSuaKhachHangg(Model model, @PathVariable("id") Integer id) {
		List<Loaikhachhang> listLoaikhachhang = loaiKhachHangService.findByTrangthaiOrderByIdDesc("active");
		List<Nhomkhachhang> listNhomkhachhang = nhomKhachHangService.findByTrangthaiOrderByIdDesc("active");
		Khachhang khachhang = khachHangService.findById(id);
		model.addAttribute("listLoaikhachhang", listLoaikhachhang);
		model.addAttribute("listNhomkhachhang", listNhomkhachhang);
		model.addAttribute("khachhang", khachhang);
		return "suakhachhang";
	}

	@PostMapping("/khachhang")
	String themKhachHang(@ModelAttribute("khachhang") Khachhang khachhang,
			@RequestParam("loaikhachhang") Integer loaikhachhang, @RequestParam("nhomkhachhang") Integer nhomkhachhang,
			@RequestParam("sodienthoai") String sodienthoai, @RequestParam("dienthoaidaidien") String dienthoaidaidien,
			@RequestParam("ngaysinhnhatndd") Date ngaysinhnhatndd,
			@RequestParam("dienthoaiphutrach") String dienthoaiphutrach,
			@RequestParam("ngaysinhphutrach") Date ngaysinhphutrach, @RequestParam("ngaycap") Date ngaycap,
			RedirectAttributes redirectAttributes) {
		try {

			Loaikhachhang getLoaiKhachHangById = loaiKhachHangService.findById(loaikhachhang);
			Nhomkhachhang getNhomKhachHangById = nhomKhachHangService.findById(nhomkhachhang);
			khachhang.setLoaikhachhang(getLoaiKhachHangById);
			khachhang.setNhomkhachhang(getNhomKhachHangById);
			khachhang.setSodienthoai(sodienthoai);
			khachhang.setDienthoaidaidien(dienthoaidaidien);
			khachhang.setNgaysinhnhatndd(ngaysinhnhatndd);
			khachhang.setDienthoaiphutrach(dienthoaiphutrach);
			khachhang.setNgaysinhphutrach(ngaysinhphutrach);
			khachhang.setNgaycap(ngaycap);
			khachhang.setTrangthai("active");
			khachhang.setTrangthainhac("chosinhnhat");
			khachHangService.saveOrUpdate(khachhang);
			redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
		return "redirect:/admin/khachhang";
	}

	@PatchMapping("/khachhang")
	String suaKhachHang(@ModelAttribute("khachhang") Khachhang khachhang,
			@RequestParam("loaikhachhang") Integer loaikhachhang, @RequestParam("nhomkhachhang") Integer nhomkhachhang,
			@RequestParam("sodienthoai") String sodienthoai, @RequestParam("dienthoaidaidien") String dienthoaidaidien,
			@RequestParam("ngaysinhnhatndd") Date ngaysinhnhatndd,
			@RequestParam("dienthoaiphutrach") String dienthoaiphutrach,
			@RequestParam("ngaysinhphutrach") Date ngaysinhphutrach, @RequestParam("ngaycap") Date ngaycap,
			RedirectAttributes redirectAttributes) {
		try {
			Loaikhachhang getLoaiKhachHangById = loaiKhachHangService.findById(loaikhachhang);
			Nhomkhachhang getNhomKhachHangById = nhomKhachHangService.findById(nhomkhachhang);
			khachhang.setLoaikhachhang(getLoaiKhachHangById);
			khachhang.setNhomkhachhang(getNhomKhachHangById);
			khachhang.setSodienthoai(sodienthoai);
			khachhang.setDienthoaidaidien(dienthoaidaidien);
			khachhang.setNgaysinhnhatndd(ngaysinhnhatndd);
			khachhang.setDienthoaiphutrach(dienthoaiphutrach);
			khachhang.setNgaysinhphutrach(ngaysinhphutrach);
			khachhang.setNgaycap(ngaycap);
			khachhang.setTrangthai("active");
			khachhang.setTrangthainhac("chosinhnhat");
			khachHangService.saveOrUpdate(khachhang);
			redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}
		return "redirect:/admin/khachhang";
	}

	@DeleteMapping("/khachhang")
	String xoaKhachHang(@RequestParam("arrayId") List<Integer> arrayId, RedirectAttributes redirectAttributes) {

		try {
			arrayId.forEach(x -> {

				Khachhang khachhang = khachHangService.findById(x);
				khachhang.setTrangthai("deleted");
				khachHangService.saveOrUpdate(khachhang);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

		return "redirect:/admin/khachhang";
	}
}
