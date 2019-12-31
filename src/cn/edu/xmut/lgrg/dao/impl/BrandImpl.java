package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.dao.BrandDao;
import cn.edu.xmut.lgrg.entity.Brand;
import cn.edu.xmut.lgrg.util.MySqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrandImpl implements BrandDao {
    @Override
    public void select(Brand brand) throws Exception {
        List<Map<Integer, String>> res = new ArrayList<>();
    }

    @Override
    public void insert(Brand brand) throws Exception {
        String sql = "insert into brand(name,create_time,img) values (?,?,?)";
        Connection conn = MySqlUtil.getCon();
        PreparedStatement pstm = conn.prepareStatement(sql);
        try {
            pstm.setString(1, brand.getBrandName());
            pstm.setString(2, brand.getCreate_time());
            pstm.setString(3, brand.getImg());
            pstm.executeQuery();
            pstm.close();
        } catch (Exception e) {
            throw new Exception("操作中出现错误！");
        } finally {
            conn.close();
        }
    }

    @Override
    public void update(Brand brand) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }
}
