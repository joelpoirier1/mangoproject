package API;

import EventSourcing.BasicClasses.CreateCommentCommand;

public interface Observer
{
    public void updateCreationEvent(CreateCommentCommand event);
}
