package EventSourcing.CommentMicroservice.Query;
import java.util.List;
import java.util.UUID;

/*Service Layer component to handle fetching a list of Events*/
public interface CommentQueryService {
    public List<Object> getCommentBytesFromEventStore(UUID commentID);
    public String getCommentMessageFromEventStore(UUID commentID);
}
