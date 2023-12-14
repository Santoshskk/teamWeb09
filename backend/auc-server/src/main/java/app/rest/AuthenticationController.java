package app.rest;

import app.authorization.JWToken;
import app.models.User;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.NotAcceptableStatusException;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private final JWToken jwtTokenGenerator;

    @Autowired
    public AuthenticationController(JWToken jwtTokenGenerator) {
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<User> authenticateAccount(@RequestBody ObjectNode signInInfo) {
        String email = signInInfo.get("email").asText();
        String password = signInInfo.get("password").asText();

        User user = new User(email, null, "registered user"); // create a temporary user for verification

        if (!user.verifyPassword(password)) {
            throw new NotAcceptableStatusException("Login failed for email: " + email);
        }

        String issuer = "HvA";
        String passphrase = "your_secret_passphrase";
        int expiration = 1200;


        // Generate JWT token
        String token = jwtTokenGenerator.encode(issuer, passphrase, expiration);

        return ResponseEntity.accepted()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(user);
    }
}

