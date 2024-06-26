package kz.alabs.vetclinic.core.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    @Value("${spring.security.jwt-secret}")
    private String jwtSecret;

    @Value("${spring.security.jwt-expiration-ms}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        final UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Jwt sign is invalid ", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Jwt token is invalid", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Jwt is expired", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Jwt is unsupported", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Jwt is empty", e.getMessage());
        }
        return false;
    }

}
