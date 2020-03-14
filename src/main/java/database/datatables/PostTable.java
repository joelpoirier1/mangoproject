package database.datatables;

import database.SQLDatabase;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostTable extends SQLDatabase
{
    public PostTable()
    {
        super();
        createPostTable();
    }

    private void createPostTable()
    {
        try
        {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "Post", null);

            if (rs.next() == false)
            {
                String query = "CREATE TABLE Post " +
                        "(IDNum VARCHAR(255) NOT NULL," +
                        "PostContent TEXT NOT NULL," +
                        "GeneratedName VARCHAR(255) NOT NULL," +
                        "PRIMARY KEY (IDNum)," +
                        "UserID VARCHAR(255) REFERENCES MangoUser(IDNum)," +
                        "ON DELETE SET NULL UPDATE CASCADE";
                statement.executeUpdate(query);
                System.out.println("Created post table\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO: add functions

}