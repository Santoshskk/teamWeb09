package app.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.jsonwebtoken.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;


public class JWToken {
    private static final String JWT_CALLNAME_CLAIM = "sub";
    private static final String JWT_ACCOUNTID_CLAIM = "id";
    private static final String JWT_ROLE_CLAIM = "role";

    @JsonProperty
    private String callName;
    @JsonProperty
    private long accountId;
    @JsonProperty
    private String role;

    public JWToken(String callName, Long accountId, String role) {
        this.callName = callName;
        this.accountId = accountId;
        this.role = role;
    }

    public String encode(String issuer, String passphrase, int expiration) {
        Key key = getKey(passphrase);

        return Jwts.builder()
                .claim(JWT_CALLNAME_CLAIM, this.callName)
                .claim(JWT_ACCOUNTID_CLAIM, this.accountId)
                .claim(JWT_ROLE_CLAIM, this.role)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    private static Key getKey(String passphrase) {
        byte[] hmacKey = passphrase.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(hmacKey, SignatureAlgorithm.HS512.getJcaName());
    }
}
