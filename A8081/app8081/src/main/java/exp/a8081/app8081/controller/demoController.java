package exp.a8081.app8081.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class demoController {
    @RequestMapping("/demo")
    public String demo(){
        return "demo";
    }
}