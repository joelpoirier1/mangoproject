package com.seng401.mango;

import api.CommentCommand;
import api.CommentRequest;
import database.repository.PostRepo;
import database.repository.UserRepo;
import model.Comment;
import model.Post;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
public class CommentController {

    private CommentRequest request = new CommentRequest();
    private UserRepo userRepo = new UserRepo();
    private PostRepo postRepo = new PostRepo();
    private CommentCommand command = new CommentCommand();
    private UUID currentUser;
    private UUID currentComment;
    private RedirectAttributes myRedirect;

    @RequestMapping(value="/comment", method = RequestMethod.GET)
    public String commentPage(Model model){
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

        return "comment";
    }

    @RequestMapping(value="/addReply", method = RequestMethod.POST)
    public String reply(@ModelAttribute("replyForm") ReplyForm replyForm, RedirectAttributes redirectAttributes) {
        myRedirect = redirectAttributes;
        if(validateReplyForm(replyForm)){
            redirectAttributes = myRedirect;
            return "redirect:/comment";
        }

        redirectAttributes.addFlashAttribute("parent", request.getCommentByCommentID(replyForm.getParentID()));
        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(replyForm.getUserID()));
        command.createComment(Optional.ofNullable(replyForm.getParentID()), request.getCommentByCommentID(replyForm.getParentID()).getPostID(), replyForm.getReply());
        redirectAttributes.addFlashAttribute("parentList", request.getCommentForParentID(replyForm.getParentID()).getComments());

        return "redirect:/comment";
    }

    @RequestMapping(value="/returnPost", method = RequestMethod.POST)
    public String returnPost(@ModelAttribute("replyForm") ReplyForm replyForm, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("post", postRepo.getPostByUUID(request.getCommentByCommentID(currentComment).getPostID()).get());

        redirectAttributes.addFlashAttribute("commentList", filterComments(request.getCommentForPostID(request.getCommentByCommentID(currentComment).getPostID()).getComments()));

        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(currentUser));

        return "redirect:/post";
    }

    //validates that the comment message is not empty
    public boolean validateReplyForm(ReplyForm replyForm){
        if(replyForm.getReply().isEmpty()) {
            myRedirect.addFlashAttribute("invalidReply", true);
            return true;
        } else return false;
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
