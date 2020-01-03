package cn.edu.xmut.lgrg.dao;

import cn.edu.xmut.lgrg.entity.CommodityCategory;

import java.util.List;

public interface CommodityCategoryDao {
    public void addCategory(CommodityCategory category) throws Exception;

    public void deleteCategory(CommodityCategory category) throws Exception;

    public void updateCategory(CommodityCategory category) throws Exception;

    public CommodityCategory selectCategory(int id) throws Exception;

    public List selectAllCategory() throws Exception;
}
