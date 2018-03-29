package bcc.springhibernate.controller;


import bcc.springhibernate.model.Quyen;
import bcc.springhibernate.service.QuyenService;
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
public class QuyenController {

    @Autowired
    QuyenService quyenService;

    @GetMapping("/quyen")
    String pageDanhSachQuyen(@RequestParam(value = "trangthai", defaultValue = "active") String trangthai,
                             Model model) {
        List<Quyen> listQuyen = quyenService.findByTrangthaiOrderByIdDesc(trangthai);
        model.addAttribute("listQuyen", listQuyen);
        return "danhsachquyen";
    }

    @GetMapping("/quyen/add")
    String pageThemQuyen(Model model) {

        model.addAttribute("quyen", new Quyen());
        return "themquyen";
    }

    @GetMapping("/quyen/{id}")
    String pageSuaQuyen(Model model, @PathVariable("id") Integer id) {
        try {
            Quyen quyen = quyenService.findById(id);
            if (quyen != null) {
                model.addAttribute("quyen", quyen);
                return "suaquyen";
            } else {
                return "redirect:/403";
            }
        } catch (Exception e) {
            return "redirect:/403";
        }
    }

    @PostMapping("/quyen")
    String themQuyen(@ModelAttribute("quyen") Quyen quyen,
                     RedirectAttributes redirectAttributes) {
        try {

            quyen.setTrangthai("active");
            quyenService.saveOrUpdate(quyen);
            redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
        }

        return "redirect:/admin/quyen?trangthai=active";
    }

    @PatchMapping(value = "/quyen", params = "update")
    String suaQuyen(@ModelAttribute("quyen") Quyen quyen,
                    RedirectAttributes redirectAttributes) {
        try {
            //quyen.setTrangthai("active");
            quyenService.saveOrUpdate(quyen);
            redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
        }

        return "redirect:/admin/quyen?trangthai=active";
    }

    @PatchMapping(value = "/quyen", params = "deleted")
    String xoaVinhVienQuyen(@ModelAttribute("quyen") Quyen quyen,
                            RedirectAttributes redirectAttributes) {
        try {

            quyenService.deleted(quyen);
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
        }

        return "redirect:/admin/quyen?trangthai=active";
    }

    @DeleteMapping("/quyen")
    String xoaQuyen(@RequestParam("arrayId") List<Integer> arrayId,
                    RedirectAttributes redirectAttributes) {

        try {
            arrayId.forEach(x -> {

                Quyen quyen = quyenService.findById(x);
                quyen.setTrangthai("deleted");
                quyenService.saveOrUpdate(quyen);

            });

            redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
        }

        return "redirect:/admin/quyen?trangthai=active";
    }
}
