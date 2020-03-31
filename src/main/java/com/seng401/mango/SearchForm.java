package com.seng401.mango;

import model.PostCategory;

//Receives category being searched
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
