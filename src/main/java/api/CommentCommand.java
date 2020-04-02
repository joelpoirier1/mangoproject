package api;


import model.Comment;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

public class CommentCommand {

    private static RestTemplate restAPI = new RestTemplate();
    private static String commentServiceURL = "http://70.65.114.188:55555/comments/";

    public CommentCommand(){};

    public void createComment(Optional<UUID> parentID, UUID postID, String commentMessage){
        Comment c = new Comment(parentID, postID, commentMessage);
        restAPI.postForObject(commentServiceURL, c , String.class);
    }


    public static void main(String[] args) {
        CommentCommand c = new CommentCommand();
        c.createComment(Optional.ofNullable(null) , UUID.randomUUID(), "if this work we almostdfxfdx home");
    }

}
