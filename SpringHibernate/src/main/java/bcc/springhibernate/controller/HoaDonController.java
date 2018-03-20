package bcc.springhibernate.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bcc.springhibernate.model.*;
import bcc.springhibernate.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

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
            List<Hoadon> listHoadon = null;
            List<Hoadon> listHoadonThongKe = null;
            List<Hoadon> listHoadonKpi = null;
            List<Nhanvienkpi> listNhanvienkpi = null;
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
               listNhanvienkpi =nhanVienKpiService.findByNhanvienAndTrangthaiAndNgaydangkyBetween
                        (taikhoan.getNhanvien(), "active", dtungay,ddenngay);
                listHoadonKpi = hoaDonService.findByTrangthaiNotAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetweenOrderByIdDesc("deleted",
                        taikhoan.getNhanvien(), dtungay, ddenngay);

                if (!tungay.equals("null")) {
                    dtungay = dateFormat.parse(tungay);
                }
                if (!denngay.equals("null")) {
                    ddenngay = dateFormat.parse(denngay);
                }
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
                    } else {
                        int listHoaDonSize = hoaDonService.findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween(
                                "deleted", taikhoan.getNhanvien(), dtungay, ddenngay).size();
                        pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);
                        listHoadon = hoaDonService.findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienbanAndNgaythanhtoanBetween(
                                "deleted", taikhoan.getNhanvien(), dtungay, ddenngay, new PageRequest(page - 1, limit));
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
                    } else {
                        int listHoaDonSize = hoaDonService.findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienban(
                                "deleted", taikhoan.getNhanvien()).size();
                        pageCount = listHoaDonSize / limit + (listHoaDonSize % limit > 0 ? 1 : 0);
                        listHoadon = hoaDonService.findByTrangthaiDaThanhToanAndNhanvienByIdnhanvienban(
                                "deleted", taikhoan.getNhanvien(), new PageRequest(page - 1, limit));
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
        } catch (Exception e) {
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

        Hoadon hoadon = hoaDonService.findById(id);
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
            return "redirect:/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1";
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
            Long tongtien = Long.valueOf(tongtien_money.replace(".", ""));
            Long tiendatra = Long.valueOf(tiendatra_money.replace(".", ""));
            Long congno = Long.valueOf(congno_money.replace(".", ""));
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Nhanvien getNhanVienBanHangById = nhanVienService.findById(nhanvienbanhang);
            Nhanvien getNhanVienChamSocById = nhanVienService.findById(nhanvienchamsoc);
            Nhanvien getNhanVienGiaoHangById = nhanVienService.findById(nhanviengiaohang);
            Taikhoan getTaiKhoanByUserName = taikhoanService.findByUsername(principal.getName());
            Nhanvien getNhanVienLapHoaDon = getTaiKhoanByUserName.getNhanvien();

            Khachhang getKhachHangById = khachHangService.findById(khachhang);

            List<Hoadon> getListHoaDonByKhachHang = hoaDonService.findByKhachhang(getKhachHangById);


            if (getListHoaDonByKhachHang.isEmpty()) {
                hoadon.setHoadondautien(true);
            } else {
                hoadon.setHoadondautien(false);
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
                        soluonghh.get(i), Long.valueOf(giabanhh.get(i).replace(".", "")),
                        Long.valueOf(thanhtienhh.get(i).replace(".", "")), "active", "");
                chiTietHoaDonService.saveOrUpdate(chitiethoadon);

            }
            Double tientrendiem = getKhachHangById.getNhomkhachhang().getSotientrendiem();

            Integer diemtrentien = getKhachHangById.getNhomkhachhang().getSodiemtrentien();

            Double phantramtien = getKhachHangById.getNhomkhachhang().getPhantramtien();

            Long sotienchamsoc = (long) (getKhachHangById.getSotienchamsoc()
                    + ((hoadon.getTongtien() * phantramtien) / 100));

            Integer diem = (int) (getKhachHangById.getDiem() + (hoadon.getTongtien() / tientrendiem) * diemtrentien);

            getKhachHangById.setSotienchamsoc(sotienchamsoc);
            getKhachHangById.setDiem(diem);
            getKhachHangById.setLanmuahang(getKhachHangById.getLanmuahang() + 1);
            khachHangService.saveOrUpdate(getKhachHangById);


            ArrayList<Nhanvien> nhanvienarr = new ArrayList<Nhanvien>();
            //nhanvienarr.add(getNhanVienLapHoaDon);
            //nhanvienarr.add(getNhanVienGiaoHangById);
            nhanvienarr.add(getNhanVienBanHangById);
            //nhanvienarr.add(getNhanVienChamSocById);

            themluong(hoadon, nhanvienarr, ngaylap);
            redirectAttributes.addFlashAttribute("msg", "Thêm Thành Công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace().toString());
            redirectAttributes.addFlashAttribute("msg", "Thêm Thất Bại");
        }

        return "redirect:/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1";

    }

    void themluong(Hoadon hoadon, ArrayList<Nhanvien> nhanvien, String ngay) {
        System.out.println(hoadon + "-=-=-=-=-==--=-=-=-=-=-=");
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String splitDate[] = df.format(date).split("/");
        String splitngay[] = ngay.split("/");
        Long tongtienvon = 0L;
        List<Chitiethoadon> chitiethoadons = chiTietHoaDonService.findByHoadon(hoadon);
        for (Chitiethoadon ct : chitiethoadons) {

            tongtienvon += (ct.getHanghoa().getGianhap() * ct.getSoluong());
        }
        for (Nhanvien nv : nhanvien) {

            Luong luong = luongService.findOneByNhanvienAndThangAndNam(nv, splitDate[1], splitDate[2]);
            Long thuong = 0L;
            if (hoadon.getTiendatra() == 0) {
                thuong = (long) -(((hoadon.getTongtien() - (tongtienvon)) * nv.getChietkhau()) / 100);
            } else {
                thuong = (long) (((hoadon.getTiendatra() - (tongtienvon)) * nv.getChietkhau()) / 100);
            }
            luong.setThuongcuahoadon(luong.getThuongcuahoadon() + (thuong));
            luongService.saveOrUpdate(luong);

            // Cộng Thưởng Hóa Đơn Cho Nhân Viên Cấp Trên
            System.out.println(nv.getIdnhanviencaptren() + "==" + hoadon.getTongtien() + "==" + hoadon.getTiendatra());
            if (nv.getIdnhanviencaptren() != 0 && hoadon.getTongtien().equals(hoadon.getTiendatra())) {
                Nhanvien nhanviencaptren = nhanVienService.findById(nv.getIdnhanviencaptren());
                System.out.println(nhanviencaptren.getId() + "==" + nhanviencaptren.getTennhanvien() + "==" + hoadon.getTiendatra());
                Luong luongnhanviencaptren = luongService.findOneByNhanvienAndThangAndNam(nhanviencaptren, splitDate[1], splitDate[2]);
                Long thuongcaptren = (long) (((hoadon.getTiendatra() - (tongtienvon)) * nv.getChietkhauchonhanviencaptren()) / 100);
                System.out.println(luongnhanviencaptren.getId() + "==" + thuongcaptren + "==" + hoadon.getTiendatra());
                luongnhanviencaptren.setThuongcuahoadon(luongnhanviencaptren.getThuongcuahoadon() + (thuongcaptren));
                luongService.saveOrUpdate(luongnhanviencaptren);
            }

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
            Long tongtien = Long.valueOf(tongtien_money.replace(".", ""));
            Long tiendatra = Long.valueOf(tiendatra_money.replace(".", ""));
            Long congno = Long.valueOf(congno_money.replace(".", ""));
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Hoadon hoadoncu = hoaDonService.findById(hoadon.getId());
            System.out.println("HOA DON CU :" + hoadoncu.getTiendatra());
            Nhanvien getNhanVienBanHangById = nhanVienService.findById(nhanvienbanhang);
            Nhanvien getNhanVienChamSocById = nhanVienService.findById(nhanvienchamsoc);
            Nhanvien getNhanVienGiaoHangById = nhanVienService.findById(nhanviengiaohang);
            Taikhoan getTaiKhoanByUserName = taikhoanService.findByUsername(principal.getName());
            Nhanvien getNhanVienLapHoaDon = getTaiKhoanByUserName.getNhanvien();

            Khachhang getKhachHangById = khachHangService.findById(khachhang);

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

            if (!hoadoncu.getTongtien().equals(hoadon.getTongtien())) {
                // Tien CHam Soc Khach Hang
                Double tientrendiem = getKhachHangById.getNhomkhachhang().getSotientrendiem();
                Integer diemtrentien = getKhachHangById.getNhomkhachhang().getSodiemtrentien();
                Double phantramtien = getKhachHangById.getNhomkhachhang().getPhantramtien();

                Double sotienchamsoccu = getKhachHangById.getSotienchamsoc()
                        - ((hoadoncu.getTongtien() * phantramtien) / 100);

                Double diemcu = getKhachHangById.getDiem() - (hoadoncu.getTongtien() / tientrendiem) * diemtrentien;

                Long sotienchamsoc = (long) (sotienchamsoccu + ((hoadon.getTongtien() * phantramtien) / 100));

                Integer diem = (int) (diemcu + (hoadon.getTongtien() / tientrendiem) * diemtrentien);

                getKhachHangById.setSotienchamsoc(sotienchamsoc);
                getKhachHangById.setDiem(diem);
                khachHangService.saveOrUpdate(getKhachHangById);

                // Luong
                /*
                 * String splitngaylap[] = ngaylap.split("/"); Luong luong =
                 * luongService.findOneByNhanvienAndThangAndNam(getNhanVienBanHangById,
                 * splitngaylap[1], splitngaylap[2]); System.out.println(luong.getThuong());
                 * System.out.println((hoadoncu.getTongtien() *
                 * getNhanVienBanHangById.getChietkhau()) / 100); Long thuongcu = (long)
                 * (luong.getThuong() - (hoadoncu.getTongtien() *
                 * getNhanVienBanHangById.getChietkhau()) / 100); System.out.println(thuongcu);
                 * Long thuong = (long) ((hoadon.getTongtien() *
                 * getNhanVienBanHangById.getChietkhau()) / 100); System.out.println(thuong);
                 *
                 * luong.setThuong(thuongcu + thuong); luongService.saveOrUpdate(luong);
                 */

            }

            ArrayList<Nhanvien> nhanvienarr = new ArrayList<Nhanvien>();
            //nhanvienarr.add(getNhanVienLapHoaDon);
            //nhanvienarr.add(getNhanVienGiaoHangById);
            nhanvienarr.add(getNhanVienBanHangById);
            //nhanvienarr.add(getNhanVienChamSocById);
            System.out.println("==== " + hoadoncu.getTiendatra());
            sualuong(hoadoncu, hoadon, nhanvienarr, ngaylap);
            hoaDonService.saveOrUpdate(hoadon);

            for (int i = 0; i < idhh.size(); i++) {
                Hanghoa hangHoaChiTietHoaDon = hangHoaService.findById(idhh.get(i));
                if (idcthd.get(i) == 0) {
                    Chitiethoadon chitiethoadon = new Chitiethoadon(hangHoaChiTietHoaDon, hoadon, hangHoaChiTietHoaDon.getGiaban(),
                            soluonghh.get(i), Long.valueOf(giabanhh.get(i).replace(".", "")),
                            Long.valueOf(thanhtienhh.get(i).replace(".", "")), "active", "");
                    chiTietHoaDonService.saveOrUpdate(chitiethoadon);

                } else {

                    Chitiethoadon chitiethoadon = new Chitiethoadon(idcthd.get(i), hangHoaChiTietHoaDon, hoadon,
                            hangHoaChiTietHoaDon.getGiaban(), soluonghh.get(i),
                            Long.valueOf(giabanhh.get(i).replace(".", "")),
                            Long.valueOf(thanhtienhh.get(i).replace(".", "")), "active", "");
                    chiTietHoaDonService.saveOrUpdate(chitiethoadon);
                }

            }

            redirectAttributes.addFlashAttribute("msg", "Sửa Thành Công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Sửa Thất Bại");
        }

        return "redirect:/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1";

    }

    void sualuong(Hoadon hoadoncu, Hoadon hoadon, ArrayList<Nhanvien> nhanvien, String ngaylap) {
        System.out.println(hoadoncu.getTiendatra() + "===" + hoadon.getTiendatra());
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String splitDate[] = df.format(date).split("/");
        String splitngaylap[] = ngaylap.split("/");
        Long tongtienvon = 0L;
        List<Chitiethoadon> chitiethoadons = chiTietHoaDonService.findByHoadon(hoadon);
        for (Chitiethoadon ct : chitiethoadons) {

            tongtienvon += ct.getHanghoa().getGianhap() * ct.getSoluong();
        }

        System.out.println("TONG TIEN VON : " + tongtienvon);
        for (Nhanvien nv : nhanvien) {
            System.out.println("NHAN VIEN : " + nv.getTennhanvien());
            Luong luong = luongService.findOneByNhanvienAndThangAndNam(nv, splitDate[1],
                    splitDate[2]);
            System.out.println("LUONG : " + luong.getId());
            Long thuongcu = 0L;
            if (hoadoncu.getTiendatra() == 0) {
                thuongcu = (long) (luong.getThuongcuahoadon()
                        - (-(((hoadoncu.getTongtien() - (tongtienvon)) * nv.getChietkhau()) / 100)));
            } else {
                thuongcu = (long) (luong.getThuongcuahoadon()
                        - (((hoadoncu.getTiendatra() - (tongtienvon)) * nv.getChietkhau()) / 100));
            }
            Long thuong = 0L;
            if (hoadon.getTiendatra() == 0) {
                thuong = (long) -(((hoadon.getTongtien() - (tongtienvon)) * nv.getChietkhau()) / 100);
            } else {
                thuong = (long) (((hoadon.getTiendatra() - (tongtienvon)) * nv.getChietkhau()) / 100);
            }

            System.out.println(luong.getThuongcuahoadon() + "===" + hoadoncu.getTiendatra() + "===" +
                    hoadon.getTiendatra() + "===" + thuongcu + "===" + thuong);
            luong.setThuongcuahoadon(thuongcu + (thuong));
            luongService.saveOrUpdate(luong);

            if (nv.getIdnhanviencaptren() != 0 && hoadoncu.getTongtien().equals(hoadoncu.getTiendatra())) {
                Nhanvien nhanviencaptren = nhanVienService.findById(nv.getIdnhanviencaptren());
                Luong luongnhanviencaptren = luongService.findOneByNhanvienAndThangAndNam(nhanviencaptren, splitDate[1], splitDate[2]);
                Long thuongcaptren = (long) (((hoadoncu.getTiendatra() - (tongtienvon)) * nv.getChietkhauchonhanviencaptren()) / 100);
                luongnhanviencaptren.setThuongcuahoadon(luongnhanviencaptren.getThuongcuahoadon() - (thuongcaptren));
                luongService.saveOrUpdate(luongnhanviencaptren);
            }
            if (nv.getIdnhanviencaptren() != 0 && hoadon.getTongtien().equals(hoadon.getTiendatra())) {
                Nhanvien nhanviencaptren = nhanVienService.findById(nv.getIdnhanviencaptren());
                Luong luongnhanviencaptren = luongService.findOneByNhanvienAndThangAndNam(nhanviencaptren, splitDate[1], splitDate[2]);
                Long thuongcaptren = (long) (((hoadon.getTiendatra() - (tongtienvon)) * nv.getChietkhauchonhanviencaptren()) / 100);
                luongnhanviencaptren.setThuongcuahoadon(luongnhanviencaptren.getThuongcuahoadon() + (thuongcaptren));
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

        return "redirect:/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1";

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

                Khachhang khachhang = hoadon.getKhachhang();
                Double tientrendiem = khachhang.getNhomkhachhang().getSotientrendiem();

                Integer diemtrentien = khachhang.getNhomkhachhang().getSodiemtrentien();

                Double phantramtien = khachhang.getNhomkhachhang().getPhantramtien();

                Long sotienchamsoc = (long) (khachhang.getSotienchamsoc()
                        - ((hoadon.getTongtien() * phantramtien) / 100));

                Integer diem = (int) (khachhang.getDiem() - (hoadon.getTongtien() / tientrendiem) * diemtrentien);

                khachhang.setSotienchamsoc(sotienchamsoc);
                khachhang.setDiem(diem);
                khachhang.setLanmuahang(khachhang.getLanmuahang() - 1);
                khachHangService.saveOrUpdate(khachhang);

                // Luong
                Long tongtienvon = 0L;
                List<Chitiethoadon> chitiethoadons = chiTietHoaDonService.findByHoadon(hoadon);
                for (Chitiethoadon ct : chitiethoadons) {

                    tongtienvon += ct.getHanghoa().getGianhap() * ct.getSoluong();
                }
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String splitngay[] = df.format(hoadon.getNgaythanhtoan()).split("/");

                System.out.println(splitngay[1] + "-" + splitngay[2]);
                Luong luong = luongService.findOneByNhanvienAndThangAndNam(hoadon.getNhanvienByIdnhanvienban(),
                        splitngay[1], splitngay[2]);
                Long thuongcu = 0L;
                if (hoadon.getTiendatra() == 0) {
                    thuongcu = (long) (luong.getThuongcuahoadon()
                            - (-(((hoadon.getTongtien() - (tongtienvon)) * hoadon.getNhanvienByIdnhanvienban().getChietkhau()) / 100)));
                } else {
                    thuongcu = (long) (luong.getThuongcuahoadon()
                            - (((hoadon.getTiendatra() - (tongtienvon)) * hoadon.getNhanvienByIdnhanvienban().getChietkhau()) / 100));
                }


                luong.setThuongcuahoadon(thuongcu);
                luongService.saveOrUpdate(luong);
                hoaDonService.saveOrUpdate(hoadon);
                if (hoadon.getNhanvienByIdnhanvienban().getIdnhanviencaptren() != 0
                        && hoadon.getTongtien().equals(hoadon.getTiendatra())) {
                    Nhanvien nhanviencaptren = nhanVienService.findById(hoadon.getNhanvienByIdnhanvienban().getIdnhanviencaptren());
                    Luong luongnhanviencaptren = luongService.findOneByNhanvienAndThangAndNam(nhanviencaptren,
                            splitngay[1], splitngay[2]);
                    Long thuongcaptren = (long) (((hoadon.getTiendatra() - (tongtienvon)) * hoadon.getNhanvienByIdnhanvienban().getChietkhauchonhanviencaptren()) / 100);
                    luongnhanviencaptren.setThuongcuahoadon(luongnhanviencaptren.getThuongcuahoadon() - (thuongcaptren));
                    luongService.saveOrUpdate(luongnhanviencaptren);
                }
            });

            redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("msg", "Xóa Thất Bại");
        }

        return "redirect:/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1";
    }
}
