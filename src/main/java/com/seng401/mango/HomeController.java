package com.seng401.mango;


import api.CommentRequest;
import model.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private CommentRequest request = new CommentRequest();

    @RequestMapping(value="/addComment", method = RequestMethod.POST)
    public String addComment(@ModelAttribute("homeForm") CommentForm commentForm, Model model) {

            //homeForm.getParentID();

        Comment newComment = new Comment(commentForm.getComment());
//        Comment comment = new Comment(id);
        model.addAttribute("myPost", commentForm.getParentID().toString());

       // model.addAttribute("myPost", request.getCommentByCommentID(UUID.fromString(id)));
        return "home";
    }

    @RequestMapping(value="/addPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("postForm") PostForm postForm, Model model) {

        //Create comment

        Comment newComment = new Comment(postForm.getComment());
        model.addAttribute("posts", request.getAllComments().getComments());

        return "home";
    }
}