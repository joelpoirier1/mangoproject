package EventSourcing.CommentMicroservice.Query;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//This class is used for retrieving comment events in the event store
@Service
public class CommentQueryServiceImpl implements CommentQueryService {
    private final EventStore eventStore;

    //This constructor is called immediately when the application is run
    public CommentQueryServiceImpl(EventStore eventStore) {
        System.out.println("Inside Constructor CommentQueryServiceImpl(EventStore eventStore)");
        this.eventStore = eventStore;
    }

    //Retrieves the comment event from the event store, in Byte format
    @Override
    public List<Object> getCommentBytesFromEventStore(UUID commentID) {
        System.out.println("Inside getCommentBytesFromEventStore(UUID commentID)");
        String commentStringID = commentID.toString();
        List<Object> aCommentEvent = eventStore.readEvents(commentStringID).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
        return aCommentEvent;
    }
}