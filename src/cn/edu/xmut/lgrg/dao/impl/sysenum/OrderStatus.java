package cn.edu.xmut.lgrg.dao.impl.sysenum;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: ZiNan
 * @Date: 2019/12/12 16:48
 * @Description:
 */
public class OrderStatus {
    private final static Map<String,String> orderStatus=new HashMap<>();
    private final static Map<String,String> orderToStatus=new HashMap<>();
    static{
        orderStatus.put("1","已下单");
        orderStatus.put("2","付款成功");
        orderStatus.put("3","商品出库");
        orderStatus.put("4","订单完成");

        orderToStatus.put("1","待付款");
        orderToStatus.put("2","待发货");
        orderToStatus.put("3","待收货");
        orderToStatus.put("4","订单完成");
    }

    public static String getOrderStatus(String key){
        return orderStatus.get(key);
    }

    public static String getOrderToStatus(String key){
        return orderToStatus.get(key);
    }
}
