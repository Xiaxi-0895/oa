package org.example.imooc_oa.controller;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.jdbc.Null;
import org.example.imooc_oa.Entity.User;
import org.example.imooc_oa.Utils.ResponseUtils;
import org.example.imooc_oa.service.UserService;
import java.io.IOException;


@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    private UserService userService=new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        ResponseUtils responseUtils;
        try{
            User user=userService.checkLogin(username, password);
            user.setPassword(null);
            user.setSalt(0);
            responseUtils=new ResponseUtils().put("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            responseUtils=new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        resp.getWriter().println(responseUtils.toJson());
    }
}
