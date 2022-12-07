package cn.lntu.weiyin.controllers;

import cn.lntu.weiyin.pojo.User;
import cn.lntu.weiyin.services.UserService;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获得前端的注册信息
        String inputuser = request.getReader().readLine();
        User user = JSON.parseObject(inputuser, User.class);
//        执行命令
        UserService service = new UserService();
        Boolean aBoolean = service.register(user);
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
