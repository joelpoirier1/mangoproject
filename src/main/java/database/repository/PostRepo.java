package database.repository;

import database.InterfacePostDatabase;
import database.datatables.PostTable;
import model.Post;
import model.PostCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

public class PostRepo implements InterfacePostDatabase
{
    PostTable postTable;

    public PostRepo()
    {
        postTable = new PostTable();
    }

    @Override
    public Optional<Post> getPostByUUID(UUID postID) {
        return postTable.getPostByUUID(postID);
    }

    @Override
    public ArrayList<Post> getAllPosts()
    {
        ArrayList<Post> posts = postTable.getAllPosts();
        Collections.sort(posts, Collections.reverseOrder());
        return posts;
    }

    @Override
    public ArrayList<Post> getPostsByCategory(PostCategory category) {
        ArrayList<Post> posts = postTable.getPostsByCategory(category);
        Collections.sort(posts, Collections.reverseOrder());

        return posts;
    }

    @Override
    public ArrayList<Post> getPostsByUserID(UUID userID) {
        ArrayList<Post> posts = postTable.getPostsByUserID(userID);
        Collections.sort(posts, Collections.reverseOrder());
        return posts;
    }

    @Override
    public boolean addPost(Post post) {
        return postTable.addPost(post);
    }

    @Override
    public boolean updatePost(Post post) {
        return postTable.updatePost(post);
    }

    //Searches the
    public ArrayList<Post> getPostsByKeyword(String keyword)
    {
        ArrayList<Post> searchResults = new ArrayList<>();
        ArrayList<Post> allPosts = getAllPosts();
        for(Post p : allPosts)
        {
            if(p.getTitle().toLowerCase().contains(keyword.toLowerCase()) || p.getContent().toLowerCase().contains(keyword.toLowerCase()))
                searchResults.add(p);
        }
        Collections.sort(searchResults, Collections.reverseOrder());
        return searchResults;
    }
}
