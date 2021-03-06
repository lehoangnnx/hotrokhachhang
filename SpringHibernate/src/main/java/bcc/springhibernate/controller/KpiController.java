package bcc.springhibernate.controller;

import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.model.Nhanvienkpi;
import bcc.springhibernate.service.KpiService;
import bcc.springhibernate.service.NhanVienKpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
        try {
            Kpi kpi = kpiService.findById(id);
            if (kpi != null) {
                model.addAttribute("kpi", kpi);
                return "suakpi";
            } else {
                return "redirect:/403";
            }
        } catch (Exception e) {
            return "redirect:/403";
        }
    }

    @PostMapping("/kpi")
    String themKpi(@ModelAttribute("kpi") Kpi kpi,
                   @RequestParam("kieukpi") String kieukpi,
                   RedirectAttributes redirectAttributes) {
        try {
            kpi.setTrangthai("active");
            kpi.setKieukpi(kieukpi);
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
            //kpi.setTrangthai("active");
            if (kpi.getId() != 1 && kpi.getId() != 2 && kpi.getId() != 3) {
                kpiService.saveOrUpdate(kpi);
                redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Không Thể Sửa KPI");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
        }

        return "redirect:/admin/kpi?trangthai=active";
    }

    @PatchMapping(value = "/kpi", params = "deleted")
    String xoaVinhVienKpi(@ModelAttribute("kpi") Kpi kpi, RedirectAttributes redirectAttributes) {
        List<Nhanvienkpi> nhanvienkpis = null;
        try {
            if (kpi.getId() != 1 && kpi.getId() != 2 && kpi.getId() != 3) {
                nhanvienkpis = nhanVienKpiService.findByKpi(kpi);
                if (nhanvienkpis.isEmpty()) {
                    kpiService.deleted(kpi);
                    redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
                } else {
                    redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
                }
            } else {
                redirectAttributes.addFlashAttribute("msg", "Không Thể Xóa KPI");
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
                if (x != 1 && x != 2 && x != 3) {
                    Kpi kpi = kpiService.findById(x);
                    kpi.setTrangthai("deleted");
                    kpiService.saveOrUpdate(kpi);
                    redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
                } else {
                    redirectAttributes.addFlashAttribute("msg", "Không Thể Xóa KPI");
                }
            });


        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
        }

        return "redirect:/admin/kpi?trangthai=active";
    }
}
