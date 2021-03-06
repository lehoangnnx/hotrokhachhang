package bcc.springhibernate.controller;

import bcc.springhibernate.model.Hoadon;
import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhanvienkpi;
import bcc.springhibernate.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class ThongKeKpiController {

    @Autowired
    NhanVienKpiService nhanVienKpiService;
    @Autowired
    HoaDonService hoaDonService;
    @Autowired
    ChamSocService chamSocService;
    @Autowired
    KpiService kpiService;
    @Autowired
    NhanVienService nhanVienService;

    @GetMapping("/thongkekpi")
    String pageThongKeKpi(@RequestParam(value = "tungay", defaultValue = "null") String tungay,
                          @RequestParam(value = "denngay", defaultValue = "null") String denngay,
                          @RequestParam(value = "nhanvien", defaultValue = "0") Integer nhanvien,
                          @RequestParam(value = "kpi", defaultValue = "0") Integer kpi, Model model) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date ddenngay = new Date();
            Date dtungay = new Date(ddenngay.getYear(), ddenngay.getMonth(), 01);
            if (!tungay.equals("null")) {
                dtungay = dateFormat.parse(tungay);
            }
            if (!denngay.equals("null")) {
                ddenngay = dateFormat.parse(denngay);
            }
            List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiNotOrderByIdDesc("deleted");
            List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
            // List<Nhanvienkpi> listNhanvienkpi =
            // nhanVienKpiService.findByKpiAndTrangthaiNotOrderByNgaydangkyDesc(kpiById,"deleted");
            List<Kpi> listKpi = kpiService.findByTrangthaiNotOrderByIdDesc("deleted");
            Kpi kpiById = null;
            if (kpi == 0) {
                kpiById = kpiService.findById(listKpi.get(0).getId());
            } else {
                kpiById = kpiService.findById(kpi);
            }

            List<Nhanvienkpi> listNhanvienkpi = null;
            if (nhanvien == 0) {
                listNhanvienkpi = nhanVienKpiService.findByKpiAndTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc(
                        kpiById, "deleted", dtungay, ddenngay);
            } else {
                Nhanvien getNhanVienById = nhanVienService.findById(nhanvien);
                listNhanvienkpi = nhanVienKpiService
                        .findByNhanvienAndKpiAndTrangthaiNotAndNgaydangkyBetweenOrderByNgaydangkyDesc(getNhanVienById,
                                kpiById, "deleted", dtungay, ddenngay);
            }

            if (!listNhanvienkpi.isEmpty()) {
                for (Nhanvienkpi nvk : listNhanvienkpi) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    Long tiendatra = 0L;
                    int khachhangmoi = 0;
                    int khachhangtailap = 0;
                    int tongsolanchamsoc = chamSocService
                            .findByNhanvienchamsocAndTrangthaiNotAndNgayBetweenOrderByIdDesc(nvk.getNhanvien().getId(),
                                    "deleted", nvk.getNgaydangky(), nvk.getNgayhoanthanh())
                            .size();
					/*List<Hoadon> listHoadon = hoaDonService
							.findByNhanvienByIdnhanvienbanAndTrangthaiNotAndNgaylapBetweenOrderByIdDesc(
									nvk.getNhanvien(), "deleted", nvk.getNgaydangky(), nvk.getNgayhoanthanh());*/
                    List<Hoadon> listHoadon = hoaDonService.findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc("deleted",
                            nvk.getNhanvien(), dtungay, ddenngay);
                    for (Hoadon hd : listHoadon) {
                        tiendatra += hd.getTiendatra();
                        if (hd.getHoadondautien() != null) {
                            if (hd.getHoadondautien() == true) {
                                khachhangmoi += 1;
                            } else if (hd.getHoadondautien() == false) {
                                khachhangtailap += 1;
                            }
                        }
                    }

                    map.put("idnvkpi", nvk.getId());
                    map.put("idnv", nvk.getNhanvien().getId());
                    map.put("tiendatra", tiendatra);
                    map.put("khachhangmoi", khachhangmoi);
                    map.put("khachhangtailap", khachhangtailap);
                    map.put("tongsolanchamsoc", tongsolanchamsoc);
                    listMap.add(map);


                    model.addAttribute("listMap", listMap);

                }
            }
            model.addAttribute("listNhanvienkpi", listNhanvienkpi);
            model.addAttribute("listNhanvien", listNhanvien);
            model.addAttribute("listKpi", listKpi);
            model.addAttribute("tungay", dtungay);
            model.addAttribute("denngay", ddenngay);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "thongkekpi";
    }
}
