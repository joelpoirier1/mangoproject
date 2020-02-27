package com.seng401.mango;

import database.repository.UserRepo;
import model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
        private UserRepo userRepo;

        //to get register page
        @RequestMapping(value = "/register", method = RequestMethod.GET)
        public String getRegisterForm() {
            return "register";
        }

        //registering user
        @RequestMapping(value="/register", method = RequestMethod.POST)
        public String register(@ModelAttribute("registerForm") RegisterForm registerForm, Model model)
        {
            //Check if input is valid
            if(registerForm.getUsername().equals("") || registerForm.getPassword().equals("")){
                model.addAttribute("invalidInput", true);
                return "register";
            }



            userRepo = new UserRepo();

            if (userRepo.validateUsernameExistence(registerForm.getUsername())) {
                model.addAttribute("alreadyExists", true);
                return "register";
            }

            UserModel user = new UserModel(registerForm.getUsername(), registerForm.getPassword());

            //add user to the database
            userRepo.addUser(user);

            //return html to login
            return "login";
        }

}
