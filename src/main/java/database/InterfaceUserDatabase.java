package database;

import model.User;

import java.util.Optional;

public interface InterfaceUserDatabase
{
    public Optional<User> getUser(String username);
    public boolean addUser(User user);
    public boolean removeUser(User user);
    public Optional<User> login(String username, String password);
    public boolean changeUsername(User user, String newUsername);
    public boolean changePassword(User user, String newPassword);

}
