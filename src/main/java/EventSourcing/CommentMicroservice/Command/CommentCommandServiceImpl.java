package EventSourcing.CommentMicroservice.Command;

import EventSourcing.BasicClasses.Comment;
import EventSourcing.BasicClasses.CreateCommentCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

//This class is used for storing comment events in the event store
@Service
public class CommentCommandServiceImpl implements CommentCommandService {

    private final CommandGateway commandGateway;

    //This constructor is called immediately when the application is run
    public CommentCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
        System.out.println("Inside Constructor CommentCommandServiceImpl(CommandGateway commandGateway");
    }

    //Store comment event into the event store. Requires the comment data members to have already been initialized.
    @Override
    public CompletableFuture<String> createComment(Comment comment) {
        System.out.println("Inside createComment(Comment comment)");
        CreateCommentCommand createCommentCommand = (new CreateCommentCommand(comment.getPostID(),
                comment.getCommentID(), comment.getParentCommentID(), comment.getMessage()));
        return commandGateway.send(createCommentCommand);
    }
}

