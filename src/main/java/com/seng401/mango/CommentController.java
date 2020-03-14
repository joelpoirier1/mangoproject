package com.seng401.mango;

import api.CommentRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommentController {

    private CommentRequest request = new CommentRequest();
    //to get comment form
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public String getCommentForm() {
        return "comment";
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public String addComment(@ModelAttribute("commentForm") CommentForm commentForm, Model model) {
        //NestedComment newComment = new NestedComment(commentForm.getComment(), );

        model.addAttribute("posts", request.getAllComments().getComments());
        return "home";
    }

    @RequestMapping(value = "/comment/cancel", method = RequestMethod.POST)
    public String cancel(Model model) {
        model.addAttribute("posts", request.getAllComments().getComments());
        return "home";
    }
}
