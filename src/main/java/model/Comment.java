package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class Comment {

    private int likes;
    private Date date;
    private UUID commentID;
    private String message;
    private String displayName;
    //private UUID userID;
    private UUID postID;
    private Optional<UUID> parentID;

    ArrayList<Comment> replyComments;

    //Constructor
    public Comment() {

    }

    public Comment(Optional<UUID> parentID, UUID postID, String message) {
        date = new Date();
        this.commentID = UUID.randomUUID();
        this.parentID = parentID;
        this.postID = postID;
        this.message = message;
        displayName = generateDisplayName();
        ArrayList<Comment> replyComments = new ArrayList<>();
    }


    public Comment(UUID commentID, Optional<UUID> parentID, UUID postID, String message) {
        date = new Date();
        this.commentID = commentID;
        this.parentID = parentID;
        this.postID = postID;
        this.message = message;
        displayName = generateDisplayName();
        ArrayList<Comment> replyComments = new ArrayList<>();
    }

    public Comment(UUID commentID, Optional<UUID> parentID, UUID postID, String message, Date date, String displayName) {
        this.date = date;
        this.commentID = commentID;
        this.parentID = parentID;
        this.postID = postID;
        this.message = message;
        this.displayName = displayName;
        ArrayList<Comment> replyComments = new ArrayList<>();
    }

    //Returns the current date as a string
    public String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDate = formatter.format(date);
        return currentDate;
    }

    //Increases likes by 1
    public void incrementLikes(){ likes++; }

    //Decreases likes by 1
    public void decrementLikes(){ likes--; }

    //Generates a random display name and returns it. For example "Red_Apple"
    public String generateDisplayName (){
        DisplayNameGenerator aDisplayNameGenerator = new DisplayNameGenerator();
        return aDisplayNameGenerator.generateDisplayName();
    }

    public int getLikes()  { return likes; }
    public void setLikes(int likes)  { this.likes = likes; }

    public UUID getCommentID()  { return commentID; }
    public void setCommentID(UUID commentID)  { this.commentID = commentID; }

    public String getMessage()  { return message; }
    public void setMessage(String message)  { this.message = message; }

    public String getDisplayName()  { return displayName; }
    public void setDisplayName(String displayName)  { this.displayName= displayName; }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPostID(UUID postID) {
        this.postID = postID;
    }

    public Optional<UUID> getParentID() {
        return parentID;
    }

    public void setParentID(Optional<UUID> parentID) {
        this.parentID = parentID;
    }

    public UUID getPostID() {
        return postID;
    }

    public Date getDate() {
        return date;
    }

    public void setReplyComments(ArrayList<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public ArrayList<Comment> getReplyComments() {
        return replyComments;
    }

    @Override
    public String toString() {
        return "Comment{" +
                " commentID=" + commentID +
                ", postID=" + postID +
                ", message='" + message + '\'' +
                '}';
    }
}
