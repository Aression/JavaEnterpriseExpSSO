package exp.b8082.app8082.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("sysB")
public class sysBController {
    @RequestMapping("wel")
    public String wel(){
        return "wel";
    }
}
