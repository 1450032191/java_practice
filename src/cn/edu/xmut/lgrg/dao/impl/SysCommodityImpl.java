package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.annotation.ZnService;
import cn.edu.xmut.lgrg.dao.SysCommodityDao;
import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.entity.SysCommodity;
import cn.edu.xmut.lgrg.entity.SysUser;
import cn.edu.xmut.lgrg.util.ConnDBUtil;
import cn.edu.xmut.lgrg.util.MySqlPageUtlil;
import cn.edu.xmut.lgrg.util.MySqlUtil;
import cn.edu.xmut.lgrg.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ZnService
public class SysCommodityImpl implements SysCommodityDao {

    @Override
    public void addComm(SysCommodity comm) throws Exception {
        String sql = "insert into sys_commodity(com_id,com_name,com_price,com_op,com_create_time,com_brand_id,com_category_id,detail_info,`status`,is_vip,com_img,com_stock) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection con = MySqlUtil.getCon();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, comm.getComId());
            pstm.setString(2, comm.getComName());
            pstm.setDouble(3, comm.getComPrice());
            pstm.setDouble(4, comm.getComOp());
            pstm.setString(5, comm.getComCreateTime());
            pstm.setString(6, comm.getComBrandId());
            pstm.setString(7, comm.getComCategoryId());
            pstm.setString(8, comm.getDetailInfo());
            pstm.setString(9, comm.getStatus());
            pstm.setString(10, comm.getIsVip());
            pstm.setString(11, comm.getComImg());
            pstm.setString(12, comm.getComStock());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("新增数据错误！");
        }
    }

    @Override
    public void deleteComm(SysCommodity comm) throws Exception {
        String sql = "delete from sys_commodity where com_id=?";
        try {
            Connection con = MySqlUtil.getCon();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, comm.getComId());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("删除数据错误！");
        }
    }

    @Override
    public void updateComm(SysCommodity comm) throws Exception {
        String sql = "update sys_commodity set com_name=?,com_price=?,com_op=?,com_brand_id=?,com_category_id=?,detail_info=?,status=?,is_vip=?,com_stock=? where com_id=?";
        try {
            Connection con = MySqlUtil.getCon();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, comm.getComName());
            pstm.setDouble(2, comm.getComPrice());
            pstm.setDouble(3, comm.getComOp());
            pstm.setString(4, comm.getComBrandId());
            pstm.setString(5, comm.getComCategoryId());
            pstm.setString(6, comm.getDetailInfo());
            pstm.setString(7, comm.getStatus());
            pstm.setString(8, comm.getIsVip());
            pstm.setString(9, comm.getComId());
            pstm.setString(10, comm.getComStock());
            pstm.executeUpdate();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("更新数据错误！");
        }
    }

    @Override
    public SysCommodity selectComm(int comId) throws Exception {
        SysCommodity comm = null;
        String sql = "Select com_id,com_name,com_price,com_create_time,com_brand_id,com_category_id,detail_info,is_vip," +
                "com_img,b.name as brand_name,cc.name as category_name,com_op,com_stock" +
                " from sys_commodity c " +
                "left join brand b on b.id = c.com_brand_id left join commodity_category cc on c.com_category_id = cc.id where com_id=?";
        try {
            Connection con = MySqlUtil.getCon();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, comId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                comm = new SysCommodity();
                comm.setComId(rs.getString(1));
                comm.setComName(rs.getString(2));
                comm.setComPrice(rs.getDouble(3));
                comm.setComCreateTime(rs.getString(4));
                comm.setComBrandId(rs.getString(5));
                comm.setComCategoryId(rs.getString(6));
                comm.setDetailInfo(rs.getString(7));
                comm.setIsVip(rs.getString(8));

                comm.setComImg(rs.getString("com_img"));
                comm.setBrandName(rs.getString("brand_name"));
                comm.setCategoryName(rs.getString("category_name"));
                comm.setComOp(rs.getDouble("com_op"));
                comm.setComStock(rs.getString("com_stock"));
            }
            rs.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("id查询错误！");
        }
        return comm;
    }

    public List<SysCommodity> getComList(Integer pageSize) throws Exception {
        MySqlPageUtlil pageUtlil = new MySqlPageUtlil("select * from sys_commodity",pageSize);
        return pageUtlil.getArray(SysCommodity.class);
    }



    public PageData list(PageData params) throws Exception {
        String key = params.getString("seachKey");
        String serachBrand = params.getString("serachBrand");
        String stock = params.getString("stock");
        String baseSql = "Select com_id,com_name,com_price,com_create_time,com_brand_id,com_category_id,detail_info,is_vip," +
                "com_img,b.name as brand_name,cc.name as category_name,com_op,com_stock,'0' as sales" +
                " from sys_commodity c " +
                "left join brand b on b.id = c.com_brand_id left join commodity_category cc on c.com_category_id = cc.id where (1=1)";
        if(!StringUtil.isNull(key)){
            baseSql += "and com_name LIKE CONCAT(CONCAT('%','"+key+"'),'%')";
        }
        if(!StringUtil.isNull(serachBrand)){
            baseSql += "and com_brand_id = "+serachBrand+" ";
        }
        if(!StringUtil.isNull(stock)){
            baseSql += "and com_stock <= 10 ";
        }
        System.out.println(baseSql);
        MySqlPageUtlil pageUtlil = new MySqlPageUtlil(baseSql,params);
        Map<String,Object> page = new PageData();
        page.put("total",pageUtlil.getTotal());
        page.put("pageSize",pageUtlil.getPageSize());
        page.put("pageIndex",pageUtlil.getPageIndex());
        PageData res = new PageData();
        res.put("page",page);
        res.put("list",pageUtlil.getArray(SysCommodity.class));
        return res;
    }

    @Override
    public List selectAllComm() throws Exception {
        List ListAll = new ArrayList();
        String sql = "select * from sys_commodity";
        try {
            Connection con = MySqlUtil.getCon();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SysCommodity comm = new SysCommodity();
                comm.setComId(rs.getString(1));
                comm.setComName(rs.getString(2));
                comm.setComPrice(rs.getDouble(3));
                comm.setComOp(rs.getDouble(4));
                comm.setComCreateTime(rs.getString(5));
                comm.setComBrandId(rs.getString(6));
                comm.setComCategoryId(rs.getString(7));
                comm.setDetailInfo(rs.getString(8));
                comm.setStatus(rs.getString(9));
                comm.setIsVip(rs.getString(10));
                comm.setComImg(rs.getString(11));
                comm.setComStock(rs.getString(12));
                ListAll.add(comm);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("查询全部错误！！！");
        }
        return ListAll;
    }
}
