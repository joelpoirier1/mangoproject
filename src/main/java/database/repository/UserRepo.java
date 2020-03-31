package database.repository;

import database.InterfaceUserDatabase;
import database.datatables.UserTable;
import model.Post;
import model.User;

import java.util.Optional;
import java.util.UUID;

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
    public Optional<User> getUserByID(UUID userID) {
        return userTable.getUserByID(userID);
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
        PostRepo pt = new PostRepo();

        User user = db.getUser("admin").get();
        Post post = pt.getAllPosts().get(3);
        System.out.println(post.getPostID());


        //System.out.println(pt.addLikedPost(post.getPostID(), user.getId(), LikeStatus.like));
//        System.out.println(pt.getPostStatusByUser(post.getPostID(), user.getId()).get());
//        System.out.println(pt.removeLikedPost(post.getPostID(), user.getId()));
    }
}
