package EventSourcing.CommentMicroservice.Query;

import java.util.List;
import java.util.UUID;

public interface CommentQueryService {
    public List<Object> getCommentBytesFromEventStore(UUID commentID);
}
