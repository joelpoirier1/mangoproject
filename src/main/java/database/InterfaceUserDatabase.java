package database;

import model.User;

import java.util.Optional;

public interface InterfaceUserDatabase
{
    /**
     * Retrieves a user from the database
     */
    public Optional<User> getUser(String username);

    /**
     * Adds a user to the database.
     * Returns true if user is successfully added, false if otherwise.
     */
    public boolean addUser(User user);

    /**
     * Removes a user from the database.
     * Returns true if user is successfully added, false if otherwise.
     */
    public boolean removeUser(User user);

    /**
     * Validates if a user is in the database when a username and password is entered.
     * Returns an Optional User
     */
    public Optional<User> validateUser(String username, String password);

    /**
     * Validates if a username is in the database.
     * Returns true if the username already exists and false if otherwise
     */
    public boolean validateUsernameExistence(String username);

    /**
     * Changes a user's username if the username is not currently taken.
     * Returns true if change was successful, returns false otherwise.
     */
    public boolean changeUsername(User user, String newUsername);

    /**
     * Changes a user's password.
     * Returns true if change was successful, returns false otherwise.
     */
    public boolean changePassword(User user, String newPassword);

}
