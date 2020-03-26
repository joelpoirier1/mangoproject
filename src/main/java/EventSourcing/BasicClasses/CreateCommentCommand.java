package EventSourcing.BasicClasses;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

//Similar to the Comment class, but this class is specifically used for event storing and retrieving
public class CreateCommentCommand {
    public UUID postID;
    @TargetAggregateIdentifier
    public final UUID commentID;
    public UUID parentCommentID;
    public String message;

    //Constructor
    public CreateCommentCommand(UUID postID, UUID commentID, UUID parentCommentID, String message) {
        this.postID = postID;
        this.commentID = commentID;
        this.parentCommentID = parentCommentID;
        this.message = message;
        System.out.println("Inside Constructor CreateCommentCommand(UUID commentID, UUID parentID, String message)");
    }
}
