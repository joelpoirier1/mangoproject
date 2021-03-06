package com.seng401.mango;

import database.repository.PostRepo;
import database.repository.UserRepo;
import model.PostCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private UserRepo userRepo;
    private PostRepo postRepo;
    private RedirectAttributes myRedirect;

    //Shows the login page
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm() {
        return "redirect:";
    }

    //Allows the user to logout
    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public String logout() {
        return "redirect:";
    }

    //checks users login credentials
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model, RedirectAttributes redirectAttributes) {
        myRedirect = redirectAttributes;
        userRepo = new UserRepo();
        postRepo = new PostRepo();

        if(validate(loginForm)) {
            redirectAttributes = myRedirect;
            return "redirect:";         //returns to login page if username or password were invalid
        }
        else {
            redirectAttributes.addFlashAttribute("currentUser", userRepo.getUser(loginForm.getUsername()));
            redirectAttributes.addFlashAttribute("posts", postRepo.getAllPosts());
            redirectAttributes.addFlashAttribute("validate", userRepo.getUser(loginForm.getUsername()).get().getId());
            redirectAttributes.addFlashAttribute("categories", PostCategory.values());

            return "redirect:/home";        //logs user in to the main homepage
        }
    }

    //Validates the username and password being entered
    public boolean validate(LoginForm loginForm){
        if(loginForm.getUsername().equals("") && loginForm.getPassword().equals("")){       //checks if username and password are empty
            myRedirect.addFlashAttribute("invalidUsername", true);
            myRedirect.addFlashAttribute("invalidPassword", true);
            return true;
        } else if(loginForm.getUsername().equals("")) {
            myRedirect.addFlashAttribute("invalidUsername", true);
            return true;
        }else if(loginForm.getPassword().equals("")) {
            myRedirect.addFlashAttribute("invalidPassword", true);
            return true;
        }else if(!userRepo.validateUsernameExistence(loginForm.getUsername())) {       //checks if username exists
            myRedirect.addFlashAttribute("usernameExistence", true);
            return true;
        } else if(!userRepo.validateUser(loginForm.getUsername(), loginForm.getPassword()).isPresent()) {       //checks if the password is correct
            myRedirect.addFlashAttribute("incorrectPassword", true);
            return true;
        } else return false;
    }

    //redirects user to the registration page
    @RequestMapping(value="/submit", method = RequestMethod.POST)
    public String submit() {
        return "redirect:/register";
    }
}