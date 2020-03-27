package api;

import model.Comment;
import model.CommentList;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;

public class CommentRequest {

    private static RestTemplate restAPI = new RestTemplate();
    private static String commentServiceURL = "http://localhost:55555/comments/";


    public CommentRequest(){};

    public CommentList getCommentsForUserID(UUID id){
         return restAPI.getForObject(commentServiceURL + "user/" + id.toString(), CommentList.class);
    }

    public CommentList getAllComments(){
        return restAPI.getForObject(commentServiceURL, CommentList.class);
    }

    public Comment getCommentByCommentID(UUID id){
        return restAPI.getForObject(commentServiceURL + "commentID/" + id.toString(), Comment.class);

    }

    public CommentList getCommentForParentID(UUID parent){
        return restAPI.getForObject(commentServiceURL + "parentID/" + parent.toString(), CommentList.class);
    }



    public static void main(String[] args) {
        CommentRequest r = new CommentRequest();
//        System.out.println(r.getCommentsForUserID(UUID.fromString("90b9ce7f-93d7-4623-98c4-a961c20bac7c")));
//        System.out.println(r.getAllComments());
//        System.out.println(r.getCommentByCommentID(UUID.fromString("cf72de3a-1645-4999-8f3c-01f854c3271b")));
        System.out.println(r.getCommentForParentID(UUID.fromString("07edb597-7f5a-4ac2-a629-23e9fb754daf")));
    }



}
