package database.repository;

import database.InterfaceUserDatabase;
import database.datatables.PostTable;
import database.datatables.UserTable;
import model.Post;
import model.PostCategory;
import model.User;

import java.util.Optional;

public class UserRepo implements InterfaceUserDatabase
{
    private UserTable userTable;

    public UserRepo()
    {
        userTable = new UserTable();
    }

    @Override
    public Optional<User> getUser(String username)
    {
        return userTable.getUser(username);
    }

    @Override
    public boolean addUser(User user)
    {
        return userTable.addUser(user);
    }

    @Override
    public boolean removeUser(User user)
    {
        return userTable.removeUser(user);
    }

    @Override
    public Optional<User> validateUser(String username, String password)
    {
        return userTable.validateUser(username, password);
    }

    @Override
    public boolean validateUsernameExistence(String username) {
        return userTable.validateUsernameExistence(username);
    }

    @Override
    public boolean changeUsername(User user, String newUsername)
    {
        return userTable.changeUsername(user, newUsername);
    }

    @Override
    public boolean changePassword(User user, String newPassword)
    {
        return userTable.changePassword(user, newPassword);
    }



    //Tests
    // TODO: ...Delete main later
    public static void main(String args[])
    {
        UserRepo db = new UserRepo();
        PostTable pt = new PostTable();

        //populate DB upon startup... comment out after db is created and delete when db is running on a server...
        User user = new User("alyssa", "lee");
        User user1 = new User("admin", "admin");
        db.addUser(user);
        db.addUser(user1);
        Post post = new Post("YO", PostCategory.Miscellaneous, user.getId());
        System.out.println(pt.addPost(post));
        System.out.println(pt.addPost(new Post("Hello", PostCategory.Lifestyle, user1.getId())));
        System.out.println(pt.getPostByUUID(post.getPostID()));
        System.out.println(pt.getAllPosts());
        post.incrementLikes();
        System.out.println(pt.updatePost(post));
        System.out.println(pt.getPostsByUserID(user.getId()));
        System.out.println(pt.getPostsByCategory(PostCategory.Lifestyle));



        //db.addUser(new User("admin", "admin"));
        //db.addUser(new User("username", "password"));

        //System.out.println(db.validateUsernameExistence("alyssa"));
    }
}
