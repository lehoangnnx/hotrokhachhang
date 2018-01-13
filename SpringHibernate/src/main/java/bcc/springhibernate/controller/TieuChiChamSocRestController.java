package bcc.springhibernate.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcc.springhibernate.model.Hanghoa;
import bcc.springhibernate.model.Tieuchichamsoc;
import bcc.springhibernate.service.TieuChiChamSocService;
@RestController
@RequestMapping("/admin")
public class TieuChiChamSocRestController {

	@Autowired
	TieuChiChamSocService tieuChiChamSocService;
	
	@PostMapping("/gettieuchichamsocid")
	Map<String, Object> getTieuChiChamSoc(@RequestBody Integer id) {
		
		Tieuchichamsoc tieuchichamsoc= null;
		Map<String, Object> tieuChiChamSocMap = new HashMap<String, Object>();
		try {
			 tieuchichamsoc = tieuChiChamSocService.findById(id);
			 tieuChiChamSocMap.put("id", tieuchichamsoc.getId());
			 tieuChiChamSocMap.put("tentieuchi", tieuchichamsoc.getTentieuchi());
			 tieuChiChamSocMap.put("kieutieuchi", tieuchichamsoc.getKieutieuchi());
			 
		} catch (Exception e) {
			
		}
		return tieuChiChamSocMap;
		
	}
	@PostMapping("/getkieutieuchi")
	Map<String, Object> getKieuTieuChi(@RequestBody Integer id) {
		
		Tieuchichamsoc tieuchichamsoc= null;
		Map<String, Object> tieuChiChamSocMap = new HashMap<String, Object>();
		try {
			 tieuchichamsoc = tieuChiChamSocService.findById(id);
			 tieuChiChamSocMap.put("id", tieuchichamsoc.getId());
			 tieuChiChamSocMap.put("tentieuchi", tieuchichamsoc.getTentieuchi());
			 tieuChiChamSocMap.put("kieutieuchi", tieuchichamsoc.getKieutieuchi());
			 
		} catch (Exception e) {
			
		}
		return tieuChiChamSocMap;
		
	}
}
