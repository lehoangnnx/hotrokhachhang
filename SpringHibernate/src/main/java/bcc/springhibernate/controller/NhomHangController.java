package bcc.springhibernate.controller;


import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.service.NhomHangService;
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
public class NhomHangController {

    @Autowired
    NhomHangService nhomHangService;

    @GetMapping("/nhomhang")
    String pageDanhSachNhomHang(@RequestParam(value = "trangthai", defaultValue = "active") String trangthai,
                                Model model) {
        List<Nhomhang> listNhomhang = nhomHangService.findByTrangthaiOrderByIdDesc(trangthai);
        List<Nhomhang> listNhomhangcha = nhomHangService.findAll();
        model.addAttribute("listNhomhang", listNhomhang);
        model.addAttribute("listNhomhangcha", listNhomhangcha);
        return "danhsachnhomhang";
    }

    @GetMapping("/nhomhang/add")
    String pageThemNhomHang(Model model) {
        List<Nhomhang> listNhomhang = nhomHangService.findByTrangthaiOrderByIdDesc("active");
        model.addAttribute("listNhomhang", listNhomhang);
        model.addAttribute("nhomhang", new Nhomhang());
        return "themnhomhang";
    }

    @GetMapping("/nhomhang/{id}")
    String pageSuaNhomHang(Model model, @PathVariable("id") Integer id) {
        try {
            Nhomhang nhomhang = nhomHangService.findById(id);
            if (nhomhang != null) {
                List<Nhomhang> listNhomhang = nhomHangService.findByTrangthaiOrderByIdDesc("active");
                model.addAttribute("listNhomhang", listNhomhang);
                model.addAttribute("nhomhang", nhomhang);
                return "suanhomhang";
            } else {
                return "redirect:/403";
            }
        } catch (Exception e) {
            return "redirect:/403";
        }
    }

    @PostMapping("/nhomhang")
    String themNhomHang(@ModelAttribute("nhomhang") Nhomhang nhomhang,
                        RedirectAttributes redirectAttributes) {
        try {

            nhomhang.setTrangthai("active");
            nhomHangService.saveOrUpdate(nhomhang);

            redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
        }

        return "redirect:/admin/nhomhang?trangthai=active";
    }

    @PatchMapping(value = "/nhomhang", params = "update")
    String suaNhomHang(@ModelAttribute("nhomhang") Nhomhang nhomhang,
                       RedirectAttributes redirectAttributes) {
        try {
            //nhomhang.setTrangthai("active");
            nhomHangService.saveOrUpdate(nhomhang);
            redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
        }

        return "redirect:/admin/nhomhang?trangthai=active";
    }

    @PatchMapping(value = "/nhomhang", params = "restore")
    String khoiPhucNhomHang(@ModelAttribute("nhomhang") Nhomhang nhomhang,
                            RedirectAttributes redirectAttributes) {
        try {
            nhomhang.setTrangthai("active");
            nhomHangService.saveOrUpdate(nhomhang);
            redirectAttributes.addFlashAttribute("msg", "Khôi Phục Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Khôi Phục Thất Bại");
        }

        return "redirect:/admin/nhomhang?trangthai=active";
    }

    @PatchMapping(value = "/nhomhang", params = "deleted")
    String xoaVinhVienNhomHang(@ModelAttribute("nhomhang") Nhomhang nhomhang,
                               RedirectAttributes redirectAttributes) {
        List<Nhomhang> listNhomhang = null;
        try {
            listNhomhang = nhomHangService.findByManhomcha(nhomhang.getManhom());
            if (listNhomhang == null) {
                nhomHangService.deleted(nhomhang);
                redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
            }
            nhomHangService.deleted(nhomhang);
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
        }

        return "redirect:/admin/nhomhang?trangthai=active";
    }

    @DeleteMapping("/nhomhang")
    String xoaNhomHang(@RequestParam("arrayId") List<Integer> arrayId,
                       RedirectAttributes redirectAttributes) {

        try {
            arrayId.forEach(x -> {

                Nhomhang nhomhang = nhomHangService.findById(x);
                nhomhang.setTrangthai("deleted");
                nhomHangService.saveOrUpdate(nhomhang);

            });

            redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
        }

        return "redirect:/admin/nhomhang?trangthai=active";
    }
}
