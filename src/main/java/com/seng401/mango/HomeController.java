package com.seng401.mango;


import api.CommentRequest;
import model.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class HomeController {

    private CommentRequest request = new CommentRequest();
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addPost() {
        return "post";
    }

    @RequestMapping(value="/addComment", method = RequestMethod.POST)
    public String addComment(@ModelAttribute("homeForm") HomeForm homeForm, Model model) {

            //homeForm.getParentID();

//        Comment comment = new Comment(id);
        model.addAttribute("myPost", homeForm.getParentID().toString());

       // model.addAttribute("myPost", request.getCommentByCommentID(UUID.fromString(id)));
        return "comment";
    }
}