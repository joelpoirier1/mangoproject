package EventSourcing.BasicClasses;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

public class CreateCommentCommand {
    @TargetAggregateIdentifier
    public final UUID commentID;
    public UUID parentID;
    public String message;
    public UUID postID;

    //Called when a comment is created
    public CreateCommentCommand(UUID commentID, UUID parentID, UUID postiD, String message) {
        this.commentID = commentID;
        this.parentID = parentID;
        this.message = message;
        this.postID = postiD;
        System.out.println("Inside Constructor CreateCommentCommand(UUID commentID, UUID parentID, String message)");
    }
}
