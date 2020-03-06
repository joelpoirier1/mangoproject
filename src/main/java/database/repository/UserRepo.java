package database.repository;
import database.InterfaceUserDatabase;
import database.datatables.*;
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

        //populate DB upon startup... comment out after db is created and delete when db is running on a server...
        /*db.addUser(new UserModel("alyssa", "lee"));
        db.addUser(new UserModel("admin", "admin"));
        db.addUser(new UserModel("username", "password"));*/

        //System.out.println(db.validateUsernameExistence("alyssa"));
    }
}
