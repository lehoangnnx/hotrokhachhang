package bcc.springhibernate.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

import bcc.springhibernate.model.Chamsoc;
import bcc.springhibernate.model.Chitietchamsoc;
import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.model.Tieuchichamsoc;
import bcc.springhibernate.service.ChamSocService;
import bcc.springhibernate.service.ChiTietChamSocService;
import bcc.springhibernate.service.HoaDonService;
import bcc.springhibernate.service.KhachHangService;
import bcc.springhibernate.service.NhanVienService;
import bcc.springhibernate.service.NhomKhachHangService;
import bcc.springhibernate.service.TaikhoanService;
import bcc.springhibernate.service.TieuChiChamSocService;

@Controller
@RequestMapping("/admin")
public class ChamSocController {

	@Autowired
	TieuChiChamSocService tieuChiChamSocService;
	@Autowired
	NhanVienService nhanVienService;
	@Autowired
	KhachHangService khachHangService;
	@Autowired
	ChamSocService chamSocService;
	@Autowired
	HoaDonService hoaDonService;
	@Autowired
	TaikhoanService taikhoanService;
	@Autowired
	ChiTietChamSocService chiTietChamSocService;

	@GetMapping("/chamsoc")
	String pageDanhSachChamSoc(@RequestParam(value="trangthai", defaultValue="chochamsoc") String trangthai,
			@RequestParam(value="limit", defaultValue="100") Integer limit, 
			@RequestParam(value="page", defaultValue="1") Integer page,
			Model model) {
		int sizeListChamSoc = chamSocService.findByTrangthaiOrderByIdDesc(trangthai).size();
		int pageCount = (sizeListChamSoc / limit 
				+ (sizeListChamSoc % limit > 0 ? 1 : 0));
		List<Chamsoc> listChamsoc = chamSocService
				.findByTrangthaiOrderByIdDesc(trangthai, new PageRequest(page - 1, limit));
		model.addAttribute("listChamsoc", listChamsoc);
		System.out.println(pageCount);
		 model.addAttribute("currentpage", page);
         model.addAttribute("pagecount", pageCount);
		return "danhsachchamsoc";
	}

	@GetMapping("/chamsoc/add")
	String pageThemChamSoc(Model model) {
		List<Khachhang> listKhachhang = khachHangService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
		List<Hoadon> listHoadon = hoaDonService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Tieuchichamsoc> listTieuchichamsoc = tieuChiChamSocService.findByTrangthaiOrderByIdDesc("active");
		model.addAttribute("listTieuchichamsoc", listTieuchichamsoc);
		model.addAttribute("listHoadon", listHoadon);
		model.addAttribute("listKhachhang", listKhachhang);
		model.addAttribute("listNhanvien", listNhanvien);
		model.addAttribute("chamsoc", new Chamsoc());
		return "themchamsoc";
	}

	@GetMapping("/chamsoc/{id}")
	String pageSuaChamSoc(Model model, @PathVariable("id") Integer id) {

		Chamsoc chamsoc = chamSocService.findById(id);
		List<Chitietchamsoc> listChitietchamsoc = chiTietChamSocService.findByChamsoc(chamsoc);
		List<Khachhang> listKhachhang = khachHangService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
		List<Hoadon> listHoadon = hoaDonService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Tieuchichamsoc> listTieuchichamsoc = tieuChiChamSocService.findByTrangthaiOrderByIdDesc("active");
		model.addAttribute("listTieuchichamsoc", listTieuchichamsoc);
		model.addAttribute("listHoadon", listHoadon);
		model.addAttribute("listKhachhang", listKhachhang);
		model.addAttribute("listNhanvien", listNhanvien);
		model.addAttribute("listChitietchamsoc", listChitietchamsoc);
		model.addAttribute("chamsoc", chamsoc);
		return "suachamsoc";
	}

	@PostMapping("/chamsoc")
	String themChamSoc(@ModelAttribute("chamsoc") Chamsoc chamsoc,
			@RequestParam("nhanvienbanhang") Integer nhanvienbanhang,
			@RequestParam("nhanviengiaohang") Integer nhanviengiaohang, @RequestParam("khachhang") Integer khachhang,
			@RequestParam("hoadon") Integer hoadon, @RequestParam("ngay") Date ngay,
			@RequestParam("ngaycstiep") Date ngaycstiep,
			@RequestParam(value = "idtccs", defaultValue = "0") List<Integer> idtccs,
			@RequestParam(value = "kieutieuchitccs", defaultValue = "") List<String> kieutieuchitccs,

			RedirectAttributes redirectAttributes, Principal principal) {
		try {

			Taikhoan getTaiKhoanByUserName = taikhoanService.findByUsername(principal.getName());
			Nhanvien getNhanVienChamSocById = getTaiKhoanByUserName.getNhanvien();

			Khachhang getKhachHangById = khachHangService.findById(khachhang);

			chamsoc.setNhanvienbanhang(nhanvienbanhang);
			chamsoc.setNhanviengiaohang(nhanviengiaohang);
			chamsoc.setNhanvienchamsoc(getNhanVienChamSocById.getId());
			chamsoc.setKhachhang(getKhachHangById);
			chamsoc.setNgay(ngay);
			chamsoc.setNgaycstiep(ngaycstiep);
			chamsoc.setHoadonId(hoadon);
			chamsoc.setTrangthai("dachamsoc");
			chamSocService.saveOrUpdate(chamsoc);

			for (int i = 0; i < idtccs.size(); i++) {
				if (idtccs.get(i) != 0) {
					Tieuchichamsoc tieuchichamsoc = tieuChiChamSocService.findById(idtccs.get(i));
					Chitietchamsoc chitietchamsoc = new Chitietchamsoc();
					chitietchamsoc.setChamsoc(chamsoc);
					chitietchamsoc.setTieuchichamsoc(tieuchichamsoc);
					chitietchamsoc.setTrangthai("active");
					if (tieuchichamsoc.getKieutieuchi().equals("so")) {
						chitietchamsoc.setDiem(Integer.parseInt(kieutieuchitccs.get(i)));
					} else if (tieuchichamsoc.getKieutieuchi().equals("cokhong")) {
						System.out.println(kieutieuchitccs.get(i) + "-" + Boolean.parseBoolean(kieutieuchitccs.get(i)));
						chitietchamsoc.setCokhong(Boolean.parseBoolean(kieutieuchitccs.get(i)));
					} else if (tieuchichamsoc.getKieutieuchi().equals("tien")) {
						Long tien = Long.parseLong(kieutieuchitccs.get(i));
						chitietchamsoc.setTienchamsoc(tien);
						getKhachHangById.setSotienchamsoc(getKhachHangById.getSotienchamsoc() - tien);
						getKhachHangById.setSotiendachamsoc(getKhachHangById.getSotiendachamsoc() + tien);
					}

					chiTietChamSocService.saveOrUpdate(chitietchamsoc);
					getKhachHangById.setSolanchamsoc(getKhachHangById.getSolanchamsoc() + 1);
				}else {
					getKhachHangById.setSolandamphan(getKhachHangById.getSolandamphan() + 1);
				}
			}

			
			khachHangService.saveOrUpdate(getKhachHangById);
			redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}

		return "redirect:/admin/chamsoc";
	}

	@PatchMapping("/chamsoc")
	String suaChamSoc(@ModelAttribute("chamsoc") Chamsoc chamsoc,
			@RequestParam("nhanvienbanhang") Integer nhanvienbanhang,
			@RequestParam("nhanviengiaohang") Integer nhanviengiaohang, @RequestParam("khachhang") Integer khachhang,
			@RequestParam("hoadon") Integer hoadon, @RequestParam("ngay") Date ngay,
			@RequestParam("ngaycstiep") Date ngaycstiep, @RequestParam("idctcs") List<Integer> idctcs,
			@RequestParam("idtccs") List<Integer> idtccs,
			// @RequestParam("diemtccs") List<Integer> diemtccs,
			@RequestParam(value = "kieutieuchitccs", defaultValue = "") List<String> kieutieuchitccs,
			RedirectAttributes redirectAttributes, Principal principal) {
		try {

			Taikhoan getTaiKhoanByUserName = taikhoanService.findByUsername(principal.getName());
			Nhanvien getNhanVienChamSocById = getTaiKhoanByUserName.getNhanvien();

			Khachhang getKhachHangById = khachHangService.findById(khachhang);

			chamsoc.setNhanvienbanhang(nhanvienbanhang);
			chamsoc.setNhanviengiaohang(nhanviengiaohang);
			chamsoc.setNhanvienchamsoc(getNhanVienChamSocById.getId());
			chamsoc.setKhachhang(getKhachHangById);
			chamsoc.setNgay(ngay);
			chamsoc.setNgaycstiep(ngaycstiep);
			chamsoc.setHoadonId(hoadon);
			chamsoc.setTrangthai("dachamsoc");
			chamSocService.saveOrUpdate(chamsoc);

			for (int i = 0; i < idtccs.size(); i++) {
				if (idctcs.get(i) == 0) {
					Tieuchichamsoc tieuchichamsoc = tieuChiChamSocService.findById(idtccs.get(i));
					Chitietchamsoc chitietchamsoc = new Chitietchamsoc();
					chitietchamsoc.setChamsoc(chamsoc);
					chitietchamsoc.setTieuchichamsoc(tieuchichamsoc);
					chitietchamsoc.setTrangthai("active");
					if (tieuchichamsoc.getKieutieuchi().equals("so")) {
						chitietchamsoc.setDiem(Integer.parseInt(kieutieuchitccs.get(i)));
					} else if (tieuchichamsoc.getKieutieuchi().equals("cokhong")) {

						chitietchamsoc.setCokhong(Boolean.parseBoolean(kieutieuchitccs.get(i)));
					} else if (tieuchichamsoc.getKieutieuchi().equals("tien")) {
						Long tien = Long.parseLong(kieutieuchitccs.get(i));
						chitietchamsoc.setTienchamsoc(tien);
						getKhachHangById.setSotienchamsoc(getKhachHangById.getSotienchamsoc() - tien);
						getKhachHangById.setSotiendachamsoc(getKhachHangById.getSotiendachamsoc() + tien);

					}

					chiTietChamSocService.saveOrUpdate(chitietchamsoc);
					khachHangService.saveOrUpdate(getKhachHangById);
				} else {
					System.out.println(kieutieuchitccs);
					Tieuchichamsoc tieuchichamsoc = tieuChiChamSocService.findById(idtccs.get(i));
					Chitietchamsoc chitietchamsoc = chiTietChamSocService.findById(idctcs.get(i));
					chitietchamsoc.setChamsoc(chamsoc);
					chitietchamsoc.setTieuchichamsoc(tieuchichamsoc);
					chitietchamsoc.setTrangthai("active");
					if (tieuchichamsoc.getKieutieuchi().equals("so")) {
						chitietchamsoc.setDiem(Integer.parseInt(kieutieuchitccs.get(i)));
					} else if (tieuchichamsoc.getKieutieuchi().equals("cokhong")) {

						chitietchamsoc.setCokhong(Boolean.parseBoolean(kieutieuchitccs.get(i)));
					} else if (tieuchichamsoc.getKieutieuchi().equals("tien")) {
						Long tien = Long.parseLong(kieutieuchitccs.get(i));
						Long tienchamsoccu, tiendachamsoccu;
						tienchamsoccu = chitietchamsoc.getTienchamsoc() + getKhachHangById.getSotienchamsoc();
						tiendachamsoccu = getKhachHangById.getSotiendachamsoc() - chitietchamsoc.getTienchamsoc();
						chitietchamsoc.setTienchamsoc(tien);

						getKhachHangById.setSotienchamsoc(tienchamsoccu - tien);
						getKhachHangById.setSotiendachamsoc(tiendachamsoccu + tien);
					}

					chiTietChamSocService.saveOrUpdate(chitietchamsoc);
					khachHangService.saveOrUpdate(getKhachHangById);
				}

			}
			redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}

		return "redirect:/admin/chamsoc";
	}

	@DeleteMapping("/chamsoc")
	String xoaChamSoc(@RequestParam(value="trangthai", defaultValue="chochamsoc") String trangthai,
			@RequestParam(value="limit", defaultValue="100") Integer limit, 
			@RequestParam(value="page", defaultValue="1") Integer page,
			@RequestParam("arrayId") List<Integer> arrayId, RedirectAttributes redirectAttributes) {

		try {
			arrayId.forEach(x -> {

				Chamsoc chamsoc = chamSocService.findById(x);
				chamsoc.setTrangthai("deleted");

				chamSocService.saveOrUpdate(chamsoc);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

		return "redirect:/admin/chamsoc?trangthai="+trangthai+"&limit="+limit+"&page="+page+"";
	}
}
