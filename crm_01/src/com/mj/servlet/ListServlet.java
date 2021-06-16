package com.mj.servlet;

import com.mj.bean.Customer;
import com.mj.bean.Data;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /*
        转发（forward）：只能在同一个context内转发（context：上下文、等同于整个项目） 浏览器URL不会改变
        可以通过request.setAttribute request.getAttribute 实现多个Servlet、JSP共享数据（仅仅是在一次请求中-依赖于request对象存储：
                                                                                     request对象里的attribute是个HashMap）
        重定向：服务器通知客户端重新发送新的请求到任意URL，浏览器URL会改变
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        目标：JSP不写一句Java代码  Servlet不写一句HTML代码  JSP本质Servlet
         */
        //模拟从数据库获取数据
        List<Customer> customers = Data.getCustomers();
        //将数据存储到request对象中
        request.setAttribute("customers", customers);
        //转发到list.jsp进行页面展示
        request.getRequestDispatcher("/page/list.jsp").forward(request, response);
    }

    //模拟从数据库取数据
    private List<Customer>  getCustomer(){
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customers.add(new Customer("张三"+i, 10 + i, 2.3 + i));
        }
        return customers;
    }
}
