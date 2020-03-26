package API;

import EventSourcing.BasicClasses.CommentCreatedEvent;

public class SubscriberEventStore implements Observer {
    @Override
    public void update(CommentCreatedEvent event) {

    }
}