package Database;

import Model.Comment;
import Model.CommentList;

import java.util.Optional;
import java.util.UUID;


public interface CommentData {

    boolean addComment(Comment c);
    CommentList selectAllComments();
    Optional<Comment> selectCommentByID(UUID id);
    CommentList selectCommentsByUserID(UUID uid);
    boolean deleteComment(UUID id);
    boolean updateComment(UUID id, Comment newCom);


}
