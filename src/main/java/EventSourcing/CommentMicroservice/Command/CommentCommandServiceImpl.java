package EventSourcing.CommentMicroservice.Command;

import EventSourcing.BasicClasses.CommentCreateDTO;
import EventSourcing.BasicClasses.CreateCommentCommand;
import EventSourcing.Database.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/*Implementation of interface "CommentCommandService"*/
/*A state change within an application starts with a command*/
@Service
public class CommentCommandServiceImpl implements CommentCommandService {

    /*Convenience interface provided by Axon used to dispatch commands*/
    private final CommandGateway commandGateway;
    private CommentTable localComment;

    //This constructor is called immediately when the application is run
    public CommentCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
        localComment = new CommentTable();
        System.out.println("Inside Constructor CommentCommandServiceImpl(CommandGateway commandGateway");
    }

    /*send() method here will send a command and wait for a response*/
    @Override
    public CompletableFuture<String> createComment(CommentCreateDTO commentCreateDTO) {
        System.out.println("Inside createComment(CommentCreateDTO commentCreateDTO)");

        UUID localCommentID = UUID.randomUUID();
        String localCommentIDString = localCommentID.toString();

        boolean bool = false;
        while(!bool) {
            bool = localComment.addComment(localCommentIDString);
        }

        System.out.println("Comment UUID store success");
        return commandGateway.send(new CreateCommentCommand(localCommentID, commentCreateDTO.getParentID(), commentCreateDTO.getMessage()));
    }
}

