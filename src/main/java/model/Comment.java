package model;
import java.util.Date;
import java.util.UUID;
import java.text.SimpleDateFormat;

public class Comment {
    final int MESSAGE_FONT_SIZE = 12;
    final int DISPLAY_NAME_FONT_SIZE = 15;
    final int INITIAL_LIKES = 0;

    private int messageFontSize;
    private int displayNameFontSize;
    private int likes;
    private String date;
    private UUID commentID;
    private UUID postID;
    private UUID userID;
    private String comment;
    private String displayName;


    public Comment(){};

    //Constructor
    public Comment(String message) {
        messageFontSize = MESSAGE_FONT_SIZE;
        displayNameFontSize = DISPLAY_NAME_FONT_SIZE;
        likes = INITIAL_LIKES;
        date = getCurrentDate();
        commentID = UUID.randomUUID();
        comment = message;
        displayName = generateDisplayName();
    }

    //Returns the current date as a string
    public String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
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

    //Getters and setters
    public int getMessageFontSize()  { return messageFontSize; }
    public void setMessageFontSize(int messageFontSize)  { this.messageFontSize = messageFontSize; }

    public int getDisplayNameFontSize()  { return displayNameFontSize; }
    public void setDisplayNameFontSize(int displayNameFontSize)  { this.displayNameFontSize = displayNameFontSize; }

    public int getLikes()  { return likes; }
    public void setLikes(int likes)  { this.likes = likes; }

    public UUID getCommentID()  { return commentID; }
    public void setCommentID(UUID commentID)  { this.commentID = commentID; }

    public String getMessage()  { return comment; }
    public void setMessage(String message)  { this.comment = message; }

    public String getDisplayName()  { return displayName; }
    public void setDisplayName(String displayName)  { this.displayName= displayName; }

    public UUID getUserID() { return userID;  }
    public void setUserID(UUID userID) {        this.userID = userID;     }

    public UUID getPostID() {        return postID;     }
    public void setPostID(UUID postID) {        this.postID = postID;    }

    public String getComment() {         return comment;     }
    public void setComment(String comment) {        this.comment = comment;    }

    @Override
    public String toString() {
        return "Comment{" +
                "likes=" + likes +
                ", date='" + date + '\'' +
                ", commentID=" + commentID +
                ", postID=" + postID +
                ", userID=" + userID +
                ", comment='" + comment + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
