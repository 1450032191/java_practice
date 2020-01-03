package cn.edu.xmut.lgrg.dao;

import cn.edu.xmut.lgrg.entity.SysCommodity;

import java.util.List;

public interface CommodityDao {
    public void addComm(SysCommodity comm) throws Exception;

    public void deleteComm(SysCommodity comm) throws Exception;

    public void updateComm(SysCommodity comm) throws Exception;

    public SysCommodity selectComm(int comId) throws Exception;

    public List selectAllComm(int comId) throws Exception;
}
