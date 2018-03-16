package bcc.springhibernate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import bcc.springhibernate.model.Hanghoa;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Nhomhang;
import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.service.HangHoaService;
import bcc.springhibernate.service.LoaiKhachHangService;
import bcc.springhibernate.service.NhomHangService;
import bcc.springhibernate.service.NhomKhachHangService;

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
			if(hanghoa.getGiakhuyenmai() != null){
				hangHoaMap.put("giaban", hanghoa.getGiakhuyenmai());
			}else {
				hangHoaMap.put("giaban", hanghoa.getGiaban());
			}
			System.out.println("GKM : "+ hanghoa.getGiakhuyenmai().longValue());
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
			if(hanghoa.getGianhap() > getHangHoa.getGiaban()){
					return "success";
			}else {
				return  "error";
			}


		} catch (Exception e) {
			return  "error";
		}

	}
}
