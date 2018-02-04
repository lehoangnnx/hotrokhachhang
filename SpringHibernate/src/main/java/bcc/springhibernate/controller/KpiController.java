package bcc.springhibernate.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.model.Nhanvienkpi;
import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.repository.KpiRepository;
import bcc.springhibernate.service.KpiService;
import bcc.springhibernate.service.NhanVienKpiService;
import bcc.springhibernate.service.NhomKhachHangService;

@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin")
public class KpiController {

	@Autowired
	KpiService kpiService;
	@Autowired
	NhanVienKpiService nhanVienKpiService;

	@GetMapping("/kpi")
	String pageDanhSachKpi(@RequestParam(value = "trangthai", defaultValue = "active") String trangthai, Model model) {
		List<Kpi> listKpi = kpiService.findByTrangthaiOrderByIdDesc(trangthai);
		model.addAttribute("listKpi", listKpi);
		return "danhsachkpi";
	}

	@GetMapping("/kpi/add")
	String pageThemKpi(Model model) {
		model.addAttribute("kpi", new Kpi());
		return "themkpi";
	}

	@GetMapping("/kpi/{id}")
	String pageSuaKpi(Model model, @PathVariable("id") Integer id) {
		Kpi kpi = kpiService.findById(id);
		model.addAttribute("kpi", kpi);
		return "suakpi";
	}

	@PostMapping("/kpi")
	String themKpi(@ModelAttribute("kpi") Kpi kpi, RedirectAttributes redirectAttributes) {
		try {
			kpi.setTrangthai("active");
			kpiService.saveOrUpdate(kpi);
			redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
		}

		return "redirect:/admin/kpi?trangthai=active";
	}

	@PatchMapping(value = "/kpi", params = "update")
	String suaKpi(@ModelAttribute("kpi") Kpi kpi, RedirectAttributes redirectAttributes) {
		try {
			kpi.setTrangthai("active");
			kpiService.saveOrUpdate(kpi);
			redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
		}

		return "redirect:/admin/kpi?trangthai=active";
	}

	@PatchMapping(value = "/kpi", params = "deleted")
	String xoaVinhVienKpi(@ModelAttribute("kpi") Kpi kpi, RedirectAttributes redirectAttributes) {
		List<Nhanvienkpi> nhanvienkpis = null;
		try {
			nhanvienkpis = nhanVienKpiService.findByKpi(kpi);
			System.out.println(nhanvienkpis);
			if (nhanvienkpis.isEmpty()) {
				kpiService.deleted(kpi);
				redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
		}

		return "redirect:/admin/kpi?trangthai=active";
	}

	@DeleteMapping("/kpi")
	String xoaKpi(@RequestParam("arrayId") List<Integer> arrayId, RedirectAttributes redirectAttributes) {

		try {
			arrayId.forEach(x -> {

				Kpi kpi = kpiService.findById(x);
				kpi.setTrangthai("deleted");
				kpiService.saveOrUpdate(kpi);

			});

			redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
		}

		return "redirect:/admin/kpi?trangthai=active";
	}
}
