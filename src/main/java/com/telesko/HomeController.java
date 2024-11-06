package com.telesko;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {




    @RequestMapping(value="/api/hello", method  = RequestMethod.GET)
    public String getPage(){
        return "hello";
    }
    
}
