package az.newsportal.msuser.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);

    Claims extractAllClaims(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimResolver);

    String generateToken(
            Map<String, Object> extraClaims, //For any extra info
            UserDetails userDetails
    );

    String generateToken(
            UserDetails userDetails
    );

    boolean isTokenExpired(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
