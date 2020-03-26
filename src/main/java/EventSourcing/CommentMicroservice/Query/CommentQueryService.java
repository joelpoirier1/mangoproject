package EventSourcing.CommentMicroservice.Query;

import EventSourcing.BasicClasses.Comment;
import EventSourcing.BasicClasses.CommentList;

import java.util.List;
import java.util.UUID;

/*EventSourcing.Service Layer component to handle fetching a list of Events*/
public interface CommentQueryService {
    public List<Object> getCommentBytesFromEventStore(UUID commentID);
    public Comment getCommentCreatedEventClassFromEventStore(UUID commentID);
    public String getCommentMessageFromEventStore(UUID commentID);
    public CommentList getAllCommentsFromEventStore();
}
