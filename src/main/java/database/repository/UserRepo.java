package database.repository;
import database.InterfaceUserDatabase;
import database.datatables.*;
import model.UserModel;

import java.util.Optional;

public class UserRepo implements InterfaceUserDatabase
{
    private UserTable userTable;

    public UserRepo()
    {
        userTable = new UserTable();
    }

    @Override
    public Optional<UserModel> getUser(String username)
    {
        return userTable.getUser(username);
    }

    @Override
    public boolean addUser(UserModel user)
    {
        return userTable.addUser(user);
    }

    @Override
    public boolean removeUser(UserModel user)
    {
        return userTable.removeUser(user);
    }

    @Override
    public Optional<UserModel> validateUser(String username, String password)
    {
        return userTable.validateUser(username, password);
    }

    @Override
    public boolean changeUsername(UserModel user, String newUsername)
    {
        return userTable.changeUsername(user, newUsername);
    }

    @Override
    public boolean changePassword(UserModel user, String newPassword)
    {
        return userTable.changePassword(user, newPassword);
    }



    //Tests
    // TODO: ...Delete main later
    public static void main(String args[])
    {
        UserRepo db = new UserRepo();
        UserModel user = new UserModel("alyssa", "lee");

        //System.out.println(db.addUser(user));

        if(db.validateUser("alyssa", "lee").isPresent())
        {
            System.out.println("Login successful\n");
            System.out.println(db.validateUser("alyssa", "lee").get().getUsername());
        }
        else
        {
            System.out.println("Login unsuccessful\n");
        }

        UserModel alyssa = db.getUser("alyssa").get();
        System.out.println(alyssa);

        //db.changePassword(alyssa, "whooooo");
        //db.changeUsername(alyssa, "Whoooooo");

        db.removeUser(alyssa);





    }
}
