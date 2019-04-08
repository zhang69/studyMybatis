package com.zzy.study.dao;

import com.zzy.study.model.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zhangzhiyuan
 * @since 2019-04-05
 */
public interface SysUserMapper {
    public void insertUser(SysUser sysUser);
}
