package EventSourcing.API;

import EventSourcing.Model.Comment;
import EventSourcing.Model.*;
import EventSourcing.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/comments")
@RestController
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService comServ){
        commentService=comServ;
    }

    @PostMapping
    public void addComment(@RequestBody Comment c){
        commentService.addComment(c);
    }

    @GetMapping
    public CommentList getAllComments(){
        System.out.println("INSIDE GETALLCOMMENTS!!!");
        return commentService.getAllComments();
    }

    @GetMapping(path = "commentID/{id}")
    public Comment selectCommentByID(@PathVariable("id")UUID id ){
        return commentService.selectCommentByID(id)
                .orElse(null);
        //return null if not found
    }

    @GetMapping(path = {"user/{uid}"})
    public CommentList selectCommentsByUserID(@PathVariable("uid")UUID uid ){
        return commentService.selectCommentsByUserID(uid);
    }

    @DeleteMapping(path = "{id}")
    public void deleteComment(@PathVariable("id") UUID id){
            commentService.deleteComment(id);
    }

    @PutMapping(path = "{id}")
    public void updateComment(@PathVariable("id") UUID id, @RequestBody Comment commentToUpdate){
            commentService.updateComment(id,commentToUpdate);
    }

}
