package com.anushka.config;



import java.util.Date;


import javax.crypto.SecretKey;



 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.Claims;
 import io.jsonwebtoken.security.Keys;
 //import io.jsonwebtoken.SignatureAlgorithm;

 import org.springframework.security.core.Authentication;


public class JwtProvider {

    static SecretKey key=Keys.hmacShaKeyFor(JwtConstant.SECRETE_KEY.getBytes());


    public static String generateToken(Authentication auth){

        


        @SuppressWarnings("deprecation")
        String jwt= Jwts.builder().setIssuedAt(new Date())
             .setExpiration(new Date(new Date().getTime()+86400000))
             .claim("email",auth.getName())
             .signWith(key)
             .compact();
        
        return jwt;     
            
    }

    public static String getEmailFromToken(String jwt){
        
        jwt=jwt.substring( 7);

        Claims claims= Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        
        return String.valueOf(claims.get("email"));

    }

}
