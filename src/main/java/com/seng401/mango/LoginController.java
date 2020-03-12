package com.seng401.mango;


import database.repository.UserRepo;
import model.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class LoginController
{
    private UserRepo userRepo;
    private Model myModel;

    //to get login page
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm() {
        return "index";
    }

    //checking for login credentials
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model)
    {
        myModel = model;
        userRepo = new UserRepo();

        if(validate(loginForm)) {
            model = myModel;
            return "index";
        }
        else {
            model = myModel;
            model.addAttribute("posts", getComments());
            return "home";
        }
    }

    public ArrayList<Comment> getComments(){

        ArrayList<Comment> comments = new ArrayList<>();
        Comment comment = new Comment("hey man. Im doing so great");
        Comment comm = new Comment("bro Im awesome");
        comments.add(comment);
        comments.add(comm);
        return comments;
    }

    public boolean validate(LoginForm loginForm){
        if(loginForm.getUsername().equals("") || loginForm.getPassword().equals("")){
            myModel.addAttribute("invalidInput", true);
            return true;
        } else if(!userRepo.validateUsernameExistence(loginForm.getUsername())) {
            myModel.addAttribute("usernameExistence", true);
            return true;
        } else if(!userRepo.validateUser(loginForm.getUsername(), loginForm.getPassword()).isPresent()) {
            myModel.addAttribute("incorrectPassword", true);
            return true;
        } else return false;
    }

    //checking for login credentials
    @RequestMapping(value="/submit", method = RequestMethod.POST)
    public String submit() {
        return "register";
    }
}