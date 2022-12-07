package cn.lntu.weiyin.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

//创建mybatis mysql会话类
public class SqlSessionUtil {

    static SqlSession sqlSession;
    public static SqlSession getSqlSession() {
        InputStream resource = null;
        try {
            resource = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resource);
        sqlSession = factory.openSession(true);
        return sqlSession;
    }

    public static void closeSession() {
        sqlSession.close();
    }


}
