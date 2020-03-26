package EventSourcing.BasicClasses;

/*Data Transfer Class for associated objects*/
/*The entirety of Aggregate "Comment" is our resource,
* however, different commands require different payloads,
* giving the necessity of having DTO objects*/

/*This class is primarily used for creating a new comment
* Declared standard getters and setters for users to be able
* to serialize and deserialize objects*/

public class CommentCreateDTO { //DTO = Data Transfer Object
    /*The data members here will be displayed in the fields of the GUI*/
//    private UUID parentID; /*TODO take care of this alter*/
    private String message;

    //Getters and setters
//    public UUID getParentID()  { return parentID; }
//    public void getParentID(UUID parentID)  { this.parentID = parentID; }

    public String getMessage()  { return message; }
    public void setMessage(String message)  { this.message = message; }
}