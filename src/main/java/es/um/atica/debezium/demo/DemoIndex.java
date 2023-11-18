package es.um.atica.debezium.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoIndex {

    @Autowired
    private DataSourceProperties userSourceProperties;
    @Autowired
    private DataSourceProperties userTargetProperties;

    @RequestMapping("/")
    public String index(Model model) {
        String sourceType = userSourceProperties.getDriverClassName().contains("oracle")?"Oracle":"MySQL";
        String targetType = userTargetProperties.getDriverClassName().contains("oracle")?"Oracle":"MySQL";
        model.addAttribute("title","SpringBoot Debezium Demo");
        model.addAttribute("srcdatabase",String.format("Source Database [%s]",sourceType));
        model.addAttribute("tgtdatabase",String.format("Target Database [%s]",targetType));
        return "index";
    }

}
