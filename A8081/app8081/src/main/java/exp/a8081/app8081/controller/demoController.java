package exp.a8081.app8081.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class demoController {
    @RequestMapping("/demo")
    public String demo(Model model){
        model.addAttribute("name", "testData!");
        
        return "demo";
    }
     
}
