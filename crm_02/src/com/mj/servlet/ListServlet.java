package com.mj.servlet;

import com.mj.bean.Customer;
import com.mj.dao.CustomerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //从数据库拿到所有客户数据
        CustomerDao dao = new CustomerDao();
        List<Customer> customers = dao.query();
        //添加到request对象中
        request.setAttribute("customers", customers); //哈希表
        //转发到JSP页面展示
        request.getRequestDispatcher("/page/list.jsp").forward(request, response);

    }

}
