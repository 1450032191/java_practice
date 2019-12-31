package cn.edu.xmut.lgrg.dao;

import cn.edu.xmut.lgrg.entity.Brand;

public interface BrandDao {
    public void select(Brand brand) throws Exception;

    public void insert(Brand brand) throws Exception;

    public void update(Brand brand) throws Exception;

    public void delete(int id) throws Exception;
}
