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

    //to get login page
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm() {
        return "login";
    }

    //checking for login credentials
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model)
    {
        userRepo = new UserRepo();

        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        if(userRepo.validateUser(username, password).isPresent())
        {
            //return the html for home
            return "home";
        }
        model.addAttribute("invalidCredentials", true);
        //return html for login
        return "login";
    }

    //checking for login credentials
    @RequestMapping(value="/submit", method = RequestMethod.POST)
    public String submit() {
        return "register";
    }
}