package bcc.springhibernate.controller;

import bcc.springhibernate.model.Quyen;
import bcc.springhibernate.service.QuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class QuyenRestController {

    @Autowired
    QuyenService quyenService;

    @PostMapping("/kiemtramaquyen")
    String kiemTraMaQuyen(@RequestBody Quyen quyen) {

        Quyen quyenByMaQuyen = null;
        try {
            quyenByMaQuyen = quyenService.findByMaquyen(quyen.getMaquyen());
            if (quyen.getId() == null) {

                if (quyenByMaQuyen == null) {

                    return "success";
                }
            } else {
                if (quyenByMaQuyen != null) {
                    if (quyen.getId() == quyenByMaQuyen.getId()) {

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
}
