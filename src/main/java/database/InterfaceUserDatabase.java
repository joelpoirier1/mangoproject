package database;

import model.UserModel;

import java.util.Optional;

public interface InterfaceUserDatabase
{
    public Optional<UserModel> getUser(String username);
    public boolean addUser(UserModel user);
    public boolean removeUser(UserModel user);
    public Optional<UserModel> login(String username, String password);
    public boolean changeUsername(UserModel user, String newUsername);
    public boolean changePassword(UserModel user, String newPassword);

}
