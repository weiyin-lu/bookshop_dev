package cn.lntu.weiyin.services;

import cn.lntu.weiyin.mappers.UserMapper;
import cn.lntu.weiyin.pojo.User;
import cn.lntu.weiyin.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class UserService {
    /**
     * 用户登录方法
     * @param user 用户键入的信息
     * @return 能正确登录的用户信息，如果没有会返回null
     */
    public User login(User user) {
//        创建sql会话
        SqlSession session = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
//        执行sql命令
        User resultUser = userMapper.selectOneByUserPhonePassWord(user);
//        关闭sqlsession
        SqlSessionUtil.closeSession();
//        登录业务逻辑
//        如果没查询到结果，返回一个null
//        如果有，返回对应对象
        if (resultUser == null) {
            return null;
        }
        else {
            return resultUser;
        }
    }

    /**
     * 用户注册方法
     * @param user 用户键入的注册信息
     * @return 注册成功标记，成功为true
     */
    public Boolean register(User user) {
//        录入用户信息成功的标签
        boolean successFlag = true;
//        创建sql会话
        SqlSession session = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
//        执行sql命令及业务逻辑，如果插入捕获任何异常，说明插入未成功
        try {
            userMapper.insertAllUser(user);
        }
        catch(Exception e) {
            successFlag = false;
        }
//        关闭sqlsession
        SqlSessionUtil.closeSession();
        return successFlag;
    }
}
