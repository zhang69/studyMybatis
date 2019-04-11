package com.zzy.study.dao;

import com.zzy.study.model.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zhangzhiyuan
 * @since 2019-04-05
 */
public interface SysUserMapper {
    public int insertUser(SysUser sysUser);

    public int updateById(SysUser sysUser);

    public SysUser selectById(int id);

    public List<SysUser> selectByNameOrInfo(@Param("userName") String userName, @Param("userInfo") String userInfo);

    public List<SysUser> selectUserAndRole();

    public List<SysUser> selectAssociation();

    public List<SysUser> selectAssociationBySelect();
}
