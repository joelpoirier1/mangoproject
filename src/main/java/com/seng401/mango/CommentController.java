package com.seng401.mango;

import api.CommentRequest;
import model.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

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

        model.addAttribute("posts", getComments());
        //model.addAttribute("posts", request.getAllComments().getComments());
        return "home";
    }

    @RequestMapping(value = "/comment/cancel", method = RequestMethod.POST)
    public String cancel(Model model) {
        model.addAttribute("posts", getComments());
        //model.addAttribute("posts", request.getAllComments().getComments());
        return "home";
    }


     /*
 This function is temporary
  */
    public ArrayList<Comment> getComments(){
        ArrayList<Comment> comments = new ArrayList<>();
        Comment comment = new Comment("hey man. Im doing so great");
        Comment comm = new Comment("bro Im awesome");
        comments.add(comment);
        comments.add(comm);
        return comments;
    }
}
