package cn.edu.xmut.lgrg.dao;

import cn.edu.xmut.lgrg.entity.SysUserAddr;

import java.util.List;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/4 22:10
 * @Description:
 */
public interface SysUserAddrDao {
    public boolean addAddr(SysUserAddr addr) throws Exception;

    public boolean deleteAddr(SysUserAddr addr) throws Exception;

    public boolean updateAddr(SysUserAddr addr) throws Exception;

    public SysUserAddr selectAddr(int addrId) throws Exception;

    public List selectAllAddr(int addrId) throws Exception;
}
