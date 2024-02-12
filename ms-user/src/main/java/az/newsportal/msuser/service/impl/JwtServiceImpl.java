package az.newsportal.msuser.service.impl;

import az.newsportal.msuser.service.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    private static final String SECRET_KEY = "4xBJ9w5cTn7CxHJnJM9vJ1Sjz3B2mlLCG6vLghwFJxQ=";

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); //subject = username
    }


    @Override
    public Claims extractAllClaims(String token) {  //claims are the part of jwt token that contain details about the user
        Claims claims = Jwts
                .parser()
                .verifyWith(getSignInKey()) //sign in key is used to create signature part of token and is used to verify the sender of jwt and ensure that message hasn't change along the way
                .build()
                .parseSignedClaims(token)
                .getPayload();

        log.info("Extracted Claims: " + claims);

        return claims;
    }


    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims); //??
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .claims()
                .empty()
                .add(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))  //valid for 24 hours 1000ms
                .and()
                .signWith(getSignInKey()) // JwtBuilder signWith(Key key) throws InvalidKeyException;
                .compact();
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }


    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    @Override
    public boolean isTokenExpired(String token) {
        Date exp = extractClaim(token, Claims::getExpiration);
        return exp.before(new Date());
    }
}
