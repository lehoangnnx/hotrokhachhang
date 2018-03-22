package bcc.springhibernate.controller;

import bcc.springhibernate.model.*;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.service.HoaDonService;
import bcc.springhibernate.service.LuongService;
import bcc.springhibernate.service.NhanVienKpiService;
import bcc.springhibernate.service.NhanVienService;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin")
public class SaoKeHoaDonController {
    @Autowired
    HoaDonService hoaDonService;
    @Autowired
    LuongService luongService;
    @Autowired
    NhanVienKpiService nhanVienKpiService;
    @Autowired
    NhanVienService nhanVienService;

    @GetMapping("/saokehoadon")
    String pageSaoKeHoaDon(@RequestParam(value = "tungay", defaultValue = "null") String tungay,
                           @RequestParam(value = "denngay", defaultValue = "null") String denngay,
                           @RequestParam(value = "nhanvienbanhang", defaultValue = "0") Integer nhanvienbanhang, Model model,
                           RedirectAttributes redirectAttributes) {
        List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiNotOrderByIdDesc("deleted");
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date ddenngay = new Date();
            Date dtungay = new Date(ddenngay.getYear(), ddenngay.getMonth(), 01);


            int thang = 0;
            int nam = 0;
            if (!tungay.equals("null")) {
                dtungay = df.parse(tungay);
                thang = Integer.parseInt(tungay.substring(3, 5));
                nam = Integer.parseInt(tungay.substring(6, 10));


            } else {
                thang = Integer.parseInt(df.format(dtungay).substring(3, 5));
                nam = Integer.parseInt(df.format(dtungay).substring(6, 10));


            }
            if (!denngay.equals("null")) {
                ddenngay = df.parse(denngay);
            }
            Nhanvien getnhanvienbanhang = null;
            if (nhanvienbanhang != 0) {
                getnhanvienbanhang = nhanVienService.findById(nhanvienbanhang);
            } else {
                getnhanvienbanhang = listNhanvien.get(0);
            }


            LocalDateTime start = new LocalDateTime(dtungay);
            LocalDateTime end = new LocalDateTime(ddenngay);
            Period difference = new Period(start, end, PeriodType.months());
            int months = difference.getMonths(); // etc

            List<Hoadon> listHoadon = hoaDonService.findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc
                    ("deleted", getnhanvienbanhang, dtungay, ddenngay);
            List<Nhanvienkpi> listNhanvienkpi = nhanVienKpiService.findByNhanvienAndTrangthaiAndNgaydangkyBetween
                    (getnhanvienbanhang, "active", dtungay, ddenngay);
            List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

            int count = 0;
            do {
                Map<String, Object> map = new HashMap<String, Object>();
                System.out.println(thang + " = " + nam);
                long tongtien = 0L;
                long tiendatra = 0L;
                long congno = 0L;
                int tonghoadon = 0;
                int hoadondathanhtoan = 0;
                int hoadonchuathanhtoan = 0;
                int khachhangmoi = 0;
                int khachhangtailap = 0;

                long luong = 0L;
                long thuong = 0L;
                long thuonghoadon = 0L;
                long tienung = 0L;

                for (Hoadon hd : listHoadon) {
                    int thanghd = Integer.parseInt(df.format(hd.getNgaythanhtoan()).substring(3, 5));
                    int namhd = Integer.parseInt(df.format(hd.getNgaythanhtoan()).substring(6, 10));
                    System.out.println(thanghd + " == " + namhd);
                    if (thang == thanghd && nam == namhd) {
                        tongtien += hd.getTongtien();
                        tiendatra += hd.getTiendatra();
                        congno += hd.getCongno();
                        tonghoadon += 1;
                        if (hd.getTongtien().equals(hd.getTiendatra()) && hd.getCongno() == 0) {
                            hoadondathanhtoan += 1;
                        }
                        if (hd.getTongtien() > hd.getTiendatra() && hd.getCongno() > 0) {
                            hoadonchuathanhtoan += 1;
                        }
                        if (hd.getHoadondautien() == true) {
                            khachhangmoi += 1;
                        } else {
                            khachhangtailap += 1;
                        }

                    }
                }

                String formatted = String.format("%02d", thang);
                Luong getluong = luongService.findOneByTrangthaiNotAndNhanvienAndThangAndNam
                        ("deleted", getnhanvienbanhang, formatted, String.valueOf(nam));
                System.out.println(getluong + "LUONG");
                if (getluong != null) {
                    System.out.println(getluong.getLuong() + "GET LUONG");
                    luong += getluong.getLuong();
                    thuong += getluong.getThuong();
                    thuonghoadon+=getluong.getThuongcuahoadon();
                    if (!getluong.getUngluongs().isEmpty()) {
                        for (Ungluong ul : getluong.getUngluongs()) {
                            tienung += ul.getSotienung();
                        }
                    }
                }



                List<Map<String, Object>> listMapvnk = new ArrayList<Map<String, Object>>();
                for (Nhanvienkpi nvk : listNhanvienkpi) {
                    int thangvnk = Integer.parseInt(df.format(nvk.getNgaydangky()).substring(3, 5));
                    int namnvk = Integer.parseInt(df.format(nvk.getNgaydangky()).substring(6, 10));

                    if (thang == thangvnk && nam == namnvk) {
                        Map<String, Object> mapnvk = new HashMap<String, Object>();
                        mapnvk.put("tenkpi", nvk.getKpi().getTen());
                        mapnvk.put("kieukpi", nvk.getKpi().getKieukpi());
                        mapnvk.put("chitieudangky", nvk.getChitieudangky());
                        listMapvnk.add(mapnvk);
                    }
                }
                map.put("nhanvienkpi", listMapvnk);
                map.put("tongtien", tongtien);
                map.put("tiendatra", tiendatra);
                map.put("congno", congno);
                map.put("tonghoadon", tonghoadon);
                map.put("hoadondathanhtoan", hoadondathanhtoan);
                map.put("hoadonchuathanhtoan", hoadonchuathanhtoan);
                map.put("khachhangmoi", khachhangmoi);
                map.put("khachhangtailap", khachhangtailap);

                map.put("luong", luong);
                map.put("thuong", thuong);
               /* map.put("manhanvien" , getnhanvienbanhang.getManhanvien());
                map.put("tennhanvien",getnhanvienbanhang.getTennhanvien());*/
                map.put("thuonghoadon", thuonghoadon);
                map.put("tienung", tienung);
                map.put("thang", thang);
                map.put("nam", nam);

                listMap.add(map);
                if (thang == 12) {
                    thang = 01;
                    nam += 1;
                } else {
                    thang += 1;
                }
                count++;
            } while (count <= months);

            model.addAttribute("listMap", listMap);
            model.addAttribute("listNhanvien", listNhanvien);
            model.addAttribute("tungay", dtungay);
            model.addAttribute("denngay", ddenngay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "saokehoadon";
    }
}
