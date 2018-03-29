package bcc.springhibernate.controller;


import bcc.springhibernate.model.Tieuchichamsoc;
import bcc.springhibernate.service.TieuChiChamSocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@PreAuthorize("hasAnyRole('ADMIN','CHAMSOC')")
@RequestMapping("/admin")
public class TieuChiChamSocController {


    @Autowired
    TieuChiChamSocService tieuChiChamSocService;

    @GetMapping("/tieuchichamsoc")
    String pageDanhSachTieuChiChamSoc(@RequestParam(value = "trangthai", defaultValue = "active") String trangthai,
                                      Model model) {
        List<Tieuchichamsoc> listTieuchichamsoc = tieuChiChamSocService.findByTrangthaiOrderByIdDesc(trangthai);
        model.addAttribute("listTieuchichamsoc", listTieuchichamsoc);
        return "danhsachtieuchichamsoc";
    }

    @GetMapping("/tieuchichamsoc/add")
    String pageThemTieuChiChamSoc(Model model) {
        model.addAttribute("tieuchichamsoc", new Tieuchichamsoc());
        return "themtieuchichamsoc";
    }

    @GetMapping("/tieuchichamsoc/{id}")
    String pageSuaTieuChiChamSoc(Model model, @PathVariable("id") Integer id) {
        try {
            Tieuchichamsoc tieuchichamsoc = tieuChiChamSocService.findById(id);
            if (tieuchichamsoc != null) {
                model.addAttribute("tieuchichamsoc", tieuchichamsoc);
                return "suatieuchichamsoc";
            } else {
                return "redirect:/403";
            }
        } catch (Exception e) {
            return "redirect:/403";
        }
    }

    @PostMapping("/tieuchichamsoc")
    String themTieuChiChamSoc(@ModelAttribute("tieuchichamsoc") Tieuchichamsoc tieuchichamsoc,
                              @RequestParam("kieutieuchi") String kieutieuchi,
                              RedirectAttributes redirectAttributes) {
        try {

            tieuchichamsoc.setKieutieuchi(kieutieuchi);
            tieuchichamsoc.setTrangthai("active");

            tieuChiChamSocService.saveOrUpdate(tieuchichamsoc);
            redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
        }

        return "redirect:/admin/tieuchichamsoc?trangthai=active";
    }

    @PatchMapping(value = "/tieuchichamsoc", params = "update")
    String suaTieuChiChamSoc(@ModelAttribute("tieuchichamsoc") Tieuchichamsoc tieuchichamsoc,
                             @RequestParam("kieutieuchi") String kieutieuchi,
                             RedirectAttributes redirectAttributes) {
        try {
            tieuchichamsoc.setKieutieuchi(kieutieuchi);
            //tieuchichamsoc.setTrangthai("active");

            tieuChiChamSocService.saveOrUpdate(tieuchichamsoc);
            redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
        }

        return "redirect:/admin/tieuchichamsoc?trangthai=active";
    }

    @PatchMapping(value = "/tieuchichamsoc", params = "deleted")
    String xoaVinhVienTieuChiChamSoc(@ModelAttribute("tieuchichamsoc") Tieuchichamsoc tieuchichamsoc,

                                     RedirectAttributes redirectAttributes) {
        try {


            tieuChiChamSocService.deleted(tieuchichamsoc);
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
        }

        return "redirect:/admin/tieuchichamsoc?trangthai=active";
    }

    @DeleteMapping("/tieuchichamsoc")
    String xoaTieuChiChamSoc(@RequestParam("arrayId") List<Integer> arrayId,
                             RedirectAttributes redirectAttributes) {

        try {
            arrayId.forEach(x -> {

                Tieuchichamsoc tieuchichamsoc = tieuChiChamSocService.findById(x);
                tieuchichamsoc.setTrangthai("deleted");

                tieuChiChamSocService.saveOrUpdate(tieuchichamsoc);

            });

            redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
        }

        return "redirect:/admin/tieuchichamsoc?trangthai=active";
    }
}
