package app.rest;

import app.APIConfig;
import app.authorization.JWToken;
import app.models.User;
import app.repositories.EntityRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.List;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    APIConfig apiConfig;


    @Autowired
    private EntityRepository<User> userEntityRepository;
    @PostMapping(path = "/login")
    public ResponseEntity<User> authenticateAccount(
            @RequestBody ObjectNode signInInfo,
            HttpServletRequest request) {

        String email = signInInfo.has("email") ? signInInfo.get("email").asText() : null;
        String password = signInInfo.has("password") ? signInInfo.get("password").asText() : null;

        // Check if email or password is null
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email or password is missing in the request body");
        }

        User user = new User(email,password, "Registerd user");

        if (user == null) {
            throw new NotAcceptableStatusException("User with email " + email + " not found");
        }

        String[] usernameSplit = email.split("@");

        if (usernameSplit[0].equals(password)) {
            // Issue a token for the user, valid for some time
            JWToken jwToken = new JWToken(user.getName(), user.getId(), user.getRole());
            String tokenString = jwToken.encode(this.apiConfig.getIssuer(),
                    this.apiConfig.getPassphrase(),
                    this.apiConfig.getExpiration());
            System.out.println(tokenString);

            return ResponseEntity.accepted()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenString)
                    .body(user);
        } else
            throw new NotAcceptableStatusException("Incorrect password for user with email " + email + password);




    }
}

