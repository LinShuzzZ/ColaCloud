package org.cola.colacloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
    
    @RequestMapping({"/","/index"})
    public String index()
    {
        System.out.println("===hello function===");
        return "index";
    }

}
