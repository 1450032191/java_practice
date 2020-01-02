package cn.edu.xmut.lgrg.util;

import java.sql.*;

public class ConnDBUtil {
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private Connection conn = null;
    ResultSet rs = null;
    int result = 0;

    public ConnDBUtil() {
    }

    public void OpenConn() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://120.79.216.134:3306/java_practice?serverTimezone=UTC&user=class&password=Xmut123456.&useUnicode=true&characterEncoding=utf-8";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println("db connection error:" + e.getMessage());
        }
    }

    public void createStmt() throws Exception {
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            System.err.println("db create statement error:" + e.getMessage());
        }
    }

    public PreparedStatement createPStmt(String sql) throws Exception {
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.err.println("db create preparedstatement error:" + e.getMessage());
        }
        return pstmt;
    }

    public ResultSet stmtQuery(String sql) {
        rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("stmt.executeQuery error:" + e.getMessage());
        }
        return rs;
    }

    public int stmtUpdate(String sql) {
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("stmt.executeUpdate error:" + e.getMessage());
        }
        return result;
    }

    public ResultSet pstmtQuery() {
        rs = null;
        try {
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("pstmt.executeQuery error:" + e.getMessage());
        }
        return rs;
    }

    public int pstmtUpdate() {
        try {
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("pstmt.executeUpdate error:" + e.getMessage());
        }
        return result;
    }

    public void closeResult() {
        try {
            rs.close();
        } catch (SQLException e) {
            System.err.println("close result error:" + e.getMessage());
        }
    }

    public void closeStmt() {
        try {
            stmt.close();
        } catch (SQLException e) {
            System.err.println("close Stmt error:" + e.getMessage());
        }
    }

    public void closePstmt() {
        try {
            pstmt.close();
        } catch (SQLException e) {
            System.err.println("close pstmt error:" + e.getMessage());
        }
    }

    public void closeConn() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("close Conn error:" + e.getMessage());
        }
    }

    public Connection getConn() {
        return conn;
    }

}
