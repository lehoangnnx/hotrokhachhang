package bcc.springhibernate.controller;

import bcc.springhibernate.model.Luong;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Ungluong;
import bcc.springhibernate.service.LuongService;
import bcc.springhibernate.service.NhanVienService;
import bcc.springhibernate.service.UngLuongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin")
public class UngLuongController {

    @Autowired
    UngLuongService ungLuongService;
    @Autowired
    LuongService luongService;
    @Autowired
    NhanVienService nhanVienService;

    @GetMapping("/ungluong")
    String pageDanhSachUngLuong(@RequestParam(value = "trangthai", defaultValue = "active") String trangthai,
                                Model model) {
        List<Ungluong> listUngLuong = ungLuongService.findByTrangthaiOrderByIdDesc(trangthai);
        model.addAttribute("listUngLuong", listUngLuong);
        return "danhsachungluong";
    }

    @GetMapping("/ungluong/add")
    String pageThemUngLuong(Model model) {
        List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
        model.addAttribute("listNhanvien", listNhanvien);
        model.addAttribute("ungluong", new Ungluong());
        return "themungluong";
    }

    @GetMapping("/ungluong/{id}")
    String pageSuaUngLuong(Model model, @PathVariable("id") Integer id) {
        try {
            Ungluong ungluong = ungLuongService.findById(id);
            if (ungluong != null) {
                List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");
                model.addAttribute("listNhanvien", listNhanvien);
                model.addAttribute("ungluong", ungluong);
                return "suaungluong";
            } else {
                return "redirect:/403";
            }
        } catch (Exception e) {
            return "redirect:/403";
        }
    }

    @PostMapping("/ungluong")
    String themUngLuong(@ModelAttribute("ungluong") Ungluong ungluong, @RequestParam("nhanvien") Integer nhanvien,
                        @RequestParam("sotienung_money") String sotienung,
                        @RequestParam("ngayung") String ngayung,
                        RedirectAttributes redirectAttributes) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String splitngayung[] = ngayung.split("/");
            Nhanvien getNhanvienById = nhanVienService.findById(nhanvien);
            Luong luong = luongService.findOneByNhanvienAndThangAndNam(getNhanvienById, splitngayung[1], splitngayung[2]);
            ungluong.setLuong(luong);
            ungluong.setSotienung(Long.valueOf(sotienung.replaceAll("\\.|\\,|\\s", "")));
            ungluong.setNgayung(df.parse(ngayung));
            ungluong.setTrangthai("active");
            ungLuongService.saveOrUpdate(ungluong);
            redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
        }
        return "redirect:/admin/ungluong?trangthai=active";
    }

    @PatchMapping(value = "/ungluong", params = "update")
    String suaUngLuong(@ModelAttribute("ungluong") Ungluong ungluong, @RequestParam("nhanvien") Integer nhanvien,
                       @RequestParam("sotienung_money") String sotienung,
                       @RequestParam("ngayung") String ngayung,
                       RedirectAttributes redirectAttributes) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String splitngayung[] = ngayung.split("/");
            Nhanvien getNhanvienById = nhanVienService.findById(nhanvien);
            Luong luong = luongService.findOneByNhanvienAndThangAndNam(getNhanvienById, splitngayung[1], splitngayung[2]);
            ungluong.setLuong(luong);
            ungluong.setSotienung(Long.valueOf(sotienung.replaceAll("\\.|\\,|\\s", "")));
            ungluong.setNgayung(df.parse(ngayung));
            ungLuongService.saveOrUpdate(ungluong);
            redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
        }
        return "redirect:/admin/ungluong?trangthai=active";
    }

    @PatchMapping(value = "/ungluong", params = "deleted")
    String xoaVinhVienUngLuong(@ModelAttribute("ungluong") Ungluong ungluong, RedirectAttributes redirectAttributes) {
        try {

            ungLuongService.deleted(ungluong);
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
        }
        return "redirect:/admin/ungluong?trangthai=active";
    }

    @DeleteMapping("/ungluong")
    String xoaUngLuong(@RequestParam("arrayId") List<Integer> arrayId, RedirectAttributes redirectAttributes) {

        try {
            arrayId.forEach(x -> {

                Ungluong ungluong = ungLuongService.findById(x);
                ungluong.setTrangthai("deleted");
                ungLuongService.saveOrUpdate(ungluong);

            });

            redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
        }

        return "redirect:/admin/ungluong?trangthai=active";
    }
}
