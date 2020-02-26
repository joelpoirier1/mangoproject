package model;
/*
 * To do:
 * 1) Connect to database
 * 		-Have a method to store a new username and password
 * 		-Have a method to modify an existing username and password
 * 		-Have a method to retrieve username and password
 */

import java.util.UUID;

public class UserModel {
    private UUID id;
    private String username; 
    private String password; 
    
    //Constructor
    public UserModel(String username,String password) {
    	this.username = username;
    	this.password = password;
    	id = UUID.randomUUID();
    }

    public UserModel(String username,String password, UUID id)
    {
        this.username = username;
        this.password = password;
        this.id = id;
    }
    
    //Returns the username
    public String getUsername()  { 
        return username; 
    } 
     
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

    public UUID getId()
    {
        return id;
    }

    public String toString()
    {
        return "User " + username;
    }
}

//Test: Updated on Feb 26, 3:36 pm
