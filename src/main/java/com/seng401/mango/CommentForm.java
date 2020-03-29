package com.seng401.mango;

import java.util.UUID;

//Form that receives information to create a new comment on a post
public class CommentForm {
    private String comment;
    private UUID parentID;
    private UUID uID;

    public CommentForm() {
            super();
        }

    public String getComment() {
            return comment;
        }

    public void setComment(String comment) {
            this.comment = comment;
        }

    public UUID getParentID() {
        return parentID;
    }

    public void setParentID(UUID parentID) {
        this.parentID = parentID;
    }

    public UUID getuID() {
        return uID;
    }

    public void setuID(UUID uID) {
        this.uID = uID;
    }
}
