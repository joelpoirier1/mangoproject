package API;

import EventSourcing.BasicClasses.CreateCommentCommand;

public class SubscriberEventStore implements Observer
{


    @Override
    public void update(CreateCommentCommand event)
    {

    }
}