package com.seng401.mango;

import database.repository.UserRepo;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
        private UserRepo userRepo;
        private RedirectAttributes myRedirect;

        //creates an account for a new user
        @RequestMapping(value="/createAccount", method = RequestMethod.POST)
        public String register(@ModelAttribute("registerForm") RegisterForm registerForm, RedirectAttributes redirectAttributes) {

            myRedirect = redirectAttributes;
            userRepo = new UserRepo();

            //Checks if username and password are valid
            if(validate(registerForm)){
                redirectAttributes = myRedirect;
                return "redirect:/register";
            }

            //Check if username already exists
            if (userRepo.validateUsernameExistence(registerForm.getUsername())) {
                redirectAttributes.addFlashAttribute("alreadyExists", true);
                return "redirect:/register";
            }

            //Add user to database
            User user = new User(registerForm.getUsername(), registerForm.getPassword());
            userRepo.addUser(user);

            //return html to login
            return "index";
        }

    //validates the information entered by the user
    public boolean validate(RegisterForm registerForm) {
        if(registerForm.getUsername().equals("") || registerForm.getPassword().equals("")) {
            myRedirect.addFlashAttribute("invalidInput", true);             //checks is username or password were left empty
            return true;
        }else if(!(registerForm.getPassword().equals(registerForm.getVerifyPassword()))){
            myRedirect.addFlashAttribute("passwordMatch", true);            //checks if password and verify password are the same
            return true;
        }else if(registerForm.getUsername().length() > 10 || registerForm.getPassword().length() > 10){
            myRedirect.addFlashAttribute("invalidLength", true);            //checks that username and password are of correct length
            return true;
        }else if(registerForm.getUsername().contains(" ") || registerForm.getPassword().contains(" ")){
            myRedirect.addFlashAttribute("containsSpace", true);            //checks if username of password includes a space
            return true;
        } else return false;
    }

    //returns the user to the login page
    @RequestMapping(value="/return", method = RequestMethod.POST)
    public String submit() {
        return "index";
    }

    //displays the register page
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String postPage(Model model) {
            return "register";
    }
}
