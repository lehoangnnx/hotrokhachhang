package bcc.springhibernate.controller;


import bcc.springhibernate.model.Hanghoa;
import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.service.HangHoaService;
import bcc.springhibernate.service.NhomHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

@RequestMapping("/admin")
public class HangHoaController {

    @Autowired
    HangHoaService hangHoaService;
    @Autowired
    NhomHangService nhomHangService;

    @PreAuthorize("hasAnyRole('ADMIN','BANHANG')")
    @GetMapping("/hanghoa")
    String pageDanhSachHangHoa(@RequestParam(value = "trangthai", defaultValue = "active") String trangthai, Model model) {
        List<Hanghoa> listHanghoa = hangHoaService.findByTrangthaiOrderByIdDesc(trangthai);
        model.addAttribute("listHanghoa", listHanghoa);
        return "danhsachhanghoa";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/hanghoa/add")
    String pageThemHangHoa(Model model) {
        List<Nhomhang> listNhomhang = nhomHangService.findByTrangthaiOrderByIdDesc("active");
        model.addAttribute("listNhomhang", listNhomhang);
        model.addAttribute("hanghoa", new Hanghoa());
        return "themhanghoa";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/hanghoa/{id}")
    String pageSuaHangHoa(Model model, @PathVariable("id") Integer id) {
        try {
            Hanghoa hanghoa = hangHoaService.findById(id);
            if (hanghoa != null) {
                List<Nhomhang> listNhomhang = nhomHangService.findByTrangthaiOrderByIdDesc("active");
                model.addAttribute("listNhomhang", listNhomhang);
                model.addAttribute("hanghoa", hanghoa);
                return "suahanghoa";
            } else {
                return "redirect:/403";
            }
        } catch (Exception e) {
            return "redirect:/403";
        }
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/hanghoa")
    String themHangHoa(@ModelAttribute("hanghoa") Hanghoa hanghoa,
                       @RequestParam("giaban_money") String giaban,
                       @RequestParam("giabanle_money") String giabanle,
                       @RequestParam("gianhap_money") String gianhap,
                       @RequestParam(value = "_giakhuyenmai", defaultValue = "") String giakhuyenmai,
                       @RequestParam("nhomhang") Integer nhomhang,
                       RedirectAttributes redirectAttributes) {
        try {
            Nhomhang nhomhangById = nhomHangService.findById(nhomhang);
            hanghoa.setTrangthai("active");
            hanghoa.setNhomhang(nhomhangById);
            hanghoa.setGiaban(Long.valueOf(giaban.replaceAll("\\.|\\,|\\s", "")));
            hanghoa.setGiabanle(Long.valueOf(giabanle.replaceAll("\\.|\\,|\\s", "")));
            hanghoa.setGianhap(Long.valueOf(gianhap.replaceAll("\\.|\\,|\\s", "")));
            if (!giakhuyenmai.equals("")) {
                hanghoa.setGiakhuyenmai(Long.valueOf(giakhuyenmai.replaceAll("\\.|\\,|\\s", "")));
            }
            hangHoaService.saveOrUpdate(hanghoa);
            redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
        }

        return "redirect:/admin/hanghoa?trangthai=active";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PatchMapping(value = "/hanghoa", params = "update")
    String suaHangHoa(@ModelAttribute("hanghoa") Hanghoa hanghoa,
                      @RequestParam("giaban_money") String giaban,
                      @RequestParam("giabanle_money") String giabanle,
                      @RequestParam("gianhap_money") String gianhap,
                      @RequestParam(value = "_giakhuyenmai", defaultValue = "") String giakhuyenmai,
                      @RequestParam("nhomhang") Integer nhomhang,
                      RedirectAttributes redirectAttributes) {
        try {
            Nhomhang nhomhangById = nhomHangService.findById(nhomhang);
            //hanghoa.setTrangthai("active");
            hanghoa.setNhomhang(nhomhangById);
            hanghoa.setGiaban(Long.valueOf(giaban.replaceAll("\\.|\\,|\\s", "")));
            hanghoa.setGiabanle(Long.valueOf(giabanle.replaceAll("\\.|\\,|\\s", "")));
            hanghoa.setGianhap(Long.valueOf(gianhap.replaceAll("\\.|\\,|\\s", "")));
            if (!giakhuyenmai.equals("")) {
                hanghoa.setGiakhuyenmai(Long.valueOf(giakhuyenmai.replaceAll("\\.|\\,|\\s", "")));
            }
            hangHoaService.saveOrUpdate(hanghoa);
            redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
        }

        return "redirect:/admin/hanghoa?trangthai=active";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PatchMapping(value = "/hanghoa", params = "deleted")
    String xoaVinhVienHangHoa(@ModelAttribute("hanghoa") Hanghoa hanghoa,
                              RedirectAttributes redirectAttributes) {
        try {

            hangHoaService.deleted(hanghoa);
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
        }

        return "redirect:/admin/hanghoa?trangthai=active";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/hanghoa")
    String xoaHangHoa(@RequestParam("arrayId") List<Integer> arrayId,
                      RedirectAttributes redirectAttributes) {

        try {
            arrayId.forEach(x -> {

                Hanghoa hanghoa = hangHoaService.findById(x);
                hanghoa.setTrangthai("deleted");
                hangHoaService.saveOrUpdate(hanghoa);

            });

            redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
        }

        return "redirect:/admin/hanghoa?trangthai=active";
    }
}
