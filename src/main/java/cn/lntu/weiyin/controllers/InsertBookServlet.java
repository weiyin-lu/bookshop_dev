package cn.lntu.weiyin.controllers;

import cn.lntu.weiyin.pojo.Book;
import cn.lntu.weiyin.services.BookService;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//插入图片数据
@WebServlet(name="InsertBookServlet",value="/insertBookServlet")
public class InsertBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        获得前端post请求JSON数据，转换为Book对象
        String sLine = request.getReader().readLine();
        Book book = JSON.parseObject(sLine, Book.class);

//        写入数据库
        BookService service = new BookService();
        if(service.insertBook(book)) {
            response.getWriter().write("success");
        }
        else {
            response.getWriter().write("failed");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
