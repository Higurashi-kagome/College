package com.zhangmingge.commonutils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>JWT 工具类。</p>
 * <p>假设token放在header的token字段中</p>
 * <p>https://www.cnblogs.com/xz816111/p/9620139.html</p>
 */
public class JwtUtils {

    //常量
    public static final long EXPIRE = 1000 * 60 * 60 * 24; //token过期时间（24 小时）
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO"; //秘钥

    //生成token字符串的方法
    public static String getJwtToken(String id, String nickname){

        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")//令牌类型
                .setHeaderParam("alg", "HS256")//加密算法

                .setSubject("guli-user")//发行人
                .setIssuedAt(new Date())//签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))//过期时间

                .claim("id", id)  //设置token主体部分 ，存储用户信息
                .claim("nickname", nickname)

                .signWith(SignatureAlgorithm.HS256, APP_SECRET)//使用密钥签名
                .compact();

        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean isTokenValid(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean isTokenValid(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * <p>根据token字符串获取会员id</p>
     * <p>TODO 更好的进行校验的方式（或许不该将校验放在getMemberIdByJwtToken中），检查其他使用了JWT的代码是否有进行校验</p>
     * @param request
     * @return
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(!isTokenValid(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("id");
    }
}
