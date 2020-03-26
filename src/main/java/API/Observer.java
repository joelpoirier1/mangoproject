package API;

import EventSourcing.BasicClasses.CommentCreatedEvent;

public interface Observer
{
    public void update(CommentCreatedEvent event);
}
