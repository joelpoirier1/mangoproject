package EventSourcing.CommentMicroservice.Query;

import EventSourcing.BasicClasses.Comment;
import EventSourcing.BasicClasses.CommentCreatedEvent;
import EventSourcing.BasicClasses.CommentList;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service //This has been added back in
/*Not Mandatory for Event Sourcing using Axon - used for testing purpose*/
public class CommentQueryServiceImpl implements CommentQueryService {
    /*Axon EventStore to provide a method to read events for a particular
    * Aggregate Id*/
    private final EventStore eventStore;

    //This constructor is called immediately when the application is run
    public CommentQueryServiceImpl(EventStore eventStore) {
        System.out.println("Inside Constructor CommentQueryServiceImpl(EventStore eventStore)");
        this.eventStore = eventStore;
    }

    //Returns the serialized version of the comment. Meaning, turns a sequence of bytes that holds comment info
    @Override
    public List<Object> getCommentBytesFromEventStore(UUID commentID) {
        System.out.println("Inside getCommentBytesFromEventStore(UUID commentID)");
        String commentStringID = commentID.toString();
        List<Object> aCommentEvent = eventStore.readEvents(commentStringID).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
        //List<Object> aCommentEvent = eventStore.openStream(null).asStream().collect(Collectors.toList());
        return aCommentEvent;
    }

    //Returns the message of a comment.
    public Comment getCommentCreatedEventClassFromEventStore(UUID commentID) {
        System.out.println("Inside getCommentMessageFromEventStore(UUID commentID)");
        Comment comment = (Comment) getCommentBytesFromEventStore(commentID).get(0);
        return comment;
    }

    public CommentList getAllCommentsFromEventStore(){
        CommentList commentList = new CommentList();
//        for (int i = 0; i < numberOfCommentsInCommentIDDataBase ; i++){
//            UUID commentID = UUID.fromString(getCommentIDFromDataBase(i));
//            Comment comment = getCommentCreatedEventClassFromEventStore(commentID);
//            Comment comment = new Comment(comment.getCommentID(), comment.getParentD(), comment.getMessage());
//        }
        return commentList;
    }

    //Returns the message of a comment.
    public String getCommentMessageFromEventStore(UUID commentID) {
        System.out.println("Inside getCommentMessageFromEventStore(UUID commentID)");
        CommentCreatedEvent commentCreatedEvent = (CommentCreatedEvent) getCommentBytesFromEventStore(commentID).get(0);
        String CommentMessage = commentCreatedEvent.getMessage();
        return CommentMessage;
    }
}