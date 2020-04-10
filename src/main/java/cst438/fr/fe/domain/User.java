package cst438.fr.fe.domain;

import javax.persistence.*;


@Entity
@Table(name="user")
public class User {
	
	@Id
    @GeneratedValue     //let database create the ID
    private long id;
	
    private String firstName;
    private String lastName;
    private String userId;
    private String password;
    

    public User(long id, String firstName, String lastName, String userId, String password) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.password = password;
    }
    
    

    public User() {
        // TODO Auto-generated constructor stub
    }

    public long getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }


    public void setId(long id) {
        this.id = id;
    }




    public String getFirstName() {
        return firstName;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public String getLastName() {
        return lastName;
    }



    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getUserId() {
        return userId;
    }



    public void setUserId(String userId) {
        this.userId = userId;
    }



    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userId=" + userId + "]";
    }
	


}