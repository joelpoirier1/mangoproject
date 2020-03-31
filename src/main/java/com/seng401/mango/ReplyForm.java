package com.seng401.mango;

import java.util.UUID;

//Receives reply information on a specific comment
public class ReplyForm {
    private String reply;
    private UUID parentID;
    private UUID userID;

    public ReplyForm() {
        super();
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public UUID getParentID() {
        return parentID;
    }

    public void setParentID(UUID parentID) {
        this.parentID = parentID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }
}
