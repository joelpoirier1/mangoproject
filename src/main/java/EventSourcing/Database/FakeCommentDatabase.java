package EventSourcing.Database;

import EventSourcing.Model.Comment;
import EventSourcing.Model.CommentList;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Repository("Fake")
public class FakeCommentDatabase implements CommentData {

    private static ArrayList<Comment> DB = new ArrayList<>();

    public FakeCommentDatabase(){
        DB.add( new Comment(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),"test comment"));
        DB.add( new Comment(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),"I love microservices"));
        DB.add(new Comment(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),"How do i do this"));
    }

    @Override
    public boolean addComment(Comment c) {
        DB.add(new Comment(c.getCommentID(),c.getPostID(),c.getUserID(), c.getMessage()));
        return true;
    }

    @Override
    public CommentList selectAllComments() {
        CommentList myCom = new CommentList();
        for (Comment c: DB
             ) {
            myCom.add(c);

        }
    return myCom;
    }

    @Override
    public Optional<Comment> selectCommentByID(UUID id) {
        return  DB.stream().filter(Comment->Comment.getCommentID().equals(id))
                .findFirst();
    }

    @Override
    public CommentList selectCommentsByUserID(UUID uid) {
        CommentList comments = new CommentList();
        DB.stream().filter(c -> c.getUserID().equals(uid)).forEach(
                c -> comments.add(c)
        );

        return comments;

    }


    @Override
    public boolean deleteComment(UUID id) {
        Optional<Comment> commentMaybe = selectCommentByID(id);
        if(commentMaybe.isPresent()){
            return true;
        }
        DB.remove(commentMaybe.get());
        return false;
    }

    @Override
    public boolean updateComment(UUID id, Comment newCom) {
        return selectCommentByID(id).map(com -> {
                    int indexOfUpdate = DB.indexOf(com);
                    if(indexOfUpdate>=0) {
                        DB.set(indexOfUpdate, new Comment(id, newCom.getUserID(), newCom.getPostID(), newCom.getMessage()));
                        return true;
                    }
                    return false;
                }
        ).orElse(false);
    }

}
