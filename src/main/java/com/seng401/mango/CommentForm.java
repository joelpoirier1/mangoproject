package com.seng401.mango;

import java.util.UUID;

public class CommentForm {
        private String comment;
        private UUID parentID;

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
}
