package com.auth.jwt.security;

import com.auth.jwt.entity.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("{jwt.secret}")
    private String secret;

    @PostConstruct
    protected void init(){
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(AuthUser authUser){
        Map<String,Object> claims;
        claims = Jwts.claims().setSubject(authUser.getUserName());
        claims.put("id",authUser.getId());
        var dateNow = new Date();
        var expiration = new Date(dateNow.getTime() + 3600000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(dateNow)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public Boolean validate(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String getUserNameFromToken(String token){
        try{
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        }catch (Exception e){
            return "BAD TOKEN";
        }
    }
}
