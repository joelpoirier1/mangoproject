package com.seng401.mango;


import api.CommentCommand;
import api.CommentRequest;
import database.repository.PostRepo;
import database.repository.UserRepo;
import model.Comment;
import model.Post;
import model.PostCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class HomeController {

    private CommentRequest request = new CommentRequest();
    private CommentCommand command = new CommentCommand();
    private UserRepo userRepo = new UserRepo();
    private PostRepo postRepo = new PostRepo();
    private UUID currentUser;

    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public String logout() {
        return "redirect:";
    }

    @RequestMapping(value="/addComment", method = RequestMethod.POST)
    public String addComment(@ModelAttribute("commentForm") CommentForm commentForm, Model model) {

        command.createComment(commentForm.getuID(), commentForm.getParentID(), commentForm.getComment());

        model.addAttribute("currentUser", userRepo.getUserByID(commentForm.getuID()));
        model.addAttribute("posts", postRepo.getAllPosts());

        return "redirect:/home";
    }

    @RequestMapping(value="/addPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("postForm") PostForm postForm, RedirectAttributes redirectAttributes) {

        Post post = new Post(postForm.getMessage(), postForm.getTitle(), PostCategory.Lifestyle, postForm.getUserID());
        postRepo.addPost(post);

        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(postForm.getUserID()));
        redirectAttributes.addFlashAttribute("posts", postRepo.getAllPosts());

        return "redirect:/home";
    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String homepage(Model model){

        if(model.containsAttribute("validate"))
            currentUser = (UUID) model.getAttribute("validate");

        if(!model.containsAttribute("validate") && !model.containsAttribute("currentUser"))
            return "redirect:/login";


        if(!model.containsAttribute("currentUser")){
            model.addAttribute("currentUser", userRepo.getUserByID(currentUser));
        }

        if (!model.containsAttribute("posts")){
            model.addAttribute("posts", postRepo.getAllPosts());
        }

        return "home";
    }
}