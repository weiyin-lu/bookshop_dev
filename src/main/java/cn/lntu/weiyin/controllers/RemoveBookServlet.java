package cn.lntu.weiyin.controllers;

import cn.lntu.weiyin.services.BookService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveBookServlet", value = "/removeBookServlet")
public class RemoveBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        接收请求数据
        String queryId = request.getParameter("bookId");
//        执行查询命令
        BookService service = new BookService();
        Boolean aBoolean = service.removeBook(Integer.parseInt(queryId));
//        根据标识判断结果
        if(aBoolean) {
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
