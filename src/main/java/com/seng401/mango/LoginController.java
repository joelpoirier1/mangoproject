package com.seng401.mango;


import com.seng401.mango.LoginForm;
import database.repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
            return "home";
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