package cn.edu.xmut.lgrg.dao;

import cn.edu.xmut.lgrg.entity.SysOrder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author azx
 * @create 2020-01-06-9:32
 */
public interface OrderDao {
    List<SysOrder> getOrderByUserId(HttpServletRequest request) throws Exception;

    boolean changePaymentOrderStatue(String OrderId) throws Exception;

    boolean changeSendoutOrderStatue(String OrderId) throws Exception;

    boolean changeConfirmOrderStatue(String OrderId) throws Exception;

}
