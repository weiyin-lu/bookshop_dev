package cn.lntu.weiyin.controllers;

import cn.lntu.weiyin.pojo.Book;
import cn.lntu.weiyin.services.BookService;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowBookByNameServlet", value = "/showBookByNameServlet")
public class ShowBookByNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        接收请求数据
        String queryName = request.getParameter("bookName");
//        执行查询命令
        BookService service = new BookService();
        List<Book> bookList = service.showBook(queryName);
        String bookListJson = JSON.toJSONString(bookList);

        response.setContentType("application/json;charset=utf8");
        response.getWriter().write(bookListJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
