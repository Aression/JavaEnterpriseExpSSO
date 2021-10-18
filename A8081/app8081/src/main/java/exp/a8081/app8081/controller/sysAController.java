package exp.a8081.app8081.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("sysA")
public class sysAController {
    @RequestMapping("wel")
    public String wel(){
        return "wel";
    }
}
