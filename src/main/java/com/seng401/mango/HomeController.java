package com.seng401.mango;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addPost() {
        return "post";
    }

    @RequestMapping(value="/addComment", method = RequestMethod.POST)
    public String addComment(Model model) {

        return "comment";
    }
}