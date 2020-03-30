package com.seng401.mango;

import api.CommentCommand;
import api.CommentRequest;
import database.repository.PostRepo;
import database.repository.UserRepo;
import model.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.UUID;

@Controller
public class DiaryPostController {
    private CommentRequest request = new CommentRequest();
    private UserRepo userRepo = new UserRepo();
    private PostRepo postRepo = new PostRepo();
    private UUID currentUser = null;
    private UUID currentPost = null;
    private RedirectAttributes myRedirect;

    //returns the post page
    @RequestMapping(value="/diaryPost", method = RequestMethod.GET)
    public String diaryPostPage(Model model){
        //sets the current user and post if the user just got on the post page
        if(model.containsAttribute("validate") && model.containsAttribute("postValidate")) {
            currentUser = (UUID) model.getAttribute("validate");
            currentPost = (UUID) model.getAttribute("postValidate");
        }

        //confirms if user has logged in
        if(!model.containsAttribute("validate") && currentUser == null)
            return "redirect:";

        //makes sure currentUser is not null
        if(!model.containsAttribute("currentUser")){
            model.addAttribute("currentUser", userRepo.getUserByID(currentUser));
        }

        //makes sure post is not null
        if (!model.containsAttribute("post")){
            model.addAttribute("post", postRepo.getPostByUUID(currentPost).get());
        }

        //makes sure commentList is not null
        if(!model.containsAttribute("commentList")){
            postRepo.getPostByUUID(currentPost).get().setCommentList(request.getCommentForPostID(currentPost).getComments());
            model.addAttribute("commentList", filterComments(request.getCommentForPostID(currentPost).getComments()));
        }

        if(request.getAPIStatus()){
            model.addAttribute("disabled", true);
        }else{
            model.addAttribute("disabled", false);
        }

        return "diaryPost";
    }

    @RequestMapping(value="/goToComment", method = RequestMethod.POST)
    public String goToComment(@ModelAttribute("inspectCommentForm") InspectCommentForm inspectCommentForm, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("validate", currentUser);
        redirectAttributes.addFlashAttribute("commentValidate", inspectCommentForm.getPostID());

        redirectAttributes.addFlashAttribute("parent", request.getCommentByCommentID(inspectCommentForm.getPostID()));
        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(inspectCommentForm.getUserID()));
        redirectAttributes.addFlashAttribute("parentList", request.getCommentForParentID(inspectCommentForm.getPostID()).getComments());
        return "redirect:/diaryComment";
    }

    @RequestMapping(value="/returnToDiary", method = RequestMethod.POST)
    public String returnHome(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(currentUser));
        redirectAttributes.addFlashAttribute("posts", postRepo.getPostsByUserID(currentUser));

        return "redirect:/diary";
    }

    public ArrayList<Comment> filterComments(ArrayList<Comment> oldArray){
        ArrayList<Comment> newArray = new ArrayList<>();
        for(Comment com: oldArray){
            if(!com.getParentID().isPresent()){
                newArray.add(com);
            }
        }
        return  newArray;
    }
}
