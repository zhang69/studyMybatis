package com.zzy.study.dao;

import com.zzy.study.model.SysPrivilege;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author zhangzhiyuan
 * @since 2019-04-05
 */
public interface SysPrivilegeMapper extends BaseMapper<SysPrivilege> {
    List<SysPrivilege> selectPrivilegeByRoleId(Long userId);
}
