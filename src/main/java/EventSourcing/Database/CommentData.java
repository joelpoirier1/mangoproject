package EventSourcing.Database;

import EventSourcing.Model.Comment;
import EventSourcing.Model.CommentList;

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
