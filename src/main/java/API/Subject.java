package API;

import EventSourcing.BasicClasses.CreateCommentCommand;

public interface Subject
{
    public void notifyObserver(CreateCommentCommand newComment);
}
