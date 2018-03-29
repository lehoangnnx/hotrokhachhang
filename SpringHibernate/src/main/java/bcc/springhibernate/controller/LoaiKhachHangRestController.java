package bcc.springhibernate.controller;

import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.service.LoaiKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class LoaiKhachHangRestController {

    @Autowired
    LoaiKhachHangService loaiKhachHangService;

    @PostMapping("/kiemtratenloaikhachhang")
    String kiemTraTenLoaiKhachHang(@RequestBody Loaikhachhang loaikhachhang) {
        System.out.println(loaikhachhang.getId());
        Loaikhachhang loaikhachhangByTenLoai = null;
        try {
            loaikhachhangByTenLoai = loaiKhachHangService.findByTenloai(loaikhachhang.getTenloai());
            if (loaikhachhang.getId() == null) {
                System.out.println(1);
                if (loaikhachhangByTenLoai == null) {
                    System.out.println(1 - 1);
                    return "success";
                }
            } else {
                if (loaikhachhangByTenLoai != null) {
                    if (loaikhachhang.getId() == loaikhachhangByTenLoai.getId()) {
                        System.out.println(2 - 2);
                        return "success";

                    }
                } else {
                    return "success";
                }
            }

        } catch (Exception e) {
            return "error";
        }
        return "error";
    }
}
