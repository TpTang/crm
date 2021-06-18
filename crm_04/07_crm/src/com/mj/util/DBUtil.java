package com.mj.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


//封装JDBC
public class DBUtil {
    private static JdbcTemplate tpl;
    static{
        try(InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("druid.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            //通过工厂类创建数据源（数据库连接池）
            DataSource ds = DruidDataSourceFactory.createDataSource(properties);
            tpl = new JdbcTemplate(ds);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JdbcTemplate getTpl(){
        return tpl;
    }

}
