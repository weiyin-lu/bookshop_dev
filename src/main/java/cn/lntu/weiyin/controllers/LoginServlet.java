package cn.lntu.weiyin.controllers;

import cn.lntu.weiyin.pojo.User;
import cn.lntu.weiyin.services.UserService;
import cn.lntu.weiyin.utils.JwtUtil;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = request.getReader().readLine();
        System.out.println(json);
        User inputuser = JSON.parseObject(json, User.class);
        System.out.println(inputuser);
        UserService service = new UserService();
        User user = service.login(inputuser);
        if(user != null) {
            String token = JwtUtil.generateJwtToken(user);
            response.getWriter().write(token);
        }
        else {
            response.setStatus(401);
            response.getWriter().write("failed");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
