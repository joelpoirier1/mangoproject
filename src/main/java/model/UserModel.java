package model;

import java.util.UUID;

public class UserModel {
    private UUID ID;
    private String username; 
    private String password; 
    
    //Constructor. Generates a random ID
    public UserModel(String username,String password) {
    	this.username = username;
    	this.password = password;
    	ID = UUID.randomUUID();
    }

    //Constructor. Assigns the ID
    public UserModel(String username,String password, UUID ID)
    {
        this.username = username;
        this.password = password;
        this.ID = ID;
    }

    //Returns the username
    public String getUsername()  { return username; }

    //Sets a new username
    public void setUsername(String username)  {
        this.username = username; 
    } 
     
    //Returns the password
    public String getPassword()  { 
        return password; 
    } 
     
    //Sets a new password
    public void setPassword(String password)  {
        this.password = password; 
    }

    //Returns the ID
    public UUID getId()
    {
        return ID;
    }
}