package bcc.springhibernate.controller;

import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.service.NhomHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class NhomHangRestController {

    @Autowired
    NhomHangService nhomHangService;

    @PostMapping("/kiemtramanhomhang")
    String kiemTraTenNhomKhachHang(@RequestBody Nhomhang nhomhang) {

        Nhomhang nhomhangByMaNhom = null;
        try {
            nhomhangByMaNhom = nhomHangService.findByManhom(nhomhang.getManhom());
            if (nhomhang.getId() == null) {

                if (nhomhangByMaNhom == null) {

                    return "success";
                }
            } else {
                if (nhomhangByMaNhom != null) {
                    if (nhomhang.getId() == nhomhangByMaNhom.getId()) {

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
