package cn.edu.xmut.lgrg.dao;

import cn.edu.xmut.lgrg.entity.SysCommodity;
import cn.edu.xmut.lgrg.entity.SysUserAddr;

import java.util.List;

/**
 * @Auther: ZiNan
 * @Date: 2019/12/30 18:45
 * @Description:
 */
public interface SysUserDao {
    public boolean addAddr(SysUserAddr addr) throws Exception;

    public boolean deleteAddr(SysUserAddr addr) throws Exception;

    public boolean updateAddr(SysUserAddr addr) throws Exception;

    public SysUserAddr selectAddr(int addrId) throws Exception;

    public List selectAllAddr(int addrId) throws Exception;
}
