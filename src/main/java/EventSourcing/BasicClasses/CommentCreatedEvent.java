package EventSourcing.BasicClasses;

import java.util.UUID;

public class CommentCreatedEvent{
    public final UUID commentID;
    public String parentID;
    public String message;

    public CommentCreatedEvent(UUID commentID, String parentID, String message) {
        this.commentID = commentID;
        this.parentID = parentID;
        this.message = message;
        System.out.println("Inside constructor CommentCreatedEvent(UUID commentID, String parentID, String message)");
    }

    public String getMessage(){
        return this.message;
    }

}
