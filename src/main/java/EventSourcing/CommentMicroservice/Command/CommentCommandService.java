package EventSourcing.CommentMicroservice.Command;

import EventSourcing.BasicClasses.Comment;

import java.util.concurrent.CompletableFuture;

public interface CommentCommandService {
      CompletableFuture<String> createComment(Comment comment);
}
