package bcc.springhibernate.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bcc.springhibernate.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import bcc.springhibernate.service.ChamSocService;
import bcc.springhibernate.service.HoaDonService;
import bcc.springhibernate.service.KhachHangService;
import bcc.springhibernate.service.LuongService;
import bcc.springhibernate.service.NhanVienKpiService;
import bcc.springhibernate.service.NhanVienService;
import bcc.springhibernate.service.TaikhoanService;

@Component
public class ThongBao {
	
	@Autowired
	NhanVienService nhanVienService;
	@Autowired
	KhachHangService khachHangService;

	@Autowired
	ChamSocService chamSocService;
	@Autowired
	HoaDonService hoaDonService;
	@Autowired
	NhanVienKpiService nhanVienKpiService;
	@Autowired
	LuongService luongService;
@Autowired
TaikhoanService taikhoanService;
	public void thongbao(Model model, HttpServletRequest request, Principal principal) {

		/*if (principal != null) {
			System.out.println(principal.getName());
			HttpSession session = request.getSession();
			session.setAttribute("taikhoan", taikhoanService.findByUsername(principal.getName()));

		}*/


		List<Khachhang> khachhangs =new ArrayList<>();
		List<Chamsoc> chamsocs = null;

		if(request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_CHAMSOC") ){
			khachhangs = khachHangService.findByTrangthaiNotOrderByIdDesc("deleted");
			chamsocs = chamSocService.findByTrangthaiNotOrderByIdDesc("deleted");
		}else if (request.isUserInRole("ROLE_BANHANG")){
			Taikhoan taikhoan= taikhoanService.findByUsername(principal.getName());
			khachhangs = hoaDonService.findDistinctKhachhangByNhanvienByIdnhanvienbanAndTrangthaiNot
					(taikhoan.getNhanvien(), "deleted");
			/*if (!hoadons.isEmpty()){
				for (Hoadon hd : hoadons){
					khachhangs.add(hd.getKhachhang());
				}
			}*/
			chamsocs = chamSocService.findByTrangthaiNotAndNhanvienbanhangOrderByIdDesc(
					"deleted",taikhoan.getNhanvien().getId());
		}

		List<Nhanvien> nhanviens = nhanVienService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Hoadon> hoadons = hoaDonService.findByTrangthaiNotOrderByIdDesc("deleted");
		List<Nhanvienkpi> nhanvienkpis = nhanVienKpiService.findByTrangthaiOrderByIdDesc("inactive");
		
		request.getServletContext().setAttribute("khachhangs", khachhangs);
		request.getServletContext().setAttribute("chamsocs", chamsocs);
		request.getServletContext().setAttribute("nhanviens", nhanviens);
		request.getServletContext().setAttribute("hoadons", hoadons);
		List<Map<String, Object>> listChamSoc = new ArrayList<Map<String, Object>>();

		List<Map<String, Object>> listKhachHang = new ArrayList<Map<String, Object>>();

		List<Map<String, Object>> listNhanVienKpi = new ArrayList<Map<String, Object>>();
		for (Khachhang kh : khachhangs) {
			Date date = new Date();
			int dayn = date.getDate();
			int monthn = date.getMonth();
			if (kh.getNgaysinhnhatndd() != null) {
				int daydd = kh.getNgaysinhnhatndd().getDate();
				int monthdd = kh.getNgaysinhnhatndd().getMonth();
				int dayconlaidd = daydd - dayn;
				if (monthdd == monthn && (dayconlaidd <= 14) && (dayconlaidd > 7)
						&& kh.getTrangthainhac().equals("dasinhnhat")) {
					kh.setTrangthainhac("chosinhnhat");
					khachHangService.saveOrUpdate(kh);
					// && (dayconlaidd >= 0)
				} else if (monthdd == monthn && (dayconlaidd <= 7) && kh.getTrangthainhac().equals("chosinhnhat")) {

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("ngay", kh.getNgaysinhnhatndd());
					map.put("ngaysinhnhat", dayconlaidd);
					map.put("id", kh.getId());
					map.put("makh", kh.getMakh());
					map.put("ten", kh.getTen());
					listKhachHang.add(map);
				}
			}
			if (kh.getNgaysinhphutrach() != null) {
				int daypt = kh.getNgaysinhphutrach().getDate();
				int monthpt = kh.getNgaysinhphutrach().getMonth();

				int dayconlaipt = daypt - dayn;

				if (monthpt == monthn && (dayconlaipt <= 14) && (dayconlaipt > 7)
						&& kh.getTrangthainhac().equals("dasinhnhat")) {
					kh.setTrangthainhac("chosinhnhat");
					khachHangService.saveOrUpdate(kh);
					// && (dayconlaipt >= 0)
				} else if (monthpt == monthn && (dayconlaipt <= 7) && kh.getTrangthainhac().equals("chosinhnhat")) {

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("ngay", kh.getNgaysinhphutrach());
					map.put("ngaysinhnhat", dayconlaipt);
					map.put("id", kh.getId());
					map.put("makh", kh.getMakh());
					map.put("ten", kh.getTen());
					listKhachHang.add(map);
				}
			}
		}

		for (Chamsoc cs : chamsocs) {

			Date date = new Date();
			int day = cs.getNgaycstiep().getDate();
			int month = cs.getNgaycstiep().getMonth();
			int year = cs.getNgaycstiep().getYear();
			int dayn = date.getDate();
			int monthn = date.getMonth();
			int yearn = date.getYear();

			int dayconlai = day - dayn;
			//&& (dayconlai > 7)
			if (year == yearn && month == monthn && (dayconlai <= 14) 
					&& cs.getTrangthai().equals("dachamsoc") && cs.getTrangthainhac().equals("chuanhac")) {
				cs.setTrangthai("chochamsoc");
				chamSocService.saveOrUpdate(cs);
				//&& (dayconlai >= 0)
			} 
			if (year == yearn && month == monthn && (dayconlai <= 7)
					&& cs.getTrangthai().equals("chochamsoc")  && cs.getTrangthainhac().equals("chuanhac")) {

				Map<String, Object> map = new HashMap<String, Object>();

				map.put("id", cs.getId());
				map.put("ngay", cs.getNgaycstiep());
				map.put("ngaycstiep", dayconlai);
				map.put("khachhang", cs.getKhachhang().getTen());

				listChamSoc.add(map);
			}
		}

		for (Nhanvienkpi nvk : nhanvienkpis) {
			if(!nvk.getKpi().getTrangthai().equals("deleted")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", nvk.getId());
				map.put("ngaydangky", nvk.getNgaydangky());
				map.put("manhanvien", nvk.getNhanvien().getManhanvien());
				map.put("tennhanvien", nvk.getNhanvien().getTennhanvien());
				map.put("tenkpi", nvk.getKpi().getTen());
				listNhanVienKpi.add(map);
			}
		}

		listChamSoc.sort(Comparator.comparing(s -> (int) s.get("ngaycstiep")));
		listKhachHang.sort(Comparator.comparing(s -> (int) s.get("ngaysinhnhat")));
		listNhanVienKpi.sort(Comparator.comparing(s -> (Date) s.get("ngaydangky")));
		Collections.reverse(listNhanVienKpi);
		request.getServletContext().setAttribute("listChamSoc", listChamSoc);
		request.getServletContext().setAttribute("listKhachHang", listKhachHang);
		request.getServletContext().setAttribute("listNhanVienKpi", listNhanVienKpi);

		// Tao Luong
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String splitDate[] = df.format(date).split("/|-");

		// if(new Date(date.getYear(),date.getMonth(),date.getDate()).equals(new
		// Date(date.getYear(),date.getMonth(),01))){
		List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiNotOrderByIdDesc("deleted");
		listNhanvien.forEach(x -> {
			System.out.println(x.getTennhanvien());
			Luong luong = null;
			try {
				// Lấy Tháng Trước Ngày hiện tại
				Date dt = new Date();
				Calendar c = Calendar.getInstance();
				c.add(Calendar.MONTH, -1);
				int lastmonth = c.get(Calendar.MONTH) + 1; // beware of month indexing from zero
				int lastyear  = c.get(Calendar.YEAR);

				luong = luongService.findOneByNhanvienAndThangAndNam(x, splitDate[1], splitDate[2]);
				if (luong == null) {

					Luong lastluong = luongService.findOneByNhanvienAndThangAndNam(x, String.valueOf(lastmonth),
							String.valueOf(lastyear));
					if(lastluong != null){
						if(lastluong.getThuongcuahoadon() < 0){
							luong = new Luong(x, x.getLuong(), 0L,lastluong.getThuongcuahoadon() , splitDate[1], splitDate[2], "chuatraluong", "");
						}else {
							luong = new Luong(x, x.getLuong(), 0L,0L, splitDate[1], splitDate[2], "chuatraluong", "");
						}
					}else {
						luong = new Luong(x, x.getLuong(), 0L,0L, splitDate[1], splitDate[2], "chuatraluong", "");
					}

					luongService.saveOrUpdate(luong);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		// }
	}
}
