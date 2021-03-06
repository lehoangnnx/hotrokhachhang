package bcc.springhibernate.controller;


import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Quyen;
import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.service.NhanVienService;
import bcc.springhibernate.service.QuyenService;
import bcc.springhibernate.service.TaikhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;

@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin")
public class TaiKhoanController {

    @Autowired
    TaikhoanService taikhoanService;

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    QuyenService quyenService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/taikhoan")
    String pageDanhSachTaiKhoan(@RequestParam(value = "trangthai", defaultValue = "active") String trangthai,
                                Model model) {
        List<Taikhoan> listTaikhoan = taikhoanService.findByTrangthaiOrderByIdDesc(trangthai);
        model.addAttribute("listTaikhoan", listTaikhoan);
        return "danhsachtaikhoan";
    }

    @GetMapping("/taikhoan/add")
    String pageThemTaiKhoan(Model model) {
        List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
        List<Quyen> listQuyen = quyenService.findByTrangthaiOrderByIdDesc("active");

        model.addAttribute("listNhanvien", listNhanvien);
        model.addAttribute("listQuyen", listQuyen);
        model.addAttribute("taikhoan", new Taikhoan());
        return "themtaikhoan";
    }

    @GetMapping("/taikhoan/{id}")
    String pageSuaTaiKhoan(Model model, @PathVariable("id") Integer id) {
        try {
            Taikhoan taikhoan = taikhoanService.findById(id);
            if (taikhoan != null) {
                List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
                List<Quyen> listQuyen = quyenService.findByTrangthaiOrderByIdDesc("active");
                model.addAttribute("listNhanvien", listNhanvien);
                model.addAttribute("listQuyen", listQuyen);
                model.addAttribute("taikhoan", taikhoan);
                return "suataikhoan";
            } else {
                return "redirect:/403";
            }
        } catch (Exception e) {
            return "redirect:/403";
        }
    }

    @PostMapping("/taikhoan")
    String themTaiKhoan(@ModelAttribute("taikhoan") Taikhoan taikhoan,
                        @RequestParam("nhanvien") Integer nhanvien, @RequestParam("quyen") List<Integer> quyen,
                        RedirectAttributes redirectAttributes) {
        try {
            Nhanvien nhanVienById = nhanVienService.findById(nhanvien);
            HashSet<Quyen> hsquyen = new HashSet<>();

            for (Integer q : quyen) {
                hsquyen.add(quyenService.findById(q));
            }

            taikhoan.setMatkhau(passwordEncoder.encode(taikhoan.getMatkhau()));
            taikhoan.setTrangthai("active");
            taikhoan.setNhanvien(nhanVienById);
            taikhoan.setQuyens(hsquyen);
            taikhoanService.saveOrUpdate(taikhoan);
            redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
        }

        return "redirect:/admin/taikhoan?trangthai=active";
    }

    @PatchMapping(value = "/taikhoan", params = "update")
    String suaTaiKhoan(//@ModelAttribute("taikhoan") Taikhoan taikhoan,
                       @RequestParam("id") Integer id,
                       @RequestParam("username") String username,
                       @RequestParam("email") String email,
                       @RequestParam("thongtinkhac") String thongtinkhac,
                       @RequestParam(value = "matkhau", defaultValue = "null") String matkhau,
                       @RequestParam("nhanvien") Integer nhanvien, @RequestParam("quyen") List<Integer> quyen,
                       RedirectAttributes redirectAttributes) {
        try {

            Taikhoan taikhoan = taikhoanService.findById(id);
            Nhanvien nhanVienById = nhanVienService.findById(nhanvien);
            HashSet<Quyen> hsquyen = new HashSet<>();

            for (Integer q : quyen) {
                hsquyen.add(quyenService.findById(q));
            }
            if (!matkhau.equals("null")) {
                taikhoan.setMatkhau(passwordEncoder.encode(matkhau));
            }
            taikhoan.setUsername(username);
            taikhoan.setEmail(email);
            //taikhoan.setTrangthai("active");
            taikhoan.setNhanvien(nhanVienById);
            taikhoan.setQuyens(hsquyen);
            taikhoanService.saveOrUpdate(taikhoan);
            redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
        }

        return "redirect:/admin/taikhoan?trangthai=active";
    }

    @PatchMapping(value = "/taikhoan", params = "deleted")
    String xoaVinhVienTaiKhoan(//@ModelAttribute("taikhoan") Taikhoan taikhoan,
                               @RequestParam("id") Integer id,

                               RedirectAttributes redirectAttributes) {
        try {

            Taikhoan taikhoan = taikhoanService.findById(id);

            taikhoanService.deleted(taikhoan);
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
        }

        return "redirect:/admin/taikhoan?trangthai=active";
    }

    @DeleteMapping("/taikhoan")
    String xoaTaiKhoan(@RequestParam("arrayId") List<Integer> arrayId,
                       RedirectAttributes redirectAttributes) {

        try {
            arrayId.forEach(x -> {

                Taikhoan taikhoan = taikhoanService.findById(x);
                taikhoan.setTrangthai("deleted");
                taikhoanService.saveOrUpdate(taikhoan);

            });

            redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
        }

        return "redirect:/admin/taikhoan?trangthai=active";
    }
}
