package bcc.springhibernate.controller;

import bcc.springhibernate.model.Kpi;
import bcc.springhibernate.service.KpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class KpiRestController {

    @Autowired
    KpiService kpiService;

    @PostMapping("/kiemtratenkpi")
    String kiemTraTenKpi(@RequestBody Kpi kpi) {

        Kpi kpiByTen = null;
        try {
            kpiByTen = kpiService.findByTen(kpi.getTen());
            if (kpi.getId() == null) {

                if (kpiByTen == null) {

                    return "success";
                }
            } else {
                if (kpiByTen != null) {
                    if (kpi.getId() == kpiByTen.getId()) {

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

    @PostMapping("/getkpi")
    Map<String, Object> getKKPI(@RequestBody Integer id) {

        Kpi kpi = null;
        Map<String, Object> kpiMap = new HashMap<String, Object>();
        try {
            kpi = kpiService.findById(id);
            kpiMap.put("id", kpi.getId());
            kpiMap.put("ten", kpi.getTen());
            kpiMap.put("kieukpi", kpi.getKieukpi());

        } catch (Exception e) {

        }
        return kpiMap;

    }

}
