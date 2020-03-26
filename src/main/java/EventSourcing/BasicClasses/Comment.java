//package EventSourcing.BasicClasses;
//
//import java.util.UUID;
//
//public class Comment {
//    private UUID commentID;
//    private UUID parentID;
//    private UUID userID;
//    private String message;
//
//    //Empty constructor
//    public Comment(){};
//
//    //Constructor
//    public Comment(UUID commentID, UUID parentID, String message) {
//        this.commentID = commentID;
//        this.parentID = parentID;
//        this.userID = null;  /*TODO: Change this later*/
//        this.message = message;
//
//    }
//
//    //Getters and setters
//    public UUID getCommentID()  { return commentID; }
//    public void setCommentID(UUID commentID)  { this.commentID = commentID; }
//
//    public UUID getParentID()  { return parentID; }
//    public void getParentID(UUID parentID)  { this.parentID = parentID; }
//
//    public String getMessage()  { return message; }
//    public void setMessage(String message)  { this.message = message; }
//
//    public UUID getUserID() { return userID;  }
//    public void setUserID(UUID userID) { this.userID = userID; }
//
//    @Override
//    public String toString() {
//        return "Comment{" +
//                ", commentID=" + commentID +
//                ", postID=" + parentID +
//                ", userID=" + userID +
//                ", comment='" + message + '\'' +
//                '}';
//    }
//}
