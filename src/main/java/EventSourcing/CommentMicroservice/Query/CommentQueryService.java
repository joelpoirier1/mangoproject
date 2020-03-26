package EventSourcing.CommentMicroservice.Query;
import EventSourcing.BasicClasses.CommentCreatedEvent;
import EventSourcing.BasicClasses.CommentList;

import java.util.List;
import java.util.UUID;

/*Service Layer component to handle fetching a list of Events*/
public interface CommentQueryService {
    public List<Object> getCommentBytesFromEventStore(UUID commentID);
    public CommentCreatedEvent getCommentCreatedEventClassFromEventStore(UUID commentID);
    public CommentList getAllCommentsFromEventStore();
}
