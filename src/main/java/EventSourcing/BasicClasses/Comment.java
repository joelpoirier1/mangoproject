package EventSourcing.BasicClasses;

import java.util.UUID;

//This class is used for storing the details of a comment. Used by controller and GUI
public class Comment {
    private UUID postID;
    private UUID commentID;
    private UUID parentCommentID;
    private String message;

    //Empty constructor
    public Comment(){};

    //Constructor
    public Comment( UUID postID, UUID commentID, UUID parentCommentID, String message) {
        this.postID = postID;
        this.commentID = commentID;
        this.parentCommentID = parentCommentID;
        this.message = message;
    }

    //Getters and setters
    public UUID getPostID()  { return postID; }
    public void setPostID(UUID postID)  { this.postID = postID; }

    public UUID getCommentID()  { return commentID; }
    public void setCommentID(UUID commentID)  { this.commentID = commentID; }

    public UUID getParentCommentID()  { return parentCommentID; }
    public void setParentCommentID(UUID parentCommentID)  { this.parentCommentID = parentCommentID; }

    public String getMessage()  { return message; }
    public void setMessage(String message)  { this.message = message; }

    //Returns the postID, commentID, parentCommentID, and the message in string format
    @Override
    public String toString() {
        return "Comment{" +
                ", postID=" + postID +
                ", commentID=" + commentID +
                ", parentCommentID=" + parentCommentID +
                ", message='" + message + '\'' +
                '}';
    }
}
