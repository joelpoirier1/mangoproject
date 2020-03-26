package model;

import java.util.Date;
import java.util.UUID;

public class Post
{
    final int INITIAL_LIKES = 0;

    private Date date;
    private UUID postID;
    private UUID userID;
    private String content;
    private String displayName;
    private int likes;
    private PostCategory category;

    public Post(String content, PostCategory category, UUID userID)
    {
        likes = INITIAL_LIKES;
        date = new Date();
        postID = UUID.randomUUID();
        this.category = category;
        this.content = content;
        displayName = generateDisplayName();
        this.userID = userID;
    }

    public Post(UUID postID, Date date, String content, String displayName, UUID userID, int likes, PostCategory category)
    {
        this.date = date;
        this.postID = postID;
        this.content = content;
        this.displayName = displayName;
        this.userID = userID;
        this.category = category;
        this.likes = likes;
    }

    //Generates a random display name and returns it. For example "Red_Apple"
    public String generateDisplayName ()
    {
        DisplayNameGenerator aDisplayNameGenerator = new DisplayNameGenerator();
        return aDisplayNameGenerator.generateDisplayName();
    }

    //Increases likes by 1
    public void incrementLikes(){ likes++; }

    //Decreases likes by 1
    public void decrementLikes(){ likes--; }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UUID getPostID() {
        return postID;
    }

    public void setPostID(UUID postID) {
        this.postID = postID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public PostCategory getCategory() {
        return category;
    }

    public void setCategory(PostCategory category) {
        this.category = category;
    }

    public String toString()
    {
        return "Post " + postID.toString() + ": " + content;
    }
}

