package EventSourcing.CommentMicroservice.Command;

import EventSourcing.BasicClasses.CommentCreateDTO;
import EventSourcing.BasicClasses.CreateCommentCommand;
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

    //This constructor is called immediately when the application is run
    public CommentCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
        System.out.println("Inside Constructor CommentCommandServiceImpl(CommandGateway commandGateway");
    }

    /*send() method here will send a command and wait for a response*/
    @Override
    public CompletableFuture<String> createComment(CommentCreateDTO commentCreateDTO) {
        System.out.println("Inside createComment(CommentCreateDTO commentCreateDTO)");
        /*TODO create local UUID variable*/
        return commandGateway.send(new CreateCommentCommand(UUID.randomUUID(), UUID.fromString("82525212-6f16-11ea-bc55-0242ac130003"), commentCreateDTO.getMessage()));
    }
    /*TODO: Take care of passing the parentID in the above code*/
}

