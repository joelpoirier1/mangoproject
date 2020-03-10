package api;

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

    public static void main(String[] args) {
        CommentRequest r = new CommentRequest();
        System.out.println(r.getCommentsForUserID(UUID.fromString("1f32a4d2-61f4-4341-b9a8-69085f0bb5d0")));
    }



}
