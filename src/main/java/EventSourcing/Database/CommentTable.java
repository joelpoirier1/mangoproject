package EventSourcing.Database;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentTable extends SQLDatabase{

    public CommentTable(){
        super();
        System.out.println("Inside CommentTable Constructor");
        createCommentTable();
    }

    private void createCommentTable(){
        System.out.println("Inside createCommentTable()");
        try
        {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "CommentUUID", null);
            //System.out.println(rs.next());
            if (!rs.next())
            {
                String query = "CREATE TABLE CommentUUID " +
                        "(UniqueID VARCHAR(255) NOT NULL )";
                statement.executeUpdate(query);
                System.out.println("Created user table\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addComment(String localCommentID) {
        System.out.println("Inside commentTable storeCommentID()");
        try {
            String query = "INSERT INTO CommentUUID (UniqueID)" +
                    "VALUES (?);";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, localCommentID);
            pState.execute();
            return true;
        } catch (
                SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


