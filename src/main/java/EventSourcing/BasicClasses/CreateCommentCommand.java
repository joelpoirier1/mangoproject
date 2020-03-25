package EventSourcing.BasicClasses;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

public class CreateCommentCommand {
    @TargetAggregateIdentifier
    public final UUID commentID;
    public String parentID;
    public String message;

    //Called when a comment is created
    public CreateCommentCommand(UUID commentID, String parentID, String message) {
        this.commentID = commentID;
        this.parentID = parentID;
        this.message = message;
        System.out.println("Inside Constructor CreateCommentCommand(UUID commentID, String parentID, String message)");
    }
}
