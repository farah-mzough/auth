package university.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import university.auth.entity.User;

import java.util.Date;

@Service
public class JwtService {

    private final String SECRET = "MY_SECRET_KEY_123456_MY_SECRET_KEY_123456";

    public String generateToken(User user) {

        byte[] keyBytes = SECRET.getBytes();
        var key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
