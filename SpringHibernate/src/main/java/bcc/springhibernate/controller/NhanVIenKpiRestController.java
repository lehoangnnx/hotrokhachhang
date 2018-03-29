package bcc.springhibernate.controller;

import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhanvienkpi;
import bcc.springhibernate.service.KpiService;
import bcc.springhibernate.service.NhanVienKpiService;
import bcc.springhibernate.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/admin")
public class NhanVIenKpiRestController {

    @Autowired
    NhanVienKpiService nhanVienKpiService;
    @Autowired
    NhanVienService nhanVienService;
    @Autowired
    KpiService kpiService;

    @PostMapping("/updatetrangthainhanvienkpi")
    String updatetrangthainhanvienkpi(@RequestParam("id") Integer id, @RequestParam("trangthai") String trangthai) {

        try {

            Nhanvienkpi nhanvienkpi = nhanVienKpiService.findById(id);
            nhanvienkpi.setTrangthai(trangthai);
            nhanVienKpiService.saveOrUpdate(nhanvienkpi);

            return trangthai;
        } catch (Exception e) {
            return "error";
        }

    }

    @PostMapping("/kiemtratennhanvienvakpi")
    String kiemtratennhanvienvakpi(@RequestParam(value = "id", defaultValue = "0") Integer id,
                                   @RequestParam(value = "idnhanvien", defaultValue = "0") Integer idnhanvien,
                                   @RequestParam(value = "idkpi", defaultValue = "0") Integer idkpi) {

        Nhanvienkpi nhanvienkpiByNhanvienAndKpi = null;
        Nhanvienkpi nhanvienkpiById = null;
        Date ddenngay = new Date();
        Date dtungay = new Date(ddenngay.getYear(), ddenngay.getMonth(), 01);
        System.out.println(id + "iiiiiidđ" + idnhanvien + "32" + idkpi);
        try {
            Nhanvien nhanvien = nhanVienService.findById(idnhanvien);
            Kpi kpi = kpiService.findById(idkpi);

            nhanvienkpiByNhanvienAndKpi = nhanVienKpiService.findByTrangthaiAndNhanvienAndKpiAndMonthYearNgaydangky
                    ("deleted", nhanvien, kpi, dtungay, ddenngay);
            System.out.println(nhanvienkpiByNhanvienAndKpi);
            if (id == 0) {
                System.out.println(1);
                if (nhanvienkpiByNhanvienAndKpi == null) {
                    System.out.println(1);
                    return "success";
                }
            } else {
                System.out.println(3);
                nhanvienkpiById = nhanVienKpiService.findById(id);
                if (nhanvienkpiByNhanvienAndKpi != null) {
                    System.out.println(4);
                    if (id == nhanvienkpiByNhanvienAndKpi.getId() && nhanvienkpiById.getKpi().getId() == idkpi) {
                        System.out.println(5);
                        return "success";

                    }
                } else {
                    System.out.println(6);
                    return "success";
                }
            }

        } catch (Exception e) {

            return "error";
        }
        return "error";
    }
}
