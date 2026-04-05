package spring_group1.com.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class JwtUtils {
    private final String SECRET = "FnHfyiCC5Tc4/FNDPldMiZRf/tfCUNw8320+2cfHM5U=";

    private final long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(10);

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsTResolver) {
        return claimsTResolver.apply(extractAllClaims(token));
    }

//    public String extractEmail(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }

    public String extractEmail(String token) {
        String email = extractClaim(token, Claims::getSubject);
        System.out.println("ExtractEmail from token: " + email); // Debug
        return email;
    }

    public Date extractExpiration(String token) {
        Date date = extractClaim(token, Claims::getExpiration);
        return date;
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
