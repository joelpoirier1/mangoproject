package com.seng401.mango;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

    //returns an error page if something goes wrong
    @RequestMapping("/error")
    public String handleError(){
        return "error";
    }

    @Override
    public String getErrorPath(){
        return "/error";
    }
}
