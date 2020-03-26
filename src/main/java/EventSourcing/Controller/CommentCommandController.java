package EventSourcing.Controller;

import EventSourcing.BasicClasses.Comment;
import EventSourcing.CommentMicroservice.Command.CommentCommandService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

//Used for calling functions responsible for storing comments in the event store
@RestController
@RequestMapping(value = "/comments")
@Api(value = "Command API value goes here", description = "Command Api Description goes here", tags = "Command Api tags goes here")
public class CommentCommandController {
    private final CommentCommandService commentCommandService;

    //This constructor is called immediately when the application is run
    public CommentCommandController(CommentCommandService commentCommandService) {
        System.out.println("Inside Constructor CommentCommandController(CommentCommandService commentCommandService)");
        this.commentCommandService = commentCommandService;
    }

    //Called when clicking 'Add comment'. Stores comment event into the data base.
    @PostMapping("PostMapping stuff goes here")
    public CompletableFuture<String> createComment(@RequestBody Comment comment) {
        System.out.println("Inside createComment(@RequestBody Comment comment");
        return commentCommandService.createComment(comment);
    }
}