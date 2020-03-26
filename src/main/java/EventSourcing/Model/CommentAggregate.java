package EventSourcing.Model;

import EventSourcing.BasicClasses.Comment;
import EventSourcing.BasicClasses.CreateCommentCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

//Similar to the Comment class, this class is specifically used for storing comment in the event store
@Aggregate
public class CommentAggregate {
    private UUID postID;
    @AggregateIdentifier
    private UUID commentID;
    private UUID parentCommentID;
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
        AggregateLifecycle.apply(new Comment(createCommentCommand.postID, createCommentCommand.commentID, createCommentCommand.parentCommentID, createCommentCommand.message));
    }

    //Called upon the creation of a new comment
    @EventSourcingHandler
    protected void on(Comment comment) {
        System.out.println("Inside on(CommentCreatedEvent commentCreatedEvent)");
        this.postID = comment.getPostID();
        this.commentID = comment.getCommentID();
        this.parentCommentID = comment.getParentCommentID();
        this.message = comment.getMessage();
        this.status = "CREATED";
    }
}

