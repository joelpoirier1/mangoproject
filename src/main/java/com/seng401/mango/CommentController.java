package com.seng401.mango;

import model.NestedComment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class CommentController {
    //to get comment form
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public String getCommentForm() {
        return "comment";
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("commentForm") CommentForm commentForm) {
        //NestedComment newComment = new NestedComment(commentForm.getComment(), );
        return "home";
    }

    @RequestMapping(value = "/comment/cancel", method = RequestMethod.POST)
    public String cancel() {
        return "home";
    }
}
