package bcc.springhibernate.controller;


import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.service.NhomKhachHangService;
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
public class NhomKhachHangController {

    @Autowired
    NhomKhachHangService nhomKhachHangService;

    @GetMapping("/nhomkhachhang")
    String pageDanhSachNhomKhachHangg(@RequestParam(value = "trangthai", defaultValue = "active") String trangthai,
                                      Model model) {
        List<Nhomkhachhang> listNhomkhachhang = nhomKhachHangService.findByTrangthaiOrderByIdDesc(trangthai);
        model.addAttribute("listNhomkhachhang", listNhomkhachhang);
        return "danhsachnhomkhachhang";
    }

    @GetMapping("/nhomkhachhang/add")
    String pageThemNhomKhachHangg(Model model) {
        model.addAttribute("nhomkhachhang", new Nhomkhachhang());
        return "themnhomkhachhang";
    }

    @GetMapping("/nhomkhachhang/{id}")
    String pageSuaNhomKhachHangg(Model model, @PathVariable("id") Integer id) {
        try {
            Nhomkhachhang nhomkhachhang = nhomKhachHangService.findById(id);
            if (nhomkhachhang != null) {
                model.addAttribute("nhomkhachhang", nhomkhachhang);
                return "suanhomkhachhang";
            } else {
                return "redirect:/403";
            }

        } catch (Exception e) {
            return "redirect:/403";
        }
    }

    @PostMapping("/nhomkhachhang")
    String themNhomKhachHang(@ModelAttribute("nhomkhachhang") Nhomkhachhang nhomkhachhang,

                             RedirectAttributes redirectAttributes) {
        try {

            nhomkhachhang.setTrangthai("active");
            nhomKhachHangService.saveOrUpdate(nhomkhachhang);
            redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
        }

        return "redirect:/admin/nhomkhachhang?trangthai=active";
    }

    @PatchMapping(value = "/nhomkhachhang", params = "update")
    String suaNhomKhachHang(@ModelAttribute("nhomkhachhang") Nhomkhachhang nhomkhachhang,

                            RedirectAttributes redirectAttributes) {
        try {

            //nhomkhachhang.setTrangthai("active");
            nhomKhachHangService.saveOrUpdate(nhomkhachhang);
            redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
        }

        return "redirect:/admin/nhomkhachhang?trangthai=active";
    }

    @PatchMapping(value = "/nhomkhachhang", params = "deleted")
    String xoaVinhVienNhomKhachHang(@ModelAttribute("nhomkhachhang") Nhomkhachhang nhomkhachhang,

                                    RedirectAttributes redirectAttributes) {
        try {


            nhomKhachHangService.deleted(nhomkhachhang);
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
        }

        return "redirect:/admin/nhomkhachhang?trangthai=active";
    }

    @DeleteMapping("/nhomkhachhang")
    String xoaNhomKhachHang(@RequestParam("arrayId") List<Integer> arrayId,
                            RedirectAttributes redirectAttributes) {

        try {
            arrayId.forEach(x -> {

                Nhomkhachhang nhomkhachhang = nhomKhachHangService.findById(x);
                nhomkhachhang.setTrangthai("deleted");
                nhomKhachHangService.saveOrUpdate(nhomkhachhang);

            });

            redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
        }

        return "redirect:/admin/nhomkhachhang?trangthai=active";
    }
}
