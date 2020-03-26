package com.seng401.mango;

import model.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class PostController {
    //checking for login credentials
    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String getPostForm(){
        return "post";
    }

    @RequestMapping(value="/post", method = RequestMethod.POST)
    public String login(@ModelAttribute("postForm") PostForm PostForm, Model model) {

        //add posts to the system

        model.addAttribute("posts", getComments());
            return "home";
    }

    @RequestMapping(value="/post/cancel", method = RequestMethod.POST)
    public String cancel(Model model){
        model.addAttribute("posts", getComments());
        return "home";
    }

    public ArrayList<Comment> getComments(){
        ArrayList<Comment> comments = new ArrayList<>();
        Comment comment = new Comment("hey man. Im doing so great");
        Comment comm = new Comment("bro Im awesome");
        comments.add(comment);
        comments.add(comm);
        return comments;
    }

}
