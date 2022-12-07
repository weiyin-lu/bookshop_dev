package cn.lntu.weiyin.mappers;

import cn.lntu.weiyin.pojo.Book;

import java.util.List;

public interface BookMapper {
//    插入一条数据
    void insertAllBook(Book Book);
//    查询所有数据
    List<Book> selectAllBook();
//    根据书名模糊匹配
    List<Book> selectBookByName(String bookName);
//    删除图书
    void deleteById(int id);
//    更新图书
    void updateById(Book book);
}
