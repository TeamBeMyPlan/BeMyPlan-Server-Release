package com.deploy.bemyplan.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/page/home")
    public String test() {
        return "index.html";
    }
}
