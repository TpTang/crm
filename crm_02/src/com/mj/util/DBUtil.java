package com.mj.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


//封装JDBC
public class DBUtil {
    //通过Druid数据库连接池封装JDBC
    private static DataSource ds;
    static{
        try(InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("druid.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            //通过工厂类创建数据源（数据库连接池）
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行DDL、DML语句
     * @param sql SQL语句
     * @param args 填充占位符所需的参数
     * @return 影响的记录数
     */
    public static int update(String sql, Object ...args){
        try {
            Connection conn = ds.getConnection();//通过数据库连接池拿到数据库连接对象 //不需要再手动注册驱动、手动关闭连接
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                //设置参数
                for (int i = 0; i <args.length ; i++) {
                    pstmt.setObject(i+1, args[i]);
                }
                //执行SQL
                return pstmt.executeUpdate(); //返回影响的记录数
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 从数据库查询满足条件的客户数据
     * @param sql SQL语句
     * @param mapper rs->bean的映射器
     * @return 客户数据
     */
    public static <T> List<T> query(String sql,RowMapper<T> mapper, Object ...args){
        if(mapper == null) return null;
        ResultSet rs = null;
        try {
            Connection conn = ds.getConnection();  //拿到连接对象
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                //设置参数
                for (int i = 0; i < args.length; i++) {
                    pstmt.setObject(i+1, args[i]);
                }
                //执行SQL
                 rs = pstmt.executeQuery();
                //rs转bean
                List<T> array = new ArrayList<>();
                int row = 0;
                while(rs.next()){
                    array.add(mapper.map(rs, row++));
                }
                return array;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * rs映射成bean的映射器
     * @param <T>
     */
    public interface RowMapper<T>{
        T map(ResultSet rs, int row) throws SQLException;
    }

    //通过properties配置文件简单封装
//     private static String driverClassName;
//     private static String url;
//     private static String username;
//     private static String password;

//     static{
//         //JVM通过类加载器加载Artifacts下classes下的资源
//         try(InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("db.properties")
//             //获取类加载器并通过类加载器获取输入流
//         ) {
//             Properties properties = new Properties();

//             properties.load(is); //拿到字节流去封装
//             driverClassName = properties.getProperty("driverClassName");
//             url = properties.getProperty("url");
//             username = properties.getProperty("username");
//             password = properties.getProperty("password");
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     /**
//      * 执行DDL、DML语句
//      * @param sql SQL语句
//      * @param args 填充占位符所需的参数
//      * @return 影响的记录数
//      */
//     public static int update(String sql, Object ...args){
//         try {
//             Class.forName(driverClassName);// 注册MySQL驱动到DriverManager

//             try(Connection conn = DriverManager.getConnection(url, username, password);//获取数据库连接对象
//                 PreparedStatement pstmt = conn.prepareStatement(sql)//获取执行语句对象
//                 ){
//                 //设置参数
//                 for (int i = 0; i <args.length ; i++) {
//                     pstmt.setObject(i+1, args[i]);
//                 }
//                 //执行SQL
//                 return pstmt.executeUpdate(); //返回影响的记录数
//             }
//         } catch (ClassNotFoundException | SQLException e) {
//             e.printStackTrace();
//             return 0;
//         }
//     }

//     /**
//      * 从数据库查询满足条件的客户数据
//      * @param sql SQL语句
//      * @param mapper rs->bean的映射器
//      * @return 客户数据
//      */
//     public static <T> List<T> query(String sql,RowMapper<T> mapper, Object ...args){
//         if(mapper == null) return null;
//         ResultSet rs = null;
//         try {
//             Class.forName(driverClassName); //注册MySQL驱动到DriverManager
//             try(Connection conn = DriverManager.getConnection(url, username, password); //获取数据库连接对象
//                 PreparedStatement pstmt = conn.prepareStatement(sql)){
//                 //设置参数
//                 for (int i = 0; i < args.length; i++) {
//                     pstmt.setObject(i+1, args[i]);
//                 }
//                 //执行SQL
//                  rs = pstmt.executeQuery();
//                 //rs转bean
//                 List<T> array = new ArrayList<>();
//                 int row = 0;
//                 while(rs.next()){
//                     array.add(mapper.map(rs, row++));
//                 }
//                 return array;
//             }
//         } catch (ClassNotFoundException | SQLException e) {
//             e.printStackTrace();
//             return null;
//         }finally {
//             if(rs != null){
//                 try {
//                     rs.close();
//                 } catch (SQLException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }
//     }

//     /**
//      * rs映射成bean的映射器
//      * @param <T>
//      */
//     public interface RowMapper<T>{
//         T map(ResultSet rs, int row) throws SQLException;
//     }
}
