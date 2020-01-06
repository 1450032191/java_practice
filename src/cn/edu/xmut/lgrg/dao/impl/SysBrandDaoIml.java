package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.annotation.ZnService;
import cn.edu.xmut.lgrg.entity.Brand;
import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.entity.SysUser;
import cn.edu.xmut.lgrg.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/6 20:17
 * @Description:
 */
@ZnService
public class SysBrandDaoIml {
    public PageData list(PageData params) throws Exception {
        String baseSql = "select * from brand where (1=1)";

        String key = params.getString("key");

        if(!StringUtil.isNull(key)){
            baseSql += "and name LIKE CONCAT(CONCAT('%','"+key+"'),'%')";
        }

        MySqlPageUtlil pageUtlil = new MySqlPageUtlil(baseSql,params);
        PageData page = new PageData();
        page.put("total",pageUtlil.getTotal());
        page.put("pageSize",pageUtlil.getPageSize());
        page.put("pageIndex",pageUtlil.getPageIndex());
        PageData res = new PageData();
        res.put("page",page);
        res.put("list",pageUtlil.getArray(Brand.class));
        return res;
    }

    public List<Brand> list() throws Exception {
        Connection con = MySqlUtil.getCon();
        String sql = "select * from brand where status = 1";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet resultSet = pre.executeQuery();
        List<Brand> brandList = ResultSetUtil.getArray(resultSet, Brand.class);
        return brandList;
    }


}
