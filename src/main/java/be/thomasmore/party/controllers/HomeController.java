package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
        //home.html zal gerendered worden
    }
    @GetMapping("/about")
    public String about() {
        return "about";
        //about.html zal gerendered worden
    }
}
