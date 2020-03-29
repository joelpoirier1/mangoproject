package com.seng401.mango;

import java.util.UUID;

public class PostForm {
    private String title;
    private String message;
    private UUID userID;

    public PostForm() {
        super();
    }

    public UUID getUserID() {
        return userID;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
