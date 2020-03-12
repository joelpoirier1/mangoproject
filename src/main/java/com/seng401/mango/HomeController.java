package com.seng401.mango;


import database.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addPost() {
        return "addPost";
    }

    @RequestMapping(value="/addComment", method = RequestMethod.POST)
    public String addComment() {
        return "comment";
    }
}