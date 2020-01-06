package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.annotation.ZnService;
import cn.edu.xmut.lgrg.dao.CommodityCategoryDao;
import cn.edu.xmut.lgrg.entity.CommodityCategory;
import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.util.MySqlUtil;
import cn.edu.xmut.lgrg.util.ResultSetUtil;
import cn.edu.xmut.lgrg.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ZnService
public class CommodityCategoryDaoImpl implements CommodityCategoryDao {

    public List<CommodityCategory> formatList() throws Exception {
        //查询出所有东西
        List<CommodityCategory> list = selectAllCategory();
        List<CommodityCategory> res = fList("0",list);
        return res;
    }

    public void edit(PageData params) throws Exception {
        if(params.get("id") == null && params.get("name") == null){
            throw new Exception("参数异常~");
        }
        CommodityCategory category = new CommodityCategory();
        category.setName(params.getString("name"));
        category.setId(params.getString("id"));
        updateCategory(category);
    }

    public void del(PageData params) throws Exception {
        if(params.getString("id")==null){
            throw new Exception("参数异常~");
        }
        CommodityCategory category = new CommodityCategory();
        category.setId(params.getString("id"));
        deleteCategory(category);
    }

    private List<CommodityCategory> fList(String parentId,List<CommodityCategory> list){
        List<CommodityCategory> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String parent = list.get(i).getPersonId();
            if(parentId.equals(parent)){
                newList.add(list.get(i));
                String id = list.get(i).getId();
                List<CommodityCategory> child = fList(id,list);
                if(child.size()>0){
                    list.get(i).setChild(child);
                }
            }
        }
        return newList;
    }
    public void add(PageData params) throws Exception {
        if(params.get("newName") == null && params.get("personId") == null){
            throw new Exception("参数异常~");
        }
        CommodityCategory category = new CommodityCategory();
        category.setName(params.getString("newName"));
        category.setPersonId(params.getString("personId"));
        addCategory(category);
    }

    @Override
    public void addCategory(CommodityCategory category) throws Exception {
        String sql = "INSERT INTO commodity_category(`name`,person_id,`status`,create_time) VALUES(?,?,1,now())";
        try {
            Connection con = MySqlUtil.getCon();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, category.getName());
            pstm.setString(2, category.getPersonId());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("增加数据错误！");
        }
    }

    @Override
    public void deleteCategory(CommodityCategory category) throws Exception {
        String sql = "update commodity_category set status = 0 where id=?";
        try {
            Connection con = MySqlUtil.getCon();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, category.getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("删除错误！");
        }
    }

    @Override
    public void updateCategory(CommodityCategory category) throws Exception {
        String categoryId = category.getId();
        if (StringUtil.isNull(categoryId)) {
            throw new Exception("参数异常~");
        }
        String baseSql = "update commodity_category set id=" + categoryId + "";

        if (!StringUtil.isNull(category.getName())) {
            baseSql += ",name='" + category.getName() + "'";
        }

        if (!StringUtil.isNull(category.getPersonId())) {
            baseSql += ",person_id='" + category.getPersonId() + "'";
        }

        if (!StringUtil.isNull(category.getStatus())) {
            baseSql += ",status ='" + category.getStatus() + "'";
        }

        baseSql += "where id ='" + categoryId + "'";

        Connection con = MySqlUtil.getCon();
        PreparedStatement pre = con.prepareStatement(baseSql);
        pre.executeUpdate();
    }

    @Override
    public CommodityCategory selectCategory(int id) throws Exception {
        CommodityCategory category = null;
        String sql = "select id,name,person_id,status,create_time where id=?";
        try {
            Connection con = MySqlUtil.getCon();
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, category.getId());
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                category = new CommodityCategory();
                category.setId(rs.getString(1));
                category.setName(rs.getString(2));
                category.setPersonId(rs.getString(3));
                category.setStatus(rs.getString(4));
                category.setCreateTime(rs.getString(5));
            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            throw new Exception("根据id查询错误！");
        }
        return category;
    }

    @Override
    public List<CommodityCategory> selectAllCategory() throws Exception {
        List<CommodityCategory> ListAll = new ArrayList();
        String sql = "select * from commodity_category where status = 1";
        PreparedStatement pstmt = null;
        try {
            Connection con = MySqlUtil.getCon();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            ListAll = ResultSetUtil.getArray(rs,CommodityCategory.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("查询全部错误！！！");
        }
        return ListAll;
    }
}

