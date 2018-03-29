package bcc.springhibernate.controller;

import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.service.NhomKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class NhomKhachHangRestController {

    @Autowired
    NhomKhachHangService nhomKhachHangService;

    @PostMapping("/kiemtratennhomkhachhang")
    String kiemTraTenNhomKhachHang(@RequestBody Nhomkhachhang nhomkhachhang) {

        Nhomkhachhang nhomkhachhangByTenNhom = null;
        try {
            nhomkhachhangByTenNhom = nhomKhachHangService.findByTennhom(nhomkhachhang.getTennhom());
            if (nhomkhachhang.getId() == null) {

                if (nhomkhachhangByTenNhom == null) {

                    return "success";
                }
            } else {
                if (nhomkhachhangByTenNhom != null) {
                    if (nhomkhachhang.getId() == nhomkhachhangByTenNhom.getId()) {

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
