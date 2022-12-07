package cn.lntu.weiyin.controllers.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//跨域处理
@WebFilter("*")
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
//        设置编码格式
        req.setCharacterEncoding("utf-8");

        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)res;
//允许跨域的主机地址： *表示允许来自任何域的请求
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials","true");
//允许跨域的请求方法GET, POST, HEAD 等 ,可以用*代表可以接受任何类型的请求方法
        response.setHeader("Access-Control-Allow-Methods",
                "POST,GET,HEAD,OPTIONS,DELETE,PUT");
// 重新预检验跨域的缓存时间 (s)
        response.setHeader("Access-Control-Max-Age", "3600");
// 允许跨域的请求头，
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length,Authorization,Accept");
//设置允许前端发送的请求头
        response.setHeader("Access-Control-Request-Headers","Authorization");
//暴露头信息
        response.setHeader("Access-Control-Expose-Headers","Authorization");
//        放行
        chain.doFilter(request, response);

    }
}