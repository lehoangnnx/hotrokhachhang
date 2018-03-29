package bcc.springhibernate.controller;

import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class NhanVienRestController {

    @Autowired
    NhanVienService nhanVienService;

    @PostMapping("/kiemtramanhanvien")
    String kiemTraMaNhanVien(@RequestBody Nhanvien nhanvien
    ) {

        Nhanvien nhanVienByMaNhanVien = null;
        try {
            System.out.println(nhanvien.getManhanvien());
            nhanVienByMaNhanVien = nhanVienService.findByManhanvien(nhanvien.getManhanvien());
            if (nhanvien.getId() == null) {

                if (nhanVienByMaNhanVien == null) {

                    return "success";
                }
            } else {
                if (nhanVienByMaNhanVien != null) {
                    System.out.println("XOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo");
                    if (nhanvien.getId() == nhanVienByMaNhanVien.getId()) {

                        return "success";

                    }

                } else {
                    return "success";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "error";
    }
}
