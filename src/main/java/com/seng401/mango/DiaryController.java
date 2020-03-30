package com.seng401.mango;

import api.CommentRequest;
import database.repository.PostRepo;
import database.repository.UserRepo;
import model.Post;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class DiaryController {
    private UUID currentUser;
    private PostRepo postRepo = new PostRepo();
    private UserRepo userRepo = new UserRepo();
    private CommentRequest request = new CommentRequest();

    @RequestMapping(value="/diary", method = RequestMethod.GET)
    public String diaryPage(Model model) {
        if(model.containsAttribute("validate"))
            currentUser = (UUID) model.getAttribute("validate");

        if(!model.containsAttribute("validate") && currentUser == null)
            return "redirect:";

        if(!model.containsAttribute("currentUser")){
            model.addAttribute("currentUser", userRepo.getUserByID(currentUser));
        }

        if(!model.containsAttribute("posts")){
            model.addAttribute("posts", postRepo.getPostsByUserID(currentUser));
        }

        if(postRepo.getPostsByUserID(currentUser).isEmpty()){
            model.addAttribute("empty", true);
        }

        return "diary";
    }

    @RequestMapping(value="/goToPost", method = RequestMethod.POST)
    public String goToPost(@ModelAttribute("inspectionForm") InspectionForm inspectionForm, RedirectAttributes redirectAttributes){
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
        return "redirect:/diaryPost";
    }

    @RequestMapping(value="/goHome", method = RequestMethod.POST)
    public String home() {
        return "redirect:/home";
    }
}
