package org.example.imooc_oa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.imooc_oa.Entity.Notice;
import org.example.imooc_oa.Utils.ResponseUtils;
import org.example.imooc_oa.service.NoticeService;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/notice")
public class NoticeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("employeeId");
        NoticeService noticeService = new NoticeService();
        ResponseUtils responseUtils;
        try{
            List<Notice> list = noticeService.selectNoticeByReceiverId(Integer.parseInt(id));
            responseUtils = new ResponseUtils();
            responseUtils.put("notices", list);
        }catch (Exception e){
            e.printStackTrace();
            responseUtils = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        resp.setContentType("application/json");
        resp.getWriter().write(responseUtils.toJson());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
