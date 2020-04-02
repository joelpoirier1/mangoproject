package api;

import model.Comment;
import model.CommentList;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class CommentRequest {

    private static RestTemplate restAPI = new RestTemplate();
    private static String commentServiceURL = "http://70.65.114.188:55555/comments/";


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

    public CommentList getCommentForPostID(UUID post){
            return restAPI.getForObject(commentServiceURL + "postID/" + post.toString(), CommentList.class);
    }

    public boolean getAPIStatus(){

        //timeout
        int timeoutTime = 1000;

        try {
            long startTime = System.nanoTime();

            URL url = new URL(commentServiceURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(false);
            connection.setConnectTimeout(timeoutTime);
            connection.setReadTimeout(timeoutTime);
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
            System.out.println(code);

            if(code >= 300) {
                return true;
            }

            long elapsedTime = System.nanoTime() - startTime;
            return false;
        }catch(Exception e) {
            System.out.println("TIMEOUT");
            return true;
        }
    }


    public static void main(String[] args) {
        CommentRequest r = new CommentRequest();
//        System.out.println(r.getCommentsForUserID(UUID.fromString("90b9ce7f-93d7-4623-98c4-a961c20bac7c")));
//        System.out.println(r.getAllComments());
//        System.out.println(r.getCommentByCommentID(UUID.fromString("cf72de3a-1645-4999-8f3c-01f854c3271b")));
        System.out.println(r.getCommentForPostID(UUID.fromString("94d5e585-fdc6-4f84-816d-0d87c9767fa3")));
    }



}
