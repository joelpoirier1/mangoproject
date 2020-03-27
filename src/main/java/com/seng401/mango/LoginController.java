package com.seng401.mango;


import api.CommentRequest;
import database.repository.PostRepo;
import database.repository.UserRepo;
import model.Comment;
import model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

import static model.PostCategory.Lifestyle;

@Controller
public class LoginController
{
    private CommentRequest request = new CommentRequest();
    private UserRepo userRepo;
    private PostRepo postRepo;
    private Model myModel;

    //to get login page
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm() {
        return "index";
    }

    //checking for login credentials
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model, RedirectAttributes redirectAttributes)
    {
        myModel = model;
        userRepo = new UserRepo();
        postRepo = new PostRepo();

        if(validate(loginForm)) {
            model = myModel;
            return "index";
        }
        else {
            redirectAttributes.addFlashAttribute("currentUser", userRepo.getUser(loginForm.getUsername()));
            redirectAttributes.addFlashAttribute("posts", postRepo.getAllPosts());
            redirectAttributes.addFlashAttribute("validate", userRepo.getUser(loginForm.getUsername()).get().getId());

            return "redirect:/home";
        }
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