package cn.lntu.weiyin.controllers;

import cn.lntu.weiyin.pojo.Book;
import cn.lntu.weiyin.services.BookService;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateBookServlet", value = "/updateBookServlet")
public class UpdateBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        把前端请求的数据转换成JSON对象
        String Json = request.getReader().readLine();
        Book queryBook = JSON.parseObject(Json, Book.class);
//        写入数据库
        BookService service = new BookService();
        Boolean aBoolean = service.updateBook(queryBook);
//        返回写入结果
        if(aBoolean) {
            response.getWriter().write("success");
        }
        else {
            response.getWriter().write("failed");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
