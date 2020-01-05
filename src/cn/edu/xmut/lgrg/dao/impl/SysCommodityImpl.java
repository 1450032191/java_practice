package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.dao.SysCommodityDao;
import cn.edu.xmut.lgrg.entity.SysCommodity;
import cn.edu.xmut.lgrg.util.ConnDBUtil;
import cn.edu.xmut.lgrg.util.MySqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SysCommodityImpl implements SysCommodityDao {

    @Override
    public void addComm(SysCommodity comm) throws Exception {
        String sql = "insert into sys_commodity(com_name,com_price,com_op,com_create_time,com_brand_id,com_category_id,detail_info,status,is_vip,com_img,com_stock) values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection con = MySqlUtil.getCon();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, comm.getComName());
            pstm.setDouble(2, comm.getComPrice());
            pstm.setDouble(3, comm.getComOp());
            pstm.setString(4, comm.getComCreateTime());
            pstm.setString(5, comm.getComBrandId());
            pstm.setString(6, comm.getComCategoryId());
            pstm.setString(7, comm.getDetailInfo());
            pstm.setString(8, comm.getStatus());
            pstm.setString(9, comm.getIsVip());
            pstm.setString(10, comm.getComImg());
            pstm.setString(11, comm.getComStock());
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
        String sql = "Select com_id,com_name,com_price,com_create_time,com_brand_id,com_category_id,detail_info,is_vip from sys_commodity where com_id=?";
        try {
            Connection con = MySqlUtil.getCon();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, comm.getComId());
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
            }
            rs.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("id查询错误！");
        }
        return comm;
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
