package com.zzy.study.dao;

import com.zzy.study.model.SysRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author zhangzhiyuan
 * @since 2019-04-05
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> selectRoleByUser(@Param("userId") Long userId, @Param("enabled") Long enabled);

    List<SysRole> selectRoleByUserId(Long id);
    SysRole selectRoleById(Long id);
}
