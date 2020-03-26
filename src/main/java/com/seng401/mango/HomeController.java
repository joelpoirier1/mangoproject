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

    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public String logout() {
        return "index";
    }

    @RequestMapping(value="/addComment", method = RequestMethod.POST)
    public String addComment(@ModelAttribute("commentForm") CommentForm commentForm, Model model) {

        //Add nested comment - will uncomment once functions are implemented

//        Comment newComment = new Comment(commentForm.getComment(), commentForm.getParentID());
        model.addAttribute("posts", request.getAllComments().getComments());
        return "home";
    }

    @RequestMapping(value="/addPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("postForm") PostForm postForm, Model model) {

        //Add comment - will uncomment once functions are implemented

//        Comment newComment = new Comment(postForm.getComment());
        model.addAttribute("posts", request.getAllComments().getComments());

        return "home";
    }
}