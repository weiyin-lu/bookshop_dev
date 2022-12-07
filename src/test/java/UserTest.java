import cn.lntu.weiyin.pojo.User;
import cn.lntu.weiyin.services.UserService;
import org.junit.Test;

import java.io.IOException;


public class UserTest {
//    登录测试
    @Test
    public void loginTest() throws IOException {
//        测试用例：已知账户
        User userA = new User();
        userA.setUserName("admin");
        userA.setUserPassword("admin");
        userA.setUserPhone("15640047783");
//        测试用例：未知账户
        User userB = new User();
        userB.setUserName("anonymous");
        userB.setUserPassword("anonymous");
        userB.setUserPhone("10010001000");
//        使用service层功能测试
        UserService service = new UserService();
        User resultUser = service.login(userA);
        User resultUser2 = service.login(userB);

        System.out.println("Authorize:" + resultUser.getUserName());
        System.out.println("unAuthor:" + resultUser2.getUserName());
    }
//    注册测试
    @Test
    public void registerTest() {
//        测试用例：已有账户
        User userA = new User();
        userA.setUserName("admin");
        userA.setUserPassword("admin");
        userA.setUserPhone("15640047783");
//        测试用例：新账户，但用户名与已有用户一致
        User userB = new User();
        userB.setUserName("admin");
        userB.setUserPassword("admin");
        userB.setUserPhone("10010001000");
//        使用service层功能测试
        UserService service = new UserService();
        Boolean aBoolean = service.register(userA);
        Boolean aBoolean1 = service.register(userB);

        System.out.println("已有账户：" + aBoolean + "\n新账户：" + aBoolean1);

    }
}
