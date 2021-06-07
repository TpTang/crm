package com.mj.dao;

import com.mj.bean.Customer;
import com.mj.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//DAO:Data(base) Access Object - 负责与数据库交互数据
public class CustomerDao { //命名规则：最好是 数据库表名+Dao

    /**
     * 将给定的bean保存到数据库
     * @param customer 客户
     * @return 保存成功与否
     */
    public boolean save(Customer customer){
        String sql = "INSERT INTO `customer`(name, age, height) VALUES(?, ?, ?)";
        int res = DBUtil.update(sql, customer.getName(), customer.getAge(), customer.getHeight());
        return res > 0;
    }

    /**
     * 从数据库里获取所有客户
     * @return 所有客户
     */
    public List<Customer> query(){
        String sql = "SELECT `id`,`name`, `age`, `height` FROM `customer`";

        List<Customer> customers = DBUtil.query(sql, (rs, row)->{
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setAge(rs.getInt("age"));
            customer.setHeight(rs.getDouble("height"));
            return customer;
        });
        return customers;
    }

}
