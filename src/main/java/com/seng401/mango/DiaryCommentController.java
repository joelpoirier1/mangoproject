package com.seng401.mango;

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
public class DiaryCommentController {

    private CommentRequest request = new CommentRequest();
    private UserRepo userRepo = new UserRepo();
    private PostRepo postRepo = new PostRepo();
    private UUID currentUser;
    private UUID currentComment;

    //Shows the diary comment page to the user. This shows the replies to comments of a post the user created
    @RequestMapping(value="/diaryComment", method = RequestMethod.GET)
    public String dairyCommentPage(Model model){

        if(model.containsAttribute("validate") && model.containsAttribute("commentValidate")) {
            currentUser = (UUID) model.getAttribute("validate");
            currentComment = (UUID) model.getAttribute("commentValidate");
        }

        //confirms if user has logged in
        if(!model.containsAttribute("validate") && currentUser == null)
            return "redirect:";

        //makes sure currentUser is not null
        if(!model.containsAttribute("currentUser")){
            model.addAttribute("currentUser", userRepo.getUserByID(currentUser));
        }

        //makes sure parent is not null
        if (!model.containsAttribute("parent")){
            model.addAttribute("parent", request.getCommentByCommentID((currentComment)));
        }

        //makes sure parentList is not null
        if(!model.containsAttribute("parentList")){
            model.addAttribute("parentList", request.getCommentForParentID(currentComment).getComments());
        }

        //check if microservice in enabled
        if(request.getAPIStatus()){
            model.addAttribute("disabled", true);
        }else{
            model.addAttribute("disabled", false);
        }

        return "diaryComment";
    }

    @RequestMapping(value="/returnToPost", method = RequestMethod.POST)
    public String returnToPost(@ModelAttribute("replyForm") ReplyForm replyForm, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("post", postRepo.getPostByUUID(request.getCommentByCommentID(currentComment).getPostID()).get());
        redirectAttributes.addFlashAttribute("commentList", filterComments(request.getCommentForPostID(request.getCommentByCommentID(currentComment).getPostID()).getComments()));
        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(currentUser));

        return "redirect:/diaryPost";
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
