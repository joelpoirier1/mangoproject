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
    public Optional<User> login(String username, String password)
    {
        return userTable.login(username, password);
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
    public static void main(String [] args)
    {
        UserRepo db = new UserRepo();
        User user = new User("alyssa", "lee");

        //System.out.println(db.addUser(user));

        if(db.login("alyssa", "lee").isPresent())
        {
            System.out.println("Login successful\n");
            System.out.println(db.login("alyssa", "lee").get().getUsername());
        }
        else
        {
            System.out.println("Login unsuccessful\n");
        }

        User alyssa = db.getUser("alyssa").get();
        System.out.println(alyssa);

        //db.changePassword(alyssa, "whooooo");
        //db.changeUsername(alyssa, "Whoooooo");

        db.removeUser(alyssa);





    }
}
