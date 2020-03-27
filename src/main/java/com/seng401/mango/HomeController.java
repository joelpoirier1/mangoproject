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

import java.util.ArrayList;
import java.util.UUID;

@Controller
public class HomeController {

    private CommentRequest request = new CommentRequest();
    private UserRepo userRepo = new UserRepo();
    private PostRepo postRepo = new PostRepo();
    private UUID currentUser;
    private Model myModel;
    private RedirectAttributes myRedirect;

    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public String logout() {
        return "redirect:";
    }

    @RequestMapping(value="/inspectPost", method = RequestMethod.POST)
    public String inspect(@ModelAttribute("inspectionForm") InspectionForm inspectionForm, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("post", postRepo.getPostByUUID(inspectionForm.getPostID()).get());

        for(Post p: postRepo.getAllPosts()){
            if(p.getPostID().compareTo(inspectionForm.getPostID()) == 0) {
                p.setCommentList(request.getCommentForPostID(inspectionForm.getPostID()).getComments());
                redirectAttributes.addFlashAttribute("commentList", p.getCommentList());
            }
        }

        redirectAttributes.addFlashAttribute("validate", currentUser);
        redirectAttributes.addFlashAttribute("postValidate", inspectionForm.getPostID());
        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(inspectionForm.getUserID()));
        return "redirect:/post";
    }


    @RequestMapping(value="/addPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("postForm") PostForm postForm, Model model, RedirectAttributes redirectAttributes) {
        myModel = model;
        myRedirect = redirectAttributes;

        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(postForm.getUserID()));

        if(validatePostForm(postForm)) {
            redirectAttributes = myRedirect;
            return "redirect:/home";
        }

        Post post = new Post(postForm.getMessage(), postForm.getTitle(), PostCategory.Lifestyle, postForm.getUserID());
        postRepo.addPost(post);


        redirectAttributes.addFlashAttribute("posts", postRepo.getAllPosts());

        return "redirect:/home";
    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String homepage(Model model){

        if(model.containsAttribute("validate"))
            currentUser = (UUID) model.getAttribute("validate");

        if(!model.containsAttribute("validate") && !model.containsAttribute("currentUser"))
            return "redirect:";


        if(!model.containsAttribute("currentUser")){
            model.addAttribute("currentUser", userRepo.getUserByID(currentUser));
        }

        if (!model.containsAttribute("posts")){
            model.addAttribute("posts", postRepo.getAllPosts());
        }

        return "home";
    }


    public boolean validatePostForm(PostForm postForm){
        if(postForm.getTitle().isEmpty() && postForm.getMessage().isEmpty()) {
            myRedirect.addFlashAttribute("invalidTitle", true);
            myRedirect.addFlashAttribute("invalidMessage", true);
            return true;
        } else if(postForm.getTitle().isEmpty()){
            myRedirect.addFlashAttribute("invalidTitle", true);
            return true;
        } else if(postForm.getMessage().isEmpty()) {
            myRedirect.addFlashAttribute("invalidMessage", true);
            return true;
        } return false;
    }
}