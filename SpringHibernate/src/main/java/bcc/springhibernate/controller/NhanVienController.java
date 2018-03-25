package bcc.springhibernate.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.Luong;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhanvienkpi;
import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.model.Quyen;
import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.service.BoPhanService;
import bcc.springhibernate.service.HoaDonService;
import bcc.springhibernate.service.LuongService;
import bcc.springhibernate.service.NhanVienKpiService;
import bcc.springhibernate.service.NhanVienService;
import bcc.springhibernate.service.NhomHangService;
import bcc.springhibernate.service.QuyenService;
import bcc.springhibernate.service.TaikhoanService;

@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
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
	@Autowired
	LuongService luongService;
	@Autowired
	NhanVienKpiService nhanVienKpiService;
	@Autowired
	HoaDonService hoaDonService;

	@GetMapping("/nhanvien")
	String pageDanhSachNhanVien(@RequestParam(value = "trangthai", defaultValue = "active") String trangthai,
			Model model) {
		List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc(trangthai);
		model.addAttribute("listNhanvien", listNhanvien);
		return "danhsachnhanvien";
	}

	@GetMapping("/nhanvien/add")
	String pageThemNhanVien(Model model) {
		List<Bophan> listBophan = boPhanService.findByTrangthaiOrderByIdDesc("active");
		List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
		model.addAttribute("listBophan", listBophan);
		model.addAttribute("listNhanvien", listNhanvien);
		model.addAttribute("nhanvien", new Nhanvien());
		return "themnhanvien";
	}

	@GetMapping("/nhanvien/{id}")
	String pageSuaNhanVien(Model model, @PathVariable("id") Integer id) {
		Nhanvien nhanvien = nhanVienService.findById(id);
		List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
		List<Bophan> listBophan = boPhanService.findByTrangthaiOrderByIdDesc("active");

		model.addAttribute("listBophan", listBophan);
		model.addAttribute("listNhanvien", listNhanvien);
		model.addAttribute("nhanvien", nhanvien);
		return "suanhanvien";
	}

	@PostMapping("/nhanvien")
	String themNhanVien(@ModelAttribute("nhanvien") Nhanvien nhanvien,
						@RequestParam("hienthiluong") String hienthiluong,
			@RequestParam("socmnd") String socmnd, @RequestParam("ngaycap") String ngaycap,
						@RequestParam("nhanviencaptren") Integer nhanviencaptren,
			@RequestParam("sodienthoai") String sodienthoai,

			@RequestParam("ngayvaolam") String ngayvaolam,
			@RequestParam("luong_money") String luong,
			@RequestParam("bophan") Integer bophan, RedirectAttributes redirectAttributes) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Bophan bophanById = boPhanService.findById(bophan);
			nhanvien.setSocmnd(socmnd);
			nhanvien.setNgaycap(df.parse(ngaycap));
			nhanvien.setSodienthoai(sodienthoai.replace("_", ""));
			nhanvien.setNgayvaolam(df.parse(ngayvaolam));
			nhanvien.setBophan(bophanById);
			nhanvien.setHienthiluong(hienthiluong);
			nhanvien.setIdnhanviencaptren(nhanviencaptren);
			nhanvien.setTrangthai("active");
			nhanvien.setLuong(Long.valueOf(luong.replaceAll("\\.|\\,|\\s","")));
			nhanVienService.saveOrUpdate(nhanvien);
			redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
		// return "redirect:/admin/nhanvien/add";
		return "redirect:/admin/nhanvien?trangthai=active";
	}

	@PatchMapping(value = "/nhanvien", params = "update")
	String suaNhanVien(@ModelAttribute("nhanvien") Nhanvien nhanvien,
					   @RequestParam("hienthiluong") String hienthiluong,
			@RequestParam("socmnd") String socmnd, @RequestParam("ngaycap") String ngaycap,
					   @RequestParam("nhanviencaptren") Integer nhanviencaptren,
			@RequestParam("sodienthoai") String sodienthoai,

			@RequestParam("ngayvaolam") String ngayvaolam,
					   @RequestParam("luong_money") String luong,
			@RequestParam("bophan") Integer bophan, RedirectAttributes redirectAttributes) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Bophan bophanById = boPhanService.findById(bophan);
			nhanvien.setSocmnd(socmnd);
			nhanvien.setNgaycap(df.parse(ngaycap));
			nhanvien.setSodienthoai(sodienthoai.replace("_", ""));
			nhanvien.setNgayvaolam(df.parse(ngayvaolam));
			nhanvien.setBophan(bophanById);
			nhanvien.setHienthiluong(hienthiluong);
			nhanvien.setIdnhanviencaptren(nhanviencaptren);
			//nhanvien.setTrangthai("active");
			nhanvien.setLuong(Long.valueOf(luong.replaceAll("\\.|\\,|\\s","")));
			nhanVienService.saveOrUpdate(nhanvien);
			redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}

		return "redirect:/admin/nhanvien?trangthai=active";
	}

	@PatchMapping(value = "/nhanvien", params = "deleted")
	String xoaVinhVienNhanVien(@ModelAttribute("nhanvien") Nhanvien nhanvien, RedirectAttributes redirectAttributes) {
		List<Luong> luongs = null;
		List<Nhanvienkpi> nhanvienkpis = null;
		List<Hoadon> hoadons = null;
		try {
			luongs = luongService.findByNhanvien(nhanvien);
			nhanvienkpis = nhanVienKpiService.findByNhanvien(nhanvien);
			hoadons = hoaDonService
					.findByNhanvienByIdnhanvienlaphoadonOrNhanvienByIdnhanvienbanOrNhanvienByIdnhanviengiaohangOrNhanvienByIdnhanvienchamsoc(
							nhanvien, nhanvien, nhanvien, nhanvien);
			if (luongs.isEmpty() && nhanvienkpis.isEmpty() && hoadons.isEmpty()) {
				nhanVienService.deleted(nhanvien);
				redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn  Thất Bại");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn  Thất Bại");
		}

		return "redirect:/admin/nhanvien?trangthai=active";
	}

	@DeleteMapping("/nhanvien")
	String xoaNhanVien(@RequestParam("arrayId") List<Integer> arrayId, RedirectAttributes redirectAttributes) {

		try {
			arrayId.forEach(x -> {

				Nhanvien nhanvien = nhanVienService.findById(x);
				nhanvien.setTrangthai("deleted");
				nhanVienService.saveOrUpdate(nhanvien);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

		return "redirect:/admin/nhanvien?trangthai=active";
	}
}
