package Database.datatables;

import Model.Comment;
import Database.SQLDatabase;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class NestedCommentTable extends SQLDatabase
{
    public NestedCommentTable()
    {
        super();
        createNestedCommentTable();
    }

    /**
     * Creates a comment table in the database if it does not exist upon startup
     */
    private void createNestedCommentTable()
    {
        try
        {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "NestedComments", null);

            if (rs.next() == false)
            {
                String query = "CREATE TABLE NestedComments " +
                        "(ParentCommentID VARCHAR(255) NOT NULL," +
                        "ReplyCommentID VARCHAR(255) NOT NULL," +
                        "PRIMARY KEY (ParentCommentID, ReplyCommentID)," +
                        "FOREIGN KEY(ParentCommentID) REFERENCES Comments(IDNum)" +
                        "ON DELETE CASCADE ON UPDATE CASCADE," +
                        "FOREIGN KEY(ReplyCommentID) REFERENCES Comments(IDNum)" +
                        "ON DELETE CASCADE ON UPDATE CASCADE)";
                statement.executeUpdate(query);
                System.out.println("Created nested comment table\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an arraylist of all comments directly replying to the parent comment
     */
    public ArrayList<Comment> selectReplyCommentsByParentID(UUID parentID)
    {
        ArrayList<Comment> comments = new ArrayList<>();
        try
        {
            String query = "SELECT Comment.* FROM NestedComments, Comment WHERE " +
                            "NestedComments.ParentCommentID = ? AND NestedComments.ReplyCommentID = Comments.IDNum";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, parentID.toString());

            resultSet = pState.executeQuery();
            while (resultSet.next())
            {
                UUID commentID = UUID.fromString(resultSet.getString("IDNum"));
                UUID userID = UUID.fromString(resultSet.getString("UserID"));
                UUID postID = UUID.fromString(resultSet.getString("PostID"));
                String message = resultSet.getString("CommentContent");
                Date date = resultSet.getDate("Date");
                String displayName = resultSet.getString("DisplayName");

                comments.add(new Comment(commentID, userID, postID, message, date, displayName));
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return comments;
    }

    public static void main(String args[])
    {
        NestedCommentTable nestedCommentTable = new NestedCommentTable();

        UUID comID = UUID.randomUUID();
        UUID replyID = UUID.randomUUID();
        UUID reply2ID = UUID.randomUUID();

        Comment com = new Comment(comID, UUID.randomUUID(), UUID.randomUUID(), "HEY YO");
        Comment reply = new Comment(replyID, UUID.randomUUID(), UUID.randomUUID(), "I am reply");
        Comment reply2 = new Comment(reply2ID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "I am reply too");


    }
}
