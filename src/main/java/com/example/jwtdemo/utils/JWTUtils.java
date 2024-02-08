package com.example.jwtdemo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.example.jwtdemo.Constants.secret;

public class JWTUtils {

    public static String create(String id, String name) {
        // 指定token过期时间为30min
        Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.MINUTE, 30);
        calendar.add(Calendar.SECOND, 20);

        return JWT.create()
                .withHeader(new HashMap<>())
                .withClaim("userId", id)
                .withClaim("username", name)
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(secret));
    }

    public static Map<String, String> resolver(String token) {

        // 创建解析对象，使用的算法和secret要与创建token时保持一致
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        // 解析指定的token
        DecodedJWT decodedJWT;
        try {
            decodedJWT = jwtVerifier.verify(token);
        } catch (TokenExpiredException e) {
            return null;
        }
        // 获取解析后的token中的payload信息
        Claim userId = decodedJWT.getClaim("userId");
        Claim userName = decodedJWT.getClaim("username");

        Map<String, String> result = new HashMap<>();
        result.put(userId.asString(), userName.asString());

        // 输出超时时间
        System.out.println(decodedJWT.getExpiresAt());
        return result;
    }
}
