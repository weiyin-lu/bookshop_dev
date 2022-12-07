import cn.lntu.weiyin.pojo.User;
import cn.lntu.weiyin.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;

public class LoginTest {
    @Test
    public void loginTest() {
        User user = new User();
        user.setUserPhone("17696646843");
        user.setUserPassword("admin");

        String s = JwtUtil.generateJwtToken(user);
        System.out.println(s);
        Claims jwt = JwtUtil.getClaimsFromJwt(s);
        System.out.println(jwt);
    }
}
