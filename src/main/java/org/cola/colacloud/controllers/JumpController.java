package org.cola.colacloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("jump")
public class JumpController {
    
    @RequestMapping("query")
    public String QueryBook()
    {
        System.out.println("querybook controller");
        return "querybook";
    }

    @RequestMapping("add")
    public String AddBook()
    {
        return "addbook";
    }

    @RequestMapping("delete")
    public String DeleteBook()
    {
        return "deletebook";
    }

}
