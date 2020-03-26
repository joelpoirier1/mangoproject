package database;

import model.Post;
import model.PostCategory;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface InterfacePostDatabase {

    /**
     * Retrieves a post from the database from UUID
     */
    public Optional<Post> getPostByUUID(UUID postID);

    /**
     * Retrieves all post from the database
     */
    public ArrayList<Post> getAllPosts();

    /**
     * Retrieves post from the database under a given category
     */
    public ArrayList<Post> getPostsByCategory(PostCategory category);

    /**
     * Retrieves all post from the database
     */
    public ArrayList<Post> getPostsByUserID(UUID userID);

    /**
     * Adds a post to the database.
     * Returns true if user is successfully added, false if otherwise.
     */
    public boolean addPost(Post post);

    /**
     * Updates the posts in the database
     */
    public boolean updatePost(Post post);

}
