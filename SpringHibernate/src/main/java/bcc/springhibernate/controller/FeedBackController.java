package bcc.springhibernate.controller;


import bcc.springhibernate.model.*;
import bcc.springhibernate.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
@PreAuthorize("hasAnyRole('ADMIN','CHAMSOC')")
@RequestMapping("/admin")
public class FeedBackController {

    @Autowired
    FeedBackService feedBackService;
    @Autowired
    HangHoaService hangHoaService;
    @Autowired
    KhachHangService khachHangService;
    @Autowired
    NhanVienService nhanVienService;
    @Autowired
    TaikhoanService taikhoanService;

    @GetMapping("/feedback")
    String pageDanhSachFeedBack(@RequestParam(value = "trangthai", defaultValue = "active") String trangthai,
                                Model model) {
        List<Feedback> listFeedback = feedBackService.findByTrangthaiOrderByIdDesc(trangthai);
        List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
        List<Khachhang> listKhachhang = khachHangService.findByTrangthaiOrderByIdDesc("active");
        List<Hanghoa> listHanghoa = hangHoaService.findByTrangthaiOrderByIdDesc("active");

        model.addAttribute("listNhanvien", listNhanvien);
        model.addAttribute("listKhachhang", listKhachhang);
        model.addAttribute("listHanghoa", listHanghoa);
        model.addAttribute("listFeedback", listFeedback);
        return "danhsachfeedback";
    }

    @GetMapping("/feedback/add")
    String pageThemFeedBack(Model model) {
        List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
        List<Khachhang> listKhachhang = khachHangService.findByTrangthaiOrderByIdDesc("active");
        List<Hanghoa> listHanghoa = hangHoaService.findByTrangthaiOrderByIdDesc("active");

        model.addAttribute("listNhanvien", listNhanvien);
        model.addAttribute("listKhachhang", listKhachhang);
        model.addAttribute("listHanghoa", listHanghoa);
        model.addAttribute("feedback", new Feedback());
        return "themfeedback";
    }

    @GetMapping("/feedback/{id}")
    String pageSuaFeedBack(Model model, @PathVariable("id") Integer id) {
        try {
            Feedback feedback = feedBackService.findById(id);
            if (feedback != null) {
                List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
                List<Khachhang> listKhachhang = khachHangService.findByTrangthaiOrderByIdDesc("active");
                List<Hanghoa> listHanghoa = hangHoaService.findByTrangthaiOrderByIdDesc("active");

                model.addAttribute("listNhanvien", listNhanvien);
                model.addAttribute("listKhachhang", listKhachhang);
                model.addAttribute("listHanghoa", listHanghoa);
                model.addAttribute("feedback", feedback);
                return "suafeedback";
            } else {
                return "redirect:/403";
            }
        } catch (Exception e) {
            return "redirect:/403";
        }

    }

    @PostMapping("/feedback")
    String themFeedBack(@ModelAttribute("feedback") Feedback feedback,
                        @RequestParam("nhanvienId") Integer nhanvienId,
                        @RequestParam("hanghoaId") Integer hanghoaId,
                        @RequestParam("khachhangId") Integer khachhangId,
                        Principal principal,
                        RedirectAttributes redirectAttributes) {
        try {
            Taikhoan getTaiKhoanByUserName = taikhoanService.findByUsername(principal.getName());
            feedback.setNhanvienId(nhanvienId);
            feedback.setHanghoaId(hanghoaId);
            feedback.setKhachhangId(khachhangId);
            feedback.setNhavientaoId(getTaiKhoanByUserName.getNhanvien().getId());
            feedback.setNgaytao(new Date());
            feedback.setTrangthai("active");
            feedBackService.saveOrUpdate(feedback);
            redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
        }

        return "redirect:/admin/feedback?trangthai=active";
    }

    @PatchMapping(value = "/feedback", params = "update")
    String suaFeedBack(@ModelAttribute("feedback") Feedback feedback,
                       @RequestParam("nhanvienId") Integer nhanvienId,
                       @RequestParam("hanghoaId") Integer hanghoaId,
                       @RequestParam("khachhangId") Integer khachhangId,
                       Principal principal,
                       RedirectAttributes redirectAttributes) {
        try {
            Taikhoan getTaiKhoanByUserName = taikhoanService.findByUsername(principal.getName());
            feedback.setNhanvienId(nhanvienId);
            feedback.setHanghoaId(hanghoaId);
            feedback.setKhachhangId(khachhangId);
            feedback.setNhavientaoId(getTaiKhoanByUserName.getNhanvien().getId());
            feedback.setNgaytao(new Date());
            //feedback.setTrangthai("active");
            feedBackService.saveOrUpdate(feedback);
            redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
        }

        return "redirect:/admin/feedback?trangthai=active";
    }

    @PatchMapping(value = "/feedback", params = "deleted")
    String xoaVinhVienFeedBack(@ModelAttribute("feedback") Feedback feedback,

                               Principal principal,
                               RedirectAttributes redirectAttributes) {
        try {

            feedBackService.deleted(feedback);
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
        }

        return "redirect:/admin/feedback?trangthai=active";
    }

    @DeleteMapping("/feedback")
    String xoaFeedBack(@RequestParam("arrayId") List<Integer> arrayId,
                       RedirectAttributes redirectAttributes) {

        try {
            arrayId.forEach(x -> {

                Feedback feedback = feedBackService.findById(x);
                feedback.setTrangthai("deleted");
                feedBackService.saveOrUpdate(feedback);

            });

            redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
        }

        return "redirect:/admin/feedback?trangthai=active";
    }
}
