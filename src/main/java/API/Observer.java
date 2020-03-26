package API;

import EventSourcing.BasicClasses.CreateCommentCommand;

public interface Observer
{
    public void update(CreateCommentCommand event);
}
