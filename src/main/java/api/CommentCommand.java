package api;


import model.Comment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class CommentCommand {

    private static RestTemplate restAPI = new RestTemplate();
    private static String commentServiceURL = "http://localhost:55555/comments/";

    public CommentCommand(){};

    public void createComment(UUID userID , UUID parentID, String commentMessage){
        Comment c = new Comment(userID, parentID , commentMessage);
        restAPI.postForObject(commentServiceURL, c , String.class);
    }


    public static void main(String[] args) {
        CommentCommand c = new CommentCommand();
        c.createComment(UUID.randomUUID() , UUID.randomUUID(), "if this work we almost home");
    }

}
