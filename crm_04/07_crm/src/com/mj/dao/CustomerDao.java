package com.mj.dao;

import com.mj.bean.Customer;
import com.mj.util.DBUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
//        int res = DBUtil.getTpl().update(sql, customer.getName(),
//                                              customer.getAge(), customer.getHeight());
//        return res > 0;
        //为了避免大规模传参，将部分传参封装起来
        List<Object> objectList = buidArgs(customer);
        return DBUtil.getTpl().update(sql, objectList.toArray()) > 0;
    }

    /**
     * 从数据库里获取所有客户
     * @return 所有客户
     */
    public List<Customer> list(){
        String sql = "SELECT `id`,`name`, `age`, `height` FROM `customer`";

//        List<Customer> customers = DBUtil.getTpl().query(sql, (rs, row)->{
//            Customer customer = new Customer();
//            customer.setId(rs.getInt("id"));
//            customer.setName(rs.getString("name"));
//            customer.setAge(rs.getInt("age"));
//            customer.setHeight(rs.getDouble("height"));
//            return customer;
//        });

        //通过Spring JDBC封装好的映射来实现rs->bean  底层原理：反射（Field）
        //如果：数据库字段real_age, bean 属性realAge - 这种符合MySQL和Java规范的东西，Spring JDBC
        //内部是能做好映射的
        List<Customer> customers = DBUtil.getTpl().query(sql,
                new BeanPropertyRowMapper<>(Customer.class)); //BeanPropertyRowMapper:继承自RowMapper
        return customers;
    }

    //根据id删除客户记录
    public boolean remove(Integer id) {
        String sql = "DELETE FROM `customer` WHERE `id`= ?";
        return DBUtil.getTpl().update(sql, id) > 0;
    }

    //根据id查找客户记录
    public Customer find(Integer id) {
        String sql = "SELECT `id`, `name`, `age`, `height` FROM `customer` WHERE `id`= ?";
        return DBUtil.getTpl().queryForObject(sql, new BeanPropertyRowMapper<>(Customer.class), id);
    }

    //更新客户记录
    public boolean update(Customer customer) {
        String sql = "UPDATE `customer` SET `name`= ?, `age`= ?, `height`= ? WHERE `id` = ?";
//        return DBUtil.getTpl().update(sql, customer.getName(),
//                customer.getAge(), customer.getHeight(),customer.getId()) > 0;
        List<Object> objectList = buidArgs(customer);
        objectList.add(customer.getId());
        return DBUtil.getTpl().update(sql, objectList.toArray()) > 0;
    }

    private List<Object> buidArgs(Customer customer){
        List<Object> args = new ArrayList<>();
        args.add(customer.getName());
        args.add(customer.getAge());
        args.add(customer.getHeight());
        return args;
    }
}
