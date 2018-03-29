package bcc.springhibernate.controller;

import bcc.springhibernate.model.Chitietchamsoc;
import bcc.springhibernate.service.ChiTietChamSocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class ChiTietChamSocRestController {

    @Autowired
    ChiTietChamSocService chiTietChamSocService;

    @PostMapping("/updatechitietchamsoc")
    String updateChiTietChamSoc(@RequestBody Chitietchamsoc chitietchamsoc) {


        try {

            Chitietchamsoc getchitietchamsoc = chiTietChamSocService.findById(chitietchamsoc.getId());
            chiTietChamSocService.delete(getchitietchamsoc);
            return "success";
        } catch (Exception e) {
            return "error";
        }

    }
}
