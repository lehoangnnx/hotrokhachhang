package bcc.springhibernate.controller;

import bcc.springhibernate.model.Chitiethoadon;
import bcc.springhibernate.service.ChiTietHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class ChiTietHoaDonRestController {

    @Autowired
    ChiTietHoaDonService chiTietHoaDonService;

    @PostMapping("/updatechitiethoadon")
    String updateChiTietHoaDon(@RequestBody Chitiethoadon chitiethoadon) {


        try {

            Chitiethoadon getChiTietHoaDonById = chiTietHoaDonService.findById(chitiethoadon.getId());

            chiTietHoaDonService.delete(getChiTietHoaDonById);
            return "success";
        } catch (Exception e) {
            return "error";
        }

    }
}
