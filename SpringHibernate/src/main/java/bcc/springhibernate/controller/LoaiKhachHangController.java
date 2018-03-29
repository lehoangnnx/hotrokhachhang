package bcc.springhibernate.controller;


import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.service.LoaiKhachHangService;
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
public class LoaiKhachHangController {

    @Autowired
    LoaiKhachHangService loaiKhachHangService;

    @GetMapping("/loaikhachhang")
    String pageDanhSachLoaiKhachHangg(@RequestParam(value = "trangthai", defaultValue = "active") String trangthai,
                                      Model model) {
        List<Loaikhachhang> listLoaiKhachHang = loaiKhachHangService.findByTrangthaiOrderByIdDesc(trangthai);
        model.addAttribute("listLoaiKhachHang", listLoaiKhachHang);
        return "danhsachloaikhachhang";
    }

    @GetMapping("/loaikhachhang/add")
    String pageThemLoaiKhachHangg(Model model) {
        model.addAttribute("loaikhachhang", new Loaikhachhang());
        return "themloaikhachhang";
    }

    @GetMapping("/loaikhachhang/{id}")
    String pageSuaLoaiKhachHangg(Model model, @PathVariable("id") Integer id) {
        try {

            Loaikhachhang loaikhachhang = loaiKhachHangService.findById(id);
            if (loaikhachhang != null) {
                model.addAttribute("loaikhachhang", loaikhachhang);
                return "sualoaikhachhang";
            } else {
                return "redirect:/403";
            }
        } catch (Exception e) {
            return "redirect:/403";
        }
    }

    @PostMapping("/loaikhachhang")
    String themLoaiKhachHang(@ModelAttribute("loaikhachhang") Loaikhachhang loaikhachhang, RedirectAttributes redirectAttributes) {
        try {

            loaikhachhang.setTrangthai("active");
            loaiKhachHangService.saveOrUpdate(loaikhachhang);
            redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
        }
        return "redirect:/admin/loaikhachhang?trangthai=active";
    }

    @PatchMapping(value = "/loaikhachhang", params = "update")
    String suaLoaiKhachHang(@ModelAttribute("loaikhachhang") Loaikhachhang loaikhachhang, RedirectAttributes redirectAttributes) {
        try {
            //loaikhachhang.setTrangthai("active");
            loaiKhachHangService.saveOrUpdate(loaikhachhang);
            redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
        }
        return "redirect:/admin/loaikhachhang?trangthai=active";
    }

    @PatchMapping(value = "/loaikhachhang", params = "deleted")
    String xoaVinhVienLoaiKhachHang(@ModelAttribute("loaikhachhang") Loaikhachhang loaikhachhang, RedirectAttributes redirectAttributes) {
        try {

            loaiKhachHangService.deleted(loaikhachhang);
            redirectAttributes.addFlashAttribute("msg", "Xóa VĨnh Viễn Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Xóa VĨnh Viễn Thất Bại");
        }
        return "redirect:/admin/loaikhachhang?trangthai=active";
    }

    @DeleteMapping("/loaikhachhang")
    String xoaLoaiKhachHang(@RequestParam("arrayId") List<Integer> arrayId,
                            RedirectAttributes redirectAttributes) {

        try {
            arrayId.forEach(x -> {

                Loaikhachhang loaikhachhang = loaiKhachHangService.findById(x);
                loaikhachhang.setTrangthai("deleted");
                loaiKhachHangService.saveOrUpdate(loaikhachhang);

            });

            redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
        }

        return "redirect:/admin/loaikhachhang?trangthai=active";
    }
}
