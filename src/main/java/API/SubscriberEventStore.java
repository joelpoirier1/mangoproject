package API;

import Database.RealCommentDatabase;
import EventSourcing.BasicClasses.CreateCommentCommand;
import Model.Comment;

import java.util.UUID;

public class SubscriberEventStore implements Observer
{
    private final RealCommentDatabase commentDatabase;

    public SubscriberEventStore()
    {
        commentDatabase = new RealCommentDatabase();
    }

    @Override
    public void updateCreationEvent(CreateCommentCommand event)
    {
        System.out.println("WE ARE IN OBSERVER");
        System.out.println(event.message);
        commentDatabase.addComment(new Comment(event.commentID, event.parentID, UUID.randomUUID(), event.message));
    }
}