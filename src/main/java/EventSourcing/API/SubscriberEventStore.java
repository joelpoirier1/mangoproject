package EventSourcing.API;

import EventSourcing.Database.RealCommentDatabase;
import EventSourcing.BasicClasses.CreateCommentCommand;
import EventSourcing.Model.Comment;
import EventSourcing.Service.CommentService;

import java.util.UUID;

public class SubscriberEventStore implements Observer
{
    private final CommentService commentDatabase;

    public SubscriberEventStore()
    {
        commentDatabase = new CommentService(new RealCommentDatabase());
    }

    @Override
    public void updateCreationEvent(CreateCommentCommand event)
    {
        System.out.println("WE ARE IN OBSERVER");
        System.out.println(event.message);
        commentDatabase.addComment(new Comment(event.commentID, event.parentID, UUID.randomUUID(), event.message));
    }
}