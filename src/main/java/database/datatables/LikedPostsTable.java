package database.datatables;

import database.InterfaceLikedPostsTable;
import database.SQLDatabase;
import model.LikeStatus;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class LikedPostsTable extends SQLDatabase implements InterfaceLikedPostsTable
{

    public LikedPostsTable()
    {
        super();
        createLikedPostsTable();
    }

    private void createLikedPostsTable()
    {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "LikedPosts", null);

            if (rs.next() == false) {
                String query = "CREATE TABLE LikedPosts " +
                        "(PostID VARCHAR(255) NOT NULL," +
                        "UserID VARCHAR(255) NOT NULL," +
                        "Status VARCHAR(255) NOT NULL," +
                        "PRIMARY KEY (PostID, UserID),"+
                        "FOREIGN KEY(PostID) REFERENCES Post(IDNum)" +
                        "ON DELETE CASCADE ON UPDATE CASCADE," +
                        "FOREIGN KEY(UserID)  REFERENCES MangoUser(IDNum)" +
                        "ON DELETE CASCADE ON UPDATE CASCADE);";
                statement.executeUpdate(query);
                System.out.println("Created post table\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the like status of a post given the postID and userID
     */
    public boolean getPostStatusByUser(UUID postID, UUID userID)
    {
        try {
            String query = "SELECT * FROM LikedPosts WHERE PostID = ? AND UserID = ?";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, postID.toString());
            pState.setString(2, userID.toString());

            ResultSet resultSet = pState.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean addLikedPost(UUID postID, UUID userID, LikeStatus status)
    {
        try
        {
            String query = "INSERT INTO LikedPosts (PostID, UserID, Status)" +
                    "VALUES ((SELECT IDNum FROM Post WHERE IDNUm = ?), (SELECT IDNum FROM mangouser WHERE IDNum = ?), ?);";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, postID.toString());
            pState.setString(2, userID.toString());
            pState.setString(3, status.toString());

            pState.execute();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeLikedPost(UUID postID, UUID userID)
    {
        try {
            String query = "DELETE FROM LikedPosts WHERE postID = ? AND userID = ?";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, postID.toString());
            pState.setString(2, userID.toString());

            pState.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLikedPostStatus(UUID postID, UUID userID, LikeStatus status)
    {
        try {
            String query = "UPDATE LikedPosts SET Status = ? "+
                           "WHERE PostID = ? AND UserID = ?";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, status.toString());
            pState.setString(2, postID.toString());
            pState.setString(3, userID.toString());

            pState.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
