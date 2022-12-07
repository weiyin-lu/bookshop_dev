package cn.lntu.weiyin.mappers;

import cn.lntu.weiyin.pojo.User;

public interface UserMapper {

//    使用电话号和密码查询字段
    User selectOneByUserPhonePassWord(User user);
//    向user表插入新字段
    void insertAllUser(User user);
}
