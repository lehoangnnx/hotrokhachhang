package bcc.springhibernate.controller;

import java.util.ArrayList;
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
import bcc.springhibernate.model.Nhanvienkpi;
import bcc.springhibernate.service.ChamSocService;
import bcc.springhibernate.service.HoaDonService;
import bcc.springhibernate.service.KpiService;
import bcc.springhibernate.service.NhanVienKpiService;

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

	@GetMapping("/thongkekpi")
	String pageThongKeKpi(@RequestParam(value = "kpi", defaultValue = "0") Integer kpi, Model model) {
		Kpi kpiById = kpiService.findById(kpi);

		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

		
		List<Nhanvienkpi> listNhanvienkpi = nhanVienKpiService.findByKpiAndTrangthaiNotOrderByNgaydangkyDesc(kpiById,
				"deleted");
		List<Kpi> listKpi = kpiService.findByTrangthaiNotOrderByIdDesc("deleted");
		for (Nhanvienkpi nvk : listNhanvienkpi) {
			Map<String, Object> map = new HashMap<String, Object>();
			Long tongtien = 0L;
			int tongsolanchamsoc = chamSocService.findByNhanvienchamsocAndTrangthaiNotAndNgayBetweenOrderByIdDesc
					(nvk.getNhanvien().getId(), "deleted", nvk.getNgaydangky(), nvk.getNgayhoanthanh()).size();
			List<Hoadon> listHoadon = hoaDonService
					.findByNhanvienByIdnhanvienbanAndTrangthaiNotAndNgaylapBetweenOrderByIdDesc(nvk.getNhanvien(),
							"deleted", nvk.getNgaydangky(), nvk.getNgayhoanthanh());
			
			for (Hoadon hd : listHoadon) {
				tongtien += hd.getTongtien();
			}
			
			map.put("idnvkpi", nvk.getId());
			map.put("idnv", nvk.getNhanvien().getId());
			map.put("tongtien", tongtien);
			map.put("tongsolanchamsoc", tongsolanchamsoc);
			listMap.add(map);

		}
		model.addAttribute("listKpi", listKpi);
		model.addAttribute("listNhanvienkpi", listNhanvienkpi);
		model.addAttribute("listMap", listMap);
		return "thongkekpi";
	}
}
