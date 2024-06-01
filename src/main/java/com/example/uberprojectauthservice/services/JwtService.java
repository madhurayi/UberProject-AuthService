package com.example.uberprojectauthservice.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements CommandLineRunner {

    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private String SECRET;

    private Key getSignKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    }
     /**
      * This method creates a brand new jwt token for us based on a payload
      * */
     private String CreateToken(Map<String,Object> payload,String user){
         Date now= new Date();
         Date expiryDate=new Date(now.getTime()+expiry*1000L);
         return Jwts.builder()
                 .claims(payload)
                 .issuedAt(new Date(System.currentTimeMillis()))
                 .expiration(expiryDate)
                 .subject(user)
                 .signWith(getSignKey())
                 .compact();
     }

     private Claims extractAllPayloads(String token){
         return Jwts.parser()
                 .setSigningKey(getSignKey())
                 .build()
                 .parseClaimsJws(token)
                 .getBody();
     }

     private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
         final Claims claims = extractAllPayloads(token);
         System.out.println(claims);
         return claimsResolver.apply(claims);
     }

     private String extractSubject(String token){
         return extractClaim(token, Claims::getSubject);
     }
     private String extractpayload(String token,String payloadKey){
         return (String) extractAllPayloads(token).get(payloadKey);
     }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

     private Boolean isTokenExpired(String token){
         return extractExpiration(token).before(new Date());
     }

     private Boolean validateToken(String token,String email){
         final String userEmailFetchedFromToken = extractEmail(token);
         return (userEmailFetchedFromToken.equals(email)) && !isTokenExpired(token);
     }
    @Override
    public void run(String... args) throws Exception {
            Map<String,Object> mp= new HashMap<>();
            mp.put("email","a@b.com");
            mp.put("phoneNumber","6666");
            String result=CreateToken(mp,"madhu");
            System.out.println(result);

        System.out.println("Extracted claim "+extractAllPayloads(result));

        System.out.println("Extracted payload "+extractpayload(result,"email"));
    }

}
