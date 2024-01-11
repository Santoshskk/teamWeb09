package app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
@NamedQueries({
        @NamedQuery(name="Users_find_by_email",
                query = "select a from User a where a.email = ?1")
})
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    @JsonProperty
    private String name;
    @JsonProperty
    private String email;
    @JsonProperty
    private String hashedPassword;
    @JsonProperty
    private String role;

    public User( String email, String hashedPassword, String role) {
        this.id = randomId();
        this.name = extractName(email);
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
            return null; // or handle as needed based on your requirements
        }
        String[] usernameSplit = userEmail.split("@");
        return usernameSplit[0];
    }

    public boolean verifyPassword(String enterdPassword){
        if(Objects.equals(this.name, enterdPassword)){
            return true;
        }
        return false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
