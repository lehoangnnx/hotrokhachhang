package bcc.springhibernate.controller;

import bcc.springhibernate.model.*;
import bcc.springhibernate.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller

@RequestMapping("/admin")
public class HoaDonController {

    @Autowired
    HoaDonService hoaDonService;
    @Autowired
    HangHoaService hangHoaService;
    @Autowired
    NhanVienService nhanVienService;
    @Autowired
    KhachHangService khachHangService;
    @Autowired
    TaikhoanService taikhoanService;
    @Autowired
    ChiTietHoaDonService chiTietHoaDonService;
    @Autowired
    LuongService luongService;
    @Autowired
    NhanVienKpiService nhanVienKpiService;
    @Autowired
    NhomKhachHangService nhomKhachHangService;
    @PreAuthorize("hasAnyRole('ADMIN', 'BANHANG', 'GIAOHANG')")
    @GetMapping("/hoadon")
    String pageDanhSachHoaDon(@RequestParam(value = "hthd", required = false) String hthd,
                              @RequestParam(value = "tungay", defaultValue = "null") String tungay,
                              @RequestParam(value = "denngay", defaultValue = "null") String denngay,
                              @RequestParam(value = "trangthai", defaultValue = "dathanhtoan") String trangthai,
                              @RequestParam(value = "limit", defaultValue = "100") Integer limit,
                              @RequestParam(value = "page", defaultValue = "1") Integer page, Model model, Principal principal,
                              HttpServletRequest request) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date ddenngay = new Date();
        Date dtungay = new Date(ddenngay.getYear(), ddenngay.getMonth(), 01);
        try {

            int pageCount = 0;
            List<Hoadon> listHoadon = new ArrayList<>();
            List<Hoadon> listHoadonThongKe = new ArrayList<>();
            List<Hoadon> listHoadonKpi = new ArrayList<>();
            List<Nhanvienkpi> listNhanvienkpi = new ArrayList<>();
            if (request.isUserInRole("ROLE_ADMIN")) {
                if (trangthai.equals("deleted")) {
                    int listHoaDonSize = hoaDonService.findByTrangthaiOrderByIdDesc("deleted").size();
                    pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);
                    listHoadon = hoaDonService.findByTrangthaiOrderByIdDesc("deleted", new PageRequest(page - 1, limit));

                } else if (trangthai.equals("chuathanhtoan")) {
                    int listHoaDonSize = hoaDonService.findByTrangthaiChuaThanhToan("deleted").size();
                    pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);
                    listHoadon = hoaDonService.findByTrangthaiChuaThanhToan("deleted", new PageRequest(page - 1, limit));
                } else {
                    int listHoaDonSize = hoaDonService.findByTrangthaiDaThanhToan("deleted").size();
                    pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);
                    listHoadon = hoaDonService.findByTrangthaiDaThanhToan("deleted", new PageRequest(page - 1, limit));
                }
                listHoadonThongKe = hoaDonService.findByTrangthaiNotOrderByIdDesc("deleted");
            } else {
                Taikhoan taikhoan = taikhoanService.findByUsername(principal.getName());
                List<Nhanvien> nhanVienCapTren = nhanVienService.findByIdnhanviencaptren(taikhoan.getNhanvien().getId());


                Date ddenngaynvk = new Date();
                Date dtungaynvk = new Date(ddenngaynvk.getYear(), ddenngaynvk.getMonth(), 01);
                if (!tungay.equals("null")) {
                    dtungay = dateFormat.parse(tungay);
                }
                if (!denngay.equals("null")) {
                    ddenngay = dateFormat.parse(denngay);
                    ddenngaynvk = dateFormat.parse(denngay);
                    dtungaynvk = new Date(ddenngaynvk.getYear(), ddenngaynvk.getMonth(), 01);
                }
                listHoadonKpi = hoaDonService.findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc("deleted",
                        taikhoan.getNhanvien(), dtungaynvk, ddenngaynvk);
                listNhanvienkpi = nhanVienKpiService.findByNhanvienAndTrangthaiAndNgaydangkyBetween
                        (taikhoan.getNhanvien(), "active", dtungaynvk, ddenngaynvk);
                if (hthd != null) {

                    if (trangthai.equals("deleted")) {
                        int listHoaDonSize = hoaDonService.findByTrangthaiAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc(
                                "deleted", taikhoan.getNhanvien(), dtungay, ddenngay).size();
                        pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);
                        listHoadon = hoaDonService.findByTrangthaiAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc("deleted",
                                taikhoan.getNhanvien(), dtungay, ddenngay, new PageRequest(page - 1, limit));

                    } else if (trangthai.equals("chuathanhtoan")) {
                        int listHoaDonSize = hoaDonService.findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween(
                                "deleted", taikhoan.getNhanvien(), dtungay, ddenngay).size();
                        pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);
                        listHoadon = hoaDonService.findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween(
                                "deleted", taikhoan.getNhanvien(), dtungay, ddenngay, new PageRequest(page - 1, limit));
                    } else if (trangthai.equals("dathanhtoan")) {
                        int listHoaDonSize = hoaDonService.findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween(
                                "deleted", taikhoan.getNhanvien(), dtungay, ddenngay).size();
                        pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);
                        listHoadon = hoaDonService.findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween(
                                "deleted", taikhoan.getNhanvien(), dtungay, ddenngay, new PageRequest(page - 1, limit));
                    } else if (trangthai.equals("hoadonnhanviencapduoi")) {
                        int listHoaDonSize = 0;
                        for (Nhanvien nv : nhanVienCapTren) {
                            listHoaDonSize += hoaDonService.findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc(
                                    "deleted", nv, dtungay, ddenngay).size();
                            List<Hoadon> listHoaDonNhanVienCapDuoi = hoaDonService.findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc(
                                    "deleted", nv, dtungay, ddenngay, new PageRequest(page - 1, limit));
                            if (!listHoaDonNhanVienCapDuoi.isEmpty()) {
                                listHoadon.addAll(listHoaDonNhanVienCapDuoi);
                            }

                        }

                        pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);

                    }
                } else {

                    if (trangthai.equals("deleted")) {
                        int listHoaDonSize = hoaDonService.findByTrangthaiAndNhanvienByIdnhanvienbanOrderByIdDesc(
                                "deleted", taikhoan.getNhanvien()).size();
                        pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);
                        listHoadon = hoaDonService.findByTrangthaiAndNhanvienByIdnhanvienbanOrderByIdDesc("deleted",
                                taikhoan.getNhanvien(), new PageRequest(page - 1, limit));

                    } else if (trangthai.equals("chuathanhtoan")) {
                        int listHoaDonSize = hoaDonService.findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienban(
                                "deleted", taikhoan.getNhanvien()).size();
                        pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);
                        listHoadon = hoaDonService.findByTrangthaiChuaThanhToanAndNhanvienByIdnhanvienban(
                                "deleted", taikhoan.getNhanvien(), new PageRequest(page - 1, limit));
                    } else if (trangthai.equals("dathanhtoan")) {
                        int listHoaDonSize = hoaDonService.findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienban(
                                "deleted", taikhoan.getNhanvien()).size();
                        pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);
                        listHoadon = hoaDonService.findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienban(
                                "deleted", taikhoan.getNhanvien(), new PageRequest(page - 1, limit));
                    } else if (trangthai.equals("hoadonnhanviencapduoi")) {
                        int listHoaDonSize = 0;
                        for (Nhanvien nv : nhanVienCapTren) {
                            listHoaDonSize += hoaDonService.findByTrangthaiNotAndNhanvienByIdnhanvienbanOrderByIdDesc(
                                    "deleted", nv).size();
                            List<Hoadon> listHoaDonNhanVienCapDuoi = hoaDonService.findByTrangthaiNotAndNhanvienByIdnhanvienbanOrderByIdDesc(
                                    "deleted", nv, new PageRequest(page - 1, limit));

                            if (!listHoaDonNhanVienCapDuoi.isEmpty()) {
                                listHoadon.addAll(listHoaDonNhanVienCapDuoi);
                            }
                        }

                        pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);

                    }
                }
                listHoadonThongKe = hoaDonService.findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc("deleted",
                        taikhoan.getNhanvien(), dtungay, ddenngay);
            }

            model.addAttribute("tungay", dtungay);
            model.addAttribute("denngay", ddenngay);
            model.addAttribute("listHoadon", listHoadon);
            model.addAttribute("listNhanvienkpi", listNhanvienkpi);
            model.addAttribute("listHoadonThongKe", listHoadonThongKe);

            model.addAttribute("listHoadonKpi", listHoadonKpi);
            model.addAttribute("currentpage", page);
            model.addAttribute("pagecount", pageCount);
            model.addAttribute("hthd", hthd);
            model.addAttribute("trangthai", trangthai);
            model.addAttribute("limit", limit);
            model.addAttribute("page", page);
        } catch (Exception e) {
            e.printStackTrace();
            return "danhsachhoadon";
        }

        return "danhsachhoadon";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BANHANG')")
    @GetMapping("/hoadon/add")
    String pageThemHoaDon(Model model) {

        List<Hanghoa> listHanghoa = hangHoaService.findByTrangthaiOrderByIdDesc("active");
        List<Khachhang> listKhachhang = khachHangService.findByTrangthaiNotOrderByIdDesc("deleted");
        List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");

        model.addAttribute("listHanghoa", listHanghoa);
        model.addAttribute("listKhachhang", listKhachhang);
        model.addAttribute("listNhanvien", listNhanvien);
        model.addAttribute("hoadon", new Hoadon());
        return "themhoadon";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BANHANG', 'GIAOHANG', 'TAICHINH')")
    @GetMapping("/hoadon/{id}")
    String pageSuaHoaDon(@PathVariable("id") Integer id, Model model, Principal principal, HttpServletRequest request) {


        try {
            Hoadon hoadon = hoaDonService.findById(id);
            if (hoadon != null) {
                List<Chitiethoadon> listChitiethoadon = chiTietHoaDonService.findByHoadonAndTrangthaiOrderByIdDesc(hoadon,
                        "active");
                List<Hanghoa> listHanghoa = hangHoaService.findByTrangthaiOrderByIdDesc("active");
                List<Khachhang> listKhachhang = khachHangService.findByTrangthaiNotOrderByIdDesc("deleted");
                List<Nhanvien> listNhanvien = nhanVienService.findByTrangthaiOrderByIdDesc("active");


                model.addAttribute("listHanghoa", listHanghoa);
                model.addAttribute("listKhachhang", listKhachhang);
                model.addAttribute("listNhanvien", listNhanvien);

                model.addAttribute("listChitiethoadon", listChitiethoadon);
                Taikhoan taikhoan = taikhoanService.findByUsername(principal.getName());
                if (request.isUserInRole("ROLE_ADMIN") || hoadon.getNhanvienByIdnhanvienban() == taikhoan.getNhanvien()) {
                    model.addAttribute("hoadon", hoadon);
                    return "suahoadon";
                } else {
                    return "redirect:/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1&hthd=on";
                }
            } else {
                return "redirect:/403";
            }

        } catch (Exception e) {
            return "redirect:/403";
        }

    }


    @PreAuthorize("hasAnyRole('ADMIN', 'BANHANG')")
    @PostMapping("/hoadon")
    String themHoaDon(@ModelAttribute("hoadon") Hoadon hoadon,
                      @RequestParam("tongtien_money") String tongtien_money,
                      @RequestParam("tiendatra_money") String tiendatra_money,
                      @RequestParam("congno_money") String congno_money,
                      @RequestParam("nhanvienbanhang") Integer nhanvienbanhang,
                      @RequestParam("nhanviengiaohang") Integer nhanviengiaohang,
                      @RequestParam("nhanvienchamsoc") Integer nhanvienchamsoc, @RequestParam("khachhang") Integer khachhang,
                      @RequestParam("hinhthucthanhtoan") String hinhthucthanhtoan, @RequestParam("ngaylap") String ngaylap,
                      @RequestParam("ngayxuat") String ngayxuat, @RequestParam("ngaythanhtoan") String ngaythanhtoan,
                      @RequestParam("sodienthoai") String sodienthoai, @RequestParam("trangthai") String trangthai,
                      @RequestParam("idhh") List<Integer> idhh, @RequestParam("soluonghh") List<Integer> soluonghh,
                      @RequestParam("giabanhh") List<String> giabanhh, @RequestParam("thanhtienhh") List<String> thanhtienhh,
                      RedirectAttributes redirectAttributes, Principal principal) {

        try {
            Long tongtien = Long.valueOf(tongtien_money.replaceAll("\\.|\\,|\\s", ""));
            Long tiendatra = Long.valueOf(tiendatra_money.replaceAll("\\.|\\,|\\s", ""));
            Long congno = Long.valueOf(congno_money.replaceAll("\\.|\\,|\\s", ""));
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Nhanvien getNhanVienBanHangById = nhanVienService.findById(nhanvienbanhang);
            Nhanvien getNhanVienChamSocById = nhanVienService.findById(nhanvienchamsoc);
            Nhanvien getNhanVienGiaoHangById = nhanVienService.findById(nhanviengiaohang);
            Taikhoan getTaiKhoanByUserName = taikhoanService.findByUsername(principal.getName());
            Nhanvien getNhanVienLapHoaDon = getTaiKhoanByUserName.getNhanvien();

            Khachhang getKhachHangById = khachHangService.findById(khachhang);


            if (tongtien.equals(tiendatra) && congno.equals(0L)) {
                List<Hoadon> getListHoaDonByKhachHang = hoaDonService.findByKhachhang(getKhachHangById);
                if (getListHoaDonByKhachHang.isEmpty()) {
                    hoadon.setHoadondautien(true);
                } else {
                    List<Hoadon> getListHoaDonByKhachHangAndIdNot = getListHoaDonByKhachHang
                            .stream().filter(x -> x.getHoadondautien() != null).collect(Collectors.toList());
                    if (getListHoaDonByKhachHangAndIdNot.isEmpty()) {
                        hoadon.setHoadondautien(true);
                    } else if (getListHoaDonByKhachHangAndIdNot.size() < 2) {
                        hoadon.setHoadondautien(false);
                    }
                }
            }

            hoadon.setNhanvienByIdnhanvienban(getNhanVienBanHangById);
            hoadon.setNhanvienByIdnhanvienchamsoc(getNhanVienChamSocById);
            hoadon.setNhanvienByIdnhanviengiaohang(getNhanVienGiaoHangById);
            hoadon.setNhanvienByIdnhanvienlaphoadon(getNhanVienLapHoaDon);
            hoadon.setKhachhang(getKhachHangById);
            hoadon.setHinhthucthanhtoan(hinhthucthanhtoan);
            hoadon.setNgaylap(df.parse(ngaylap));
            hoadon.setNgayxuat(df.parse(ngayxuat));
            hoadon.setNgaythanhtoan(df.parse(ngaythanhtoan));
            hoadon.setSodienthoai(sodienthoai.replace("_", ""));
            hoadon.setTrangthai(trangthai);

            hoadon.setTongtien(tongtien);
            hoadon.setTiendatra(tiendatra);
            hoadon.setCongno(congno);


            hoaDonService.saveOrUpdate(hoadon);
            for (int i = 0; i < idhh.size(); i++) {
                Hanghoa hangHoaChiTietHoaDon = hangHoaService.findById(idhh.get(i));
                Chitiethoadon chitiethoadon = new Chitiethoadon(hangHoaChiTietHoaDon, hoadon, hangHoaChiTietHoaDon.getGiaban(),
                        soluonghh.get(i), Long.valueOf(giabanhh.get(i).replaceAll("\\.|\\,|\\s", "")),
                        Long.valueOf(thanhtienhh.get(i).replaceAll("\\.|\\,|\\s", "")), "active", "");
                chiTietHoaDonService.saveOrUpdate(chitiethoadon);

            }

            // Tiính tiền chăm sóc khách hàng
            if(hoadon.getTiendatra().equals(hoadon.getTongtien())){
                Long tongtienvon = 0L;
                List<Chitiethoadon> chitiethoadons = chiTietHoaDonService.findByHoadon(hoadon);
                for (Chitiethoadon ct : chitiethoadons) {
                    tongtienvon += (ct.getHanghoa().getGianhap() * ct.getSoluong());
                }
                Double tientrendiem = getKhachHangById.getNhomkhachhang().getSotientrendiem();
                Integer diemtrentien = getKhachHangById.getNhomkhachhang().getSodiemtrentien();
                Double phantramtien = getKhachHangById.getNhomkhachhang().getPhantramtien();
                Long sotienchamsoc = (long) (getKhachHangById.getSotienchamsoc()
                        + (((hoadon.getTongtien() - tongtienvon) * phantramtien) / 100));
                Integer diem = (int) (getKhachHangById.getDiem() + (hoadon.getTongtien() / tientrendiem) * diemtrentien);
                getKhachHangById.setSotienchamsoc(sotienchamsoc);
                getKhachHangById.setDiem(diem);
                getKhachHangById.setLanmuahang(getKhachHangById.getLanmuahang() + 1);
                Nhomkhachhang nhomkhachhang = nhomKhachHangService.findFirst1ByDiemLessThanEqualOrderByDiemDesc(diem);
                if(nhomkhachhang != null){
                    getKhachHangById.setNhomkhachhang(nhomkhachhang);
                }

                khachHangService.saveOrUpdate(getKhachHangById);
            }
            ArrayList<Nhanvien> nhanvienarr = new ArrayList<Nhanvien>();
            nhanvienarr.add(getNhanVienBanHangById);

            themluong(hoadon, nhanvienarr, ngaythanhtoan, getNhanVienChamSocById);

            redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace().toString());
            redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
        }

        return "redirect:/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1&hthd=on";

    }

    boolean themluong(Hoadon hoadon, ArrayList<Nhanvien> nhanvien, String ngay, Nhanvien nvchamsoc) {

        try {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String splitDate[] = df.format(date).split("/|-");
            String splitngay[] = ngay.split("/|-");
            Long tongtienvon = 0L;
            List<Chitiethoadon> chitiethoadons = chiTietHoaDonService.findByHoadon(hoadon);
            for (Chitiethoadon ct : chitiethoadons) {

                tongtienvon += (ct.getHanghoa().getGianhap() * ct.getSoluong());
            }
            if (hoadon.getTongtien().equals(hoadon.getTiendatra())){
                Luong luong = luongService.findOneByNhanvienAndThangAndNam(nvchamsoc, splitngay[1], splitngay[2]);
                Long thuong = 0L;
                thuong = (long) (((hoadon.getTiendatra() - (tongtienvon)) * nvchamsoc.getChietkhau()) / 100);
                luong.setThuongcuahoadon(luong.getThuongcuahoadon() + (thuong));
                luongService.saveOrUpdate(luong);
            }
            for (Nhanvien nv : nhanvien) {

                Luong luong = luongService.findOneByNhanvienAndThangAndNam(nv, splitngay[1], splitngay[2]);
                Long thuong = 0L;
                if (hoadon.getTiendatra() < tongtienvon) {
                    thuong = (long) -(((hoadon.getTongtien() - (tongtienvon)) * nv.getChietkhau()) / 100);
                } else {
                    thuong = (long) (((hoadon.getTiendatra() - (tongtienvon)) * nv.getChietkhau()) / 100);

                }
                luong.setThuongcuahoadon(luong.getThuongcuahoadon() + (thuong));
                luongService.saveOrUpdate(luong);

                // Cộng Thưởng Hóa Đơn Cho Nhân Viên Cấp Trên

                if (nv.getIdnhanviencaptren() != 0) {
                    Nhanvien nhanviencaptren = nhanVienService.findById(nv.getIdnhanviencaptren());

                    Luong luongnhanviencaptren = luongService.findOneByNhanvienAndThangAndNam(nhanviencaptren, splitngay[1], splitngay[2]);
                    Long thuongcaptren = 0L;
                    if (hoadon.getTiendatra() < tongtienvon) {
                        thuongcaptren = (long) -(((hoadon.getTongtien() - (tongtienvon)) * nv.getChietkhauchonhanviencaptren()) / 100);
                    } else {
                        thuongcaptren = (long) (((hoadon.getTiendatra() - (tongtienvon)) * nv.getChietkhauchonhanviencaptren()) / 100);

                    }
                    luongnhanviencaptren.setThuongcuahoadon(luongnhanviencaptren.getThuongcuahoadon() + (thuongcaptren));
                    luongService.saveOrUpdate(luongnhanviencaptren);
                }

            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }


    @PreAuthorize("hasAnyRole('ADMIN', 'BANHANG')")
    @PatchMapping(value = "/hoadon", params = "update")
    String suaHoaDon(@ModelAttribute("hoadon") Hoadon hoadon,
                     @RequestParam("tongtien_money") String tongtien_money,
                     @RequestParam("tiendatra_money") String tiendatra_money,
                     @RequestParam("congno_money") String congno_money,
                     @RequestParam("nhanvienbanhang") Integer nhanvienbanhang,
                     @RequestParam("nhanviengiaohang") Integer nhanviengiaohang,
                     @RequestParam("nhanvienchamsoc") Integer nhanvienchamsoc, @RequestParam("khachhang") Integer khachhang,
                     @RequestParam("hinhthucthanhtoan") String hinhthucthanhtoan, @RequestParam("ngaylap") String ngaylap,
                     @RequestParam("ngayxuat") String ngayxuat, @RequestParam("ngaythanhtoan") String ngaythanhtoan,
                     @RequestParam("sodienthoai") String sodienthoai, @RequestParam("trangthai") String trangthai,
                     @RequestParam("idcthd") List<Integer> idcthd, @RequestParam("idhh") List<Integer> idhh,
                     @RequestParam("soluonghh") List<Integer> soluonghh, @RequestParam("giabanhh") List<String> giabanhh,
                     @RequestParam("thanhtienhh") List<String> thanhtienhh, RedirectAttributes redirectAttributes,
                     Principal principal) {

        try {

            Long tongtien = Long.valueOf(tongtien_money.replaceAll("\\.|\\,|\\s", ""));
            Long tiendatra = Long.valueOf(tiendatra_money.replaceAll("\\.|\\,|\\s", ""));
            Long congno = Long.valueOf(congno_money.replaceAll("\\.|\\,|\\s", ""));
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            Nhanvien getNhanVienBanHangById = nhanVienService.findById(nhanvienbanhang);
            Nhanvien getNhanVienChamSocById = nhanVienService.findById(nhanvienchamsoc);
            Nhanvien getNhanVienGiaoHangById = nhanVienService.findById(nhanviengiaohang);
            Taikhoan getTaiKhoanByUserName = taikhoanService.findByUsername(principal.getName());
            Nhanvien getNhanVienLapHoaDon = getTaiKhoanByUserName.getNhanvien();

            Hoadon hoadoncu = hoaDonService.findById(hoadon.getId());
            Khachhang getKhachHangById = khachHangService.findById(khachhang);


            if (tongtien.equals(tiendatra) && congno.equals(0L)) {
                List<Hoadon> getListHoaDonByKhachHang = hoaDonService.findByKhachhang(getKhachHangById);
                if (getListHoaDonByKhachHang.isEmpty()) {
                    hoadon.setHoadondautien(true);
                } else {
                    List<Hoadon> getListHoaDonByKhachHangAndIdNot = getListHoaDonByKhachHang
                            .stream().filter(x -> !x.getId().equals(hoadon.getId())
                                    && x.getHoadondautien() != null).collect(Collectors.toList());
                    if (getListHoaDonByKhachHangAndIdNot.isEmpty()) {
                        hoadon.setHoadondautien(true);
                    } else if (getListHoaDonByKhachHangAndIdNot.size() < 2) {
                        hoadon.setHoadondautien(false);
                    }
                }
            }
            hoadon.setNhanvienByIdnhanvienban(getNhanVienBanHangById);
            hoadon.setNhanvienByIdnhanvienchamsoc(getNhanVienChamSocById);
            hoadon.setNhanvienByIdnhanviengiaohang(getNhanVienGiaoHangById);
            hoadon.setNhanvienByIdnhanvienlaphoadon(getNhanVienLapHoaDon);
            hoadon.setKhachhang(getKhachHangById);
            hoadon.setHinhthucthanhtoan(hinhthucthanhtoan);
            hoadon.setNgaylap(df.parse(ngaylap));
            hoadon.setNgayxuat(df.parse(ngayxuat));
            hoadon.setNgaythanhtoan(df.parse(ngaythanhtoan));
            hoadon.setSodienthoai(sodienthoai.replace("_", ""));
            hoadon.setTrangthai(trangthai);

            hoadon.setTongtien(tongtien);
            hoadon.setTiendatra(tiendatra);
            hoadon.setCongno(congno);



            ArrayList<Nhanvien> nhanvienarr = new ArrayList<Nhanvien>();
            //nhanvienarr.add(getNhanVienLapHoaDon);
            //nhanvienarr.add(getNhanVienGiaoHangById);
            nhanvienarr.add(getNhanVienBanHangById);
            //nhanvienarr.add(getNhanVienChamSocById);


            //Update luong
            Date date = new Date();
            // laays ngay thanh toan
            String splitDate[] = df.format(date).split("/|-");
            String splitngay[] = ngaythanhtoan.split("/|-");
            Long tongtienvoncu = 0L;
            List<Chitiethoadon> chitiethoadonscu = chiTietHoaDonService.findByHoadon(hoadoncu);
            for (Chitiethoadon ct : chitiethoadonscu) {

                tongtienvoncu += ct.getHanghoa().getGianhap() * ct.getSoluong();
            }

            // update chi tiet hoa don
            for (int i = 0; i < idhh.size(); i++) {
                Hanghoa hangHoaChiTietHoaDon = hangHoaService.findById(idhh.get(i));
                if (idcthd.get(i) == 0) {
                    Chitiethoadon chitiethoadon = new Chitiethoadon(hangHoaChiTietHoaDon, hoadon, hangHoaChiTietHoaDon.getGiaban(),
                            soluonghh.get(i), Long.valueOf(giabanhh.get(i).replaceAll("\\.|\\,|\\s", "")),
                            Long.valueOf(thanhtienhh.get(i).replaceAll("\\.|\\,|\\s", "")), "active", "");
                    chiTietHoaDonService.saveOrUpdate(chitiethoadon);

                } else {

                    Chitiethoadon chitiethoadon = new Chitiethoadon(idcthd.get(i), hangHoaChiTietHoaDon, hoadon,
                            hangHoaChiTietHoaDon.getGiaban(), soluonghh.get(i),
                            Long.valueOf(giabanhh.get(i).replaceAll("\\.|\\,|\\s", "")),
                            Long.valueOf(thanhtienhh.get(i).replaceAll("\\.|\\,|\\s", "")), "active", "");
                    chiTietHoaDonService.saveOrUpdate(chitiethoadon);
                }

            }

            // Update luong - next
            Long tongtienvon = 0L;
            List<Chitiethoadon> chitiethoadons = chiTietHoaDonService.findByHoadon(hoadon);
            for (Chitiethoadon ct : chitiethoadons) {

                tongtienvon += ct.getHanghoa().getGianhap() * ct.getSoluong();
            }

            // Tien CHam Soc Khach Hang
            if (hoadoncu.getTongtien().equals(hoadoncu.getTiendatra())
                    && hoadon.getTongtien().equals(hoadon.getTiendatra())) {

                    Double tientrendiem = getKhachHangById.getNhomkhachhang().getSotientrendiem();
                    Integer diemtrentien = getKhachHangById.getNhomkhachhang().getSodiemtrentien();
                    Double phantramtien = getKhachHangById.getNhomkhachhang().getPhantramtien();
                    Double sotienchamsoccu = getKhachHangById.getSotienchamsoc()
                            - (((hoadoncu.getTongtien() - tongtienvoncu ) * phantramtien) / 100);
                    Double diemcu = getKhachHangById.getDiem() - (hoadoncu.getTongtien() / tientrendiem) * diemtrentien;
                    Long sotienchamsoc = (long) (sotienchamsoccu + (((hoadon.getTongtien() - tongtienvon) * phantramtien) / 100));
                    Integer diem = (int) (diemcu + (hoadon.getTongtien() / tientrendiem) * diemtrentien);

                    getKhachHangById.setSotienchamsoc(sotienchamsoc);
                    getKhachHangById.setDiem(diem);
                    Nhomkhachhang nhomkhachhang = nhomKhachHangService.findFirst1ByDiemLessThanEqualOrderByDiemDesc(diem);
                    if(nhomkhachhang != null){
                        getKhachHangById.setNhomkhachhang(nhomkhachhang);
                    }
                    khachHangService.saveOrUpdate(getKhachHangById);

            }else if(hoadoncu.getTongtien().equals(hoadoncu.getTiendatra())){
                Double tientrendiem = getKhachHangById.getNhomkhachhang().getSotientrendiem();
                Integer diemtrentien = getKhachHangById.getNhomkhachhang().getSodiemtrentien();
                Double phantramtien = getKhachHangById.getNhomkhachhang().getPhantramtien();
                Long sotienchamsoc = (long) (getKhachHangById.getSotienchamsoc()
                        - (((hoadon.getTongtien() - tongtienvon ) * phantramtien) / 100));
                Integer diem = (int) (getKhachHangById.getDiem() - (hoadon.getTongtien() / tientrendiem) * diemtrentien);

                getKhachHangById.setSotienchamsoc(sotienchamsoc);
                getKhachHangById.setDiem(diem);
                getKhachHangById.setLanmuahang(getKhachHangById.getLanmuahang() - 1);
                khachHangService.saveOrUpdate(getKhachHangById);
            }else if( hoadon.getTongtien().equals(hoadon.getTiendatra())){
                Double tientrendiem = getKhachHangById.getNhomkhachhang().getSotientrendiem();
                Integer diemtrentien = getKhachHangById.getNhomkhachhang().getSodiemtrentien();
                Double phantramtien = getKhachHangById.getNhomkhachhang().getPhantramtien();
                Long sotienchamsoc = (long) (getKhachHangById.getSotienchamsoc()
                        + (((hoadon.getTongtien() - tongtienvon) * phantramtien) / 100));
                Integer diem = (int) (getKhachHangById.getDiem() + (hoadon.getTongtien() / tientrendiem) * diemtrentien);
                getKhachHangById.setSotienchamsoc(sotienchamsoc);
                getKhachHangById.setDiem(diem);
                getKhachHangById.setLanmuahang(getKhachHangById.getLanmuahang() + 1);
                khachHangService.saveOrUpdate(getKhachHangById);
            }

            String ngaythanhtoanhoadoncu[] = df.format(hoadoncu.getNgaythanhtoan()).split("/|-");
            String ngaythanhtoanhoadon[] = df.format(hoadon.getNgaythanhtoan()).split("/|-");

            if(hoadoncu.getTongtien().equals(hoadoncu.getTiendatra())){
                Luong luong = luongService.findOneByNhanvienAndThangAndNam(getNhanVienChamSocById, splitngay[1],
                        splitngay[2]);
                Long thuongcu = (long) (luong.getThuongcuahoadon()
                        - (((hoadoncu.getTiendatra() - (tongtienvoncu)) * getNhanVienChamSocById.getChietkhau()) / 100));
                luong.setThuongcuahoadon(thuongcu);
                luongService.saveOrUpdate(luong);
            }
            if (hoadon.getTongtien().equals(hoadon.getTiendatra())){
                Luong luong = luongService.findOneByNhanvienAndThangAndNam(getNhanVienChamSocById, splitngay[1], splitngay[2]);
                Long thuong = (long) (((hoadon.getTiendatra() - (tongtienvon)) * getNhanVienChamSocById.getChietkhau()) / 100);
                luong.setThuongcuahoadon(luong.getThuongcuahoadon() + (thuong));
                luongService.saveOrUpdate(luong);
            }
            for (Nhanvien nv : nhanvienarr) {
                Long thuongcu = 0L;
                Long thuong = 0L;
                Luong luong = luongService.findOneByNhanvienAndThangAndNam(nv, splitngay[1],
                        splitngay[2]);
                if (ngaythanhtoanhoadoncu[1].equals(ngaythanhtoanhoadon[1])
                        && ngaythanhtoanhoadoncu[2].equals(ngaythanhtoanhoadon[2])) {

                    if (hoadoncu.getTiendatra() < tongtienvoncu) {
                        thuongcu = (long) (luong.getThuongcuahoadon()
                                + (((hoadoncu.getTongtien() - (tongtienvoncu)) * nv.getChietkhau()) / 100));
                    } else {
                        thuongcu = (long) (luong.getThuongcuahoadon()
                                - (((hoadoncu.getTiendatra() - (tongtienvoncu)) * nv.getChietkhau()) / 100));
                    }

                } else {
                    thuongcu = luong.getThuongcuahoadon();
                }

                if (hoadon.getTiendatra() < tongtienvon) {
                    thuong = (long) -(((hoadon.getTongtien() - (tongtienvon)) * nv.getChietkhau()) / 100);
                } else {
                    thuong = (long) (((hoadon.getTiendatra() - (tongtienvon)) * nv.getChietkhau()) / 100);
                }
                luong.setThuongcuahoadon(thuongcu + (thuong));
                luongService.saveOrUpdate(luong);

                if (nv.getIdnhanviencaptren() != 0) {
                    Long thuongcunhanviencaptren = 0L;
                    Long thuongnhanviencaptren = 0L;
                    Nhanvien nhanviencaptren = nhanVienService.findById(nv.getIdnhanviencaptren());
                    Luong luongnhanviencaptren = luongService.findOneByNhanvienAndThangAndNam(nhanviencaptren, splitngay[1], splitngay[2]);
                    if (ngaythanhtoanhoadoncu[1].equals(ngaythanhtoanhoadon[1])
                            && ngaythanhtoanhoadoncu[2].equals(ngaythanhtoanhoadon[2])) {
                        if (hoadoncu.getTiendatra() < tongtienvoncu) {
                            thuongcunhanviencaptren = (long) (luongnhanviencaptren.getThuongcuahoadon()
                                    + (((hoadoncu.getTongtien() - (tongtienvoncu)) * nv.getChietkhauchonhanviencaptren()) / 100));
                        } else {
                            thuongcunhanviencaptren = (long) (luongnhanviencaptren.getThuongcuahoadon()
                                    - (((hoadoncu.getTiendatra() - (tongtienvoncu)) * nv.getChietkhauchonhanviencaptren()) / 100));
                        }

                    } else {
                        thuongcunhanviencaptren = luongnhanviencaptren.getThuongcuahoadon();
                    }

                    if (hoadon.getTiendatra() < tongtienvon) {
                        thuongnhanviencaptren = (long) -(((hoadon.getTongtien() - (tongtienvon)) * nv.getChietkhauchonhanviencaptren()) / 100);
                    } else {
                        thuongnhanviencaptren = (long) (((hoadon.getTiendatra() - (tongtienvon)) * nv.getChietkhauchonhanviencaptren()) / 100);
                    }

                    luongnhanviencaptren.setThuongcuahoadon(thuongcunhanviencaptren + (thuongnhanviencaptren));
                    luongService.saveOrUpdate(luongnhanviencaptren);
                }
            }


            //sualuong(hoadoncu, hoadon, nhanvienarr, ngaythanhtoan);
            hoaDonService.saveOrUpdate(hoadon);
            redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
        }

        return "redirect:/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1&hthd=on";

    }

    void sualuong(Hoadon hoadoncu, Hoadon hoadon, ArrayList<Nhanvien> nhanvien, String ngay) {

        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        // laays ngay thanh toan
        String splitDate[] = df.format(date).split("/|-");
        String splitngay[] = ngay.split("/|-");
        Long tongtienvoncu = 0L;
        List<Chitiethoadon> chitiethoadonscu = chiTietHoaDonService.findByHoadon(hoadoncu);
        for (Chitiethoadon ct : chitiethoadonscu) {

            tongtienvoncu += ct.getHanghoa().getGianhap() * ct.getSoluong();
        }

        Long tongtienvon = 0L;
        List<Chitiethoadon> chitiethoadons = chiTietHoaDonService.findByHoadon(hoadon);
        for (Chitiethoadon ct : chitiethoadons) {

            tongtienvon += ct.getHanghoa().getGianhap() * ct.getSoluong();
        }


        String ngaythanhtoanhoadoncu[] = df.format(hoadoncu.getNgaythanhtoan()).split("/|-");
        String ngaythanhtoanhoadon[] = df.format(hoadon.getNgaythanhtoan()).split("/|-");


        for (Nhanvien nv : nhanvien) {
            Long thuongcu = 0L;
            Long thuong = 0L;
            Luong luong = luongService.findOneByNhanvienAndThangAndNam(nv, splitngay[1],
                    splitngay[2]);
            if (ngaythanhtoanhoadoncu[1].equals(ngaythanhtoanhoadon[1])
                    && ngaythanhtoanhoadoncu[2].equals(ngaythanhtoanhoadon[2])) {


                if (hoadoncu.getTiendatra() < tongtienvoncu) {
                    thuongcu = (long) (luong.getThuongcuahoadon()
                            + (((hoadoncu.getTongtien() - (tongtienvoncu)) * nv.getChietkhau()) / 100));
                } else {
                    thuongcu = (long) (luong.getThuongcuahoadon()
                            - (((hoadoncu.getTiendatra() - (tongtienvoncu)) * nv.getChietkhau()) / 100));
                }

            } else {
                thuongcu = luong.getThuongcuahoadon();
            }

            if (hoadon.getTiendatra() < tongtienvon) {
                thuong = (long) -(((hoadon.getTongtien() - (tongtienvon)) * nv.getChietkhau()) / 100);
            } else {
                thuong = (long) (((hoadon.getTiendatra() - (tongtienvon)) * nv.getChietkhau()) / 100);
                /*double phantramdatduoc = ((float)(hoadon.getTiendatra() / hoadon.getTongtien())) * 100;
                long tiendatduoc =(long) ((phantramdatduoc * (hoadon.getTongtien() - tongtienvon)) / 100);
                thuong = (long) ((tiendatduoc * nv.getChietkhau()) / 100);*/

            }


            luong.setThuongcuahoadon(thuongcu + (thuong));
            luongService.saveOrUpdate(luong);

            /*Nhanvien nhanviencaptren = nhanVienService.findById(nv.getIdnhanviencaptren());
            Luong luongnhanviencaptren = luongService.findOneByNhanvienAndThangAndNam(nhanviencaptren, splitngay[1], splitngay[2]);
            if (nv.getIdnhanviencaptren() != 0 && hoadoncu.getTongtien().equals(hoadoncu.getTiendatra())) {

                Long thuongcaptren = (long) (((hoadoncu.getTiendatra() - (tongtienvon)) * nv.getChietkhauchonhanviencaptren()) / 100);
                luongnhanviencaptren.setThuongcuahoadon(luongnhanviencaptren.getThuongcuahoadon() - (thuongcaptren));
                luongService.saveOrUpdate(luongnhanviencaptren);
            }
            if (nv.getIdnhanviencaptren() != 0 && hoadon.getTongtien().equals(hoadon.getTiendatra())) {
                Long thuongcaptren = (long) (((hoadon.getTiendatra() - (tongtienvon)) * nv.getChietkhauchonhanviencaptren()) / 100);
                luongnhanviencaptren.setThuongcuahoadon(luongnhanviencaptren.getThuongcuahoadon() + (thuongcaptren));
                luongService.saveOrUpdate(luongnhanviencaptren);
            }*/
            if (nv.getIdnhanviencaptren() != 0) {
                Long thuongcunhanviencaptren = 0L;
                Long thuongnhanviencaptren = 0L;
                Nhanvien nhanviencaptren = nhanVienService.findById(nv.getIdnhanviencaptren());
                Luong luongnhanviencaptren = luongService.findOneByNhanvienAndThangAndNam(nhanviencaptren, splitngay[1], splitngay[2]);
                if (ngaythanhtoanhoadoncu[1].equals(ngaythanhtoanhoadon[1])
                        && ngaythanhtoanhoadoncu[2].equals(ngaythanhtoanhoadon[2])) {
                    if (hoadoncu.getTiendatra() < tongtienvoncu) {
                        thuongcunhanviencaptren = (long) (luongnhanviencaptren.getThuongcuahoadon()
                                + (((hoadoncu.getTongtien() - (tongtienvoncu)) * nv.getChietkhauchonhanviencaptren()) / 100));
                    } else {
                        thuongcunhanviencaptren = (long) (luongnhanviencaptren.getThuongcuahoadon()
                                - (((hoadoncu.getTiendatra() - (tongtienvoncu)) * nv.getChietkhauchonhanviencaptren()) / 100));
                    }

                } else {
                    thuongcunhanviencaptren = luongnhanviencaptren.getThuongcuahoadon();
                }

                if (hoadon.getTiendatra() < tongtienvon) {
                    thuongnhanviencaptren = (long) -(((hoadon.getTongtien() - (tongtienvon)) * nv.getChietkhauchonhanviencaptren()) / 100);
                } else {
                    thuongnhanviencaptren = (long) (((hoadon.getTiendatra() - (tongtienvon)) * nv.getChietkhauchonhanviencaptren()) / 100);
                }

                luongnhanviencaptren.setThuongcuahoadon(thuongcunhanviencaptren + (thuongnhanviencaptren));
                luongService.saveOrUpdate(luongnhanviencaptren);
            }
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BANHANG')")
    @PatchMapping(value = "/hoadon", params = "deleted")
    String xoaVinhVienHoaDon(@ModelAttribute("hoadon") Hoadon hoadon, RedirectAttributes redirectAttributes) {
        List<Chitiethoadon> chitiethoadons = null;
        try {
            chitiethoadons = chiTietHoaDonService.findByHoadon(hoadon);
            if (!chitiethoadons.isEmpty()) {
                for (Chitiethoadon cthd : chitiethoadons) {
                    chiTietHoaDonService.delete(cthd);
                }
            }
            hoaDonService.deleted(hoadon);

            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
        }

        return "redirect:/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1&hthd=on";

    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BANHANG')")
    @PatchMapping(value = "/xoavinhvienhoadon")
    String xoaVinhVienHoaDonPopup(@RequestParam(value = "idds") Integer idds, RedirectAttributes redirectAttributes) {
        List<Chitiethoadon> chitiethoadons = null;
        try {
            Hoadon hoadon = hoaDonService.findById(idds);
            chitiethoadons = chiTietHoaDonService.findByHoadon(hoadon);
            if (!chitiethoadons.isEmpty()) {
                for (Chitiethoadon cthd : chitiethoadons) {
                    chiTietHoaDonService.delete(cthd);
                }
            }
            hoaDonService.deleted(hoadon);

            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Xóa Vĩnh Viễn Thất Bại");
        }

        return "redirect:/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1&hthd=on";

    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BANHANG')")
    @DeleteMapping("/hoadon")
    String xoaHoaDon(@RequestParam(value = "trangthai", defaultValue = "dathanhtoan") String trangthai,
                     @RequestParam(value = "limit", defaultValue = "100") Integer limit,
                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                     @RequestParam("arrayId") List<Integer> arrayId, RedirectAttributes redirectAttributes) {

        try {
            arrayId.forEach(x -> {

                Hoadon hoadon = hoaDonService.findById(x);
                hoadon.setTrangthai("deleted");

                Long tongtienvon = 0L;
                List<Chitiethoadon> chitiethoadons = chiTietHoaDonService.findByHoadon(hoadon);
                for (Chitiethoadon ct : chitiethoadons) {

                    tongtienvon += ct.getHanghoa().getGianhap() * ct.getSoluong();
                }

                Khachhang khachhang = hoadon.getKhachhang();
                Double tientrendiem = khachhang.getNhomkhachhang().getSotientrendiem();

                Integer diemtrentien = khachhang.getNhomkhachhang().getSodiemtrentien();

                Double phantramtien = khachhang.getNhomkhachhang().getPhantramtien();

                Long sotienchamsoc = (long) (khachhang.getSotienchamsoc()
                        - (((hoadon.getTongtien() - tongtienvon ) * phantramtien) / 100));

                Integer diem = (int) (khachhang.getDiem() - (hoadon.getTongtien() / tientrendiem) * diemtrentien);

                khachhang.setSotienchamsoc(sotienchamsoc);
                khachhang.setDiem(diem);
                khachhang.setLanmuahang(khachhang.getLanmuahang() - 1);
                Nhomkhachhang nhomkhachhang = nhomKhachHangService.findFirst1ByDiemLessThanEqualOrderByDiemDesc(diem);
                if(nhomkhachhang != null){
                    khachhang.setNhomkhachhang(nhomkhachhang);
                }
                khachHangService.saveOrUpdate(khachhang);

                // Luong

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String splitngay[] = df.format(hoadon.getNgaythanhtoan()).split("/|-");


                Luong luong = luongService.findOneByNhanvienAndThangAndNam(hoadon.getNhanvienByIdnhanvienban(),
                        splitngay[1], splitngay[2]);
                Long thuongcu = 0L;
                if (hoadon.getTiendatra() == 0L) {
                    thuongcu = (long) (luong.getThuongcuahoadon()
                            - (-(((hoadon.getTongtien() - (tongtienvon)) * hoadon.getNhanvienByIdnhanvienban().getChietkhau()) / 100)));
                } else {
                    thuongcu = (long) (luong.getThuongcuahoadon()
                            - (((hoadon.getTiendatra() - (tongtienvon)) * hoadon.getNhanvienByIdnhanvienban().getChietkhau()) / 100));
                }


                luong.setThuongcuahoadon(thuongcu);
                luongService.saveOrUpdate(luong);
                hoaDonService.saveOrUpdate(hoadon);
               /* if (hoadon.getNhanvienByIdnhanvienban().getIdnhanviencaptren() != 0
                        && hoadon.getTongtien().equals(hoadon.getTiendatra())) {
                    Nhanvien nhanviencaptren = nhanVienService.findById(hoadon.getNhanvienByIdnhanvienban().getIdnhanviencaptren());
                    Luong luongnhanviencaptren = luongService.findOneByNhanvienAndThangAndNam(nhanviencaptren,
                            splitngay[1], splitngay[2]);
                    Long thuongcaptren = (long) (((hoadon.getTiendatra() - (tongtienvon)) * hoadon.getNhanvienByIdnhanvienban().getChietkhauchonhanviencaptren()) / 100);
                    luongnhanviencaptren.setThuongcuahoadon(luongnhanviencaptren.getThuongcuahoadon() - (thuongcaptren));
                    luongService.saveOrUpdate(luongnhanviencaptren);
                }*/
                if (hoadon.getNhanvienByIdnhanvienban().getIdnhanviencaptren() != 0) {
                    Long thuongnhanviencaptren = 0L;
                    Nhanvien nhanviencaptren = nhanVienService.findById
                            (hoadon.getNhanvienByIdnhanvienban().getIdnhanviencaptren());
                    Luong luongnhanviencaptren = luongService.findOneByNhanvienAndThangAndNam
                            (nhanviencaptren, splitngay[1], splitngay[2]);


                    if (hoadon.getTiendatra() < tongtienvon) {
                        thuongnhanviencaptren = (long) -(((hoadon.getTongtien() - (tongtienvon)) * hoadon.getNhanvienByIdnhanvienban().getChietkhauchonhanviencaptren()) / 100);
                    } else {
                        thuongnhanviencaptren = (long) (((hoadon.getTiendatra() - (tongtienvon)) * hoadon.getNhanvienByIdnhanvienban().getChietkhauchonhanviencaptren()) / 100);
                    }

                    luongnhanviencaptren.setThuongcuahoadon(luongnhanviencaptren.getThuongcuahoadon() - (thuongnhanviencaptren));
                    luongService.saveOrUpdate(luongnhanviencaptren);
                }
            });

            redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
        }

        return "redirect:/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1&hthd=on";
    }
}
