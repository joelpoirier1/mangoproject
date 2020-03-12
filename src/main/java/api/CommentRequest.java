package api;

import model.Comment;
import model.CommentList;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;

public class CommentRequest {

    private static RestTemplate restAPI = new RestTemplate();
    private static String commentServiceURL = "http://localhost:8081/comments/";


    public CommentRequest(){};

    public CommentList getCommentsForUserID(UUID id){
         return restAPI.getForObject(commentServiceURL + "user/" + id.toString(), CommentList.class);
    }

    public CommentList getAllComments(){
        return restAPI.getForObject(commentServiceURL, CommentList.class);
    }


    public static void main(String[] args) {
        CommentRequest r = new CommentRequest();
        System.out.println(r.getCommentsForUserID(UUID.fromString("90b9ce7f-93d7-4623-98c4-a961c20bac7c")));
        System.out.println(r.getAllComments());
    }



}
