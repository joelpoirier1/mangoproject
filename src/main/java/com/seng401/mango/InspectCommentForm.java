package com.seng401.mango;

import java.util.UUID;

//Form used to obtain post and user information when going to a specific comment
public class InspectCommentForm {
    private UUID postID;
    private UUID userID;

    public InspectCommentForm() {
        super();
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
}
