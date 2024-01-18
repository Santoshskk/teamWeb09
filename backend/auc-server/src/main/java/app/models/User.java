package app.models;

import app.repositories.Identifiable;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Objects;
//@NamedQueries({
//        @NamedQuery(name="Find_by_email",
//                query = "select a from User a where a.email = ?1")
//})
@Entity
@Table(name = "webuser")
public class User implements Identifiable {

    @Id
    private long userId;

    @JsonProperty
    private String name;
    @JsonProperty
    private String email;
    @JsonProperty
    private String hashedPassword;
    @JsonProperty
    private String role;

    public User( String email, String hashedPassword, String role) {
        this.userId = randomId();
        this.name= extractName(email);
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }

    public User() {

    }

    private static long randomId(){
        long randomId = (long) (Math.random()*100);
        return randomId;
    }

    private String extractName(String userEmail) {
        if (userEmail == null) {
            return null;
        }
        String[] usernameSplit = userEmail.split("@");
        return usernameSplit[0];
    }

    public boolean verifyPassword(String enteredPassword){
        if(Objects.equals(this.name, enteredPassword)){
            System.out.println(this.name + " goed");
            return true;
        }
        return false;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long id) {
        this.userId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public long getIdentifiableId() {
        return 0;
    }

    @Override
    public void setIdentifiableId(long id) {

    }
}
