package com.zh.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static final String SECRET_KEY = "sliverdev";
    private static final long EXPIRATION_TIME =  24*60*60*1000; // 24 hours in milliseconds

    public static void main(String[] args) {
        String token = generateJwtToken("userId123");
        System.out.println("Generated JWT: " + token);
        System.out.println(parseJwtToken(token));
    }

    public static String generateJwtToken(String userId) {
        JwtBuilder builder = Jwts.builder()
                .setId(userId) //用户id jti
                .setIssuedAt(new Date()) //签发时间 iat
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //过期时间 exp
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY);

        // You can add custom claims to the token
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "user");
        builder.addClaims(claims);

        return builder.compact();
    }

    public static Claims parseJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
