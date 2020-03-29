package com.seng401.mango;

import model.Post;
import model.PostCategory;

import java.util.UUID;

public class SearchForm {
    private PostCategory category;

    public SearchForm() {
        super();
    }

    public void setCategory(PostCategory category) {
        this.category = category;
    }

    public PostCategory getCategory() {
        return category;
    }
}
