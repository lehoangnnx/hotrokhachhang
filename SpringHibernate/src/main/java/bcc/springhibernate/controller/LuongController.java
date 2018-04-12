package bcc.springhibernate.controller;

import bcc.springhibernate.model.Luong;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.service.LoaiKhachHangService;
import bcc.springhibernate.service.LuongService;
import bcc.springhibernate.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin")
public class LuongController {

    @Autowired
    LoaiKhachHangService loaiKhachHangService;
    @Autowired
    LuongService LuongService;
    @Autowired
    NhanVienService nhanVienService;

    @GetMapping("/luong")
    String pageDanhSachLuong(@RequestParam(value = "trangthai", defaultValue = "active") String trangthai,
                             @RequestParam(value = "thang", defaultValue = "null") String thang,
                             @RequestParam(value = "nam", defaultValue = "null") String nam,
                             Model model) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String splitDate[] = df.format(date).split("/|-");

        if(thang.equals("null") && nam.equals("null")){
            thang = splitDate[1];
            nam = splitDate[2];
        }
        List<Luong> listLuong = LuongService.findByTrangthaiAndThangAndNam(trangthai,thang,nam);
        model.addAttribute("listLuong", listLuong);
        model.addAttribute("thang", thang);
        model.addAttribute("nam", nam);
        return "danhsachluong";
    }

    @GetMapping("/luong/add")
    String pageThemLuong(Model model) {
        List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
        model.addAttribute("listNhanvien", listNhanvien);
        model.addAttribute("luong", new Luong());
        return "themluong";
    }

    @GetMapping("/luong/{id}")
    String pageSuaLuong(Model model, @PathVariable("id") Integer id) {
        try {
            Luong luong = LuongService.findById(id);
            if (luong != null) {
                List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
                model.addAttribute("listNhanvien", listNhanvien);
                model.addAttribute("luong", luong);
                return "sualuong";
            } else {
                return "redirect:/403";
            }
        } catch (Exception e) {
            return "redirect:/403";
        }
    }

    @PostMapping("/luong")
    String themLuong(@ModelAttribute("luong") Luong luong, @RequestParam("nhanvien") Integer nhanvien,
                     @RequestParam("luong_money") String tienluong, @RequestParam("thuong_money") String thuong,
                     @RequestParam("thang") String thang, @RequestParam("nam") String nam,
                     @RequestParam("trangthai") String trangthai, RedirectAttributes redirectAttributes) {
        try {
            Nhanvien getNhanvienById = nhanVienService.findById(nhanvien);
            luong.setNhanvien(getNhanvienById);
            luong.setThang(thang);
            luong.setNam(nam);
            luong.setTrangthai(trangthai);
            luong.setLuong(Long.valueOf(tienluong.replaceAll("\\.|\\,|\\s", "")));
            luong.setThuong(Long.valueOf(thuong.replaceAll("\\.|\\,|\\s", "")));
            luong.setThuongcuahoadon(0L);
            LuongService.saveOrUpdate(luong);
            redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
        }
        return "redirect:/admin/luong?trangthai=chuatraluong";
    }

    @PatchMapping(value = "/luong", params = "update")
    String suaLuong(@ModelAttribute("luong") Luong luong, @RequestParam("nhanvien") Integer nhanvien,
                    @RequestParam("luong_money") String tienluong, @RequestParam("thuong_money") String thuong,
                    @RequestParam("thang") String thang, @RequestParam("nam") String nam,
                    @RequestParam("trangthai") String trangthai, RedirectAttributes redirectAttributes) {
        try {
            Nhanvien getNhanvienById = nhanVienService.findById(nhanvien);
            luong.setNhanvien(getNhanvienById);
            luong.setThang(thang);
            luong.setNam(nam);
            luong.setTrangthai(trangthai);
            luong.setLuong(Long.valueOf(tienluong.replaceAll("\\.|\\,|\\s", "")));
            luong.setThuong(Long.valueOf(thuong.replaceAll("\\.|\\,|\\s", "")));

            LuongService.saveOrUpdate(luong);
            redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
        }
        return "redirect:/admin/luong?trangthai=chuatraluong";
    }

    @PatchMapping(value = "/luong", params = "deleted")
    String xoaVinhVienLuong(@ModelAttribute("luong") Luong luong, RedirectAttributes redirectAttributes) {
        try {

            LuongService.deleted(luong);
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
        }
        return "redirect:/admin/luong?trangthai=chuatraluong";
    }

    @DeleteMapping("/luong")
    String xoaLuong(@RequestParam("arrayId") List<Integer> arrayId, RedirectAttributes redirectAttributes) {

        try {
            arrayId.forEach(x -> {

                Luong luong = LuongService.findById(x);
                luong.setTrangthai("deleted");
                LuongService.saveOrUpdate(luong);

            });

            redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
        }

        return "redirect:/admin/luong?trangthai=chuatraluong";
    }
}
