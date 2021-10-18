package exp.server.app_server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    private static String SECRET = "token!Q@W#E$R";

    // 生成token
    public static String getToken(Map<String, String> map) {
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7); //默认7天过期

        builder.withExpiresAt(instance.getTime());//指定令牌的过期时间
        String token = builder.sign(Algorithm.HMAC256(SECRET));//签名
        return token;
    }

    // 验证token
    public static boolean verify(String token) {
        //如果有任何验证异常，此处都会抛出异常
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
            //e.printStackTrace();
        } catch (IllegalArgumentException e) {
            //return false;
            //e.printStackTrace();
        }

        return false;
    }

//    /**
//     * 获取token中的 payload
//     */
//    public static DecodedJWT getToken(String token) {
//        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
//        return decodedJWT;
//    }
}
