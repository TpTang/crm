package com.mj.test;

import com.mj.bean.Customer;
import com.mj.dao.CustomerDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

//单元测试 Junit4
public class CustomerDaoTest {
    @Test
    public void testSave(){
        Customer customer = new Customer();
        customer.setName("郑和");
        customer.setAge(34);
        customer.setHeight(1.99);

        CustomerDao dao = new CustomerDao();
        Assert.assertTrue(dao.save(customer)); //dao.save()执行成功则不报错
    }

    @Test
    public void testList(){
        CustomerDao dao = new CustomerDao();
        List<Customer> list = dao.list();
        Assert.assertTrue(list.size() > 0);
    }
}
