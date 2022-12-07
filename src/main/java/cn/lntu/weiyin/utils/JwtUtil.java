package cn.lntu.weiyin.utils;

import cn.lntu.weiyin.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//jwt常量类
public class JwtUtil {
        //加密算法
        private final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

        //私钥 / 生成签名的时候使用的秘钥secret，一般可以从本地配置文件中读取，切记这个秘钥不能外露，只在服务端使用，在任何场景都不应该流露出去。
        // 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        private final static String secret = "secretKey";

        // 过期时间（单位秒）/ 2小时
        private final static Long access_token_expiration = 7200L;

        //jwt签发者
        private final static String jwt_iss = "bookdev";

        //jwt所有人
        private final static String subject = "bookfront";

    /**
     * 创建jwt
     * @return
     *      返回生成的jwt token
     */
    public static String generateJwtToken(User user){

        // 头部 map / Jwt的头部承载，第一部分
        // 可不设置 默认格式是{"alg":"HS256"}
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        //载荷 map / Jwt的载荷，第二部分
        Map<String,Object> claims = new HashMap<String,Object>();

        //私有声明 / 自定义数据，根据业务需要添加
        claims.put("userPhone",user.getUserPhone());
        claims.put("userPassword", user.getUserPassword());
        claims.put("userName",user.getUserName());

        //下面就是在为payload添加各种标准声明和私有声明了
        return Jwts.builder() // 这里其实就是new一个JwtBuilder，设置jwt的body
                .setHeader(map)         // 头部信息
                .setClaims(claims)      // 载荷信息
                .setId(UUID.randomUUID().toString()) // 设置jti(JWT ID)：是JWT的唯一标识，从而回避重放攻击。
                .setIssuedAt(new Date())       // 设置iat: jwt的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + access_token_expiration * 1000)) // 设置exp：jwt过期时间
                .setSubject(subject)    //设置sub：代表这个jwt所面向的用户，所有人
                .signWith(SIGNATURE_ALGORITHM, secret)//设置签名：通过签名算法和秘钥生成签名
                .compact(); // 开始压缩为xxxxx.yyyyy.zzzzz 格式的jwt token
    }

    /**
     *  从jwt中获取 载荷 信息
     * @param jwt
     * @return
     */
    public static Claims getClaimsFromJwt(String jwt) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }
}
