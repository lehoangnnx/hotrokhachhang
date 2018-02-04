package bcc.springhibernate.controller;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.service.HoaDonService;
import bcc.springhibernate.service.KhachHangService;
import bcc.springhibernate.service.LoaiKhachHangService;
import bcc.springhibernate.service.NhomHangService;
import bcc.springhibernate.service.NhomKhachHangService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@PreAuthorize("hasAnyRole('ADMIN','BANHANG','CHAMSOC')")
@RequestMapping("/admin")
public class KhachHangController {

	@Autowired
	LoaiKhachHangService loaiKhachHangService;

	@Autowired
	NhomKhachHangService nhomKhachHangService;
	@Autowired
	KhachHangService khachHangService;
	@Autowired
	HoaDonService hoaDonService;

	@GetMapping("/khachhang")
	String pageDanhSachKhachHangg(@RequestParam(value = "trangthai", defaultValue = "active") String trangthai,
			@RequestParam(value = "limit", defaultValue = "100") Integer limit,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "loaikhachhang", defaultValue = "0") Integer loaikhachhang,
			@RequestParam(value = "nhomkhachhang", defaultValue = "0") Integer nhomkhachhang, Model model) {

		List<Khachhang> listKhachhang = null;

		int pageCount = 0;

		if (loaikhachhang != 0 && nhomkhachhang != 0) {
			Loaikhachhang getLoaiKhachHangById = loaiKhachHangService.findById(loaikhachhang);
			Nhomkhachhang getNhomKhachHangById = nhomKhachHangService.findById(nhomkhachhang);

			int sizeListKhachHang = khachHangService.findByLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc(
					getLoaiKhachHangById, getNhomKhachHangById, trangthai).size();

			pageCount = (sizeListKhachHang / limit + (sizeListKhachHang % limit > 0 ? 1 : 0));

			listKhachhang = khachHangService.findByLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc(
					getLoaiKhachHangById, getNhomKhachHangById, trangthai, new PageRequest(page - 1, limit));
		} else if (loaikhachhang != 0) {
			Loaikhachhang getLoaiKhachHangById = loaiKhachHangService.findById(loaikhachhang);
			int sizeListKhachHang = khachHangService
					.findByLoaikhachhangAndTrangthaiOrderByIdDesc(getLoaiKhachHangById, trangthai).size();
			pageCount = (sizeListKhachHang / limit + (sizeListKhachHang % limit > 0 ? 1 : 0));
			listKhachhang = khachHangService.findByLoaikhachhangAndTrangthaiOrderByIdDesc(getLoaiKhachHangById,
					trangthai, new PageRequest(page - 1, limit));
		} else if (nhomkhachhang != 0) {
			Nhomkhachhang getNhomKhachHangById = nhomKhachHangService.findById(nhomkhachhang);
			int sizeListKhachHang = khachHangService
					.findByNhomkhachhangAndTrangthaiOrderByIdDesc(getNhomKhachHangById, trangthai).size();
			pageCount = (sizeListKhachHang / limit + (sizeListKhachHang % limit > 0 ? 1 : 0));
			listKhachhang = khachHangService.findByNhomkhachhangAndTrangthaiOrderByIdDesc(getNhomKhachHangById,
					trangthai, new PageRequest(page - 1, limit));
		} else {
			int sizeListKhachHang = khachHangService.findByTrangthaiOrderByIdDesc(trangthai).size();
			pageCount = (sizeListKhachHang / limit + (sizeListKhachHang % limit > 0 ? 1 : 0));
			listKhachhang = khachHangService.findByTrangthaiOrderByIdDesc(trangthai, new PageRequest(page - 1, limit));
		}
		List<Loaikhachhang> listLoaikhachhang = loaiKhachHangService.findByTrangthaiOrderByIdDesc("active");
		List<Nhomkhachhang> listNhomkhachhang = nhomKhachHangService.findByTrangthaiOrderByIdDesc("active");
		model.addAttribute("listLoaikhachhang", listLoaikhachhang);
		model.addAttribute("listNhomkhachhang", listNhomkhachhang);
		listKhachhang.sort(Comparator.comparing(Khachhang::getUutien));

		model.addAttribute("listKhachhang", listKhachhang);

		model.addAttribute("currentpage", page);
		model.addAttribute("pagecount", pageCount);
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
	String themKhachHang(// @ModelAttribute("khachhang") Khachhang khachhang,
			@RequestParam(value = "loaikhachhang") Integer loaikhachhang,
			@RequestParam("nhomkhachhang") Integer nhomkhachhang,
			@RequestParam(value = "makh", defaultValue = "null") String makh,
			@RequestParam(value = "ten", defaultValue = "null") String ten,
			@RequestParam(value = "manganhnghe", defaultValue = "0") Integer manganhnghe,
			@RequestParam(value = "sotaikhoan", defaultValue = "null") String sotaikhoan,
			@RequestParam(value = "diachi", defaultValue = "null") String diachi,
			@RequestParam(value = "sodienthoai", defaultValue = "null") String sodienthoai,
			@RequestParam(value = "tendaidien", defaultValue = "null") String tendaidien,
			@RequestParam(value = "dienthoaidaidien", defaultValue = "null") String dienthoaidaidien,
			@RequestParam(value = "ngaysinhnhatndd", defaultValue = "null") String ngaysinhnhatndd,
			@RequestParam(value = "tenphutrach", defaultValue = "null") String tenphutrach,
			@RequestParam(value = "dienthoaiphutrach", defaultValue = "null") String dienthoaiphutrach,
			@RequestParam(value = "ngaysinhphutrach", defaultValue = "null") String ngaysinhphutrach,
			@RequestParam(value = "sogpkd", defaultValue = "null") String sogpkd,
			@RequestParam(value = "noicap", defaultValue = "null") String noicap,
			@RequestParam(value = "ngaycap", defaultValue = "null") String ngaycap,
			@RequestParam(value = "sotienchamsoc", defaultValue = "0") Long sotienchamsoc,
			@RequestParam(value = "sotiendachamsoc", defaultValue = "0") Long sotiendachamsoc,
			@RequestParam(value = "diem", defaultValue = "0") Integer diem,
			@RequestParam(value = "solanchamsoc", defaultValue = "0") Integer solanchamsoc,
			@RequestParam(value = "solandamphan", defaultValue = "0") Integer solandamphan,
			@RequestParam(value = "diemtiemnang", defaultValue = "0") Integer diemtiemnang,
			@RequestParam(value = "uutien", defaultValue = "khong") String uutien,
			@RequestParam(value = "ghichu", defaultValue = "null") String ghichu,
			RedirectAttributes redirectAttributes) {
		try {
			System.out.println(ngaysinhnhatndd + "-" + ngaysinhphutrach + manganhnghe);
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Loaikhachhang getLoaiKhachHangById = loaiKhachHangService.findById(loaikhachhang);
			Nhomkhachhang getNhomKhachHangById = nhomKhachHangService.findById(nhomkhachhang);
			Khachhang khachhang = new Khachhang();
			khachhang.setLoaikhachhang(getLoaiKhachHangById);
			khachhang.setNhomkhachhang(getNhomKhachHangById);
			khachhang.setMakh(makh);
			khachhang.setTen(ten);
			if (manganhnghe != 0) {
				khachhang.setManganhnghe(manganhnghe);
			}
			if (!sotaikhoan.equals("null")) {
				khachhang.setSotaikhoan(sotaikhoan);
			}

			if (!diachi.equals("null")) {
				khachhang.setDiachi(diachi);
			}

			if (!sodienthoai.equals("null")) {
				khachhang.setSodienthoai(sodienthoai);
			}
			if (!tendaidien.equals("null")) {
				khachhang.setTendaidien(tendaidien);
			}
			if (!dienthoaidaidien.equals("null")) {
				khachhang.setDienthoaidaidien(dienthoaidaidien);
			}
			if (!ngaysinhnhatndd.equals("null")) {
				khachhang.setNgaysinhnhatndd(df.parse(ngaysinhnhatndd));
			}

			if (!tenphutrach.equals("null")) {
				khachhang.setTenphutrach(tenphutrach);
			}
			if (!dienthoaiphutrach.equals("null")) {
				khachhang.setDienthoaiphutrach(dienthoaiphutrach);
			}
			if (!ngaysinhphutrach.equals("null")) {
				khachhang.setNgaysinhphutrach(df.parse(ngaysinhphutrach));
			}
			if (!sogpkd.equals("null")) {
				khachhang.setSogpkd(sogpkd);

			}
			if (!noicap.equals("null")) {
				khachhang.setNoicap(noicap);
			}
			if (!ngaycap.equals("null")) {
				khachhang.setNgaycap(df.parse(ngaycap));
			}
			khachhang.setSotienchamsoc(sotienchamsoc);
			khachhang.setSotiendachamsoc(sotiendachamsoc);
			khachhang.setDiem(diem);
			khachhang.setSolanchamsoc(solanchamsoc);
			khachhang.setSolandamphan(solandamphan);
			khachhang.setDiemtiemnang(diemtiemnang);
			if (!ghichu.equals("null")) {
				khachhang.setGhichu(ghichu);
			}
			khachhang.setUutien(uutien);
			khachhang.setTrangthai("active");
			khachhang.setTrangthainhac("chosinhnhat");
			khachHangService.saveOrUpdate(khachhang);
			redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
		return "redirect:/admin/khachhang?trangthai=active&loaikhachhang=0&nhomkhachhang=0&limit=100&page=1";
	}

	@PatchMapping(value = "/khachhang", params = "update")
	String suaKhachHang(// @ModelAttribute("khachhang") Khachhang khachhang,
			@RequestParam("id") Integer id, @RequestParam(value = "loaikhachhang") Integer loaikhachhang,
			@RequestParam("nhomkhachhang") Integer nhomkhachhang,
			@RequestParam(value = "makh", defaultValue = "null") String makh,
			@RequestParam(value = "ten", defaultValue = "null") String ten,
			@RequestParam(value = "manganhnghe", defaultValue = "0") Integer manganhnghe,
			@RequestParam(value = "sotaikhoan", defaultValue = "null") String sotaikhoan,
			@RequestParam(value = "diachi", defaultValue = "null") String diachi,
			@RequestParam(value = "sodienthoai", defaultValue = "null") String sodienthoai,
			@RequestParam(value = "tendaidien", defaultValue = "null") String tendaidien,
			@RequestParam(value = "dienthoaidaidien", defaultValue = "null") String dienthoaidaidien,
			@RequestParam(value = "ngaysinhnhatndd", defaultValue = "null") String ngaysinhnhatndd,
			@RequestParam(value = "tenphutrach", defaultValue = "null") String tenphutrach,
			@RequestParam(value = "dienthoaiphutrach", defaultValue = "null") String dienthoaiphutrach,
			@RequestParam(value = "ngaysinhphutrach", defaultValue = "null") String ngaysinhphutrach,
			@RequestParam(value = "sogpkd", defaultValue = "null") String sogpkd,
			@RequestParam(value = "noicap", defaultValue = "null") String noicap,
			@RequestParam(value = "ngaycap", defaultValue = "null") String ngaycap,
			@RequestParam(value = "sotienchamsoc", defaultValue = "0") Long sotienchamsoc,
			@RequestParam(value = "sotiendachamsoc", defaultValue = "0") Long sotiendachamsoc,
			@RequestParam(value = "diem", defaultValue = "0") Integer diem,
			@RequestParam(value = "solanchamsoc", defaultValue = "0") Integer solanchamsoc,
			@RequestParam(value = "solandamphan", defaultValue = "0") Integer solandamphan,
			@RequestParam(value = "diemtiemnang", defaultValue = "0") Integer diemtiemnang,
			@RequestParam(value = "uutien", defaultValue = "khong") String uutien,
			@RequestParam(value = "ghichu", defaultValue = "null") String ghichu,
			RedirectAttributes redirectAttributes) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Loaikhachhang getLoaiKhachHangById = loaiKhachHangService.findById(loaikhachhang);
			Nhomkhachhang getNhomKhachHangById = nhomKhachHangService.findById(nhomkhachhang);
			Khachhang khachhang = khachHangService.findById(id);
			khachhang.setLoaikhachhang(getLoaiKhachHangById);
			khachhang.setNhomkhachhang(getNhomKhachHangById);
			khachhang.setMakh(makh);
			khachhang.setTen(ten);
			if (manganhnghe != 0) {
				khachhang.setManganhnghe(manganhnghe);
			}
			if (!sotaikhoan.equals("null")) {
				khachhang.setSotaikhoan(sotaikhoan);
			}

			if (!diachi.equals("null")) {
				khachhang.setDiachi(diachi);
			}

			if (!sodienthoai.equals("null")) {
				khachhang.setSodienthoai(sodienthoai);
			}
			if (!tendaidien.equals("null")) {
				khachhang.setTendaidien(tendaidien);
			}
			if (!dienthoaidaidien.equals("null")) {
				khachhang.setDienthoaidaidien(dienthoaidaidien);
			}
			if (!ngaysinhnhatndd.equals("null")) {
				khachhang.setNgaysinhnhatndd(df.parse(ngaysinhnhatndd));
			}

			if (!tenphutrach.equals("null")) {
				khachhang.setTenphutrach(tenphutrach);
			}
			if (!dienthoaiphutrach.equals("null")) {
				khachhang.setDienthoaiphutrach(dienthoaiphutrach);
			}
			if (!ngaysinhphutrach.equals("null")) {
				khachhang.setNgaysinhphutrach(df.parse(ngaysinhphutrach));
			}
			if (!sogpkd.equals("null")) {
				khachhang.setSogpkd(sogpkd);

			}
			if (!noicap.equals("null")) {
				khachhang.setNoicap(noicap);
			}
			if (!ngaycap.equals("null")) {
				khachhang.setNgaycap(df.parse(ngaycap));
			}
			khachhang.setSotienchamsoc(sotienchamsoc);
			khachhang.setSotiendachamsoc(sotiendachamsoc);
			khachhang.setDiem(diem);
			khachhang.setSolanchamsoc(solanchamsoc);
			khachhang.setSolandamphan(solandamphan);
			khachhang.setDiemtiemnang(diemtiemnang);
			if (!ghichu.equals("null")) {
				khachhang.setGhichu(ghichu);
			}
			khachhang.setUutien(uutien);
			khachhang.setTrangthai("active");
			khachhang.setTrangthainhac("chosinhnhat");
			khachHangService.saveOrUpdate(khachhang);
			redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}
		return "redirect:/admin/khachhang?trangthai=active&loaikhachhang=0&nhomkhachhang=0&limit=100&page=1";
	}

	@PatchMapping(value = "/khachhang", params = "deleted")
	String xoaVinhVienKhachHang(// @ModelAttribute("khachhang") Khachhang khachhang,
			@RequestParam("id") Integer id,

			RedirectAttributes redirectAttributes) {
		List<Hoadon> hoadons = null;
		try {

			Khachhang khachhang = khachHangService.findById(id);
			hoadons = hoaDonService.findByKhachhang(khachhang);

			if (!hoadons.isEmpty()) {
				khachHangService.deleted(khachhang);
				redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
		}
		return "redirect:/admin/khachhang?trangthai=active&loaikhachhang=0&nhomkhachhang=0&limit=100&page=1";
	}

	@DeleteMapping("/khachhang")
	String xoaKhachHang(@RequestParam(value = "trangthai", defaultValue = "active") String trangthai,
			@RequestParam(value = "limit", defaultValue = "100") Integer limit,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "loaikhachhang", defaultValue = "0") Integer loaikhachhang,
			@RequestParam(value = "nhomkhachhang", defaultValue = "0") Integer nhomkhachhang,
			@RequestParam("arrayId") List<Integer> arrayId, RedirectAttributes redirectAttributes) {

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

		return "redirect:/admin/khachhang?trangthai=active&loaikhachhang=0&nhomkhachhang=0&limit=100&page=1";
	}

	/*
	 * @GetMapping("/timkiemkhachhang") String timKiemKhachHang(@RequestParam(value
	 * = "trangthai", defaultValue = "chochamsoc") String trangthai,
	 * 
	 * @RequestParam(value = "limit", defaultValue = "100") Integer limit,
	 * 
	 * @RequestParam(value = "page", defaultValue = "1") Integer page,
	 * 
	 * @RequestParam(value = "loaikhachhang", defaultValue = "0") Integer
	 * loaikhachhang,
	 * 
	 * @RequestParam(value = "nhomkhachhang", defaultValue = "0") Integer
	 * nhomkhachhang, Model model) { List<Khachhang> listKhachhang = null;
	 * 
	 * int pageCount = 0;
	 * 
	 * if (loaikhachhang != 0 && nhomkhachhang != 0) { Loaikhachhang
	 * getLoaiKhachHangById = loaiKhachHangService.findById(loaikhachhang);
	 * Nhomkhachhang getNhomKhachHangById =
	 * nhomKhachHangService.findById(nhomkhachhang);
	 * 
	 * int sizeListKhachHang = khachHangService.
	 * findByLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc(
	 * getLoaiKhachHangById, getNhomKhachHangById, trangthai).size();
	 * 
	 * pageCount = (sizeListKhachHang / limit + sizeListKhachHang % limit > 0 ? 1 :
	 * 0);
	 * 
	 * listKhachhang = khachHangService.
	 * findByLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc(
	 * getLoaiKhachHangById, getNhomKhachHangById, trangthai, new PageRequest(page -
	 * 1, limit)); } else if (loaikhachhang != 0) { Loaikhachhang
	 * getLoaiKhachHangById = loaiKhachHangService.findById(loaikhachhang); int
	 * sizeListKhachHang = khachHangService
	 * .findByLoaikhachhangAndTrangthaiOrderByIdDesc(getLoaiKhachHangById,
	 * trangthai).size(); pageCount = (sizeListKhachHang / limit + sizeListKhachHang
	 * % limit > 0 ? 1 : 0); listKhachhang =
	 * khachHangService.findByLoaikhachhangAndTrangthaiOrderByIdDesc(
	 * getLoaiKhachHangById, trangthai, new PageRequest(page - 1, limit)); } else if
	 * (nhomkhachhang != 0) { Nhomkhachhang getNhomKhachHangById =
	 * nhomKhachHangService.findById(nhomkhachhang); int sizeListKhachHang =
	 * khachHangService
	 * .findByNhomkhachhangAndTrangthaiOrderByIdDesc(getNhomKhachHangById,
	 * trangthai).size(); pageCount = (sizeListKhachHang / limit + sizeListKhachHang
	 * % limit > 0 ? 1 : 0); listKhachhang =
	 * khachHangService.findByNhomkhachhangAndTrangthaiOrderByIdDesc(
	 * getNhomKhachHangById, trangthai, new PageRequest(page - 1, limit)); } else {
	 * int sizeListKhachHang =
	 * khachHangService.findByTrangthaiOrderByIdDesc(trangthai).size(); pageCount =
	 * (sizeListKhachHang / limit + sizeListKhachHang % limit > 0 ? 1 : 0);
	 * listKhachhang = khachHangService.findByTrangthaiOrderByIdDesc(trangthai, new
	 * PageRequest(page - 1, limit)); }
	 * 
	 * List<Loaikhachhang> listLoaikhachhang =
	 * loaiKhachHangService.findByTrangthaiOrderByIdDesc("active");
	 * List<Nhomkhachhang> listNhomkhachhang =
	 * nhomKhachHangService.findByTrangthaiOrderByIdDesc("active");
	 * model.addAttribute("listLoaikhachhang", listLoaikhachhang);
	 * model.addAttribute("listNhomkhachhang", listNhomkhachhang);
	 * model.addAttribute("listKhachhang", listKhachhang);
	 * 
	 * model.addAttribute("currentpage", page); model.addAttribute("pagecount",
	 * pageCount); return "danhsachkhachhang"; }
	 */
}
