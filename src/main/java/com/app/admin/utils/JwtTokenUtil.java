package com.app.admin.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.algorithm}")
    private String algorithm;

    @Value("${jwt.expiration}")
    private Long expiration;

    //生成token
    public String generateToken(Map<String,?> claims)
    {
        String jwsString = Jwts.builder()
                .setClaims(claims).signWith(restoreKey())
                .setExpiration(generationExpiration())
                .compact();

        return jwsString;
    }

    //解析token
    private Jws<Claims> parserToken(String jwsString)
    {
        Jws<Claims> jws = null;
        try{
            jws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwsString);

            // we can safely trust the JWT
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return jws;
    }

    //验签
    public Boolean verifySign(String jwtString)
    {
        try{
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwtString);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    //生成secret
    private String createKey(){
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String keyString = Encoders.BASE64.encode(key.getEncoded());
        return keyString;
    }

    public SecretKey restoreKey()
    {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    //获取key
    public String getKey()
    {
        return secretKey;
    }


    /**
     * 生成时间
     * @return
     */
    private Date generationExpiration()
    {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


    /**
     * 获取jwt中字段
     * @param jwsString
     * @param fieldMap
     * @return
     */
    public String getBodyField(String jwsString, String fieldMap)
    {
        Jws<Claims> claims =  parserToken(jwsString);
        return claims.getBody().get(fieldMap).toString();
    }




}
