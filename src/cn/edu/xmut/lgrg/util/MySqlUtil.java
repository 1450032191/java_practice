package cn.edu.xmut.lgrg.util;

import cn.edu.xmut.lgrg.entity.PageData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * @Auther: ZiNan
 * @Date: 2019/10/22 20:12
 * @Description:
 */
public class MySqlUtil {
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

//    static final String DB_URL = "jdbc:mysql://120.79.216.134:3306/java_practice?useUnicode=true&characterEncoding=utf8";
//    // 数据库的用户名与密码，需要根据自己的设置
//    static final String USER = "class";
//    static final String PASS = "Xmut123456.";

    static final String DB_URL = "jdbc:mysql://localhost:3306/java_practice?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "root";

    static Connection conn = null;

    public static Connection getCon()throws Exception{
        if(conn == null){
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            return conn;
        }else {
            return conn;
        }
    }
}
