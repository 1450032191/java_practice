package cn.edu.xmut.lgrg.dao;

import cn.edu.xmut.lgrg.entity.SysCar;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author azx
 * @create 2019-12-31-9:20
 */
public interface SysCarDAO {
    //获取所有购物车的信息
   List<SysCar> getAllShoes(HttpServletRequest request) throws Exception;

   //清空购物车
    boolean deleteAllCommodity(HttpServletRequest request) throws Exception;

    //删除单个商品
    boolean deleteById(HttpServletRequest request ,int comId) throws Exception;

    //改变商品数量
   boolean changeQuantity(HttpServletRequest request,int comId,int quantity) throws Exception;

}
