package com.seng401.mango;

import database.repository.UserRepo;
import model.UserModel;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
        private UserRepo userRepo;
        private Model myModel;

        //to get register page
        @RequestMapping(value = "/register", method = RequestMethod.GET)
        public String getRegisterForm() {
            return "register";
        }

        //registering user
        @RequestMapping(value="/register", method = RequestMethod.POST)
        public String register(@ModelAttribute("registerForm") RegisterForm registerForm, Model model)
        {
            myModel = model;
            userRepo = new UserRepo();

            //Checks if username and password are valid
            if(validate(registerForm)){
                model = myModel;
                return "register";
            }

            //Check if username already exists
            if (userRepo.validateUsernameExistence(registerForm.getUsername())) {
                model.addAttribute("alreadyExists", true);
                return "register";
            }

            //Add user to database
            UserModel user = new UserModel(registerForm.getUsername(), registerForm.getPassword());
            userRepo.addUser(user);

            model = myModel;

            //return html to login
            return "index";
        }

    //valid username and password
    public boolean validate(RegisterForm registerForm) {
        if(registerForm.getUsername().equals("") || registerForm.getPassword().equals("")) {
            myModel.addAttribute("invalidInput", true);
            return true;
        }else if(!(registerForm.getPassword().equals(registerForm.getVerifyPassword()))){
            System.out.println(registerForm.getPassword() + " " + registerForm.getVerifyPassword());
            myModel.addAttribute("passwordMatch", true);
            return true;
        }else if(registerForm.getUsername().length() > 10 || registerForm.getPassword().length() > 10){
            myModel.addAttribute("invalidLength", true);
            return true;
        }else if(registerForm.getUsername().contains(" ") || registerForm.getPassword().contains(" ")){
            myModel.addAttribute("containsSpace", true);
            return true;
        } else return false;
    }


    //return to login
    @RequestMapping(value="/return", method = RequestMethod.POST)
    public String submit() {
        return "index";
    }

}
