package bcc.springhibernate.controller;

import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class KhachHangRestController {

    @Autowired
    KhachHangService khachHangService;

    @PostMapping("/kiemtramakhachhang")
    String kiemTraMaKhachHang(@RequestBody Khachhang khachhang) {

        Khachhang khachHangByMaKh = null;
        try {
            khachHangByMaKh = khachHangService.findByMakh(khachhang.getMakh());
            if (khachhang.getId() == null) {

                if (khachHangByMaKh == null) {

                    return "success";
                }
            } else {
                if (khachHangByMaKh != null) {
                    if (khachhang.getId() == khachHangByMaKh.getId()) {

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

    @PostMapping("/updatethongbaokhachhang")
    String updateThongBaoKhachHang(@RequestParam("id") Integer id) {

        try {

            Khachhang khachhang = khachHangService.findById(id);

            khachhang.setTrangthainhac("dasinhnhat");
            khachHangService.saveOrUpdate(khachhang);
            return "success";
        } catch (Exception e) {
            return "error";
        }

    }

    @PostMapping("/getsolanchamsocvadamphankhachhang")
    Map<String, Object> getsolanchamsocvadamphankhachhang(@RequestBody Integer id) {

        Map<String, Object> khachHangMap = new HashMap<String, Object>();
        try {

            Khachhang khachhang = khachHangService.findById(id);
            khachHangMap.put("solanchamsoc", khachhang.getSolanchamsoc());

            khachHangMap.put("solandamphan", khachhang.getSolandamphan());
        } catch (Exception e) {

        }
        return khachHangMap;
    }

    @PostMapping("/updateuutienkhachhang")
    String updateuutienkhachhang(@RequestParam("id") Integer id, @RequestParam("uutien") String uutien) {

        try {

            Khachhang khachhang = khachHangService.findById(id);

            khachhang.setUutien(uutien);
            khachHangService.saveOrUpdate(khachhang);
            return uutien.toString();
        } catch (Exception e) {
            return "error";
        }

    }

    @PostMapping("/getthongtinkhachhang")
    Map<String, Object> getThongTinKhachHang(@RequestBody Integer id) {

        Map<String, Object> khachHangMap = new HashMap<String, Object>();
        try {

            Khachhang khachhang = khachHangService.findById(id);
            khachHangMap.put("diachi", khachhang.getDiachi());

            khachHangMap.put("sodienthoai", khachhang.getSodienthoai());
        } catch (Exception e) {

        }
        return khachHangMap;
    }
}
