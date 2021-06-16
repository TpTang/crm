package com.mj.servlet;

import com.mj.bean.Customer;
import com.mj.bean.Data;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 *处理客户端执行"添加"操作发送的数据
 */
@WebServlet("/save")
public class SaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获取请求参数
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String height = request.getParameter("height");

        //转成Java bean对象
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAge(Integer.valueOf(age));
        customer.setHeight(Double.valueOf(height));

        //模拟保存数据
        Data.add(customer);

        //转发
        //request.getRequestDispatcher("/list").forward(request, response);
        
        //重定向：如果Location不是完整URL->浏览器拿到IP+端口拼接Location去请求资源
        response.sendRedirect("/hello/list");
    }
}
