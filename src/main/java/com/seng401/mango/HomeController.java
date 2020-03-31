package com.seng401.mango;

import api.CommentRequest;
import database.repository.PostRepo;
import database.repository.UserRepo;
import model.LikeStatus;
import model.Post;
import model.PostCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class HomeController {

    private CommentRequest request = new CommentRequest();
    private UserRepo userRepo = new UserRepo();
    private PostRepo postRepo = new PostRepo();
    private UUID currentUser;
    private RedirectAttributes myRedirect;
    private PostCategory search;
    private String searchKeyword;

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String homepage(Model model){

        if(model.containsAttribute("validate"))
            currentUser = (UUID) model.getAttribute("validate");

        //confirms user is logged in
        if(!model.containsAttribute("validate") && currentUser == null)
            return "redirect:";

        //updates page to show the posts in the category that was searched
        if(search != null && !model.containsAttribute("posts")){
            model.addAttribute("posts", postRepo.getPostsByCategory(search));
        }

        //updates page to show the posts with the keyword that was searched
        if(searchKeyword != null && !model.containsAttribute("posts")){
            model.addAttribute("posts", postRepo.getPostsByKeyword(searchKeyword));
        }

        //makes sure currentUser is not null
        if(!model.containsAttribute("currentUser")){
            model.addAttribute("currentUser", userRepo.getUserByID(currentUser));
        }

        //makes sure posts is not null
        if (!model.containsAttribute("posts")){
            model.addAttribute("posts", postRepo.getAllPosts());
        }

        //makes sure categories is not null
        if(!model.containsAttribute("categories")){
            model.addAttribute("categories", PostCategory.values());
        }

        //makes sure postRepo is not null
        if(!model.containsAttribute("postRepo")){
            model.addAttribute("postRepo", postRepo);
        }

        return "home";
    }

    //Allows user to display the information of a specific post
    @RequestMapping(value="/inspectPost", method = RequestMethod.POST)
    public String inspect(@ModelAttribute("inspectionForm") InspectionForm inspectionForm, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("post", postRepo.getPostByUUID(inspectionForm.getPostID()).get());

        for(Post p: postRepo.getAllPosts()){
            if(p.getPostID().compareTo(inspectionForm.getPostID()) == 0) {
                p.setCommentList(request.getCommentForPostID(inspectionForm.getPostID()).getComments());
                redirectAttributes.addFlashAttribute("commentList", p.getCommentList());
            }
        }

        redirectAttributes.addFlashAttribute("validate", currentUser);
        redirectAttributes.addFlashAttribute("postValidate", inspectionForm.getPostID());
        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(inspectionForm.getUserID()));
        return "redirect:/post";
    }

    //Allows user to like/unlike a post
    @RequestMapping(value="/likePost", method = RequestMethod.POST)
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

        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(currentUser));
        redirectAttributes.addFlashAttribute("posts", postRepo.getAllPosts());
        redirectAttributes.addFlashAttribute("categories", PostCategory.values());

        return "redirect:/home";
    }

    //Allows user to add a post
    @RequestMapping(value="/addPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("postForm") PostForm postForm, RedirectAttributes redirectAttributes) {
        myRedirect = redirectAttributes;
        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(postForm.getUserID()));

        if(validatePostForm(postForm)) {
            redirectAttributes = myRedirect;
            return "redirect:/home";
        }

        Post post = new Post(postForm.getMessage(), postForm.getTitle(), postForm.getCategory(), postForm.getUserID());
        postRepo.addPost(post);

        redirectAttributes.addFlashAttribute("categories", PostCategory.values());
        redirectAttributes.addFlashAttribute("posts", postRepo.getAllPosts());
        search = null;
        searchKeyword = null;

        return "redirect:/home";
    }

    //Allows user to search by a category
    @RequestMapping(value="/search", method = RequestMethod.POST)
    public String search(@ModelAttribute("searchForm") SearchForm searchForm, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(currentUser));
        redirectAttributes.addFlashAttribute("categories", PostCategory.values());
        redirectAttributes.addFlashAttribute("posts", postRepo.getPostsByCategory(searchForm.getCategory()));
        search = searchForm.getCategory();

        return "redirect:/home";
    }

    //Allows user to search by a keyword
    @RequestMapping(value="/searchKeyword", method = RequestMethod.POST)
    public String searchKeyword(@ModelAttribute("searchKeywordForm") SearchKeywordForm searchKeywordForm, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(currentUser));
        redirectAttributes.addFlashAttribute("categories", PostCategory.values());

        if(searchKeywordForm.getKeyword().isEmpty()){
            redirectAttributes.addFlashAttribute("invalidSearch", true);
            return "redirect:/home";
        }

        redirectAttributes.addFlashAttribute("posts", postRepo.getPostsByKeyword(searchKeywordForm.getKeyword()));
        searchKeyword = searchKeywordForm.getKeyword();

        return "redirect:/home";
    }

    //Allows user to cancel their search
    @RequestMapping(value="/cancelSearch", method = RequestMethod.POST)
    public String cancelSearch( RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(currentUser));
        redirectAttributes.addFlashAttribute("categories", PostCategory.values());
        redirectAttributes.addFlashAttribute("posts", postRepo.getAllPosts());
        search = null;
        searchKeyword = null;

        return "redirect:/home";
    }

    //Allows user to go to their diary page
    @RequestMapping(value="/goToDiary", method = RequestMethod.POST)
    public String diary(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("posts", postRepo.getPostsByUserID(currentUser));
        redirectAttributes.addFlashAttribute("currentUser", userRepo.getUserByID(currentUser));
        redirectAttributes.addFlashAttribute("validate", currentUser);
        return "redirect:/diary";
    }

    //Validates the post form to ensure no values are null
    public boolean validatePostForm(PostForm postForm){
        if(postForm.getTitle().isEmpty() && postForm.getMessage().isEmpty()) {
            myRedirect.addFlashAttribute("invalidTitle", true);
            myRedirect.addFlashAttribute("invalidMessage", true);
            return true;
        } else if(postForm.getTitle().isEmpty()){
            myRedirect.addFlashAttribute("invalidTitle", true);
            return true;
        } else if(postForm.getMessage().isEmpty()) {
            myRedirect.addFlashAttribute("invalidMessage", true);
            return true;
        } return false;
    }
}