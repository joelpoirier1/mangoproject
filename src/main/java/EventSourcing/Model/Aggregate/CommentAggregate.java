package EventSourcing.Model.Aggregate;
import EventSourcing.BasicClasses.CreateCommentCommand;
import EventSourcing.BasicClasses.CommentCreatedEvent;
import EventSourcing.BasicClasses.Status;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class CommentAggregate {
    @AggregateIdentifier
    private UUID commentID;
    private String parentID;
    private String message;
    private String status;

    //Empty constructor. Never called, but needed for some reason
    public CommentAggregate() {
        System.out.println("Inside Constructor CommentAggregate()");
    }

    //Constructor
    @CommandHandler
    public CommentAggregate(CreateCommentCommand createCommentCommand){
        System.out.println("Inside Constructor CommentAggregate(CreateCommentCommand createCommentCommand)");
        AggregateLifecycle.apply(new CommentCreatedEvent(createCommentCommand.commentID, createCommentCommand.parentID, createCommentCommand.message));
    }

    //Called upon the creation of a new comment
    @EventSourcingHandler
    protected void on(CommentCreatedEvent commentCreatedEvent) {
        System.out.println("Inside on(CommentCreatedEvent commentCreatedEvent)");
        this.commentID = commentCreatedEvent.commentID;
        this.parentID = commentCreatedEvent.parentID;
        this.message = commentCreatedEvent.message;
        this.status = String.valueOf(Status.CREATED);
    }
}

