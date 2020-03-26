package EventSourcing.Controller;

import EventSourcing.CommentMicroservice.Query.CommentQueryService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/*Controller class to expose one end-point to list the events on an Aggregate*/
@RestController
@RequestMapping(value = "/comments")/*See note below*/
@Api(value = "Query Api value goes here", description = "Query Api description goes here", tags = "Query Api tags goes here")
public class CommentQueryController {
    private final CommentQueryService commentQueryService;

    //This constructor is called immediately when the application is run
    public CommentQueryController(CommentQueryService commentQueryService) {
        this.commentQueryService = commentQueryService;
        System.out.println("Inside Constructor CommentQueryController(CommentQueryService commentQueryService)");
    }

//    Returns the serialized version of the comment. Meaning, turns a sequence of bytes that holds comment info
//    @GetMapping("/{commentID}/events")/*See note below*/
//     public List<Object> getCommentBytesFromEventStore(@PathVariable UUID commentID){
//         System.out.println("Inside listEventsForComment(@PathVariable(value = \"commentID\") String commentID)");
//         return commentQueryService.getCommentBytesFromEventStore(commentID);
//    }

    //Returns the message of a comment. Can send it to Joel's GUI to display the comments
    @GetMapping("/{commentID}/events")/*See note below*/
    public String getCommentMessageFromEventStore(@PathVariable UUID commentID){
        System.out.println("Inside listEventsForComment(@PathVariable(value = \"commentID\") UUID commentID)");
        return commentQueryService.getCommentMessageFromEventStore(commentID);
    }

    /*Note: @RequestMapping(value = "/comments") specifies the first path. @GetMapping("/{commentID}/events") specifies the second path.
    For example: http://localhost:8080/comments/e148667a-c258-43f5-b504-27ba086ff6c3/events
    Notice that after 3rd '/' is 'comments'. This is the first path
    Notice that after 4rd '/' is [commentID]/events. This is the second path
     */
}
