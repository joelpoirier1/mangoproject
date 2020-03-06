package database.datatables;

import database.InterfaceUserDatabase;
import database.SQLDatabase;
import model.User;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

/**
 * Database user table storing information on the application users
 */
public class UserTable extends SQLDatabase implements InterfaceUserDatabase
{
    public UserTable()
    {
        super();
        createUserTable();
    }

    /**
     * Creates a user table in the database if it does not exist upon startup
     */
    private void createUserTable()
    {
        try
        {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "MangoUser", null);

            if (rs.next() == false)
            {
                String query = "CREATE TABLE MangoUser " +
                        "(IDNum VARCHAR(255) NOT NULL," +
                        "Username VARCHAR(255) NOT NULL," +
                        "Password VARCHAR(255) NOT NULL," +
                        "PRIMARY KEY (IDNum)," +
                        "UNIQUE(Username) )";
                statement.executeUpdate(query);
                System.out.println("Created user table\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Drops the user table
     */
    private void dropUserTable() throws SQLException {
        statement.executeUpdate("DELETE FROM Appointment;");
    }

    /**
     * Retrieves a user from the database
     */
    @Override
    public Optional<User> getUser(String username)
    {
        User user = null;
        try
        {
            String query = "SELECT * FROM MangoUser WHERE Username = ?";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, username);
            resultSet = pState.executeQuery();

            if(resultSet.next())
            {
                String password = resultSet.getString("Password");
                UUID id = UUID.fromString(resultSet.getString("IDNum"));

                user = new User(username, password, id);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }

    /**
     * Adds a user to the database.
     * Returns true if user is successfully added, false if otherwise.
     */
    public boolean addUser(User user)
    {
        try
        {
            String query = "INSERT INTO MangoUser (IDNum, Username, Password)" +
                    "VALUES (?, ?, ?);";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, UUID.randomUUID().toString());
            pState.setString(2, user.getUsername());
            pState.setString(3, user.getPassword());
            pState.execute();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Removes a user from the database.
     * Returns true if user is successfully added, false if otherwise.
     */
    @Override
    public boolean removeUser(User user)
    {
        try
        {
            String query = "DELETE FROM MangoUser WHERE IDNum = ?";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, user.getId().toString());
            pState.execute();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Validates if a user is in the database.
     * Returns an Optional User
     */
    @Override
    public Optional<User> validateUser(String username, String password)
    {
        User user = null;
        try
        {
            String query = "SELECT * FROM MangoUser WHERE username = ? AND password = ?";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, username);
            pState.setString(2, password);
            resultSet = pState.executeQuery();

            if(resultSet.next())
            {
                user = new User(username, password);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean validateUsernameExistence(String username)
    {
        try {
            String query = "SELECT * FROM MangoUser WHERE username = ?";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, username);
            resultSet = pState.executeQuery();
            if(resultSet.next())
            {
                return true;
            }
            return false;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Changes a user's username if the username is not currently taken.
     * Returns true if change was successful, returns false otherwise.
     */
    @Override
    public boolean changeUsername(User user, String newUsername)
    {
        try
        {
            String query = "UPDATE MangoUser SET Username = ? WHERE IDNum = ?";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, newUsername);
            pState.setString(2, user.getId().toString());                          //TODO: Change to User id getter function
            pState.execute();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Changes a user's password.
     * Returns true if change was successful, returns false otherwise.
     */
    @Override
    public boolean changePassword(User user, String newPassword)
    {
        try
        {
            String query = "UPDATE MangoUser SET Password = ? WHERE IDNum = ?";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, newPassword);
            pState.setString(2, user.getId().toString());                          //TODO: Change to User id getter function
            pState.execute();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }


}
