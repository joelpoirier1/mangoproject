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
    private String parentID;
    private String message;

    public CommentCreateDTO(){
        System.out.println("Inside Constructor CommentCreateDTO()");
    }

    //Getters and setters
    public String getParentID()  { return parentID; }
    public void getParentID(String parentID)  { this.parentID = parentID; }

    public String getMessage()  { return message; }
    public void setMessage(String message)  { this.message = message; }
}