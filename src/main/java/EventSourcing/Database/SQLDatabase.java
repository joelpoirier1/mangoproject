package EventSourcing.Database;

import java.sql.*;
import java.util.Properties;

public abstract class SQLDatabase implements DatabaseCredentials
{
    protected static Connection connection;
    protected static Statement statement;

    public SQLDatabase()
    {
        connect();
    }

    private void connect()
    {
        if (connection != null) return;

        try
        {
            Class.forName(JDBC_Driver);
            Properties properties = new Properties();
            properties.setProperty("user", DB_Username);
            properties.setProperty("password", DB_Password);
            properties.setProperty("useTimezone", "true");
            properties.setProperty("useLegacyDatetimeCode", "false");
            //Azure: US/Pacific
            //Local: America/Boise
            properties.setProperty("serverTimezone", "America/Boise");
            connection = DriverManager.getConnection(Database_URL, properties);
            statement = connection.createStatement();
            System.out.println("Connected to database");

        } catch (SQLException | ClassNotFoundException e)
        {
            System.out.println("Could not connect to database");
            e.printStackTrace();
        }
    }
}

