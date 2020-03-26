package EventSourcing.BasicClasses;

import java.util.UUID;

public class CommentCreatedEvent{
    public final UUID commentID;
    public UUID parentID;
    public String message;

    public CommentCreatedEvent(UUID commentID, UUID parentID, String message) {
        this.commentID = commentID;
        this.parentID = parentID;
        this.message = message;
        System.out.println("Inside constructor CommentCreatedEvent(UUID commentID, UUID parentID, String message)");
    }

    //Getters and setters
    public UUID getCommentID() {
        return commentID;
    }

    public UUID getParentD() {
        return parentID;
    }

    public String getMessage(){
        return this.message;
    }
}
