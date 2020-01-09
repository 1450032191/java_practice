package cn.edu.xmut.lgrg.servlets.bao;

import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.util.MySqlUtil;
import cn.edu.xmut.lgrg.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/9 21:44
 * @Description:
 */
@WebServlet("/bao/get.do")
public class GetBaoServlete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String sql = "select \n" +
                    "(select count(*) from sys_user where to_days(create_time) = to_days(now())) as today_user_count,\n" +
                    "(select count(*) from sys_commodity where to_days(com_create_time) = to_days(now())) as today_commodity_count,\n" +
                    "(select round(ifnull(SUM(order_price),0.00),2) from sys_order where to_days(create_time) = to_days(now())) as today_order_price,\n" +
                    "(select count(*) from sys_order where to_days(create_time) = to_days(now())) as today_order_count,\n" +
                    "(select count(*) from sys_user) as user_count,\n" +
                    "(select count(*) from sys_commodity) as commodit_count,\n" +
                    "(select round(ifnull(SUM(order_price),0.00),2) from sys_order) as order_price,\n" +
                    "(select count(*) from sys_order) as order_count,\n" +
                    "(select round(ifnull(SUM(order_price),0.00),2) from sys_order where YEAR(create_time)=YEAR(NOW())) as year_order_price,\n" +
                    "(select count(*) from sys_order where order_status != '4') / (select count(*) from sys_order) * 100 as zanbi";
            Connection connection = MySqlUtil.getCon();
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            PageData data = new PageData();
            List<String> lables = new ArrayList<>();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                lables.add(meta.getColumnLabel(i));
            }
            if(rs.next()){
                for (int i = 0; i < lables.size(); i++) {
                    data.put(lables.get(i),rs.getString(lables.get(i)));
                }
            }

            sql = "select dt.day1,(\n" +
                    "select count(*) from sys_order t where to_days(t.create_time) = to_days(dt.day1)) as day_order_count,\n" +
                    "(select round(ifnull(SUM(order_price),0.00),2) from sys_order where to_days(create_time) = to_days(dt.day1)) as day_order_price,\n" +
                    "(select count(*) from sys_user t where to_days(t.create_time) = to_days(dt.day1)) as day_new_user\n" +
                    "from (\n" +
                    "\tselect DATE_SUB(curdate(),INTERVAL -6 DAY) day1 union\n" +
                    "\tselect DATE_SUB(curdate(),INTERVAL -5 DAY) day1 union\n" +
                    "\tselect DATE_SUB(curdate(),INTERVAL -4 DAY) day1 union\n" +
                    "\tselect DATE_SUB(curdate(),INTERVAL -3 DAY) day1 union\n" +
                    "\tselect DATE_SUB(curdate(),INTERVAL -2 DAY) day1 union\n" +
                    "\tselect DATE_SUB(curdate(),INTERVAL -1 DAY) day1 union\n" +
                    "\tselect DATE_SUB(curdate(),INTERVAL -0 DAY) day1\n" +
                    ") dt";
            pre = connection.prepareStatement(sql);
            rs = pre.executeQuery();
            List<String> dates = new ArrayList<>();
            List<String> orderCounts = new ArrayList<>();
            List<String> orderPrices = new ArrayList<>();
            List<String> newUser = new ArrayList<>();

            while (rs.next()){
                dates.add(rs.getString("day1"));
                orderCounts.add(rs.getString("day_order_count"));
                orderPrices.add(rs.getString("day_order_price"));
                newUser.add(rs.getString("day_new_user"));
            }

            PageData res = new PageData();
            res.put("data",data);
            res.put("dates",dates);
            res.put("orderCounts",orderCounts);
            res.put("orderPrices",orderPrices);
            res.put("newUser",newUser);
            ResultUtil.outSuccess(resp,res);
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(resp,e.getMessage());
        }
    }
}
