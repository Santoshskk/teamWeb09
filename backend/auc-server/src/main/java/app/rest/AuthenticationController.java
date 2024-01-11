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

        String email = signInInfo.get("email").asText();
        String password = signInInfo.get("password").asText();

        // check whether we need and have the source IP-address of the user
        String ipAddress = JWToken.getIpAddress(request);
        if (ipAddress == null) {
            throw new NotAcceptableStatusException("Cannot identify your source IP-Address.");
        }

        List<User> users = userEntityRepository.findByQuery("Users_find_by_email", email);
        User user = users.size() > 0 ? users.get(0) : null;
        System.out.println(users);

        if (user == null || !user.verifyPassword(password)) {
            throw new NotAcceptableStatusException("Cannot authenticate user with email=" + email);
        }

        // Issue a token for the user, valid for some time
        JWToken jwToken = new JWToken(user.getName(), user.getId(), user.getRole());
        String tokenString = jwToken.encode(this.apiConfig.getIssuer(),
                this.apiConfig.getPassphrase(),
                this.apiConfig.getExpiration());

        return ResponseEntity.accepted()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenString)
                .body(user);
    }
}

