package EventSourcing.CommentMicroservice.Command;

import EventSourcing.BasicClasses.CommentCreateDTO;

import java.util.concurrent.CompletableFuture;

/*EventSourcing.Service Layer component to handle the Commands with respect to the comments*/
public interface CommentCommandService {
      CompletableFuture<String> createComment(CommentCreateDTO commentCreateDTO);
}
