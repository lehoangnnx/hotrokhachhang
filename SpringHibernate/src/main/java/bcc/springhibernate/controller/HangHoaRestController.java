package bcc.springhibernate.controller;

import bcc.springhibernate.model.Hanghoa;
import bcc.springhibernate.service.HangHoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class HangHoaRestController {

    @Autowired
    HangHoaService hangHoaService;

    @PostMapping("/gethanghoabyid")
    Map<String, Object> getHangHoa(@RequestBody Integer id) {

        Hanghoa hanghoa = null;
        Map<String, Object> hangHoaMap = new HashMap<String, Object>();
        try {
            hanghoa = hangHoaService.findById(id);
            hangHoaMap.put("id", hanghoa.getId());
            hangHoaMap.put("mahang", hanghoa.getMahang());
            hangHoaMap.put("tenhang", hanghoa.getTenhang());
           /* if (hanghoa.getGiakhuyenmai() != null) {
                hangHoaMap.put("giaban", hanghoa.getGiakhuyenmai());
            } else {
                hangHoaMap.put("giaban", hanghoa.getGiaban());
            }*/
            hangHoaMap.put("giaban", hanghoa.getGiaban());
            hangHoaMap.put("giabanle", hanghoa.getGiabanle());
            hangHoaMap.put("giakhuyenmai", hanghoa.getGiakhuyenmai());
            System.out.println("GKM : " + hanghoa.getGiakhuyenmai().longValue());
        } catch (Exception e) {

        }
        return hangHoaMap;

    }

    @PostMapping("/kiemtramahang")
    String kiemTraTenNhomKhachHang(@RequestBody Hanghoa hanghoa) {

        Hanghoa hangHoaByMaHang = null;
        try {
            hangHoaByMaHang = hangHoaService.findByMahang(hanghoa.getMahang());
            if (hanghoa.getId() == null) {

                if (hangHoaByMaHang == null) {

                    return "success";
                }
            } else {
                if (hangHoaByMaHang != null) {
                    if (hanghoa.getId() == hangHoaByMaHang.getId()) {

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

    @PostMapping("/kiemtragiaban")
    String kiemtragiaban(@RequestBody Hanghoa getHangHoa) {

        Hanghoa hanghoa = null;
        try {
            hanghoa = hangHoaService.findById(getHangHoa.getId());
            if (hanghoa.getGianhap() > getHangHoa.getGiaban()) {
                return "success";
            } else {
                return "error";
            }


        } catch (Exception e) {
            return "error";
        }

    }
}
