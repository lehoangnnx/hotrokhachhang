package bcc.springhibernate.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import bcc.springhibernate.model.Bophan;
import bcc.springhibernate.model.Chitiethoadon;
import bcc.springhibernate.model.Hanghoa;
import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Luong;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.service.ChiTietHoaDonService;
import bcc.springhibernate.service.HangHoaService;
import bcc.springhibernate.service.HoaDonService;
import bcc.springhibernate.service.KhachHangService;
import bcc.springhibernate.service.LuongService;
import bcc.springhibernate.service.NhanVienService;
import bcc.springhibernate.service.TaikhoanService;

@Controller
@RequestMapping("/admin")
public class HoaDonController {

	@Autowired
	HoaDonService hoaDonService;
	@Autowired
	HangHoaService hangHoaService;
	@Autowired
	NhanVienService nhanVienService;
	@Autowired
	KhachHangService khachHangService;
	@Autowired
	TaikhoanService taikhoanService;
	@Autowired
	ChiTietHoaDonService chiTietHoaDonService;
	@Autowired
	LuongService luongService;

	@GetMapping("/hoadon")
	String pageDanhSachHoaDon(@RequestParam(value = "trangthai", defaultValue = "dathanhtoan") String trangthai,
			@RequestParam(value = "limit", defaultValue = "100") Integer limit,
			@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
		int pageCount = 0;
		List<Hoadon> listHoadon = null;
		if (trangthai.equals("deleted")) {
			int listHoaDonSize = hoaDonService.findByTrangthaiOrderByIdDesc("deleted").size();
			pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);
			listHoadon = hoaDonService.findByTrangthaiOrderByIdDesc("deleted", new PageRequest(page - 1, limit));

		} else if (trangthai.equals("chuathanhtoan")) {
			int listHoaDonSize = hoaDonService.findByTrangthaiChuaThanhToan("deleted").size();
			pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);
			listHoadon = hoaDonService.findByTrangthaiChuaThanhToan("deleted", new PageRequest(page - 1, limit));
		} else {
			int listHoaDonSize = hoaDonService.findByTrangthaiDaThanhToan("deleted").size();
			pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);
			listHoadon = hoaDonService.findByTrangthaiDaThanhToan("deleted", new PageRequest(page - 1, limit));
		}

		model.addAttribute("listHoadon", listHoadon);
		model.addAttribute("currentpage", page);
		model.addAttribute("pagecount", pageCount);
		return "danhsachhoadon";
	}

	@GetMapping("/hoadon/add")
	String pageThemHoaDon(Model model) {

		List<Hanghoa> listHanghoa = hangHoaService.findByTrangthaiOrderByIdDesc("active");
		List<Khachhang> listKhachhang = khachHangService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
		model.addAttribute("listHanghoa", listHanghoa);
		model.addAttribute("listKhachhang", listKhachhang);
		model.addAttribute("listNhanvien", listNhanvien);
		model.addAttribute("hoadon", new Hoadon());
		return "themhoadon";
	}

	@GetMapping("/hoadon/{id}")
	String pageSuaHoaDon(@PathVariable("id") Integer id, Model model) {

		Hoadon hoadon = hoaDonService.findById(id);
		List<Chitiethoadon> listChitiethoadon = chiTietHoaDonService.findByHoadonAndTrangthaiOrderByIdDesc(hoadon,
				"active");
		List<Hanghoa> listHanghoa = hangHoaService.findByTrangthaiOrderByIdDesc("active");
		List<Khachhang> listKhachhang = khachHangService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
		model.addAttribute("listHanghoa", listHanghoa);
		model.addAttribute("listKhachhang", listKhachhang);
		model.addAttribute("listNhanvien", listNhanvien);

		model.addAttribute("listChitiethoadon", listChitiethoadon);
		model.addAttribute("hoadon", hoadon);
		return "suahoadon";
	}

	@PostMapping("/hoadon")
	String themHoaDon(@ModelAttribute("hoadon") Hoadon hoadon, @RequestParam("nhanvienbanhang") Integer nhanvienbanhang,
			@RequestParam("nhanviengiaohang") Integer nhanviengiaohang,
			@RequestParam("nhanvienchamsoc") Integer nhanvienchamsoc, @RequestParam("khachhang") Integer khachhang,
			@RequestParam("hinhthucthanhtoan") String hinhthucthanhtoan, @RequestParam("ngaylap") String ngaylap,
			@RequestParam("ngayxuat") String ngayxuat, @RequestParam("ngaythanhtoan") String ngaythanhtoan,
			@RequestParam("sodienthoai") String sodienthoai, @RequestParam("trangthai") String trangthai,
			@RequestParam("idhh") List<Integer> idhh, @RequestParam("soluonghh") List<Integer> soluonghh,
			@RequestParam("giabanhh") List<Long> giabanhh, @RequestParam("thanhtienhh") List<Long> thanhtienhh,
			RedirectAttributes redirectAttributes, Principal principal) {

		try {

			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Nhanvien getNhanVienBanHangById = nhanVienService.findById(nhanvienbanhang);
			Nhanvien getNhanVienChamSocById = nhanVienService.findById(nhanvienchamsoc);
			Nhanvien getNhanVienGiaoHangById = nhanVienService.findById(nhanviengiaohang);
			Taikhoan getTaiKhoanByUserName = taikhoanService.findByUsername(principal.getName());
			Nhanvien getNhanVienLapHoaDon = getTaiKhoanByUserName.getNhanvien();

			Khachhang getKhachHangById = khachHangService.findById(khachhang);

			hoadon.setNhanvienByIdnhanvienban(getNhanVienBanHangById);
			hoadon.setNhanvienByIdnhanvienchamsoc(getNhanVienChamSocById);
			hoadon.setNhanvienByIdnhanviengiaohang(getNhanVienGiaoHangById);
			hoadon.setNhanvienByIdnhanvienlaphoadon(getNhanVienLapHoaDon);
			hoadon.setKhachhang(getKhachHangById);
			hoadon.setHinhthucthanhtoan(hinhthucthanhtoan);
			hoadon.setNgaylap(df.parse(ngaylap));
			hoadon.setNgayxuat(df.parse(ngayxuat));
			hoadon.setNgaythanhtoan(df.parse(ngaythanhtoan));
			hoadon.setSodienthoai(sodienthoai.replace("_", ""));
			hoadon.setTrangthai(trangthai);
			hoaDonService.saveOrUpdate(hoadon);

			for (int i = 0; i < idhh.size(); i++) {
				Hanghoa hangHoaChiTietHoaDon = hangHoaService.findById(idhh.get(i));
				Chitiethoadon chitiethoadon = new Chitiethoadon(hangHoaChiTietHoaDon, hoadon, giabanhh.get(i),
						soluonghh.get(i), giabanhh.get(i), thanhtienhh.get(i), "active", "");
				chiTietHoaDonService.saveOrUpdate(chitiethoadon);

			}
			Double tientrendiem = getKhachHangById.getNhomkhachhang().getSotientrendiem();

			Integer diemtrentien = getKhachHangById.getNhomkhachhang().getSodiemtrentien();

			Double phantramtien = getKhachHangById.getNhomkhachhang().getPhantramtien();

			Long sotienchamsoc = (long) (getKhachHangById.getSotienchamsoc()
					+ ((hoadon.getTongtien() * phantramtien) / 100));

			Integer diem = (int) (getKhachHangById.getDiem() + (hoadon.getTongtien() / tientrendiem) * diemtrentien);

			getKhachHangById.setSotienchamsoc(sotienchamsoc);
			getKhachHangById.setDiem(diem);
			khachHangService.saveOrUpdate(getKhachHangById);

			// Luong
			/*
			 * Luong luong = null;
			 * 
			 * String splitngaylap[] = ngaylap.split("/"); luong =
			 * luongService.findOneByNhanvienAndThangAndNam(getNhanVienBanHangById,
			 * splitngaylap[1], splitngaylap[2]); Long thuong = (long)
			 * ((hoadon.getTongtien() * getNhanVienBanHangById.getChietkhau()) / 100); if
			 * (luong == null) {
			 * 
			 * Luong newluong = new Luong(getNhanVienBanHangById,
			 * getNhanVienBanHangById.getLuong(), thuong, splitngaylap[1], splitngaylap[2],
			 * "chuathanhtoan", ""); luongService.saveOrUpdate(newluong);
			 * 
			 * } else { luong.setThuong(luong.getThuong() + thuong);
			 * luongService.saveOrUpdate(luong); }
			 */
			ArrayList<Nhanvien> nhanvienarr = new ArrayList<Nhanvien>();
			nhanvienarr.add(getNhanVienLapHoaDon);
			nhanvienarr.add(getNhanVienGiaoHangById);
			nhanvienarr.add(getNhanVienBanHangById);
			nhanvienarr.add(getNhanVienChamSocById);

			for (Nhanvien nv : nhanvienarr) {
				System.out.println("Nhan VIen : " + nv.getId());
			}

			themluong(hoadon, nhanvienarr, ngaylap);
			redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace().toString());
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}

		return "redirect:/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1";

	}

	void themluong(Hoadon hoadon, ArrayList<Nhanvien> nhanvien, String ngaylap) {
		Luong luong = null;

		String splitngaylap[] = ngaylap.split("/");
		for (Nhanvien nv : nhanvien) {

			luong = luongService.findOneByNhanvienAndThangAndNam(nv, splitngaylap[1], splitngaylap[2]);
			Long thuong = (long) ((hoadon.getTongtien() * nv.getChietkhau()) / 100);
			if (luong == null) {

				Luong newluong = new Luong(nv, nv.getLuong(), thuong, splitngaylap[1], splitngaylap[2], "chuatraluong",
						"");
				luongService.saveOrUpdate(newluong);

			} else {
				luong.setThuong(luong.getThuong() + thuong);
				luongService.saveOrUpdate(luong);
			}
		}

	}

	@PatchMapping(value="/hoadon", params="update")
	String suaHoaDon(@ModelAttribute("hoadon") Hoadon hoadon, 
			@RequestParam("nhanvienbanhang") Integer nhanvienbanhang,
			@RequestParam("nhanviengiaohang") Integer nhanviengiaohang,
			@RequestParam("nhanvienchamsoc") Integer nhanvienchamsoc, @RequestParam("khachhang") Integer khachhang,
			@RequestParam("hinhthucthanhtoan") String hinhthucthanhtoan, @RequestParam("ngaylap") String ngaylap,

			@RequestParam("ngayxuat") String ngayxuat, @RequestParam("ngaythanhtoan") String ngaythanhtoan,
			@RequestParam("sodienthoai") String sodienthoai, @RequestParam("trangthai") String trangthai,
			@RequestParam("idcthd") List<Integer> idcthd, @RequestParam("idhh") List<Integer> idhh,
			@RequestParam("soluonghh") List<Integer> soluonghh, @RequestParam("giabanhh") List<Long> giabanhh,
			@RequestParam("thanhtienhh") List<Long> thanhtienhh, RedirectAttributes redirectAttributes,
			Principal principal) {

		try {

			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Hoadon hoadoncu = hoaDonService.findById(hoadon.getId());
			Nhanvien getNhanVienBanHangById = nhanVienService.findById(nhanvienbanhang);
			Nhanvien getNhanVienChamSocById = nhanVienService.findById(nhanvienchamsoc);
			Nhanvien getNhanVienGiaoHangById = nhanVienService.findById(nhanviengiaohang);
			Taikhoan getTaiKhoanByUserName = taikhoanService.findByUsername(principal.getName());
			Nhanvien getNhanVienLapHoaDon = getTaiKhoanByUserName.getNhanvien();

			Khachhang getKhachHangById = khachHangService.findById(khachhang);

			hoadon.setNhanvienByIdnhanvienban(getNhanVienBanHangById);
			hoadon.setNhanvienByIdnhanvienchamsoc(getNhanVienChamSocById);
			hoadon.setNhanvienByIdnhanviengiaohang(getNhanVienGiaoHangById);
			hoadon.setNhanvienByIdnhanvienlaphoadon(getNhanVienLapHoaDon);
			hoadon.setKhachhang(getKhachHangById);
			hoadon.setHinhthucthanhtoan(hinhthucthanhtoan);
			hoadon.setNgaylap(df.parse(ngaylap));
			hoadon.setNgayxuat(df.parse(ngayxuat));
			hoadon.setNgaythanhtoan(df.parse(ngaythanhtoan));
			hoadon.setSodienthoai(sodienthoai.replace("_", ""));
			hoadon.setTrangthai(trangthai);

			if (!hoadoncu.getTongtien().equals(hoadon.getTongtien())) {
				// Tien CHam Soc Khach Hang
				Double tientrendiem = getKhachHangById.getNhomkhachhang().getSotientrendiem();
				Integer diemtrentien = getKhachHangById.getNhomkhachhang().getSodiemtrentien();
				Double phantramtien = getKhachHangById.getNhomkhachhang().getPhantramtien();

				Double sotienchamsoccu = getKhachHangById.getSotienchamsoc()
						- ((hoadoncu.getTongtien() * phantramtien) / 100);

				Double diemcu = getKhachHangById.getDiem() - (hoadoncu.getTongtien() / tientrendiem) * diemtrentien;

				Long sotienchamsoc = (long) (sotienchamsoccu + ((hoadon.getTongtien() * phantramtien) / 100));

				Integer diem = (int) (diemcu + (hoadon.getTongtien() / tientrendiem) * diemtrentien);

				getKhachHangById.setSotienchamsoc(sotienchamsoc);
				getKhachHangById.setDiem(diem);
				khachHangService.saveOrUpdate(getKhachHangById);

				// Luong
				/*
				 * String splitngaylap[] = ngaylap.split("/"); Luong luong =
				 * luongService.findOneByNhanvienAndThangAndNam(getNhanVienBanHangById,
				 * splitngaylap[1], splitngaylap[2]); System.out.println(luong.getThuong());
				 * System.out.println((hoadoncu.getTongtien() *
				 * getNhanVienBanHangById.getChietkhau()) / 100); Long thuongcu = (long)
				 * (luong.getThuong() - (hoadoncu.getTongtien() *
				 * getNhanVienBanHangById.getChietkhau()) / 100); System.out.println(thuongcu);
				 * Long thuong = (long) ((hoadon.getTongtien() *
				 * getNhanVienBanHangById.getChietkhau()) / 100); System.out.println(thuong);
				 * 
				 * luong.setThuong(thuongcu + thuong); luongService.saveOrUpdate(luong);
				 */
				ArrayList<Nhanvien> nhanvienarr = new ArrayList<Nhanvien>();
				nhanvienarr.add(getNhanVienLapHoaDon);
				nhanvienarr.add(getNhanVienGiaoHangById);
				nhanvienarr.add(getNhanVienBanHangById);
				nhanvienarr.add(getNhanVienChamSocById);
				sualuong(hoadoncu, hoadon, nhanvienarr, ngaylap);
			}
			hoaDonService.saveOrUpdate(hoadon);

			for (int i = 0; i < idhh.size(); i++) {
				Hanghoa hangHoaChiTietHoaDon = hangHoaService.findById(idhh.get(i));
				if (idcthd.get(i) == 0) {
					Chitiethoadon chitiethoadon = new Chitiethoadon(hangHoaChiTietHoaDon, hoadon, giabanhh.get(i),
							soluonghh.get(i), giabanhh.get(i), thanhtienhh.get(i), "active", "");
					chiTietHoaDonService.saveOrUpdate(chitiethoadon);

				} else {

					Chitiethoadon chitiethoadon = new Chitiethoadon(idcthd.get(i), hangHoaChiTietHoaDon, hoadon,
							giabanhh.get(i), soluonghh.get(i), giabanhh.get(i), thanhtienhh.get(i), "active", "");
					chiTietHoaDonService.saveOrUpdate(chitiethoadon);
				}

			}

			redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}

		return "redirect:/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1";

	}

	void sualuong(Hoadon hoadoncu, Hoadon hoadon, ArrayList<Nhanvien> nhanvien, String ngaylap) {
		String splitngaylap[] = ngaylap.split("/");

		for (Nhanvien nv : nhanvien) {
			Luong luong = luongService.findOneByNhanvienAndThangAndNam(nv, splitngaylap[1],
					splitngaylap[2]);
			System.out.println(luong.getThuong());
			System.out.println((hoadoncu.getTongtien() * nv.getChietkhau()) / 100);
			Long thuongcu = (long) (luong.getThuong()
					- (hoadoncu.getTongtien() * nv.getChietkhau()) / 100);
			System.out.println(thuongcu);
			Long thuong = (long) ((hoadon.getTongtien() * nv.getChietkhau()) / 100);
			System.out.println(thuong);

			luong.setThuong(thuongcu + thuong);
			luongService.saveOrUpdate(luong);
		}
	}

	
	@PatchMapping(value="/hoadon", params="deleted")
	String xoaVinhVienHoaDon(@ModelAttribute("hoadon") Hoadon hoadon,  RedirectAttributes redirectAttributes) {
		List<Chitiethoadon> chitiethoadons = null;
		try {
			chitiethoadons = chiTietHoaDonService.findByHoadon(hoadon);
			if(!chitiethoadons.isEmpty()) {
				for(Chitiethoadon cthd : chitiethoadons) {
					chiTietHoaDonService.delete(cthd);
				}
			}
			hoaDonService.deleted(hoadon);
			
			redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
		}

		return "redirect:/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1";

	}
	
	@DeleteMapping("/hoadon")
	String xoaHoaDon(@RequestParam(value = "trangthai", defaultValue = "dathanhtoan") String trangthai,
			@RequestParam(value = "limit", defaultValue = "100") Integer limit,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam("arrayId") List<Integer> arrayId, RedirectAttributes redirectAttributes) {

		try {
			arrayId.forEach(x -> {

				Hoadon hoadon = hoaDonService.findById(x);
				hoadon.setTrangthai("deleted");

				Khachhang khachhang = hoadon.getKhachhang();
				Double tientrendiem = khachhang.getNhomkhachhang().getSotientrendiem();

				Integer diemtrentien = khachhang.getNhomkhachhang().getSodiemtrentien();

				Double phantramtien = khachhang.getNhomkhachhang().getPhantramtien();

				Long sotienchamsoc = (long) (khachhang.getSotienchamsoc()
						- ((hoadon.getTongtien() * phantramtien) / 100));

				Integer diem = (int) (khachhang.getDiem() - (hoadon.getTongtien() / tientrendiem) * diemtrentien);

				khachhang.setSotienchamsoc(sotienchamsoc);
				khachhang.setDiem(diem);
				khachHangService.saveOrUpdate(khachhang);

				// Luong
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String splitngaylap[] = df.format(hoadon.getNgaylap()).split("/");

				System.out.println(splitngaylap[1] + "-" + splitngaylap[2]);
				Luong luong = luongService.findOneByNhanvienAndThangAndNam(hoadon.getNhanvienByIdnhanvienban(),
						splitngaylap[1], splitngaylap[2]);

				Long thuong = (long) ((hoadon.getTongtien() * hoadon.getNhanvienByIdnhanvienban().getChietkhau())
						/ 100);

				luong.setThuong(luong.getThuong() - thuong);
				luongService.saveOrUpdate(luong);
				hoaDonService.saveOrUpdate(hoadon);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

		return "redirect:/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1";
	}
}
