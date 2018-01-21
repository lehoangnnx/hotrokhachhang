package bcc.springhibernate.controller;

import java.math.BigDecimal;
import java.security.Principal;
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
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.service.ChiTietHoaDonService;
import bcc.springhibernate.service.HangHoaService;
import bcc.springhibernate.service.HoaDonService;
import bcc.springhibernate.service.KhachHangService;
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
	@GetMapping("/hoadon")
	String pageDanhSachHoaDon(@RequestParam(value="trangthai", defaultValue="dathanhtoan") String trangthai,
			@RequestParam(value="limit", defaultValue="100") Integer limit, 
			@RequestParam(value="page", defaultValue="1") Integer page,
			Model model) {
		int pageCount = (hoaDonService.findByTrangthaiOrderByIdDesc(trangthai).size()) / limit 
				+ (hoaDonService.findByTrangthaiOrderByIdDesc(trangthai).size() % limit > 0 ? 1 : 0);	
		List<Hoadon> listHoadon = hoaDonService.
				findByTrangthaiOrderByIdDesc(trangthai, new PageRequest(page - 1, limit));
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
	String pageSuaHoaDon(@PathVariable("id") Integer id,Model model) {
		
		
		
		Hoadon hoadon = hoaDonService.findById(id);
		List<Chitiethoadon> listChitiethoadon = chiTietHoaDonService.findByHoadonAndTrangthaiOrderByIdDesc(hoadon, "active");
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
			@RequestParam("hinhthucthanhtoan") String hinhthucthanhtoan, @RequestParam("ngaylap") Date ngaylap,
			@RequestParam("ngayxuat") Date ngayxuat, @RequestParam("ngaythanhtoan") Date ngaythanhtoan,
			@RequestParam("sodienthoai") String sodienthoai, @RequestParam("trangthai") String trangthai,
			@RequestParam("idhh") List<Integer> idhh, @RequestParam("soluonghh") List<Integer> soluonghh,
			@RequestParam("giabanhh") List<Long> giabanhh,
			@RequestParam("thanhtienhh") List<Long> thanhtienhh, RedirectAttributes redirectAttributes,
			Principal principal) {

		try {
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
			hoadon.setNgaylap(ngaylap);
			hoadon.setNgayxuat(ngayxuat);
			hoadon.setNgaythanhtoan(ngaythanhtoan);
			hoadon.setSodienthoai(sodienthoai.replace("_", ""));
			hoadon.setTrangthai(trangthai);
			hoaDonService.saveOrUpdate(hoadon);
			
			
			
			for (int i = 0; i < idhh.size(); i++) {
				Hanghoa hangHoaChiTietHoaDon = hangHoaService.findById(idhh.get(i));
				Chitiethoadon chitiethoadon = 
						new Chitiethoadon(hangHoaChiTietHoaDon, hoadon, giabanhh.get(i), soluonghh.get(i), 
								giabanhh.get(i), thanhtienhh.get(i), "active", "");
				chiTietHoaDonService.saveOrUpdate(chitiethoadon);
				
			}
			Double tientrendiem= getKhachHangById.getNhomkhachhang().getSotientrendiem();
			
			Integer diemtrentien = getKhachHangById.getNhomkhachhang().getSodiemtrentien();
			
			Double phantramtien = getKhachHangById.getNhomkhachhang().getPhantramtien();
			
			Long sotienchamsoc = (long)(getKhachHangById.getSotienchamsoc() + ((hoadon.getTongtien() * phantramtien ) / 100)) ;
			
			Integer diem = (int) (getKhachHangById.getDiem() + (hoadon.getTongtien() / tientrendiem) * diemtrentien);
			
			getKhachHangById.setSotienchamsoc(sotienchamsoc);
			getKhachHangById.setDiem(diem);
			khachHangService.saveOrUpdate(getKhachHangById);
			redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}
		
		return "redirect:/admin/hoadon";

	}
	
	@PatchMapping("/hoadon")
	String suaHoaDon(@ModelAttribute("hoadon") Hoadon hoadon, @RequestParam("nhanvienbanhang") Integer nhanvienbanhang,
			@RequestParam("nhanviengiaohang") Integer nhanviengiaohang,
			@RequestParam("nhanvienchamsoc") Integer nhanvienchamsoc, @RequestParam("khachhang") Integer khachhang,
			@RequestParam("hinhthucthanhtoan") String hinhthucthanhtoan, @RequestParam("ngaylap") Date ngaylap,
			@RequestParam("ngayxuat") Date ngayxuat, @RequestParam("ngaythanhtoan") Date ngaythanhtoan,
			@RequestParam("sodienthoai") String sodienthoai, @RequestParam("trangthai") String trangthai,
			@RequestParam("idcthd") List<Integer> idcthd,
			@RequestParam("idhh") List<Integer> idhh, @RequestParam("soluonghh") List<Integer> soluonghh,
			@RequestParam("giabanhh") List<Long> giabanhh,
			@RequestParam("thanhtienhh") List<Long> thanhtienhh, RedirectAttributes redirectAttributes,
			Principal principal) {

		try {
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
			hoadon.setNgaylap(ngaylap);
			hoadon.setNgayxuat(ngayxuat);
			hoadon.setNgaythanhtoan(ngaythanhtoan);
			hoadon.setSodienthoai(sodienthoai.replace("_", ""));
			hoadon.setTrangthai(trangthai);
			
			
			Double tientrendiem= getKhachHangById.getNhomkhachhang().getSotientrendiem();
			Integer diemtrentien = getKhachHangById.getNhomkhachhang().getSodiemtrentien();
			Double phantramtien = getKhachHangById.getNhomkhachhang().getPhantramtien();
			
			Double sotienchamsoccu =getKhachHangById.getSotienchamsoc() - ((hoadoncu.getTongtien() * phantramtien ) / 100) ;
			System.out.println(sotienchamsoccu);
			Double diemcu = getKhachHangById.getDiem() - (hoadoncu.getTongtien() / tientrendiem) * diemtrentien;
			System.out.println(diemcu);
			System.out.println(sotienchamsoccu + (hoadon.getTongtien() * phantramtien ) / 100);
			Long sotienchamsoc =(long) (sotienchamsoccu + ((hoadon.getTongtien() * phantramtien ) / 100)) ;
			System.out.println(sotienchamsoc);
			Integer diem =(int) (diemcu +(hoadon.getTongtien() / tientrendiem) * diemtrentien);
			System.out.println(diem);
			getKhachHangById.setSotienchamsoc(sotienchamsoc);
			getKhachHangById.setDiem(diem);
			khachHangService.saveOrUpdate(getKhachHangById);
			
			hoaDonService.saveOrUpdate(hoadon);
			
			
			
			for (int i = 0; i < idhh.size(); i++) {
				Hanghoa hangHoaChiTietHoaDon = hangHoaService.findById(idhh.get(i));
				if(idcthd.get(i) == 0) {
					Chitiethoadon chitiethoadon = 
							new Chitiethoadon(hangHoaChiTietHoaDon, hoadon, giabanhh.get(i), soluonghh.get(i), 
									giabanhh.get(i), thanhtienhh.get(i), "active", "");
					chiTietHoaDonService.saveOrUpdate(chitiethoadon);
					
				}else {
					
					Chitiethoadon chitiethoadon = 
							new Chitiethoadon(idcthd.get(i),hangHoaChiTietHoaDon, hoadon, giabanhh.get(i), soluonghh.get(i), 
									giabanhh.get(i), thanhtienhh.get(i), "active", "");
					chiTietHoaDonService.saveOrUpdate(chitiethoadon);
				}
				
				
			}
			
			redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}
		
		return "redirect:/admin/hoadon/";

	}
	
	@DeleteMapping("/hoadon")
    String xoaHoaDon(@RequestParam(value="trangthai", defaultValue="dathanhtoan") String trangthai,
    				@RequestParam(value="limit", defaultValue="100") Integer limit, 
    				@RequestParam(value="page", defaultValue="1") Integer page,
    				@RequestParam("arrayId") List<Integer> arrayId,
    		RedirectAttributes redirectAttributes) {
    	
    	try {
			arrayId.forEach(x -> {

				Hoadon hoadon= hoaDonService.findById(x);
				hoadon.setTrangthai("deleted");
				hoaDonService.saveOrUpdate(hoadon);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

    	return "redirect:/admin/hoadon?trangthai="+trangthai+"&limit="+limit+"&page="+page+"";
    }
}
