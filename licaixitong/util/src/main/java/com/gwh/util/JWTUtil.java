package com.gwh.util;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gwh.entity.Administrator;
import com.gwh.entity.User;
import com.auth0.jwt.JWT;

public class JWTUtil {
//生成token的方法 将username存入token；密钥则是用户的密码
//withAudience()存入需要保存在token的信息，这里我把用户username存入token中
    public static String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getUsername())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
    public static String getTokenAdministrator(Administrator administrator) {
        String token="";
        token= JWT.create().withAudience(administrator.getUsername())
                .sign(Algorithm.HMAC256(administrator.getPassword()));
        return token;
    }
    // 验证 token 通过token和数据库中的password验证
    public static boolean checkToken(User user,String  token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("token验证错误");
        }
        return true;
    }



}
