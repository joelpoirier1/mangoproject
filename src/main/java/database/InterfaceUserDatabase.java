package database;

import model.UserModel;

import java.util.Optional;

public interface InterfaceUserDatabase
{
    /**
     * Retrieves a user from the database
     */
    public Optional<UserModel> getUser(String username);

    /**
     * Adds a user to the database.
     * Returns true if user is successfully added, false if otherwise.
     */
    public boolean addUser(UserModel user);

    /**
     * Removes a user from the database.
     * Returns true if user is successfully added, false if otherwise.
     */
    public boolean removeUser(UserModel user);

    /**
     * Validates if a user is in the database when a username and password is entered.
     * Returns an Optional UserModel
     */
    public Optional<UserModel> validateUser(String username, String password);

    /**
     * Changes a user's username if the username is not currently taken.
     * Returns true if change was successful, returns false otherwise.
     */
    public boolean changeUsername(UserModel user, String newUsername);

    /**
     * Changes a user's password.
     * Returns true if change was successful, returns false otherwise.
     */
    public boolean changePassword(UserModel user, String newPassword);

}
