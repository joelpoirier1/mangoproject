package com.seng401.mango;

import api.CommentCommand;
import api.CommentRequest;
import database.repository.PostRepo;
import database.repository.UserRepo;
import model.Comment;
import model.LikeStatus;
import model.Post;
import model.PostCategory;
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
public class PostController {

    private CommentRequest request = new CommentRequest();
    private CommentCommand command = new CommentCommand();
    private UserRepo userRepo = new UserRepo();
    private PostRepo postRepo = new PostRepo();
    private UUID currentUser = null;
    private UUID currentPost = null;
    private RedirectAttributes myRedirect;

    //returns the post page
    @RequestMapping(value="/post", method = RequestMethod.GET)
    public String postPage(Model model){
        //sets the current user and post if the user just got on the post page
        if(model.containsAttribute("validate") && model.containsAttribute("postValidate")) {
            currentUser = (UUID) model.getAttribute("validate");
            currentPost = (UUID) model.getAttribute("postValidate");
        }

        //confirms if user has logged in
        if(!model.containsAttribute("validate") && currentUser == null)
            return "redirect:";

        //makes sure currentUser is not null
        if(!model.containsAttribute("currentUser")){
            model.addAttribute("currentUser", userRepo.getUserByID(currentUser));
        }

        //makes sure post is not null
        if (!model.containsAttribute("post")){
            model.addAttribute("post", postRepo.getPostByUUID(currentPost).get());
        }

        //checks if the microservice is enabled
        if(request.getAPIStatus()){
            model.addAttribute("disabled", true);

            //makes sure commentList is not null
            if(!model.containsAttribute("commentList")){
                model.addAttribute("commentList", null);
            }
        }else{
            model.addAttribute("disabled", false);

            //makes sure commentList is not null
            if(!model.containsAttribute("commentList")){
                postRepo.getPostByUUID(currentPost).get().setCommentList(request.getCommentForPostID(currentPost).getComments());
                model.addAttribute("commentList", filterComments(request.getCommentForPostID(currentPost).getComments()));
            }
        }

        //makes sure postRepo is not null
        if(!model.containsAttribute("postRepo")){
            model.addAttribute("postRepo", postRepo);
        }

        return "post";
    }

    //Allows user to like a post in the post page
    @RequestMapping(value="/likeThisPost", method = RequestMethod.POST)
    public String like(@ModelAttribute("inspectionForm") InspectionForm inspectionForm, RedirectAttributes redirectAttributes){
        Post currentPost = postRepo.getPostByUUID(inspectionForm.getPostID()).get();

        if(postRepo.getPostStatusByUser(currentPost.getPostID(), currentUser)){
            currentPost.decrementLikes();
            postRepo.removeLikedPost(currentPost.getPostID(), currentUser);
        } else{
            currentPost.incrementLikes();
            postRepo.addLikedPost(currentPost.getPostID(), currentUser, LikeStatus.like);
        }
        postRepo.updatePost(currentPost);

        redirectAttributes.addFlashAttribute("post", postRepo.getPostByUUID(inspectionForm.getPostID()).get());

        for(Post p: postRepo.getAllPosts()){
            if(p.getPostID().compareTo(inspectionForm.getPostID()) == 0 && !request.getAPIStatus()) {
                p.setCommentList(request.getCommentForPostID(inspectionForm.getPostID()).getComments());
                redirectAttributes.addFlashAttribute("commentList", p.getCommentList());
            }
        }

        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(inspectionForm.getUserID()));

        return "redirect:/post";
    }

    //Adds a comment to the post the user is currently viewing
    @RequestMapping(value="/addComment", method = RequestMethod.POST)
    public String addComment(@ModelAttribute("commentForm") CommentForm commentForm, RedirectAttributes redirectAttributes) {
        myRedirect = redirectAttributes;

        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(commentForm.getuID()));
        redirectAttributes.addFlashAttribute("post", postRepo.getPostByUUID(commentForm.getParentID()).get());

        //validates the comment
        if(validateCommentForm(commentForm)){
            redirectAttributes = myRedirect;
            return "redirect:/post";
        }

        //creates comment in database
        command.createComment(Optional.ofNullable(null), commentForm.getParentID(), commentForm.getComment());

        //regenerates the list of comments related to the post being viewed
        for(Post p: postRepo.getAllPosts()){
            if(p.getPostID().compareTo(commentForm.getParentID()) == 0 && !request.getAPIStatus()) {
                p.setCommentList(request.getCommentForPostID(commentForm.getParentID()).getComments());
                redirectAttributes.addFlashAttribute("commentList", p.getCommentList());
            }
        }

        return "redirect:/post";
    }

    //Allows user to go see the replies on the specified comment
    @RequestMapping(value="/replyComment", method = RequestMethod.POST)
    public String replyComment(@ModelAttribute("inspectCommentForm") InspectCommentForm inspectCommentForm, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("validate", currentUser);
        redirectAttributes.addFlashAttribute("commentValidate", inspectCommentForm.getPostID());

        redirectAttributes.addFlashAttribute("parent", request.getCommentByCommentID(inspectCommentForm.getPostID()));
        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(inspectCommentForm.getUserID()));
        redirectAttributes.addFlashAttribute("parentList", request.getCommentForParentID(inspectCommentForm.getPostID()).getComments());
        return "redirect:/comment";
    }

    //Returns user to the homepage
    @RequestMapping(value="/returnHome", method = RequestMethod.POST)
    public String returnHome(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(currentUser));
        redirectAttributes.addFlashAttribute("posts", postRepo.getAllPosts());
        redirectAttributes.addFlashAttribute("categories", PostCategory.values());

        return "redirect:/home";
    }

    //validates that the comment message is not empty
    public boolean validateCommentForm(CommentForm commentForm){
        if(commentForm.getComment().isEmpty()) {
            myRedirect.addFlashAttribute("invalidComment", true);
            return true;
        } else return false;
    }

    //Filters comments to show only the ones pertaining to the post
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
