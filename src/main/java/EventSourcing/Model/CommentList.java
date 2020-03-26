package EventSourcing.Model;

import java.util.ArrayList;

public class CommentList {

    public ArrayList<Comment> comments;

    public CommentList(){
        comments = new ArrayList<>();
    }

    public void add(Comment c){
        comments.add(c);
    }

}
