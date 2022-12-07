package cn.lntu.weiyin.services;

import cn.lntu.weiyin.mappers.BookMapper;
import cn.lntu.weiyin.mappers.UserMapper;
import cn.lntu.weiyin.pojo.Book;
import cn.lntu.weiyin.utils.Base64Util;
import cn.lntu.weiyin.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class BookService {
    /**
     * 插入一条图书记录
     * @param book 键入的图书对象
     * @return 插入结果，成功为true
     */
    public Boolean insertBook(Book book) throws IOException {
//        将bookImg的BASE64转换成实体文件，存回路径
        String base64Img = book.getBookImg().split(",")[1];//拆分出数据部分
//            存回
        book.setBookImg(Base64Util.base64Decoder(base64Img));

//        录入信息成功的标签
        boolean successFlag = true;
//        创建sql会话
        SqlSession session = SqlSessionUtil.getSqlSession();
        BookMapper bookMapper = session.getMapper(BookMapper.class);

//        执行sql命令及业务逻辑，如果插入捕获任何异常，说明插入未成功
        try {
            bookMapper.insertAllBook(book);
        }
        catch(Exception e) {
            successFlag = false;
        }
//        关闭sqlsession
        SqlSessionUtil.closeSession();
        return successFlag;
    }

    /**
     * 查询所有书目
     * @return 图书类型集合
     * @throws IOException
     */
    public List<Book> showBook() throws IOException {
//        创建SQL会话
        SqlSession session = SqlSessionUtil.getSqlSession();
        BookMapper bookMapper = session.getMapper(BookMapper.class);
//        执行sql命令
        List<Book> bookList = bookMapper.selectAllBook();
//        把路径取出转换成BASE64编码
        for(int i = 0;i<bookList.size();i ++) {
//            引用传递
            Book book = bookList.get(i);
//            把BASE64编码转存进对象中
            String encoder = "data:image/jpeg;base64," + Base64Util.base64Encoder(book.getBookImg());
            book.setBookImg(encoder);
        }
        return bookList;
    }

    /**
     * 根据书名查询书目（重载）
     * @return 图书类型集合
     */
    public List<Book> showBook(String bookName) throws IOException {
//        创建SQL会话
        SqlSession session = SqlSessionUtil.getSqlSession();
        BookMapper bookMapper = session.getMapper(BookMapper.class);
//        执行sql命令
        List<Book> bookList = bookMapper.selectBookByName(bookName);
//        把路径取出转换成BASE64编码
        for(int i = 0;i<bookList.size();i ++) {
//            引用传递
            Book book = bookList.get(i);
//            把BASE64编码转存进对象中
            String encoder = "data:image/jpeg;base64," + Base64Util.base64Encoder(book.getBookImg());
            book.setBookImg(encoder);
        }
        return bookList;
    }

    /**
     * 根据id删除图书
     * @param id
     * @return
     */
    public Boolean removeBook(int id) {
        boolean successFlag = true; //删除标记
//        创建SQL会话
        SqlSession session = SqlSessionUtil.getSqlSession();
        BookMapper bookMapper = session.getMapper(BookMapper.class);
//        执行sql命令，如果捕获任何错误，说明插入未成功
        try {
            bookMapper.deleteById(id);
        }
        catch (Exception e) {
            successFlag = false;
        }
        return successFlag;
    }

    /**
     * 按ID更新图书信息
     * @param book 图书对象
     * @return 成功返回true
     */
    public Boolean updateBook(Book book) throws IOException {
        boolean successFlag = true;
//        将bookImg的BASE64转换成实体文件，存回路径
        String base64Img = book.getBookImg().split(",")[1];//拆分出数据部分
//            存回
        book.setBookImg(Base64Util.base64Decoder(base64Img));
//        创建SQL会话
        SqlSession session = SqlSessionUtil.getSqlSession();
        BookMapper bookMapper = session.getMapper(BookMapper.class);
//        执行sql命令 如果捕获任何异常说明插入失败
        try {
            bookMapper.updateById(book);
        }
        catch (Exception e) {
            successFlag = false;
            e.getStackTrace();
        }
        return successFlag;
    }
}
