package com.mj.servlet;

import com.mj.bean.Customer;
import com.mj.dao.CustomerDao;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@WebServlet("/customer/*")
public class CustomerServlet extends HttpServlet {

    private final CustomerDao dao = new CustomerDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            request.setCharacterEncoding("UTF-8");

            String[] cmps = request.getRequestURI().split("/");
            String methodName = cmps[cmps.length - 1];

            //通过反射调用方法
            Method method = getClass().getMethod(methodName,
                    HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);

        } catch (Exception e) {
            e.printStackTrace();
            //客户传递URL不符合规范时
            forwardError(request, response, "访问路径有问题"); //转发错误信息
        }
    }

    //添加客户数据到数据库
    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //将表单数据封装到bean (表单->bean)
        Customer customer = new Customer();
        BeanUtils.populate(customer, request.getParameterMap());
//        Customer customer = new Customer();
//        customer.setName(request.getParameter("name"));
//        customer.setAge(Integer.parseInt(request.getParameter("age")));
//        customer.setHeight(Double.parseDouble(request.getParameter("height")));

        //将bean持久化到数据库存储
        if(dao.save(customer)){
            //重定向到list
            response.sendRedirect( "/crm/customer/list");
        }else { //数据更新失败
            //转发到错误页面
            forwardError(request, response, "信息保存失败"); //转发错误信息
        }
    }

    //展示所有客户列表
    public void list(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //从数据库拿到所有客户数据
        List<Customer> customers = dao.list();
        //添加到request对象中
        request.setAttribute("customers", customers); //哈希表
        //转发到JSP页面展示
        request.getRequestDispatcher("/page/list.jsp").forward(request, response);
    }
    //删除一条客户记录
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取要删除记录的唯一标识:id
        Integer id = Integer.valueOf(request.getParameter("id"));
        if(dao.remove(id)){ //删除成功
            response.sendRedirect("/crm/customer/list"); //重定向
        }else{ //删除失败
            forwardError(request, response, "删除失败"); //转发错误信息
        }
    }

    //编辑客户信息
    public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //获取要编辑记录的唯一标识：id
        Integer id = Integer.valueOf(request.getParameter("id"));
        //根据id查询记录
        Customer customer = dao.find(id);
        //转发到update.jsp
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/page/update.jsp").forward(request, response);
    }

    //更新客户信息
    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //将更新的客户数据封装到bean
        Customer customer = new Customer();
        BeanUtils.populate(customer, request.getParameterMap());
//        Customer customer = new Customer();
//        customer.setId(Integer.valueOf(request.getParameter("id")));
//        customer.setName(request.getParameter("name"));
//        customer.setAge(Integer.valueOf(request.getParameter("age")));
//        customer.setHeight(Double.valueOf(request.getParameter("height")));
        //将bean持久化到数据库 (不能直接使用上面的save（））
        if(dao.update(customer)){ //持久化成功
            response.sendRedirect("/crm/customer/list"); //重定向到list
        }else{ //持久化失败
            forwardError(request, response, "更新数据失败"); //转发错误信息
        }
    }

    //将错误信息封装起来
    public void forwardError(HttpServletRequest request, HttpServletResponse response, String error)throws IOException, ServletException{
        request.setAttribute("error", error);
        request.getRequestDispatcher("/page/error.jsp").forward(request, response);
    }
}
