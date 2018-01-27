package bcc.springhibernate.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bcc.springhibernate.model.Chamsoc;
import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhanvienkpi;
import bcc.springhibernate.service.ChamSocService;
import bcc.springhibernate.service.HoaDonService;
import bcc.springhibernate.service.KpiService;
import bcc.springhibernate.service.NhanVienKpiService;
import bcc.springhibernate.service.NhanVienService;

@Controller
@RequestMapping("/admin")
public class ThongKeKpiController {

	@Autowired
	NhanVienKpiService nhanVienKpiService;
	@Autowired
	HoaDonService hoaDonService;
	@Autowired
	ChamSocService chamSocService;
	@Autowired
	KpiService kpiService;
	@Autowired
	NhanVienService nhanVienService;

	@GetMapping("/thongkekpi")
	String pageThongKeKpi(@RequestParam(value = "tungay", defaultValue = "null") String tungay,
			@RequestParam(value = "denngay", defaultValue = "null") String denngay,
			@RequestParam(value = "nhanvien", defaultValue = "0") Integer nhanvien,
			@RequestParam(value = "kpi", defaultValue = "0") Integer kpi, Model model) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date ddenngay = new Date();
			Date dtungay = new Date(ddenngay.getYear(), ddenngay.getMonth(), 01);
			if (!tungay.equals("null")) {
				dtungay = dateFormat.parse(tungay);
			}
			if (!denngay.equals("null")) {
				ddenngay = dateFormat.parse(denngay);
			}
			List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiNotOrderByIdDesc("deleted");
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			// List<Nhanvienkpi> listNhanvienkpi =
			// nhanVienKpiService.findByKpiAndTrangthaiNotOrderByNgaydangkyDesc(kpiById,"deleted");
			Kpi kpiById = kpiService.findById(kpi);
			List<Nhanvienkpi> listNhanvienkpi = null;
			if (nhanvien == 0) {
				listNhanvienkpi = nhanVienKpiService.findByKpiAndTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc(
						kpiById, "deleted", dtungay, ddenngay);
			} else {
				Nhanvien getNhanVienById = nhanVienService.findById(nhanvien);
				listNhanvienkpi = nhanVienKpiService
						.findByNhanvienAndKpiAndTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc(getNhanVienById,
								kpiById, "deleted", dtungay, ddenngay);
			}
			List<Kpi> listKpi = kpiService.findByTrangthaiNotOrderByIdDesc("deleted");
			if (!listNhanvienkpi.isEmpty()) {
				for (Nhanvienkpi nvk : listNhanvienkpi) {
					Map<String, Object> map = new HashMap<String, Object>();
					Long tongtien = 0L;
					int tongsolanchamsoc = chamSocService
							.findByNhanvienchamsocAndTrangthaiNotAndNgayBetweenOrderByIdDesc(nvk.getNhanvien().getId(),
									"deleted", nvk.getNgaydangky(), nvk.getNgayhoanthanh())
							.size();
					List<Hoadon> listHoadon = hoaDonService
							.findByNhanvienByIdnhanvienbanAndTrangthaiNotAndNgaylapBetweenOrderByIdDesc(
									nvk.getNhanvien(), "deleted", nvk.getNgaydangky(), nvk.getNgayhoanthanh());

					for (Hoadon hd : listHoadon) {
						tongtien += hd.getTongtien();
					}

					map.put("idnvkpi", nvk.getId());
					map.put("idnv", nvk.getNhanvien().getId());
					map.put("tongtien", tongtien);
					map.put("tongsolanchamsoc", tongsolanchamsoc);
					listMap.add(map);
					
					
					
					model.addAttribute("listMap", listMap);
					
				}
			}
			model.addAttribute("listNhanvienkpi", listNhanvienkpi);
			model.addAttribute("listNhanvien", listNhanvien);
			model.addAttribute("listKpi", listKpi);
			model.addAttribute("tungay", dtungay);
			model.addAttribute("denngay", ddenngay);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "thongkekpi";
	}
}
