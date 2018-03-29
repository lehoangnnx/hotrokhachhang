package bcc.springhibernate.controller;

import bcc.springhibernate.model.Luong;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.service.LuongService;
import bcc.springhibernate.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class LuongRestController {

    @Autowired
    LuongService luongService;
    @Autowired
    NhanVienService nhanVienService;

    @PostMapping("/kiemtraluong")
    String kiemTraLuong(@RequestParam("thang") String thang, @RequestParam("nam") String nam,
                        @RequestParam(value = "id", defaultValue = "0") Integer id, @RequestParam("nhanvien") Integer nhanvien) {
        Luong luong = null;
        try {
            System.out.println(thang + "-" + nam + "-" + id + "-" + nhanvien);
            Nhanvien nhanVienById = nhanVienService.findById(nhanvien);
            System.out.println(nhanVienById.getTennhanvien());
            luong = luongService.findOneByNhanvienAndThangAndNam(nhanVienById, thang, nam);

            if (id == 0) {
                if (luong == null) {
                    System.out.println(1);
                    return "success";
                }
            } else {
                if (luong != null) {
                    if (id == luong.getId()) {
                        System.out.println(2);
                        return "success";
                    }
                } else {
                    return "success";
                }
            }
        } catch (Exception e) {
            System.out.println(3 + e.getMessage());
            e.printStackTrace();
            return "error";
        }
        return "error";
    }
}
