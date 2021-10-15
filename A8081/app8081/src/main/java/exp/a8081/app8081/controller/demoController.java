package exp.a8081.app8081.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import exp.a8081.app8081.domain.User;

@Controller
public class demoController {
    @RequestMapping("/demo")
    public String demo(Model model){
        model.addAttribute("name", "testData!");
        List<String> bank=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bank.add(Integer.toString((int) (i*Math.random())));
        }
        model.addAttribute("bank", bank);
        return "demo";
    }
    @GetMapping("/register")
    public String showForm(Model model){
        User user = new User("input your name..","input your password..");
        System.out.println(user+" is created.");
        model.addAttribute("user", user);
        return "registeration";
    }
    @PostMapping("/register")
    public String submitForm(@ModelAttribute("user") User user){
        System.out.println(user);
        return "registerSuccess";
    }
}
