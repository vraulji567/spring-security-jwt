package com.example.springsecurityjwt.util;

import com.example.springsecurityjwt.model.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private static final String SECRET = "secret";

    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){

        return extractExpiration(token).before(new Date());
    }

    public TokenInfo generateToken(UserDetails user){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,user.getUsername());
    }

    private TokenInfo createToken(Map<String, Object> claims, String username) {
        TokenInfo token = new TokenInfo();
        Date issueDate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(System.currentTimeMillis() + (1000 * 60 * 10));
        token.setIssueDate(issueDate);
        token.setExpirationDate(expirationDate);
        token.setToken(Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(issueDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,SECRET).compact());
        return token;
    }

    public Boolean validateToken(String token, UserDetails user){
        final String username = extractUsername(token);
        return  username.equals(user.getUsername()) && !isTokenExpired(token);
    }

}
