package com.seng401.mango;

import api.CommentCommand;
import api.CommentRequest;
import database.repository.PostRepo;
import database.repository.UserRepo;
import model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.UUID;

@Controller
public class PostController {

    private CommentRequest request = new CommentRequest();
    private CommentCommand command = new CommentCommand();
    private UserRepo userRepo = new UserRepo();
    private PostRepo postRepo = new PostRepo();
    private UUID currentUser;
    private UUID currentPost;
    private RedirectAttributes myRedirect;

    @RequestMapping(value="/post", method = RequestMethod.GET)
    public String postPage(Model model){
        if(model.containsAttribute("validate") && model.containsAttribute("postValidate")) {
            currentUser = (UUID) model.getAttribute("validate");
            currentPost = (UUID) model.getAttribute("postValidate");
        }

        if(!model.containsAttribute("validate") && !model.containsAttribute("currentUser"))
            return "redirect:";

        if(!model.containsAttribute("currentUser")){
            model.addAttribute("currentUser", userRepo.getUserByID(currentUser));
        }

        if (!model.containsAttribute("post")){
            model.addAttribute("post", postRepo.getPostByUUID(currentPost));
        }

        if(!model.containsAttribute("commentList")){
            model.addAttribute("commentList", request.getCommentForPostID(currentPost).getComments());
        }

        return "post";
    }

    @RequestMapping(value="/addComment", method = RequestMethod.POST)
    public String addComment(@ModelAttribute("commentForm") CommentForm commentForm, Model model, RedirectAttributes redirectAttributes) {
        myRedirect = redirectAttributes;

        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(commentForm.getuID()));
        redirectAttributes.addFlashAttribute("post", postRepo.getPostByUUID(commentForm.getParentID()).get());

        if(validateCommentForm(commentForm)){
            redirectAttributes = myRedirect;
            return "redirect:/post";
        }

        command.createComment(Optional.ofNullable(null), commentForm.getParentID(), commentForm.getComment());

        for(Post p: postRepo.getAllPosts()){
            if(p.getPostID().compareTo(commentForm.getParentID()) == 0) {
                p.setCommentList(request.getCommentForPostID(commentForm.getParentID()).getComments());

                redirectAttributes.addFlashAttribute("commentList", p.getCommentList());
            }
        }

        return "redirect:/post";
    }

    public boolean validateCommentForm(CommentForm commentForm){
        if(commentForm.getComment().isEmpty()) {
            myRedirect.addFlashAttribute("invalidComment", true);
            return true;
        } else return false;
    }
}
