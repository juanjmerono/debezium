package es.um.atica.debezium.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoIndex {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title","SpringBoot Debezium Demo");
        return "index";
    }

}
