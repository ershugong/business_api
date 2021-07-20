package cn.ag.channel.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    /**
     * 自定义秘钥
     * */
    private static final String sign = "jwtSecret";
    /**
     * jwtToken的默认有效时间 单位分钟
     * */
    private static final int expireTime = 30 * 60 ;

    /**
     * 生成token的过期时间
     */
    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expireTime * 1000);
    }

    /**
     * 生成jwt token
     * @param map  要存放负载信息
     * */
    public static String createJwtToken(Map<String,Object> map){
        return  Jwts.builder()
                .setClaims(map) //放入payLoad部分的信息
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,sign)
                .compact();

    }

    /**
     * 从令牌中获取数据,就是payLoad部分存放的数据。如果jwt被改，该函数会直接抛出异常
     * @param token  令牌
     * */
    public static Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(sign)
                .parseClaimsJws(token)
                .getBody() ;
    }

    /**
     * 验证用户信息
     * @param token  jwtToken
     * */
    public static String verifyJwtToken(String token){
        Claims claims = parseToken(replaceTokenPrefix(token));
        String id = String.valueOf(claims.get("id"));
        return id ;
    }


    /**
     * 刷新令牌时间，刷新redis缓存时间
     * @param  id 用户id
     * */
    public static void refreshToken(String id){
        //重新设置User对象的过期时间，再刷新缓存
//        user.setExpireTime(System.currentTimeMillis()+1000L * 60 * expireTime);
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        createJwtToken(map);
    }

    /**
     * 删除 token 的前缀
     * 前端的安全规则会在token前自动生成 Bearer 字符串前缀,共7个字符,需要删掉
     * */
    public static String replaceTokenPrefix(String token) {
        return token.substring(7);
    }
}
