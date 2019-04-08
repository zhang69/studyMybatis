package com.zzy.study.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 角色权限关联表
 * </p>
 *
 * @author zhangzhiyuan
 * @since 2019-04-05
 */
@TableName("sys_role_privilege")
public class SysRolePrivilege extends Model<SysRolePrivilege> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;
    /**
     * 权限ID
     */
    @TableField("privilege_id")
    private Long privilegeId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "SysRolePrivilege{" +
        "roleId=" + roleId +
        ", privilegeId=" + privilegeId +
        "}";
    }
}
