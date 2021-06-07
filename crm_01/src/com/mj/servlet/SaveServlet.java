package com.mj.servlet;

import com.mj.bean.Customer;
import com.mj.dao.CustomerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/save")
public class SaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        CustomerDao dao = new CustomerDao();

        Customer customer = new Customer();
        customer.setName(request.getParameter("name"));
        customer.setAge(Integer.parseInt(request.getParameter("age")));
        customer.setHeight(Double.parseDouble(request.getParameter("height")));

        //保存数据到数据库
        if(dao.save(customer)){
            //重定向到list
            response.sendRedirect( "/crm/list");
        }else { //数据更新失败
            //转发到错误页面
            request.setAttribute("error", "信息保存失败");
            request.getRequestDispatcher("/page/error.jsp").forward(request, response);
        }
    }

}
